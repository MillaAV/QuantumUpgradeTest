package millaav.quantumupgrade.common.block.panels;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

public abstract class AbstractSSP extends BlockContainer {
    public AbstractSSP(Material mat) {
        super(mat);
    }

    public AbstractSSP() {
        this(Material.rock);
    }
}
