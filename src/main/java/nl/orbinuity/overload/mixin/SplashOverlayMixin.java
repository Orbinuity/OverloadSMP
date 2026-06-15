package nl.orbinuity.overload.mixin;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.LoadingOverlay;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.InputStream;

@Mixin(LoadingOverlay.class)
public class SplashOverlayMixin {

    @Shadow @Final @Mutable
    private static Identifier MOJANG_STUDIOS_LOGO_LOCATION;

    @Unique
    private static boolean overload$textureLoaded = false;

    @Shadow @Final @Mutable
    private static java.util.function.IntSupplier BRAND_BACKGROUND;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void overrideMojangLogo(CallbackInfo ci) {
        MOJANG_STUDIOS_LOGO_LOCATION = Identifier.withDefaultNamespace("textures/gui/title/overload_smp.png");

        BRAND_BACKGROUND = () -> 0xFF000000;
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void loadLogoFromStreamEarly(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        if (!overload$textureLoaded) {
            try (InputStream stream = SplashOverlayMixin.class.getResourceAsStream("/assets/minecraft/textures/gui/title/overload_smp.png")) {
                if (stream != null) {
                    NativeImage nativeImage = NativeImage.read(stream);

                    DynamicTexture dynamicTexture = new DynamicTexture(() -> "overload_logo", nativeImage);

                    Minecraft.getInstance().getTextureManager().register(MOJANG_STUDIOS_LOGO_LOCATION, dynamicTexture);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            overload$textureLoaded = true;
        }
    }
}