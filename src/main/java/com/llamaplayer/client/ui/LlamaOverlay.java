package com.llamaplayer.client.ui;

import com.llamaplayer.LlamaPlayerMod;
import com.llamaplayer.logic.LlamaState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class LlamaOverlay implements IGuiOverlay {

    private static final ResourceLocation LLAMA_CHILL =
            new ResourceLocation(LlamaPlayerMod.MOD_ID, "textures/gui/llama_chill.png");
    private static final ResourceLocation LLAMA_ANGY =
            new ResourceLocation(LlamaPlayerMod.MOD_ID, "textures/gui/llama_angy.png");

    @Override
    public void render(GuiGraphics gui, float partialTick, int screenWidth, int screenHeight) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        LlamaState llama = LlamaState.get(mc.player);

        int boxWidth = 80;
        int boxHeight = 80;
        int x = screenWidth - boxWidth - 4;
        int y = (screenHeight / 2) - (boxHeight / 2);

        ResourceLocation tex = llama.getMood() == LlamaState.Mood.ANGY ? LLAMA_ANGY : LLAMA_CHILL;

        gui.blit(tex, x, y, 0, 0, boxWidth, boxHeight, boxWidth, boxHeight);

        String status = llama.getMood() == LlamaState.Mood.ANGY ? "LLAMA ANGY" : "LLAMA CHILL";
        int textX = x + 6;
        int textY = y + boxHeight - 12;
        gui.drawString(mc.font, status, textX, textY, 0xFFFFFF, true);
    }
}
