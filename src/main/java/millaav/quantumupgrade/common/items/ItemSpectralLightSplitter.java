package millaav.quantumupgrade.common.items;

import millaav.quantumupgrade.QuantumUpgrade;
import net.minecraft.item.Item;

public class ItemSpectralLightSplitter extends Item {
    public ItemSpectralLightSplitter() {
        this.setCreativeTab(QuantumUpgrade.tabQU);
        this.setUnlocalizedName("SpectralLightSplitter");
        this.setMaxStackSize(64);
        this.setTextureName("quantumupgrade:spectral_light_splitter");
    }
}