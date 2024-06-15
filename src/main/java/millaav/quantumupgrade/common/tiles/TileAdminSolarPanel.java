package millaav.quantumupgrade.common.tiles;

import millaav.quantumupgrade.QuantumUpgrade;

public class TileAdminSolarPanel extends TileSolarPanel {
    public TileAdminSolarPanel() {
        super("blockAdminSolarPanel.name", 7, QuantumUpgrade.adminpanelGenDay, QuantumUpgrade.adminpanelGenNight, QuantumUpgrade.AdminpanelOutput, QuantumUpgrade.AdminpanelStorage);
    }
}
