package nl.orbinuity.overload.mixin;

import net.minecraft.client.renderer.CubeMap;
import net.minecraft.client.renderer.PanoramaRenderer;
import net.minecraft.resources.Identifier;
import nl.orbinuity.overload.OverloadSMP;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PanoramaRenderer.class)
public class PanoramaRendererMixin {

    @Mutable @Shadow @Final private CubeMap cubeMap;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void overload$initPanorama(CallbackInfo ci) {
        boolean useNight = OverloadSMP.useNight();

        this.cubeMap = new CubeMap(Identifier.fromNamespaceAndPath(OverloadSMP.MODID, "textures/gui/title/background/" + (useNight ? "night/panorama" : "day/panorama")));
    }
}