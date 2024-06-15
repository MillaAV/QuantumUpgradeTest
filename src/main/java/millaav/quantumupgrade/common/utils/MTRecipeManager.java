package millaav.quantumupgrade.common.utils;

import com.google.common.collect.Lists;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import millaav.quantumupgrade.QuantumUpgrade;
import millaav.quantumupgrade.api.IMTRecipeManager;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MTRecipeManager implements IMTRecipeManager {
    public static List<millaav.quantumupgrade.common.utils.MTRecipeRecord> transformerRecipes = Lists.newArrayList();
    public static millaav.quantumupgrade.common.utils.MTRecipeManager instance = new millaav.quantumupgrade.common.utils.MTRecipeManager();
    public static ArrayList<String> defaultRecipeList = new ArrayList();
    private static Method getUniqueName_Item;
    private static Method getUniqueName_Block;
    public static boolean rawReflectionDone = false;

    public MTRecipeManager() {
    }

    public void addMTOreDict(String name, String output, int energy) {
        List<ItemStack> inputs = OreDictionary.getOres(name);
        List<ItemStack> outputs = OreDictionary.getOres(output);
        if (outputs.size() != 0) {
            if (inputs.size() != 0) {
                Iterator i$ = inputs.iterator();

                while(i$.hasNext()) {
                    ItemStack inputIS = (ItemStack)i$.next();
                    i$ = outputs.iterator();

                    while(i$.hasNext()) {
                        ItemStack outputIS = (ItemStack)i$.next();
                        this.addMTRecipe(inputIS.copy(), outputIS.copy(), energy);
                    }
                }

            }
        }
    }

    public void addMTOreDict(String input, ItemStack output, int energy) {
        List<ItemStack> inputs = OreDictionary.getOres(input);
        Iterator i$ = inputs.iterator();

        while(i$.hasNext()) {
            ItemStack inputIS = (ItemStack)i$.next();
            this.addMTRecipe(inputIS.copy(), output.copy(), energy);
        }

    }

    public void addMTOreDict(ItemStack input, String output, int energy) {
        List<ItemStack> outputs = OreDictionary.getOres(output);
        if (outputs.size() != 0) {
            Iterator i$ = outputs.iterator();

            while(i$.hasNext()) {
                ItemStack outputIS = (ItemStack)i$.next();
                this.addMTRecipe(input.copy(), outputIS.copy(), energy);
            }

        }
    }

    public void initRecipes() {
        transformerRecipes.clear();
        String configFilePath = QuantumUpgrade.configFileName.substring(0, QuantumUpgrade.configFileName.lastIndexOf(File.separatorChar) + 1);
        String tmpFileName = QuantumUpgrade.configFileName.substring(QuantumUpgrade.configFileName.lastIndexOf(File.separatorChar) + 1);
        String recipesFileName = tmpFileName.substring(0, tmpFileName.lastIndexOf("."));
        String recipesFileExt = tmpFileName.substring(tmpFileName.lastIndexOf("."));
        recipesFileName = recipesFileName + "_MTRecipes" + recipesFileExt;
        File file = new File(configFilePath, recipesFileName);
        transformerRecipes.clear();
        transformerRecipes.addAll(MTRecipeConfig.parse(file));
    }

    private static void lazyReflectionInit() {
        if (!rawReflectionDone) {
            try {
                getUniqueName_Item = GameData.class.getDeclaredMethod("getUniqueName", Item.class);
                getUniqueName_Block = GameData.class.getDeclaredMethod("getUniqueName", Block.class);
                getUniqueName_Item.setAccessible(true);
                getUniqueName_Block.setAccessible(true);
            } catch (Exception var1) {
                Exception e = var1;
                QuantumUpgrade.addLog("Reflection failed. This is a fatal error and not recoverable");
                throw new RuntimeException(e);
            }

            rawReflectionDone = true;
        }

    }

    public static millaav.quantumupgrade.common.utils.MTRecipeManager.RawItemData getItemData(ItemStack is) {
        lazyReflectionInit();

        try {
            Item i = is.getItem();
            if (i instanceof ItemBlock) {
                Block b = Block.getBlockFromItem(i);
                GameRegistry.UniqueIdentifier ui = (GameRegistry.UniqueIdentifier)getUniqueName_Block.invoke((Object)null, b);
                return new millaav.quantumupgrade.common.utils.MTRecipeManager.RawItemData(ui.modId, ui.name);
            } else {
                GameRegistry.UniqueIdentifier ui = (GameRegistry.UniqueIdentifier)getUniqueName_Item.invoke((Object)null, i);
                return new millaav.quantumupgrade.common.utils.MTRecipeManager.RawItemData(ui.modId, ui.name);
            }
        } catch (Throwable var4) {
            Throwable t = var4;
            QuantumUpgrade.addLog("Reflection failed. Weird error, report it.");
            t.printStackTrace();
            return null;
        }
    }

    public void addMTRecipe(ItemStack inputItem, ItemStack outputItem, int energyPerOperation) {
        millaav.quantumupgrade.common.utils.MTRecipeRecord recipeToAdd = new millaav.quantumupgrade.common.utils.MTRecipeRecord();
        recipeToAdd.inputStack = inputItem.copy();
        recipeToAdd.outputStack = outputItem.copy();
        recipeToAdd.energyPerOperation = energyPerOperation;
        transformerRecipes.add(recipeToAdd);
    }

    public static class RawItemData {
        public final String modId;
        public final String itemName;

        public RawItemData(String id, String name) {
            this.modId = id;
            this.itemName = name;
        }
    }
}