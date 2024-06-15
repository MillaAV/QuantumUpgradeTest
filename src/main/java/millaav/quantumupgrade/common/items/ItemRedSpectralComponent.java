package millaav.quantumupgrade.common.items;

import millaav.quantumupgrade.QuantumUpgrade;
import net.minecraft.item.Item;

public class ItemRedSpectralComponent extends Item {
    public ItemRedSpectralComponent() {
        this.setCreativeTab(QuantumUpgrade.tabQU);
        this.setUnlocalizedName("RedSpectralComponent");
        this.setMaxStackSize(64);
        this.setTextureName("quantumupgrade:Red_Spectral_Component");
    }
}