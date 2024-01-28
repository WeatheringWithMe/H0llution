package me.morishima.h0llution.mixin.workable;

import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.metatileentity.MTETrait;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.recipes.RecipeMap;
import gregtech.common.metatileentities.multi.MetaTileEntityLargeBoiler;
import me.morishima.h0llution.H0llution;
import net.minecraft.world.chunk.Chunk;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.morishima.h0llution.H0llution.addPollution;

@Mixin(value = AbstractRecipeLogic.class, remap = false)
public abstract class MixinAbstractRecipeLogic extends MTETrait {

    @Shadow protected boolean canRecipeProgress;

    public MixinAbstractRecipeLogic(@NotNull MetaTileEntity metaTileEntity) {
        super(metaTileEntity);
    }

    @Shadow @Nullable public abstract RecipeMap<?> getRecipeMap();

    @Inject(method = "updateRecipeProgress", at = @At("HEAD"))
    protected void updateRecipeProgress(CallbackInfo ci) {
        Chunk chunk = getMetaTileEntity().getWorld().getChunk(getMetaTileEntity().getPos());
        if (canRecipeProgress) {
            if (!(getRecipeMap() == null) && H0llution.cfgMap.containsKey(getRecipeMap().unlocalizedName)) {
                addPollution(chunk, H0llution.cfgMap.get(getRecipeMap().unlocalizedName));
            }
            if (getMetaTileEntity() instanceof MetaTileEntityLargeBoiler boiler) {
                switch (boiler.boilerType) {
                    case BRONZE -> addPollution(chunk, H0llution.cfgMap.get("bronze_large_boiler"));
                    case STEEL -> addPollution(chunk, H0llution.cfgMap.get("steel_large_boiler"));
                    case TITANIUM -> addPollution(chunk, H0llution.cfgMap.get("titanium_large_boiler"));
                    case TUNGSTENSTEEL -> addPollution(chunk, H0llution.cfgMap.get("tungsten_steel_large_boiler"));
                }
            }
        }
    }

}
