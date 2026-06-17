package nl.orbinuity.overload.mixin;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import nl.orbinuity.overload.config.ModConfigScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends net.minecraft.client.gui.screens.Screen {

    protected TitleScreenMixin(Component title) {
        super(title);
    }

    @Inject(at = @At("RETURN"), method = "init")
    private void addSettingsButton(CallbackInfo ci) {
        int buttonColumnCenter = this.width / 2;
        int x = buttonColumnCenter - 124;
        int y = (this.height / 4 + 48) - 24;

        this.addRenderableWidget(Button.builder(Component.literal("⚙"), button -> {
                    this.minecraft.setScreen(new ModConfigScreen(this));
                })
                .bounds(x, y, 20, 20)
                .build());
    }
}