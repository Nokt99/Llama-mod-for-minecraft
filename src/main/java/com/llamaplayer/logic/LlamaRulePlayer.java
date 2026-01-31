package com.llamaplayer.logic;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class LlamaRuleEngine {

    private static final int ANGY_THRESHOLD = 10;

    public static void onBlockBroken(Player player, BlockState state, LlamaState llama) {
        ItemStack held = player.getMainHandItem();

        int angyDelta = 0;
        boolean violated = false;

        // Example stupid rules:

        // Breaking logs with shovels
        if (isLog(state) && isShovel(held)) {
            violated = true;
            angyDelta += 2;
        }

        // Breaking stone with non-pickaxe
        if (state.is(Blocks.STONE) && !isPickaxe(held)) {
            violated = true;
            angyDelta += 2;
        }

        // Breaking dirt with sword
        if (state.is(Blocks.DIRT) && isSword(held)) {
            violated = true;
            angyDelta += 1;
        }

        // Breaking crafting table with non-axe
        if (state.is(Blocks.CRAFTING_TABLE) && !isAxe(held)) {
            violated = true;
            angyDelta += 2;
        }

        if (violated) {
            if (!llama.isPracticeOver()) {
                llama.decrementPractice();
            } else {
                llama.addAngy(angyDelta);
            }
            updateMood(llama);
        }
    }

    public static void onPlayerTick(Player player, LlamaState llama) {
        if (llama.isPracticeOver()) {
            if (llama.getAngyScore() > 0 && player.tickCount % 100 == 0) {
                llama.addAngy(-1);
                updateMood(llama);
            }
        }
    }

    public static void onPlayerDeath(Player player, LlamaState llama) {
        if (!llama.isPracticeOver()) {
            return;
        }
        llama.incrementDeaths();
        if (llama.getDeathsUsed() >= 3) {
            llama.setFinalSlapTriggered(true);
        }
    }

    private static void updateMood(LlamaState llama) {
        if (!llama.isPracticeOver()) {
            llama.setMood(LlamaState.Mood.CHILL);
            return;
        }
        if (llama.getAngyScore() >= ANGY_THRESHOLD) {
            llama.setMood(LlamaState.Mood.ANGY);
        } else {
            llama.setMood(LlamaState.Mood.CHILL);
        }
    }

    private static boolean isLog(BlockState state) {
        return state.is(Blocks.OAK_LOG) || state.is(Blocks.SPRUCE_LOG) || state.is(Blocks.BIRCH_LOG)
                || state.is(Blocks.JUNGLE_LOG) || state.is(Blocks.ACACIA_LOG) || state.is(Blocks.DARK_OAK_LOG)
                || state.is(Blocks.MANGROVE_LOG) || state.is(Blocks.CHERRY_LOG);
    }

    private static boolean isPickaxe(ItemStack stack) {
        return stack.getItem().toString().toLowerCase().contains("pickaxe");
    }

    private static boolean isAxe(ItemStack stack) {
        return stack.getItem().toString().toLowerCase().contains("axe");
    }

    private static boolean isSword(ItemStack stack) {
        return stack.getItem().toString().toLowerCase().contains("sword");
    }

    private static boolean isShovel(ItemStack stack) {
        return stack.getItem().toString().toLowerCase().contains("shovel");
    }
}
