package millaav.quantumupgrade.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import millaav.quantumupgrade.QuantumUpgrade;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.ArrayList;
import java.util.List;

public class ItemAdvanced extends Item {
    private List<String> itemNames = new ArrayList();
    private IIcon[] IIconsList = new IIcon[14];
    private int itemsCount = 13;

    public ItemAdvanced() {
        this.setHasSubtypes(true);
        this.setCreativeTab(QuantumUpgrade.tabQU);
        this.setMaxStackSize(64);
        this.addItemsNames();
    }

    public String getUnlocalizedName(ItemStack stack) {
        return (String)this.itemNames.get(stack.getItemDamage());
    }

    public IIcon getIconFromDamage(int par1) {
        return this.IIconsList[par1];
    }

    public void addItemsNames() {
        this.itemNames.add("itemSunnarium");
        this.itemNames.add("itemSunnariumAlloy");
        this.itemNames.add("itemIrradiantUranium");
        this.itemNames.add("itemEnrichedSunnarium");
        this.itemNames.add("itemEnrichedSunnariumAlloy");
        this.itemNames.add("itemIrradiantGlassPane");
        this.itemNames.add("itemIridiumIronPlate");
        this.itemNames.add("itemReinforcedIridiumIronPlate");
        this.itemNames.add("itemIrradiantReinforcedPlate");
        this.itemNames.add("itemSunnariumPart");
        this.itemNames.add("ingotIridium");
        this.itemNames.add("itemUranIngot");
        this.itemNames.add("itemMTCore");
        this.itemNames.add("itemQuantumCore");
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister IIconRegister) {
        this.IIconsList[0] = IIconRegister.registerIcon("quantumupgrade:Sunnarium");
        this.IIconsList[1] = IIconRegister.registerIcon("quantumupgrade:SunnariumAlloy");
        this.IIconsList[2] = IIconRegister.registerIcon("quantumupgrade:IrradiantUranium");
        this.IIconsList[3] = IIconRegister.registerIcon("quantumupgrade:EnrichedSunnarium");
        this.IIconsList[4] = IIconRegister.registerIcon("quantumupgrade:EnrichedSunnariumAlloy");
        this.IIconsList[5] = IIconRegister.registerIcon("quantumupgrade:IrradiantGlassPane");
        this.IIconsList[6] = IIconRegister.registerIcon("quantumupgrade:IridiumIronPlate");
        this.IIconsList[7] = IIconRegister.registerIcon("quantumupgrade:ReinforcedIridiumIronPlate");
        this.IIconsList[8] = IIconRegister.registerIcon("quantumupgrade:IrradiantReinforcedPlate");
        this.IIconsList[9] = IIconRegister.registerIcon("quantumupgrade:SunnariumPart");
        this.IIconsList[10] = IIconRegister.registerIcon("quantumupgrade:IridiumIgnot");
        this.IIconsList[11] = IIconRegister.registerIcon("quantumupgrade:UranIngot");
        this.IIconsList[12] = IIconRegister.registerIcon("quantumupgrade:MTCore");
        this.IIconsList[13] = IIconRegister.registerIcon("quantumupgrade:QuantumCore");
    }

    public void getSubItems(Item item, CreativeTabs tabs, List itemList) {
        for(int meta = 0; meta <= this.itemNames.size() - 1; ++meta) {
            ItemStack stack = new ItemStack(this, 1, meta);
            itemList.add(stack);
        }

    }

    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
        for(int i = 0; i <= this.IIconsList.length - 1; ++i) {
            par3List.add(new ItemStack(this, 1, i));
        }

    }
}
