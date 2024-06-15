package millaav.quantumupgrade.common.tiles;


public class TileHybridSolarPanel extends TileSolarPanel {
    public TileHybridSolarPanel() {
        super("blockHybridSolarPanel.name", 3, 64, 8, 128, 100000);
    }
    public String getInvName() {
        return "Hyb Solar Panel";
    }
}