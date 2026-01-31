package com.llamaplayer.client.ui;

import com.llamaplayer.LlamaPlayerMod;
import com.llamaplayer.logic.LlamaState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class FinalSlapOverlay implements IGuiOverlay {

    private static final ResourceLocation SLAP_TEX =
            new ResourceLocation(LlamaPlayerMod.MOD_ID, "textures/gui/llama_slap_fullscreen.png");

    private boolean disconnectQueued = false;
    private int tickCounter = 0;

    @Override
    public void render(GuiGraphics gui, float partialTick, int screenWidth, int screenHeight) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        LlamaState llama = LlamaState.get(mc.player);
        if (!llama.isFinalSlapTriggered()) return;

        gui.blit(SLAP_TEX, 0, 0, 0, 0, screenWidth, screenHeight, screenWidth, screenHeight);

        tickCounter++;
        if (!disconnectQueued && tickCounter > 40) {
            disconnectQueued = true;
            mc.execute(() -> mc.player.connection.disconnect(
                    Component.literal("Gamed to death by the all powerful \uD83E\uDD99lol")));
        }
    }
}
