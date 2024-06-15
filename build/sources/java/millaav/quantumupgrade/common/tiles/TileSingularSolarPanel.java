package millaav.quantumupgrade.common.tiles;

import millaav.quantumupgrade.QuantumUpgrade;

public class TileSingularSolarPanel extends TileSolarPanel {
    public TileSingularSolarPanel() {
        super("blockSingularSolarPanel.name", 6, QuantumUpgrade.singularpanelGenDay, QuantumUpgrade.singularpanelGenNight, QuantumUpgrade.singularpanelOutput, 1000000000);
    }
}