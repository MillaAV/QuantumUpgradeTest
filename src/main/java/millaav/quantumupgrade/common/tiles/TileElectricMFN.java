package millaav.quantumupgrade.common.tiles;

import ic2.core.block.wiring.TileEntityElectricBlock;

public class TileElectricMFN extends TileEntityElectricBlock {
    public TileElectricMFN() {
        super(4, 2048, 230000000);
    }

    public String getInventoryName() {
        return "MFN";
    }
}
