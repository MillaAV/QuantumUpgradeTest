package millaav.quantumupgrade.common;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event){
        ItemRegistry.init();
        BlockRegistry.init();
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        FMLCommonHandler.instance().bus().register(new EventHandler());
    }

    public void init(FMLInitializationEvent event){
        NetworkRegistry.INSTANCE.registerGuiHandler(VajraPro.instance, new GuiHandler());
        GameRegistry.registerTileEntity(TileToolStation.class, "tiletoolstation");
        RecipeRegistry.init();
        NetworkHandler.init();
    }

    public void postInit(FMLPostInitializationEvent event){

    }

    public static void sendPlayerMessage(EntityPlayer player, String message) {
        if (VajraPro.isSimulating()) {
            player.addChatMessage(new ChatComponentTranslation(message, new Object[0]));
        }

    }
}
