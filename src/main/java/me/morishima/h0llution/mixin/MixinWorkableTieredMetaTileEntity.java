package me.morishima.h0llution.mixin;

import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.metatileentity.TieredMetaTileEntity;
import gregtech.api.metatileentity.WorkableTieredMetaTileEntity;
import gregtech.api.metatileentity.multiblock.ICleanroomProvider;
import me.morishima.h0llution.api.config.ConfigHolder;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(WorkableTieredMetaTileEntity.class)
public abstract class MixinWorkableTieredMetaTileEntity extends TieredMetaTileEntity {

    @Shadow @Final protected AbstractRecipeLogic workable;

    public MixinWorkableTieredMetaTileEntity(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, tier);
    }

    @Shadow public abstract @Nullable ICleanroomProvider getCleanroom();

    @Override
    public void update() {
        if (ConfigHolder.enableSingleMachineDefaultPollution) {
            if (getCleanroom().isClean()) {
                workable.setWorkingEnabled(false);
            }
        }
    }

}
