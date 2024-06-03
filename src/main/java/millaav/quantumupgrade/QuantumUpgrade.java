package millaav.quantumupgrade;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import static gravisuite.GraviSuite.proxy;

@Mod(
        modid = "quantumupgrade",
        name = "quantumupgrade",
        dependencies = "required-after:IC2; after:RedPowerCore",
        version = "1.0"
)

public class QuantumUpgrade {
    @SidedProxy(clientSide = "millaav.quantumupgrade.client.ClientProxy",
            serverSide = "millaav.quantumupgrade.common.CommonProxy")
//    public static CommonProxy proxy;
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
//        proxy.preInit(event);
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        proxy.init(event);
    }

    @Mod.EventHandler
    public void init(FMLPostInitializationEvent event){
        proxy.postInit(event);
    }

    @Mod.Instance("quantumupgrade")
    public static QuantumUpgrade instance;
}
