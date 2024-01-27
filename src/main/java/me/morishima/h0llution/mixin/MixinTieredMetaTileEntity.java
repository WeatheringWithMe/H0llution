package me.morishima.h0llution.mixin;

import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.TieredMetaTileEntity;
import me.morishima.h0llution.api.capability.CapabilityHandler;
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

@Mixin(value = TieredMetaTileEntity.class, remap = false)
public abstract class MixinTieredMetaTileEntity extends MetaTileEntity {
    @Unique private double h0llution$pollutionPerTick;
    @Unique private double h0llution$pollutionExplosion;
    @Shadow @Final private int tier;

    public MixinTieredMetaTileEntity(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        h0llution$pollutionPerTick = 10 * tier;
        h0llution$pollutionExplosion = 500 * tier;
    }

    @Inject(method = "update", at = @At("HEAD"))
    public void update(CallbackInfo ci) {
        Chunk chunk = getWorld().getChunk(getPos());
        if (ConfigHolder.enableSingleMachineDefaultPollution) {
            if (isActive()) {
                chunk.getCapability(CapabilityHandler.POLLUTION, null).addPollution(h0llution$pollutionPerTick);
            }
            if (wasExploded()) {
                chunk.getCapability(CapabilityHandler.POLLUTION, null).addPollution(h0llution$pollutionExplosion);
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
