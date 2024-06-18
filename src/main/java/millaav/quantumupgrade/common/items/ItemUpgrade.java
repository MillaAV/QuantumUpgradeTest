package millaav.quantumupgrade.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import millaav.quantumupgrade.QuantumUpgrade;
import millaav.quantumupgrade.common.info.ModuleInfo;
import millaav.quantumupgrade.common.registry.EnumUpgradeType;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import java.util.List;

public class ItemUpgrade extends Item {
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;
    public ItemUpgrade(){
        setCreativeTab(QuantumUpgrade.tabQU);
        setMaxStackSize(1);
        setHasSubtypes(true);
        setMaxDamage(0);
        setTextureName("quantumupgrade:upgrade");
        setUnlocalizedName("upgrade");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        int j = MathHelper.clamp_int(meta, 0, EnumUpgradeType.values().length);
        return icons[j];
    }

    public String getUnlocalizedName(ItemStack stack) {
        int i = MathHelper.clamp_int(stack.getItemDamage(), 0, 15);
        return super.getUnlocalizedName() + "." + EnumUpgradeType.values()[i].key;
    }

//    public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean par4) {
//        ModuleInfo.addInfoToUpgrade(stack, info);}

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.icons = new IIcon[EnumUpgradeType.values().length];

        for (int i = 0; i < EnumUpgradeType.values().length; ++i) {
            this.icons[i] = iconRegister.registerIcon(this.getIconString() + "_" + EnumUpgradeType.values()[i].key);
        }
    }

    @Override
    public void getSubItems(Item item, CreativeTabs creativeTab, List itemList) {
        for (int i = 0; i < EnumUpgradeType.values().length; ++i) {
            itemList.add(new ItemStack(item, 1, i));
        }
    }
}