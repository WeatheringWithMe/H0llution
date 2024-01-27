package me.morishima.h0llution.common.covers;

import me.morishima.h0llution.Tags;
import net.minecraft.util.ResourceLocation;

import static gregtech.common.covers.CoverBehaviors.registerBehavior;

public class CoverBehaviors {

    public static void init() {
        registerBehavior(new ResourceLocation(Tags.MOD_ID, "air_purifier_ulv"), GCMetaItems.ULV_CONVEYOR_MODULE, (tile, side) -> new CoverConveyor(tile, side, GTValues.ULV, 2));
    }

}
