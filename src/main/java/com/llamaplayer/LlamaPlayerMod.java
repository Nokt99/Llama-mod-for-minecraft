package com.llamaplayer;

import com.llamaplayer.registry.ModEvents;
import com.llamaplayer.registry.ModOverlays;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(LlamaPlayerMod.MOD_ID)
public class LlamaPlayerMod {

    public static final String MOD_ID = "llama_player";

    public LlamaPlayerMod() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        modBus.addListener(this::commonSetup);
        modBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(new ModEvents());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Event-driven logic; nothing heavy here yet.
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ModOverlays.register();
    }
}
