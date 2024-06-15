package millaav.quantumupgrade.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import millaav.quantumupgrade.QuantumUpgrade;
import millaav.quantumupgrade.api.MTAPI;
import millaav.quantumupgrade.client.gui.GuiAdvSolarPanel;
import millaav.quantumupgrade.client.gui.GuiMolecularTransformer;
import millaav.quantumupgrade.client.gui.GuiQGenerator;
import millaav.quantumupgrade.client.renderers.block.BlockMolecularTransformerRenderer;
import millaav.quantumupgrade.client.renderers.tile.TileMolecularTransformerRenderer;
import millaav.quantumupgrade.common.CommonProxy;
import millaav.quantumupgrade.common.tiles.TileMolecularTransformer;
import millaav.quantumupgrade.common.tiles.TileQGenerator;
import millaav.quantumupgrade.common.tiles.TileSolarPanel;
import millaav.quantumupgrade.common.utils.MTRecipeManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {
    public static final Side side = FMLCommonHandler.instance().getEffectiveSide();
    public void preInit(FMLPreInitializationEvent event){
        super.preInit(event);
    }

    public void init(FMLInitializationEvent event){
        super.init(event);
    }

    public void postInit(FMLPostInitializationEvent event){
        super.postInit(event);
    }

    public static int[][] sideAndFacingToSpriteOffset;

    public void load() {
        try {
            sideAndFacingToSpriteOffset = (int[][])((int[][])Class.forName("ic2.core.block.BlockMultiID").getField("sideAndFacingToSpriteOffset").get((Object)null));
        } catch (Exception var2) {
            sideAndFacingToSpriteOffset = new int[][]{{3, 2, 0, 0, 0, 0}, {2, 3, 1, 1, 1, 1}, {1, 1, 3, 2, 5, 4}, {0, 0, 2, 3, 4, 5}, {4, 5, 4, 5, 3, 2}, {5, 4, 5, 4, 2, 3}};
        }

    }

    public void registerRenderers() {
        QuantumUpgrade.blockMolecularTransformerRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockMolecularTransformerRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileMolecularTransformer.class, new TileMolecularTransformerRenderer());
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int X, int Y, int Z) {
        TileEntity te = world.getTileEntity(X, Y, Z);
        if (te != null) {
            if (te instanceof TileSolarPanel) {
                return new GuiAdvSolarPanel(player.inventory, (TileSolarPanel)te);
            } else if (te instanceof TileQGenerator) {
                return new GuiQGenerator(player.inventory, (TileQGenerator)te);
            } else {
                return te instanceof TileMolecularTransformer ? new GuiMolecularTransformer(player.inventory, (TileMolecularTransformer)te) : null;
            }
        } else {
            return null;
        }
    }

    public void initRecipes() {
        MTAPI.manager = MTRecipeManager.instance;
        MTRecipeManager.instance.initRecipes();
    }

    public int addArmor(String armorName) {
        return RenderingRegistry.addNewArmourRendererPrefix(armorName);
    }
}
