package me.morishima.h0llution;

import gregtech.GTInternalTags;
import me.morishima.h0llution.api.config.RecipePollutionConfiguration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

@Mod(
        modid = Tags.MOD_ID,
        name = Tags.MOD_NAME,
        version = Tags.VERSION,
        dependencies = GTInternalTags.DEP_VERSION_STRING
)
public class H0llution {

    public static ArrayList<RecipePollutionConfiguration> cfgList = new ArrayList<>();

    static {
        cfgList.add(new RecipePollutionConfiguration("primitive_blast_furnace", 200));
    }

    @SubscribeEvent
    public static void onGameConstruct(FMLConstructionEvent event) {

    }

}
