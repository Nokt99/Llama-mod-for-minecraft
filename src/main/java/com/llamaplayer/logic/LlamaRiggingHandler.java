package com.llamaplayer.logic;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

public class LlamaRiggingHandler {

    private static final Random RANDOM = new Random();

    public static void onPlayerTick(Player player, LlamaState llama) {
        if (!(player.level() instanceof ServerLevel level)) return;
        if (!llama.isPracticeOver()) return;
        if (llama.getMood() != LlamaState.Mood.ANGY) return;

        if (player.tickCount % 5 != 0) return;

        BlockPos posBelow = player.blockPosition().below();
        BlockState stateBelow = level.getBlockState(posBelow);

        if (!stateBelow.isAir() && RANDOM.nextFloat() < 0.10f) {
            level.removeBlock(posBelow, false);

            BlockPos secondBelow = posBelow.below();
            BlockState secondState = level.getBlockState(secondBelow);

            if (!secondState.isAir() && RANDOM.nextFloat() < 0.50f) {
                level.removeBlock(secondBelow, false);
            }

            if (RANDOM.nextFloat() < 0.10f) {
                player.teleportTo(player.getX(), -64, player.getZ());
            }
        }
    }
}
