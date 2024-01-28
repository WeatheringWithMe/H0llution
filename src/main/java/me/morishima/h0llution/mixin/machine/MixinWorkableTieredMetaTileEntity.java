package me.morishima.h0llution.mixin.machine;

import gregtech.api.capability.impl.AbstractRecipeLogic;
import gregtech.api.metatileentity.TieredMetaTileEntity;
import gregtech.api.metatileentity.WorkableTieredMetaTileEntity;
import gregtech.api.metatileentity.multiblock.ICleanroomProvider;
import me.morishima.h0llution.api.config.ConfigHolder;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static me.morishima.h0llution.H0llution.addPollution;

@Mixin(value = WorkableTieredMetaTileEntity.class, remap = false)
public abstract class MixinWorkableTieredMetaTileEntity extends TieredMetaTileEntity {
    @Shadow @Final protected AbstractRecipeLogic workable;
    @Unique
    private double h0llution$pollutionPerTick;
    @Unique private double h0llution$pollutionExplosion;
    @Shadow private ICleanroomProvider cleanroom;
    public MixinWorkableTieredMetaTileEntity(ResourceLocation metaTileEntityId, int tier) {
        super(metaTileEntityId, tier);
        h0llution$pollutionPerTick = ConfigHolder.singleMachineWorkingPollution - tier * ConfigHolder.adjustPerTier;
        h0llution$pollutionExplosion = ConfigHolder.singleMachineExplosionPollution - tier * ConfigHolder.adjustPerTier;
    }

    @Override
    public void update() {
        Chunk chunk = getWorld().getChunk(getPos());
        if (ConfigHolder.enableSingleMachineDefaultPollution) {
            if (workable.isWorking()) {
                addPollution(chunk, h0llution$pollutionPerTick);
                if (cleanroom.isClean()) {
                    cleanroom.adjustCleanAmount(-1);
                }
            }
            if (wasExploded()) {
                addPollution(chunk, h0llution$pollutionExplosion);
            }
        }
    }

    @Inject(method = "addInformation", at = @At("HEAD"))
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @org.jetbrains.annotations.Nullable World player, List<String> tooltip, boolean advanced, CallbackInfo ci) {
        if (ConfigHolder.enableSingleMachineDefaultPollution) {
            tooltip.add(I18n.format("h0llution.pollution_recipe.desc", h0llution$pollutionPerTick));
            tooltip.add(I18n.format("h0llution.pollution_explosion.desc", h0llution$pollutionPerTick));
        }
    }

}
