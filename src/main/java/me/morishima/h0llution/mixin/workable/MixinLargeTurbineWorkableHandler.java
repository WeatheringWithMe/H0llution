package me.morishima.h0llution.mixin.workable;

import gregtech.api.GTValues;
import gregtech.api.capability.impl.MultiblockFuelRecipeLogic;
import gregtech.api.metatileentity.multiblock.RecipeMapMultiblockController;
import gregtech.common.metatileentities.multi.electric.generator.LargeTurbineWorkableHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.morishima.h0llution.H0llution.addPollution;
import static me.morishima.h0llution.H0llution.cfgMap;

@Mixin(value = LargeTurbineWorkableHandler.class, remap = false)
public abstract class MixinLargeTurbineWorkableHandler extends MultiblockFuelRecipeLogic {

    @Shadow @Final private int BASE_EU_OUTPUT;

    public MixinLargeTurbineWorkableHandler(RecipeMapMultiblockController tileEntity) {
        super(tileEntity);
    }

    @Inject(method = "updateRecipeProgress", at = @At("HEAD"))
    protected void updateRecipeProgress(CallbackInfo ci) {
        if (canRecipeProgress && BASE_EU_OUTPUT == GTValues.V[4] * 2) {
            addPollution(getMetaTileEntity().getWorld().getChunk(getMetaTileEntity().getPos()), cfgMap.get("large_gas_turbine"));
        }
    }

}
