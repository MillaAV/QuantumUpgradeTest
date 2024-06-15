package millaav.quantumupgrade.common.items;

import millaav.quantumupgrade.QuantumUpgrade;
import net.minecraft.item.Item;

public class ItemGreenSpectralComponent extends Item {
    public ItemGreenSpectralComponent() {
        this.setCreativeTab(QuantumUpgrade.tabQU);
        this.setUnlocalizedName("GreenSpectralComponent");
        this.setMaxStackSize(64);
        this.setTextureName("quantumupgrade:Green_Spectral_Component");
    }
}
