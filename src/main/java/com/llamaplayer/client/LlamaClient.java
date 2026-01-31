package com.llamaplayer.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class LlamaClient {

    public static void initClient() {
        // Reserved for future client-only hooks.
    }
}
