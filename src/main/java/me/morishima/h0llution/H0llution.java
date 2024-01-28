package me.morishima.h0llution;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gregtech.GTInternalTags;
import me.morishima.h0llution.api.capability.CapabilityHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Mod(
        modid = Tags.MOD_ID,
        name = Tags.MOD_NAME,
        version = Tags.VERSION,
        dependencies = GTInternalTags.DEP_VERSION_STRING
)
public class H0llution {

    public static Map<String, Double> cfgMap = new HashMap<>();

    static {
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
    public void onConstruction(FMLConstructionEvent event) {
        String fileName = Minecraft.getMinecraft().gameDir.getPath() + "/config/h0llution/configuration.json";

        try (FileOutputStream fos = new FileOutputStream(fileName);
             OutputStreamWriter isr = new OutputStreamWriter(fos,
                     StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(H0llution.cfgMap, isr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
