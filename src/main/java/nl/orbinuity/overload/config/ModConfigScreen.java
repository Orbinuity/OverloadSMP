package nl.orbinuity.overload.config;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import nl.orbinuity.overload.OverloadSMP;

public class ModConfigScreen extends Screen {
    private final Screen parent;

    public ModConfigScreen(Screen parent) {
        super(Component.literal("Overload Settings"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int x = this.width / 2 - 100;

        Button manualButton = Button.builder(Component.literal("Night Mode: " + (OverloadSMP.useNight() ? "ON" : "OFF")), btn -> {
            OverloadConfig.nightMode = !OverloadSMP.useNight();
            btn.setMessage(Component.literal("Night Mode: " + (OverloadSMP.useNight() ? "ON" : "OFF")));
        }).bounds(x, this.height / 4 + 30, 200, 20).build();

        this.addRenderableWidget(Button.builder(Component.literal("Auto Mode: " + (OverloadConfig.autoMode ? "ON" : "OFF")), btn -> {
            OverloadConfig.autoMode = !OverloadConfig.autoMode;
            btn.setMessage(Component.literal("Auto Mode: " + (OverloadConfig.autoMode ? "ON" : "OFF")));
            if (OverloadConfig.autoMode) manualButton.setMessage(Component.literal("Night Mode: " + (OverloadSMP.useNight() ? "ON" : "OFF")));
            this.rebuildWidgets();
        }).bounds(x, this.height / 4, 200, 20).build());

        manualButton.active = !OverloadConfig.autoMode;

        this.addRenderableWidget(manualButton);

        this.addRenderableWidget(Button.builder(Component.literal("Done"), btn -> {
            OverloadConfig.save();
            this.minecraft.setScreen(parent);
        }).bounds(x, this.height / 2 + 50, 200, 20).build());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);
    }
}