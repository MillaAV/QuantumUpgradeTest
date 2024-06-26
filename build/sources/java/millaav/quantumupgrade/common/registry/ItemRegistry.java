package millaav.quantumupgrade.common.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import ic2.core.item.ItemBattery;
import millaav.quantumupgrade.QuantumUpgrade;
import millaav.quantumupgrade.common.items.ItemGoldBattery;
import millaav.quantumupgrade.common.items.ItemUpgrade;
import millaav.quantumupgrade.common.tiles.TileVajraC;
import millaav.quantumupgrade.common.tiles.TileVajraM;
import millaav.quantumupgrade.common.tiles.TileVajraP;
import net.minecraft.item.Item;

public class ItemRegistry {

    public static Item vajraС;
    public static Item vajraP;
    public static Item vajraM;
    public static Item upgrade;
    public static Item tier1upgrade;
    public static Item tier2upgrade;
    public static Item coolingcore;
    public static Item magnetron;
    public static Item magnetron1;
    public static Item magnetron2;
    public static Item vajracore;
    public static Item vajracore1;
    public static Item vajracore2;
    public static Item superconductorcover;
    public static Item superconductor;
    public static Item goldplatedenergycrystal;

    public static void init() {
        goldplatedenergycrystal = new ItemGoldBattery().setCreativeTab(QuantumUpgrade.tabQU).setTextureName("quantumupgrade:goldplatedenergycrystal").setUnlocalizedName("goldplatedenergycrystal");

        vajraС = (new TileVajraC(Item.ToolMaterial.EMERALD)).setUnlocalizedName("vajra");
        GameRegistry.registerItem(vajraС,"vajra");
        vajraP = (new TileVajraP(Item.ToolMaterial.EMERALD)).setUnlocalizedName("vajrap");
        GameRegistry.registerItem(vajraP, "vajrap");
        vajraM = (new TileVajraM(Item.ToolMaterial.EMERALD)).setUnlocalizedName("vajram");
        GameRegistry.registerItem(vajraM,"vajram");

        upgrade = new ItemUpgrade();
        GameRegistry.registerItem(upgrade,"upgrade");
        tier1upgrade = new Item().setCreativeTab(QuantumUpgrade.tabQU).setTextureName("quantumupgrade:tier1upgrade").setUnlocalizedName("tier1upgrade");
        GameRegistry.registerItem(tier1upgrade,"tier1upgrade");
        tier2upgrade = new Item().setCreativeTab(QuantumUpgrade.tabQU).setTextureName("quantumupgrade:tier2upgrade").setUnlocalizedName("tier2upgrade");
        GameRegistry.registerItem(tier2upgrade,"tier2upgrade");
        coolingcore = new Item().setCreativeTab(QuantumUpgrade.tabQU).setTextureName("quantumupgrade:coolingcore").setUnlocalizedName("coolingcore");
        GameRegistry.registerItem(coolingcore,"coolingcore");

        magnetron = new Item().setCreativeTab(QuantumUpgrade.tabQU).setTextureName("quantumupgrade:magnetron").setUnlocalizedName("magnetron");
        GameRegistry.registerItem(magnetron,"magnetron");
        magnetron1 = new Item().setCreativeTab(QuantumUpgrade.tabQU).setTextureName("quantumupgrade:magnetron1").setUnlocalizedName("magnetron1");
        GameRegistry.registerItem(magnetron1,"magnetron1");
        magnetron2 = new Item().setCreativeTab(QuantumUpgrade.tabQU).setTextureName("quantumupgrade:magnetron2").setUnlocalizedName("magnetron2");
        GameRegistry.registerItem(magnetron2,"magnetron2");
        superconductor = new Item().setCreativeTab(QuantumUpgrade.tabQU).setTextureName("quantumupgrade:superconductor").setUnlocalizedName("superconductor");
        GameRegistry.registerItem(superconductor,"superconductor");
        superconductorcover = new Item().setCreativeTab(QuantumUpgrade.tabQU).setTextureName("quantumupgrade:superconductorcover").setUnlocalizedName("superconductorcover");
        GameRegistry.registerItem(superconductorcover,"superconductorcover");
        vajracore = new Item().setCreativeTab(QuantumUpgrade.tabQU).setTextureName("quantumupgrade:vajracore").setUnlocalizedName("vajracore");
        GameRegistry.registerItem(vajracore,"vajracore");
        vajracore1 = new Item().setCreativeTab(QuantumUpgrade.tabQU).setTextureName("quantumupgrade:vajracore1").setUnlocalizedName("vajracore1");
        GameRegistry.registerItem(vajracore1,"vajracore1");
        vajracore2 = new Item().setCreativeTab(QuantumUpgrade.tabQU).setTextureName("quantumupgrade:vajracore2").setUnlocalizedName("vajracore2");
        GameRegistry.registerItem(vajracore2,"vajracore2");
    }
}
