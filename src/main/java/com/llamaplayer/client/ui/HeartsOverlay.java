package com.llamaplayer.client.ui;

import com.llamaplayer.logic.LlamaState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class HeartsOverlay implements IGuiOverlay {

    @Override
    public void render(GuiGraphics gui, float partialTick, int screenWidth, int screenHeight) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        LlamaState llama = LlamaState.get(mc.player);
        if (!llama.isPracticeOver()) return;

        int x = 8;
        int y = 8;

        int deaths = llama.getDeathsUsed();

        for (int i = 0; i < 3; i++) {
            int color = (i < deaths) ? 0xFF0000 : 0x550000;
            gui.fill(x + i * 12, y, x + i * 12 + 10, y + 10, 0xFF000000 | color);
        }
    }
}
