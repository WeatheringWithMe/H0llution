package me.morishima.h0llution.common.covers;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Cuboid6;
import codechicken.lib.vec.Matrix4;
import gregtech.api.cover.CoverBase;
import gregtech.api.cover.CoverDefinition;
import gregtech.api.cover.CoverWithUI;
import gregtech.api.cover.CoverableView;
import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.WorkableTieredMetaTileEntity;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.texture.Textures;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class CoverPurifier extends CoverBase implements CoverWithUI {
    public final ItemStackHandler storageHandler = new ItemStackHandler(9);
    public int tier;

    public CoverPurifier(@NotNull CoverDefinition definition, @NotNull CoverableView coverableView, @NotNull EnumFacing attachedSide, int tier) {
        super(definition, coverableView, attachedSide);
        this.tier = tier;
    }

    @Override
    public boolean canAttach(@NotNull CoverableView coverableView, @NotNull EnumFacing enumFacing) {
        return GTUtility.getMetaTileEntity(coverableView.getWorld(), coverableView.getPos()) instanceof WorkableTieredMetaTileEntity && enumFacing == EnumFacing.UP;
    }

    @Override
    public void renderCover(@NotNull CCRenderState ccRenderState, @NotNull Matrix4 matrix4, @NotNull IVertexOperation[] iVertexOperations, @NotNull Cuboid6 cuboid6, @NotNull BlockRenderLayer blockRenderLayer) {
        Textures.MAINTENANCE_OVERLAY_CLEANING.renderSided(this.getAttachedSide(), ccRenderState, matrix4, iVertexOperations);
    }

    @Override
    public ModularUI createUI(EntityPlayer player) {
        ModularUI.Builder builder = new ModularUI.Builder(GuiTextures.BACKGROUND, 176, 126);
        builder.label(5, 5, "cover.storage.title");

        for(int index = 0; index < this.storageHandler.getSlots(); ++index) {
            builder.slot(this.storageHandler, index, index * 18 + 7, 18, true, true, GuiTextures.SLOT);
        }

        builder.bindPlayerInventory(player.inventory, 44);
        return builder.build(this, player);
    }

    public @NotNull EnumActionResult onRightClick(@NotNull EntityPlayer player, @NotNull EnumHand hand, @NotNull CuboidRayTraceResult hitResult) {
        if (!this.getCoverableView().getWorld().isRemote) {
            this.openUI((EntityPlayerMP)player);
        }

        return EnumActionResult.SUCCESS;
    }

    public void writeToNBT(@NotNull NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        tagCompound.setTag("Storage", this.storageHandler.serializeNBT());
        tagCompound.setInteger("Tier", tier);
    }

    public void readFromNBT(@NotNull NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        this.storageHandler.deserializeNBT(tagCompound.getCompoundTag("Storage"));
        this.tier = tagCompound.getInteger("Tier");
    }
}
