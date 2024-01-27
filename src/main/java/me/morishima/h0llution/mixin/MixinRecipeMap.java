package me.morishima.h0llution.mixin;

import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.recipes.RecipeMap;
import me.morishima.h0llution.api.recipes.IRecipeMapPollutionGetter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RecipeMap.class)
public abstract class MixinRecipeMap implements IRecipeMapPollutionGetter
{
    @Unique
    private double h0llution$pollution;

    @Inject(method = "<init>(Ljava/lang/String;IZIZIZIZLgregtech/api/recipes/RecipeBuilder;Z)V", at = @At("RETURN"))
    private void injectedInitial(String unlocalizedName, int maxInputs, boolean modifyItemInputs, int maxOutputs, boolean modifyItemOutputs, int maxFluidInputs, boolean modifyFluidInputs, int maxFluidOutputs, boolean modifyFluidOutputs, RecipeBuilder defaultRecipeBuilder, boolean isHidden, CallbackInfo ci) {

    }

    @Override
    public double getPollutionPerRecipe() {
        return h0llution$pollution;
    }

    @Override
    public void setPollutionPerRecipe(double var) {
        h0llution$pollution = var;
    }
}
