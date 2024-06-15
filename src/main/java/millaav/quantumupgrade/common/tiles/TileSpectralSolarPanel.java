package millaav.quantumupgrade.common.tiles;


import millaav.quantumupgrade.QuantumUpgrade;

public class TileSpectralSolarPanel extends TileSolarPanel {
    public TileSpectralSolarPanel() {
        super("blockSpectralSolarPanel.name", 5, QuantumUpgrade.spectralpanelGenDay, QuantumUpgrade.spectralpanelGenNight, QuantumUpgrade.spectralpanelOutput, 100000000);
    }
}
