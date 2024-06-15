package millaav.quantumupgrade.common.tiles;


public class TileQuantumSolarPanel extends TileSolarPanel {
    public TileQuantumSolarPanel() {
        super("blockQuantumSolarPanel.name", 4, 4096, 2048, 8192, 1000000);
    }
    public String getInvName() {
        return "Quantum Solar Panel";
    }
}
