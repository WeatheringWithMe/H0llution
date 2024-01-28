package me.morishima.h0llution.mixin.workable;

import gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityLargeCombustionEngine;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static me.morishima.h0llution.H0llution.addPollution;
import static me.morishima.h0llution.H0llution.cfgMap;

@Mixin(targets = "gregtech.common.metatileentities.multi.electric.generator.MetaTileEntityLargeCombustionEngine$LargeCombustionEngineWorkableHandler", remap = false)
public abstract class MixinLargeCombustionEngineWorkableHandler {

    @Shadow @Final private MetaTileEntityLargeCombustionEngine combustionEngine;
    @Shadow @Final private int tier;

    @Shadow protected abstract boolean canProgressRecipe();

    @Inject(method = "updateRecipeProgress", at = @At("HEAD"))
    protected void updateRecipeProgress(CallbackInfo ci) {
        if (canProgressRecipe()) {
            Chunk chunk = combustionEngine.getWorld().getChunk(combustionEngine.getPos());
            switch (tier) {
                case 4 -> addPollution(chunk, cfgMap.get("large_combustion_engine"));
                case 5 -> addPollution(chunk, cfgMap.get("extreme_combustion_engine"));
            }
        }
    }

}
