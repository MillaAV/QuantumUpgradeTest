package millaav.quantumupgrade.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.IElectricItem;
import millaav.quantumupgrade.QuantumUpgrade;
import millaav.quantumupgrade.common.functions.ItemVajraPro;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashSet;

public class TileVajraP extends ItemVajraPro implements IElectricItem {
    public TileVajraP(Item.ToolMaterial toolMaterial) {
        super(0.0F, toolMaterial, new HashSet());
        this.maxCharge = 18000000;
        this.transferLimit = 120000;
        this.effPower = 2000.0F;
        this.efficiencyOnProperMaterial = this.effPower;
        this.epo = 9999;
        this.setCreativeTab(QuantumUpgrade.tabQU);
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack var1) {
        return EnumRarity.uncommon;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("quantumupgrade:itemVajraP");
    }

    public int getVajraTier(){
        return 2;
    }
}