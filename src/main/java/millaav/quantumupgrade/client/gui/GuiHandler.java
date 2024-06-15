package millaav.quantumupgrade.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import millaav.quantumupgrade.common.tiles.TileMolecularTransformer;
import millaav.quantumupgrade.common.tiles.TileQGenerator;
import millaav.quantumupgrade.common.tiles.TileSolarPanel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
    public GuiHandler() {
    }

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int X, int Y, int Z) {
        TileEntity te = world.getTileEntity(X, Y, Z);
        if (te != null) {
            if (te instanceof TileSolarPanel) {
                return ((TileSolarPanel)te).getGuiContainer(player.inventory);
            } else if (te instanceof TileQGenerator) {
                return ((TileQGenerator)te).getGuiContainer(player.inventory);
            } else {
                return te instanceof TileMolecularTransformer ? ((TileMolecularTransformer)te).getGuiContainer(player.inventory) : null;
            }
        } else {
            return null;
        }
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int X, int Y, int Z) {
        TileEntity te = world.getTileEntity(X, Y, Z);
        System.out.println("OpenGui");
        if (te != null) {
            if (te instanceof TileSolarPanel) {
                return new GuiAdvSolarPanel(player.inventory, (TileSolarPanel) te);
            } else if (te instanceof TileQGenerator) {
                return new GuiQGenerator(player.inventory, (TileQGenerator)te);
            } else {
                return te instanceof TileMolecularTransformer ? new GuiMolecularTransformer(player.inventory, (TileMolecularTransformer)te) : null;
            }
        } else {
            return null;
        }
    }

    public void registerRenderers() {
    }
}
