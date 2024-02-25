package me.morishima.h0llution.api.capability;

import me.morishima.h0llution.Tags;
import me.morishima.h0llution.api.worlddata.IPollution;
import me.morishima.h0llution.api.worlddata.Pollution;
import me.morishima.h0llution.api.worlddata.PollutionCapabilityProvider;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CapabilityHandler {
    private static final ResourceLocation Pollution = new ResourceLocation(Tags.MOD_ID, "pollution");

    @CapabilityInject(IPollution.class)
    public static Capability<IPollution> POLLUTION = null;

    @SubscribeEvent
    public static void attachToChunk(AttachCapabilitiesEvent<Chunk> event) {
        event.addCapability(Pollution, new PollutionCapabilityProvider(new Pollution()));
    }

    public static void register() {
        CapabilityManager.INSTANCE.register(IPollution.class, new Capability.IStorage<>() {
            @Override
            public NBTBase writeNBT(Capability<IPollution> capability, IPollution instance, EnumFacing side) {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setDouble("pollution", instance.getPollution());
                return nbt;
            }

            @Override
            public void readNBT(Capability<IPollution> capability, IPollution instance, EnumFacing side, NBTBase nbt) {
                if (nbt instanceof NBTTagCompound tag) {
                    instance.setPollution(tag.getDouble("pollution"));
                }
            }
        }, Pollution::new);
    }

}
