package millaav.quantumupgrade;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import ic2.api.item.IC2Items;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.Recipes;
import ic2.core.Ic2Items;
import millaav.quantumupgrade.common.CommonProxy;
import millaav.quantumupgrade.common.block.panels.*;
import millaav.quantumupgrade.common.info.ItemBlockAdmSP;
import millaav.quantumupgrade.common.info.ItemBlockPhotonSP;
import millaav.quantumupgrade.common.info.ItemBlockSSP;
import millaav.quantumupgrade.common.info.ItemBlockSpSP;
import millaav.quantumupgrade.common.items.*;
import millaav.quantumupgrade.common.network.ASPPacketHandler;
import millaav.quantumupgrade.common.tiles.*;
import millaav.quantumupgrade.common.utils.MTRecipeConfig;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;////
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;


@Mod(
        modid = "quantumupgrade",
        name = "Quantum Upgrade",
        dependencies = "required-after:IC2; after:RedPowerCore",
        version = "1.0"
)

public class QuantumUpgrade {
    @SidedProxy(clientSide = "millaav.quantumupgrade.client.ClientProxy",
            serverSide = "millaav.quantumupgrade.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance("quantumupgrade")
    public static QuantumUpgrade instance;

    public static int spectralpanelGenDay;
    public static int spectralpanelGenNight;
    public static int singularpanelGenDay;
    public static int singularpanelOutput;
    public static int spectralpanelOutput;
    public static int singularpanelGenNight;
    public static int adminpanelGenDay;
    public static int adminpanelGenNight;
    public static int AdminpanelStorage;
    public static int AdminpanelOutput;
    public static int photonicpanelGenDay;
    public static int photonicpanelGenNight;
    public static int photonicpanelOutput;
    public static int photonicpanelStorage;
    public static Item enderquantumcomponent;
    public static Item solarsplitter;
    public static Item bluecomponent;
    public static Item greencomponent;
    public static Item redcomponent;
    public static final Block BlockSingularSP = new BlockSuperSP();
    public static final Block BlockSpectralSP = new BlockSpectralSP();
    public static final Block BlockAdminSP = new BlockAdminSP();
    public static final Block BlockPhotonSP = new BlockPhotonSP();

    public static Block blockAdvSolarPanel;
    public static Block blockMolecularTransformer;
    public static Item itemAdvanced;
    public static Item advancedSolarHelmet;
    public static Item hybridSolarHelmet;
    public static Item ultimateSolarHelmet;
    public static ItemStack itemSunnarium;
    public static ItemStack itemSunnariumPart;
    public static ItemStack itemSunnariumAlloy;
    public static ItemStack ingotIridium;
    public static ItemStack itemIrradiantUranium;
    public static ItemStack itemEnrichedSunnarium;
    public static ItemStack itemEnrichedSunnariumAlloy;
    public static ItemStack itemIrradiantGlassPane;
    public static ItemStack itemIridiumIronPlate;
    public static ItemStack itemReinforcedIridiumIronPlate;
    public static ItemStack itemIrradiantReinforcedPlate;
    public static ItemStack itemQuantumCore;
    public static ItemStack itemUranIngot;
    public static ItemStack itemUHSP;
    public static ItemStack itemMTCore;
    public static ItemStack itemMolecularTransformer;
    public static Configuration config;
    public static String configFileName;
    public static int advGenDay;
    public static int advGenNight;
    public static int advStorage;
    public static int advOutput;
    public static int hGenDay;
    public static int hGenNight;
    public static int hStorage;
    public static int hOutput;
    public static int uhGenDay;
    public static int uhGenNight;
    public static int uhStorage;
    public static int uhOutput;
    public static int qpGenDay;
    public static int qpGenNight;
    public static int qpStorage;
    public static int qpOutput;
    public static int qgbaseProduction;
    public static int qgbaseMaxPacketSize;
    public static int blockMolecularTransformerRenderID;
    public static Block TileElectricMFN;
    private static boolean disableAdvancedSolarHelmetRecipe;
    private static boolean disableHybridSolarHelmetRecipe;
    private static boolean disableUltimateSolarHelmetRecipe;
    private static boolean disableAdvancedSolarPanelRecipe;
    private static boolean disableHybridSolarPanelRecipe;
    private static boolean disableUltimateSolarPanelRecipe;
    private static boolean disableQuantumSolarPanelRecipe;
    private static boolean disableMolecularTransformerRecipe;
    private static boolean disableDoubleSlabRecipe;
    private static boolean enableSimpleAdvancedSolarPanelRecipes;
    private static boolean enableHardRecipes;
    public static final String CATEGORY_RECIPES = "recipes settings";
    public static final String CATEGORY_QGENERATOR = "quantum generator";
    public static final Side side = FMLCommonHandler.instance().getEffectiveSide();

    public static ItemStack setItemsSize(ItemStack itemStack, int newSize) {
        ItemStack newStack = itemStack.copy();
        newStack.stackSize = newSize;
        return newStack;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        try {
            config.load();
            configFileName = event.getSuggestedConfigurationFile().getAbsolutePath();
            spectralpanelGenDay = config.get("general", "SpectralSPGenDay", 32768).getInt(32768);
            spectralpanelGenNight = config.get("general", "SpectralSPGenNight", 20000).getInt(20000);
            singularpanelGenDay = config.get("general", "SingularSPGenDay", 262144).getInt(262144);
            singularpanelGenNight = config.get("general", "SingularSPGenNight", 196608).getInt(196608);
            singularpanelOutput = config.get("general", "SingularSPOutput", 262144).getInt(262144);
            spectralpanelOutput = config.get("general", "SpectralSPOutput", 32768).getInt(32768);
            adminpanelGenDay = config.get("general", "AdminPanelGenDay", 1048576).getInt(1048576);
            adminpanelGenNight = config.get("general", "AdminPanelGenNight", 1048576).getInt(1048576);
            AdminpanelStorage = config.get("general", "AdminPanelStorage", 1000000000).getInt(1000000000);
            AdminpanelOutput = config.get("general", "AdminPanelOutput", 1048576).getInt(1048576);
            photonicpanelGenDay = config.get("general", "PhotonicPanelGenDay", 1000000000).getInt(1000000000);
            photonicpanelGenNight = config.get("general", "PhotonicPanelGenNight", 1000000000).getInt(1000000000);
            photonicpanelOutput = config.get("general", "PhotonicPanelOutput", 1000000000).getInt(1000000000);
            photonicpanelStorage = config.get("general", "PhotonicPanelStorage", 1000000000).getInt(1000000000);

            configFileName = event.getSuggestedConfigurationFile().getAbsolutePath();
            advGenDay = config.get("general", "AdvancedSPGenDay", 8).getInt(8);
            advGenNight = config.get("general", "AdvancedSPGenNight", 1).getInt(1);
            advStorage = config.get("general", "AdvancedSPStorage", 32000).getInt(32000);
            advOutput = config.get("general", "AdvancedSPOutput", 32).getInt(32);
            hGenDay = config.get("general", "HybrydSPGenDay", 64).getInt(64);
            hGenNight = config.get("general", "HybrydSPGenNight", 8).getInt(8);
            hStorage = config.get("general", "HybrydSPStorage", 100000).getInt(100000);
            hOutput = config.get("general", "HybrydSPOutput", 128).getInt(128);
            uhGenDay = config.get("general", "UltimateHSPGenDay", 512).getInt(512);
            uhGenNight = config.get("general", "UltimateHSPGenNight", 64).getInt(64);
            uhStorage = config.get("general", "UltimateHSPStorage", 1000000).getInt(1000000);
            uhOutput = config.get("general", "UltimateHSPOutput", 512).getInt(512);
            qpGenDay = config.get("general", "QuantumSPGenDay", 4096).getInt(4096);
            qpGenNight = config.get("general", "QuantumSPGenNight", 2048).getInt(2048);
            qpStorage = config.get("general", "QuantumSPStorage", 10000000).getInt(10000000);
            qpOutput = config.get("general", "QuantumSPOutput", 8192).getInt(8192);
            qgbaseProduction = config.get("quantum generator", "quantumGeneratorDefaultProduction", 512).getInt(512);
            qgbaseMaxPacketSize = config.get("quantum generator", "quantumGeneratorDefaultPacketSize", 512).getInt(512);
            disableAdvancedSolarHelmetRecipe = config.get("recipes settings", "Disable Advanced Solar Helmet recipe", false).getBoolean(false);
            disableHybridSolarHelmetRecipe = config.get("recipes settings", "Disable Hybrid Solar Helmet recipe", false).getBoolean(false);
            disableUltimateSolarHelmetRecipe = config.get("recipes settings", "Disable Ultimate Solar Helmet recipe", false).getBoolean(false);
            disableAdvancedSolarPanelRecipe = config.get("recipes settings", "Disable AdvancedSolarPanel recipe", false).getBoolean(false);
            disableHybridSolarPanelRecipe = config.get("recipes settings", "Disable HybridSolarPanel recipe", false).getBoolean(false);
            disableUltimateSolarPanelRecipe = config.get("recipes settings", "Disable UltimateSolarPanel recipe", false).getBoolean(false);
            disableQuantumSolarPanelRecipe = config.get("recipes settings", "Disable QuantumSolarPanel recipe", false).getBoolean(false);
            disableMolecularTransformerRecipe = config.get("recipes settings", "Disable MolecularTransformer recipe", false).getBoolean(false);
            disableDoubleSlabRecipe = config.get("recipes settings", "Disable DoubleSlab recipe", false).getBoolean(false);
            enableSimpleAdvancedSolarPanelRecipes = config.get("recipes settings", "Enable simple Advanced Solar Panel recipe", false).getBoolean(false);
            enableHardRecipes = config.get("recipes settings", "Enable hard recipes", true).getBoolean(true);
        } catch (Exception var7) {
            Exception e = var7;
            System.out.println("[QuantumUpgrade] error occurred parsing config file");
            throw new RuntimeException(e);
        } finally {
            config.save();
        }

        GameRegistry.registerBlock(BlockSingularSP, ItemBlockSSP.class, "SingularSolarPanel");
        GameRegistry.registerBlock(BlockSpectralSP, ItemBlockSpSP.class, "SpectralSolarPanel");
        GameRegistry.registerBlock(BlockAdminSP, ItemBlockAdmSP.class, "AdminSolarPanel");
        GameRegistry.registerBlock(BlockPhotonSP, ItemBlockPhotonSP.class, "PhotonicSolarPanel");
        enderquantumcomponent = new millaav.quantumupgrade.common.items.ItemEnderQuantumComponent();
        GameRegistry.registerItem(enderquantumcomponent, "enderquantumcomponent");
        solarsplitter = new millaav.quantumupgrade.common.items.ItemSpectralLightSplitter();
        GameRegistry.registerItem(solarsplitter, "solarsplitter");
        bluecomponent = new millaav.quantumupgrade.common.items.ItemBlueSpectralComponent();
        greencomponent = new millaav.quantumupgrade.common.items.ItemGreenSpectralComponent();
        redcomponent = new ItemRedSpectralComponent();
        GameRegistry.registerItem(bluecomponent, "bluecomponent");
        GameRegistry.registerItem(greencomponent, "greencomponent");
        GameRegistry.registerItem(redcomponent, "redcomponent");

        blockMolecularTransformer = new BlockMolecularTransformer();
        GameRegistry.registerBlock(blockMolecularTransformer, ItemMolecularTransformer.class, "BlockMolecularTransformer");
        GameRegistry.registerTileEntity(TileMolecularTransformer.class, "Molecular Transformer");
        blockAdvSolarPanel = new BlockAdvSolarPanel();
        GameRegistry.registerBlock(blockAdvSolarPanel, ItemAdvSolarPanel.class, "BlockAdvSolarPanel");
        GameRegistry.registerTileEntity(TileAdvancedSolarPanel.class, "Advanced Solar Panel");
        GameRegistry.registerTileEntity(TileHybridSolarPanel.class, "Hybrid Solar Panel");
        GameRegistry.registerTileEntity(TileUltimateSolarPanel.class, "Ultimate Hybrid Solar Panel");
        GameRegistry.registerTileEntity(TileQuantumSolarPanel.class, "Quantum Solar Panel");
        GameRegistry.registerTileEntity(TileQGenerator.class, "Quantum Generator");
        advancedSolarHelmet = (new ItemAdvancedSolarHelmet(ItemArmor.ArmorMaterial.DIAMOND, proxy.addArmor("advancedSolarHelmet"), 0, 1)).setUnlocalizedName("advancedSolarHelmet");
        GameRegistry.registerItem(advancedSolarHelmet, "advanced_solar_helmet");
        hybridSolarHelmet = (new ItemAdvancedSolarHelmet(ItemArmor.ArmorMaterial.DIAMOND, proxy.addArmor("hybridSolarHelmet"), 0, 2)).setUnlocalizedName("hybridSolarHelmet");
        GameRegistry.registerItem(hybridSolarHelmet, "hybrid_solar_helmet");
        ultimateSolarHelmet = (new ItemAdvancedSolarHelmet(ItemArmor.ArmorMaterial.DIAMOND, proxy.addArmor("ultimateSolarHelmet"), 0, 3)).setUnlocalizedName("ultimateSolarHelmet");
        GameRegistry.registerItem(ultimateSolarHelmet, "ultimate_solar_helmet");
        itemAdvanced = new ItemAdvanced();
        GameRegistry.registerItem(itemAdvanced, "asp_crafting_items");
        itemSunnarium = new ItemStack(itemAdvanced.setUnlocalizedName("itemSunnarium"), 1, 0);
        itemSunnariumAlloy = new ItemStack(itemAdvanced.setUnlocalizedName("itemSunnariumAlloy"), 1, 1);
        itemIrradiantUranium = new ItemStack(itemAdvanced.setUnlocalizedName("itemIrradiantUranium"), 1, 2);
        itemEnrichedSunnarium = new ItemStack(itemAdvanced.setUnlocalizedName("itemEnrichedSunnarium"), 1, 3);
        itemEnrichedSunnariumAlloy = new ItemStack(itemAdvanced.setUnlocalizedName("itemEnrichedSunnariumAlloy"), 1, 4);
        itemIrradiantGlassPane = new ItemStack(itemAdvanced.setUnlocalizedName("itemIrradiantGlassPlane"), 1, 5);
        itemIridiumIronPlate = new ItemStack(itemAdvanced.setUnlocalizedName("itemIridiumIronPlate"), 1, 6);
        itemReinforcedIridiumIronPlate = new ItemStack(itemAdvanced.setUnlocalizedName("itemReinforcedIridiumIronPlate"), 1, 7);
        itemIrradiantReinforcedPlate = new ItemStack(itemAdvanced.setUnlocalizedName("itemIrradiantReinforcedPlate"), 1, 8);
        itemSunnariumPart = new ItemStack(itemAdvanced.setUnlocalizedName("itemSunnariumPart"), 1, 9);
        ingotIridium = new ItemStack(itemAdvanced.setUnlocalizedName("ingotIridium"), 1, 10);
        itemUranIngot = new ItemStack(itemAdvanced.setUnlocalizedName("itemUranIngot"), 1, 11);
        itemMTCore = new ItemStack(itemAdvanced.setUnlocalizedName("itemMTCore"), 1, 12);
        itemQuantumCore = new ItemStack(itemAdvanced.setUnlocalizedName("itemQuantumCore"), 1, 13);
        itemMolecularTransformer = new ItemStack(blockMolecularTransformer, 1, 0);
        itemUHSP = new ItemStack(blockAdvSolarPanel, 1, 2);
        Recipes.compressor.addRecipe(new RecipeInputItemStack(IC2Items.getItem("iridiumOre"), 1), (NBTTagCompound)null, new ItemStack[]{ingotIridium});
        Recipes.compressor.addRecipe(new RecipeInputItemStack(IC2Items.getItem("UranFuel"), 1), (NBTTagCompound)null, new ItemStack[]{itemUranIngot});
        Recipes.compressor.addRecipe(new RecipeInputItemStack(IC2Items.getItem("uraniumOre"), 1), (NBTTagCompound)null, new ItemStack[]{itemUranIngot});
        Recipes.compressor.addRecipe(new RecipeInputItemStack(IC2Items.getItem("crushedUraniumOre"), 1), (NBTTagCompound)null, new ItemStack[]{itemUranIngot});
        OreDictionary.registerOre("ingotUranium", itemUranIngot);
        OreDictionary.registerOre("ingotIridium", ingotIridium);
        OreDictionary.registerOre("craftingSolarPanelHV", itemUHSP);
        OreDictionary.registerOre("craftingSunnariumPart", itemSunnariumPart);
        OreDictionary.registerOre("craftingSunnarium", itemSunnarium);
        OreDictionary.registerOre("craftingMTCore", itemMTCore);
        OreDictionary.registerOre("craftingMolecularTransformer", itemMolecularTransformer);
        proxy.registerRenderers();
        proxy.load();
        MTRecipeConfig.doDebug();


    }

    public static void addLog(String logLine) {
        System.out.println("[QuantumUpgrade] " + logLine);
    }


    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        ASPPacketHandler.load();
        proxy.init(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        GameRegistry.registerTileEntity(millaav.quantumupgrade.common.tiles.TileSingularSolarPanel.class, "SingularSolarPanel");
        GameRegistry.registerTileEntity(millaav.quantumupgrade.common.tiles.TileSpectralSolarPanel.class, "SpectralSolarPanel");
        GameRegistry.registerTileEntity(millaav.quantumupgrade.common.tiles.TileAdminSolarPanel.class, "AdminSolarPanel");
        GameRegistry.registerTileEntity(TilePhotonicSolarPanel.class, "PhotonicSolarPanel");
    }

    @Mod.EventHandler
    public void init(FMLPostInitializationEvent event){
        proxy.postInit(event);
    }


    public static CreativeTabs tabQU = new CreativeTabs("tabQU")
    {
        @Override
        public ItemStack getIconItemStack()
        {
            return new ItemStack(Ic2Items.nanoHelmet.getItem(), 1, 0);
        }

        @Override
        public Item getTabIconItem()
        {
            return Ic2Items.nanoHelmet.getItem();
        }
    };

    public static boolean isSimulating() {
        return !FMLCommonHandler.instance().getEffectiveSide().isClient();
    }


}
