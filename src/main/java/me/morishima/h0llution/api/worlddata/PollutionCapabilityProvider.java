package me.morishima.h0llution.api.worlddata;

import me.morishima.h0llution.api.capability.CapabilityHandler;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PollutionCapabilityProvider implements ICapabilityProvider {

    private final IPollution pollution;

    public PollutionCapabilityProvider(IPollution pollution) {
        this.pollution = pollution;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityHandler.POLLUTION;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityHandler.POLLUTION) {
            return CapabilityHandler.POLLUTION.cast(this.pollution);
        }
        return null;
    }
}
