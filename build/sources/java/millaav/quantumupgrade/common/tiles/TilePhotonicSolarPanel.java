package millaav.quantumupgrade.common.tiles;


import millaav.quantumupgrade.QuantumUpgrade;

public class TilePhotonicSolarPanel extends TileSolarPanel {
    public TilePhotonicSolarPanel() {
        super("blockPhotonicSolarPanel.name", 8, QuantumUpgrade.photonicpanelGenDay, QuantumUpgrade.photonicpanelGenNight, QuantumUpgrade.photonicpanelOutput, QuantumUpgrade.photonicpanelStorage);
    }
}
