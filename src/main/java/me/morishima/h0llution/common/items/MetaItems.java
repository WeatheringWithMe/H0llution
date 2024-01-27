package me.morishima.h0llution.common.items;

import gregtech.api.GregTechAPI;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.common.items.behaviors.TooltipBehavior;
import net.minecraft.client.resources.I18n;

public class MetaItems {

    public static MetaItem<?>.MetaValueItem AIR_PURIFIER_COVER_ULV;
    public static MetaItem<?>.MetaValueItem AIR_PURIFIER_COVER_LV;
    public static MetaItem<?>.MetaValueItem AIR_PURIFIER_COVER_MV;
    public static MetaItem<?>.MetaValueItem AIR_PURIFIER_COVER_HV;
    public static MetaItem<?>.MetaValueItem AIR_PURIFIER_COVER_EV;
    public static MetaItem<?>.MetaValueItem AIR_PURIFIER_COVER_IV;
    public static MetaItem<?>.MetaValueItem AIR_PURIFIER_COVER_LUV;
    public static MetaItem<?>.MetaValueItem AIR_PURIFIER_COVER_ZPM;
    public static MetaItem<?>.MetaValueItem AIR_PURIFIER_COVER_UV;

    public static MetaItem<?>.MetaValueItem AIR_PURIFIER_COVER_UHV;
    public static MetaItem<?>.MetaValueItem AIR_PURIFIER_COVER_UEV;
    public static MetaItem<?>.MetaValueItem AIR_PURIFIER_COVER_UIV;
    public static MetaItem<?>.MetaValueItem AIR_PURIFIER_COVER_UXV;
    public static MetaItem<?>.MetaValueItem AIR_PURIFIER_COVER_OPV;
    public static MetaItem<?>.MetaValueItem AIR_PURIFIER_COVER_MAX;

    private static StandardMetaItem metaItem;


    public static void initMetaItems() {
        metaItem = new StandardMetaItem();
        metaItem.setRegistryName("meta_item_h");
    }

    public static void initSubitems() {
        initMetaItem();
    }

    private static void initMetaItem() {
        AIR_PURIFIER_COVER_ULV = cover(0, "ulv");
        AIR_PURIFIER_COVER_LV = cover(1, "lv");
    }

    public static MetaItem<?>.MetaValueItem cover(int meta, String voltage) {
        return metaItem.addItem(meta, "cover.air_purifier." + voltage).setMaxStackSize(64).setCreativeTabs(GregTechAPI.TAB_GREGTECH).addComponents(new TooltipBehavior(lines -> {
            lines.add(I18n.format("metaitem.conveyor.module.tooltip"));
        }));
    }

}
