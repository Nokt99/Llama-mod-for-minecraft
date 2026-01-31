package com.llamaplayer.registry;

import com.llamaplayer.LlamaPlayerMod;
import com.llamaplayer.client.ui.FinalSlapOverlay;
import com.llamaplayer.client.ui.HeartsOverlay;
import com.llamaplayer.client.ui.LlamaOverlay;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.common.MinecraftForge;

public class ModOverlays {

    public static void register() {
        MinecraftForge.EVENT_BUS.addListener(ModOverlays::onRegisterOverlays);
    }

    private static void onRegisterOverlays(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll(LlamaPlayerMod.MOD_ID + "_llama_box", new LlamaOverlay());
        event.registerAboveAll(LlamaPlayerMod.MOD_ID + "_hearts", new HeartsOverlay());
        event.registerAboveAll(LlamaPlayerMod.MOD_ID + "_final_slap", new FinalSlapOverlay());
    }
}
