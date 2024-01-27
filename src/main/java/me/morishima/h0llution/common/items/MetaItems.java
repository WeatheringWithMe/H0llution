package me.morishima.h0llution.common.items;

import gregtech.api.GregTechAPI;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.common.items.behaviors.TooltipBehavior;
import net.minecraft.client.resources.I18n;

public class MetaItems {

    public static MetaItem<?>.MetaValueItem AIR_PURIFIER_COVER;
    public static MetaItem<?>.MetaValueItem AIR_FILTRATION_UNIT_COVER;
    public static MetaItem<?>.MetaValueItem CLEAN_FORCE_FIELD_COVER;

    private static StandardMetaItem metaItem;


    public static void initMetaItems() {
        metaItem = new StandardMetaItem();
        metaItem.setRegistryName("meta_item_h");
    }

    public static void initSubitems() {
        initMetaItem();
    }

    private static void initMetaItem() {
        AIR_PURIFIER_COVER = cover(0, "mv");
        AIR_FILTRATION_UNIT_COVER = cover(1, "luv");
        CLEAN_FORCE_FIELD_COVER = cover(2, "uhv");
    }

    public static MetaItem<?>.MetaValueItem cover(int meta, String voltage) {
        return metaItem.addItem(meta, "cover.air_purifier." + voltage).setMaxStackSize(64).setCreativeTabs(GregTechAPI.TAB_GREGTECH).addComponents(new TooltipBehavior(lines -> {
            lines.add(I18n.format("metaitem.air_purifier.cover.tooltip"));
        }));
    }

}
