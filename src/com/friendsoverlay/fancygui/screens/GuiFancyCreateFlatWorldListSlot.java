package com.friendsoverlay.fancygui.screens;

import net.minecraft.src.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;
import com.friendsoverlay.fancygui.*;

public class GuiFancyCreateFlatWorldListSlot extends GuiFancySlot {

    public int field_82454_a;

    final GuiFancyCreateFlatWorld createFlatWorldGui;
	
    public GuiFancyCreateFlatWorldListSlot(GuiFancyCreateFlatWorld par1)
    {
        super(par1.mc, par1.width, par1.height, 43, par1.height - 60, 24);
        this.createFlatWorldGui = par1;
        this.field_82454_a = -1;
    }

    private void func_82452_a(int par1, int par2, ItemStack par3ItemStack)
    {
        this.func_82451_d(par1 + 1, par2 + 1);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);

        if (par3ItemStack != null)
        {
            RenderHelper.enableGUIStandardItemLighting();
            GuiFancyCreateFlatWorld.getRenderItem().renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer, this.createFlatWorldGui.mc.renderEngine, par3ItemStack, par1 + 2, par2 + 2);
            RenderHelper.disableStandardItemLighting();
        }

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    }

    private void func_82451_d(int par1, int par2)
    {
        this.func_82450_b(par1, par2, 0, 0);
    }

    private void func_82450_b(int par1, int par2, int par3, int par4)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.createFlatWorldGui.mc.renderEngine.bindTexture("/gui/slot.png");
		Tessellator var10 = Tessellator.instance;
        var10.startDrawingQuads();
        var10.addVertexWithUV((double)(par1 + 0), (double)(par2 + 18), (double)this.createFlatWorldGui.zLevel, (double)((float)(par3 + 0) * 0.0078125F), (double)((float)(par4 + 18) * 0.0078125F));
        var10.addVertexWithUV((double)(par1 + 18), (double)(par2 + 18), (double)this.createFlatWorldGui.zLevel, (double)((float)(par3 + 18) * 0.0078125F), (double)((float)(par4 + 18) * 0.0078125F));
        var10.addVertexWithUV((double)(par1 + 18), (double)(par2 + 0), (double)this.createFlatWorldGui.zLevel, (double)((float)(par3 + 18) * 0.0078125F), (double)((float)(par4 + 0) * 0.0078125F));
        var10.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), (double)this.createFlatWorldGui.zLevel, (double)((float)(par3 + 0) * 0.0078125F), (double)((float)(par4 + 0) * 0.0078125F));
        var10.draw();
    }

    /**
     * Gets the size of the current slot list.
     */
    protected int getSize()
    {
        return GuiFancyCreateFlatWorld.func_82271_a(this.createFlatWorldGui).getFlatLayers().size();
    }

    /**
     * the element in the slot that was clicked, boolean for wether it was double clicked or not
     */
    protected void elementClicked(int par1, boolean par2)
    {
        this.field_82454_a = par1;
        this.createFlatWorldGui.func_82270_g();
    }

    /**
     * returns true if the element passed in is currently selected
     */
    protected boolean isSelected(int par1)
    {
        return par1 == this.field_82454_a;
    }
    
    protected void drawBackground() {}

    protected void drawSlot(int par1, int par2, int par3, int par4, Tessellator par5Tessellator)
    {
        FlatLayerInfo var6 = (FlatLayerInfo)GuiFancyCreateFlatWorld.func_82271_a(this.createFlatWorldGui).getFlatLayers().get(GuiFancyCreateFlatWorld.func_82271_a(this.createFlatWorldGui).getFlatLayers().size() - par1 - 1);
        ItemStack var7 = var6.getFillBlock() == 0 ? null : new ItemStack(var6.getFillBlock(), 1, var6.getFillBlockMeta());
        String var8 = var7 == null ? "Air" : Item.itemsList[var6.getFillBlock()].func_77653_i(var7);
        this.func_82452_a(par2, par3, var7);
        Minecraft.getMinecraft().fontRenderer.drawString(var8, par2 + 18 + 5, par3 + 3, 16777215);
        String var9;

        if (par1 == 0)
        {
            var9 = StatCollector.translateToLocalFormatted("createWorld.customize.flat.layer.top", new Object[] {Integer.valueOf(var6.getLayerCount())});
        }
        else if (par1 == GuiFancyCreateFlatWorld.func_82271_a(this.createFlatWorldGui).getFlatLayers().size() - 1)
        {
            var9 = StatCollector.translateToLocalFormatted("createWorld.customize.flat.layer.bottom", new Object[] {Integer.valueOf(var6.getLayerCount())});
        }
        else
        {
            var9 = StatCollector.translateToLocalFormatted("createWorld.customize.flat.layer", new Object[] {Integer.valueOf(var6.getLayerCount())});
        }

        Minecraft.getMinecraft().fontRenderer.drawString(var9, par2 + 2 + 213 - Minecraft.getMinecraft().fontRenderer.getStringWidth(var9), par3 + 3, 16777215);
    }

    protected int getScrollBarX()
    {
        return this.createFlatWorldGui.width - 70;
    }
}
