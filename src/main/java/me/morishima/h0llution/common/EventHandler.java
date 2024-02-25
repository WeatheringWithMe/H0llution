package me.morishima.h0llution.common;

import me.morishima.h0llution.H0llution;
import me.morishima.h0llution.api.capability.CapabilityHandler;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber
public class EventHandler {

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.player.world.getChunk(new BlockPos(event.player)).getCapability(CapabilityHandler.POLLUTION, null).getPollution() == 400) {
            event.player.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS));
        }
        H0llution.LOGGER.info(event.player.world.getChunk(new BlockPos(event.player)).getCapability(CapabilityHandler.POLLUTION, null).getPollution());
    }

}
