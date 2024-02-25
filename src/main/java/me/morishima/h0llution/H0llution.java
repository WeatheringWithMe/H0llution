package me.morishima.h0llution;

import gregtech.GTInternalTags;
import me.morishima.h0llution.api.capability.CapabilityHandler;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

@Mod(
        modid = Tags.MOD_ID,
        name = Tags.MOD_NAME,
        version = Tags.VERSION,
        dependencies = GTInternalTags.DEP_VERSION_STRING
)
public class H0llution {
    public static final HashMap<String, Double> cfgMap = new HashMap<>();
    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);

    static  {
        cfgMap.put("primitive_blast_furnace", 200.0);
        cfgMap.put("electric_blast_furnace", 400.0);
        cfgMap.put("implosion_compressor", 50000.0);
        cfgMap.put("electric_furnace", 400.0);

        cfgMap.put("bronze_large_boiler", 1000.0);
        cfgMap.put("steel_large_boiler", 2000.0);
        cfgMap.put("titanium_large_boiler", 3000.0);
        cfgMap.put("tungsten_steel_large_boiler", 4000.0);

        cfgMap.put("large_combustion_engine", 480.0);
        cfgMap.put("extreme_combustion_engine", 3840.0);

        cfgMap.put("large_gas_turbine", 300.0);

        cfgMap.put("charcoal_pile_igniter", 100.0);

        cfgMap.put("coke_oven", 10.0);
        cfgMap.put("cracker", 300.0);
    }

    public static void addPollution(Chunk chunk, double pollution) {
        chunk.getCapability(CapabilityHandler.POLLUTION, null).addPollution(pollution);
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        CapabilityHandler.register();
    }

}
