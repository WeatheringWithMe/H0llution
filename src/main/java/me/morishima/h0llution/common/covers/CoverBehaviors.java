package me.morishima.h0llution.common.covers;

import me.morishima.h0llution.Tags;
import me.morishima.h0llution.common.items.MetaItems;
import net.minecraft.util.ResourceLocation;

import static gregtech.common.covers.CoverBehaviors.registerBehavior;

public class CoverBehaviors {

    public static void init() {
        registerBehavior(new ResourceLocation(Tags.MOD_ID, "air_purifier_mv"), MetaItems.AIR_PURIFIER_COVER,
                (def, tile, side) -> new CoverPurifier(def, tile, side, 1));

        registerBehavior(new ResourceLocation(Tags.MOD_ID, "air_purifier_luv"), MetaItems.AIR_FILTRATION_UNIT_COVER,
                (def, tile, side) -> new CoverPurifier(def, tile, side, 2));

        registerBehavior(new ResourceLocation(Tags.MOD_ID, "air_purifier_uhv"), MetaItems.CLEAN_FORCE_FIELD_COVER,
                (def, tile, side) -> new CoverPurifier(def, tile, side, 3));
    }

}
