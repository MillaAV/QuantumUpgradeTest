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

public class TileVajraM extends ItemVajraPro implements IElectricItem {

    public TileVajraM(Item.ToolMaterial toolMaterial) {
        super(0.0F, toolMaterial, new HashSet());;
        this.setCreativeTab(QuantumUpgrade.tabQU);
        setMaxStackSize(1);
        this.maxCharge = 48000000;
        this.transferLimit = 600000;
        this.effPower = 2000.0F;
        this.efficiencyOnProperMaterial = effPower;
        this.epo = 29997;
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack var1) {
        return EnumRarity.epic;
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon("quantumupgrade:itemVajraM");
    }

    public int getVajraTier(){
        return 3;
    }
}
