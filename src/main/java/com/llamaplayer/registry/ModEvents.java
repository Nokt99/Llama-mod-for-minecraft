package com.llamaplayer.registry;

import com.llamaplayer.logic.LlamaRiggingHandler;
import com.llamaplayer.logic.LlamaRuleEngine;
import com.llamaplayer.logic.LlamaState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModEvents {

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getPlayer() instanceof Player player)) return;
        BlockState state = event.getState();
        LlamaState llama = LlamaState.get(player);
        LlamaRuleEngine.onBlockBroken(player, state, llama);
        llama.saveTo(player);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;
        LlamaState llama = LlamaState.get(player);
        LlamaRuleEngine.onPlayerTick(player, llama);
        LlamaRiggingHandler.onPlayerTick(player, llama);
        llama.saveTo(player);
    }

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        LlamaState llama = LlamaState.get(player);
        LlamaRuleEngine.onPlayerDeath(player, llama);
        llama.saveTo(player);
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) return;
        LlamaState oldState = LlamaState.get(event.getOriginal());
        LlamaState newState = LlamaState.get(event.getEntity());
        newState.copyFrom(oldState);
        newState.saveTo(event.getEntity());
    }
}
