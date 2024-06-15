package millaav.quantumupgrade.common;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import millaav.quantumupgrade.api.MTAPI;
import millaav.quantumupgrade.common.registry.BlockRegistry;
import millaav.quantumupgrade.common.registry.ItemRegistry;
import millaav.quantumupgrade.common.tiles.TileMolecularTransformer;
import millaav.quantumupgrade.common.tiles.TileQGenerator;
import millaav.quantumupgrade.common.tiles.TileSolarPanel;
import millaav.quantumupgrade.common.utils.MTRecipeManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler {
    public void preInit(FMLPreInitializationEvent event){
        ItemRegistry.init();
        BlockRegistry.init();
    }

    public void init(FMLInitializationEvent event){
    }

    public void postInit(FMLPostInitializationEvent event){

    }

    public void load() {
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
        return null;
    }

    public void initRecipes() {
        MTAPI.manager = MTRecipeManager.instance;
        MTRecipeManager.instance.initRecipes();
    }

    public int addArmor(String armorName) {
        return 0;
    }

    public void registerRenderers() {
    }

}
