package millaav.quantumupgrade.common.info;

import millaav.quantumupgrade.QuantumUpgrade;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BaseItems extends Item {
    public BaseItems() {
        this.setCreativeTab(QuantumUpgrade.tabQU);
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    public String getUnlocalizedName() {
        return String.format("item.%s%s", "quantumupgrade:", this.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    public String getUnlocalizedName(ItemStack itemstack) {
        int meta = itemstack.getItemDamage();
        return meta > 0 ? String.format("item.%s%s", "quantumupgrade:", this.getUnwrappedUnlocalizedName(super.getUnlocalizedName()) + meta) : String.format("item.%s%s", "supersolarpanel:", this.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }
}
