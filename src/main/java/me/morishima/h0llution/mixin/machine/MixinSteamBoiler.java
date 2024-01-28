package me.morishima.h0llution.mixin.machine;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.common.metatileentities.steam.boiler.SteamBoiler;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.morishima.h0llution.H0llution.addPollution;

@Mixin(value = SteamBoiler.class, remap = false)
public abstract class MixinSteamBoiler extends MetaTileEntity {

    @Shadow @Final protected boolean isHighPressure;

    public MixinSteamBoiler(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }

    @Shadow public abstract boolean isActive();

    @Inject(method = "update", at = @At("HEAD"))
    public void update(CallbackInfo ci) {
        Chunk chunk = getWorld().getChunk(getPos());
        if (isActive()) {
            if (metaTileEntityId.getPath().contains("steam_boiler_coal")) {
                if (isHighPressure) {
                    addPollution(chunk, 30);
                } else {
                    addPollution(chunk, 20);
                }
            } else if (metaTileEntityId.getPath().equals("steam_boiler_lava_steel")) {
                addPollution(chunk, 20);
            }
        }
    }

}
