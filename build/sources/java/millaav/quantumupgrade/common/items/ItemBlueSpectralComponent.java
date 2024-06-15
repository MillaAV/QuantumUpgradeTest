package millaav.quantumupgrade.common.items;

import millaav.quantumupgrade.QuantumUpgrade;
import net.minecraft.item.Item;

public class ItemBlueSpectralComponent extends Item {
    public ItemBlueSpectralComponent() {
        this.setCreativeTab(QuantumUpgrade.tabQU);
        this.setUnlocalizedName("BlueSpectralComponent");
        this.setMaxStackSize(64);
        this.setTextureName("quantumupgrade:Blue_Spectral_Component");
    }
}
