package me.morishima.h0llution.mixin;

import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.metatileentity.TieredMetaTileEntity;
import gregtech.api.metatileentity.WorkableTieredMetaTileEntity;
import gregtech.api.metatileentity.multiblock.DummyCleanroom;
import gregtech.api.metatileentity.multiblock.ICleanroomProvider;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.ore.OrePrefix;
import me.morishima.h0llution.api.config.ConfigHolder;
import me.morishima.h0llution.common.covers.CoverPurifier;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.ItemHandlerHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = WorkableTieredMetaTileEntity.class, remap = false)
public abstract class MixinWorkableTieredMetaTileEntity extends TieredMetaTileEntity {
    public MixinWorkableTieredMetaTileEntity(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, tier);
    }

    @Shadow @Final protected AbstractRecipeLogic workable;
    @Shadow private ICleanroomProvider cleanroom;

    @Shadow public abstract void setCleanroom(ICleanroomProvider provider);

    @Override
    public void update() {
        if (ConfigHolder.enableSingleMachineDefaultPollution) {
            if (getCoverAtSide(EnumFacing.UP) instanceof CoverPurifier purifier) {
                if (purifier.tier == 1) {
                    workable.setWorkingEnabled(cleanroom.isClean());
                    if (workable.isWorking()) {
                        cleanroom.adjustCleanAmount(-1);
                        ItemHandlerHelper.insertItem(purifier.storageHandler, OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash, 2), false);
                    }
                } else if (purifier.tier == 2) {
                    workable.setWorkingEnabled(true);
                    setCleanroom(DummyCleanroom.createForAllTypes());
                    if (workable.isWorking()) {
                        ItemHandlerHelper.insertItem(purifier.storageHandler, OreDictUnifier.get(OrePrefix.dustTiny, Materials.Ash), false);
                    }
                }
            } else {
                if (cleanroom.isClean()) {
                    workable.setWorkingEnabled(false);
                }
            }
        }
    }

}
