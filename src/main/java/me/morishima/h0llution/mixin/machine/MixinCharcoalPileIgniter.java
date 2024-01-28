package me.morishima.h0llution.mixin.machine;

import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.common.metatileentities.primitive.MetaTileEntityCharcoalPileIgniter;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.morishima.h0llution.H0llution.addPollution;
import static me.morishima.h0llution.H0llution.cfgMap;

@Mixin(value = MetaTileEntityCharcoalPileIgniter.class, remap = false)
public abstract class MixinCharcoalPileIgniter extends MultiblockControllerBase {

    @Shadow private boolean isActive;

    public MixinCharcoalPileIgniter(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
    }

    @Inject(method = "update", at = @At("HEAD"))
    public void update(CallbackInfo ci) {
        if (isActive) {
            addPollution(getWorld().getChunk(getPos()), cfgMap.get("charcoal_pile_igniter"));
        }
    }

}
