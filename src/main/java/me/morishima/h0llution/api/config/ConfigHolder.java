package me.morishima.h0llution.api.config;

import me.morishima.h0llution.Tags;
import net.minecraftforge.common.config.Config;

@Config(modid = Tags.MOD_ID, name = "h0llution/h0llution")
public final class ConfigHolder {

    @Config.RequiresMcRestart
    @Config.RequiresWorldRestart
    @Config.Comment({
            "Exhaust default pollution when single machine working and exploded",
            "Default exhaust 1 pollution per tick and 500 pollution when exploded.",
            "Each additional level of voltage, exhaust pollution multiple will be affected by the value of the D:pollutionMultiplePerVoltage configuration entry."
    })
    public static boolean enableSingleMachineDefaultPollution = true;

    @Config.RequiresMcRestart
    @Config.RequiresWorldRestart
    @Config.Comment({
            "Exhaust pollution amount when single machine exploded."
    })
    public static double singleMachineExplosionPollution = 500;

}
