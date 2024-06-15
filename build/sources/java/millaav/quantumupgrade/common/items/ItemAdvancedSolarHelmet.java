package millaav.quantumupgrade.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.api.item.IMetalArmor;
import millaav.quantumupgrade.QuantumUpgrade;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

import java.util.List;

public class ItemAdvancedSolarHelmet extends ItemArmor implements IElectricItem, IMetalArmor, ISpecialArmor {
    private double maxCharge;
    private double transferLimit;
    private int tier;
    private int ticker;
    private int generating;
    private int genDay;
    private int genNight;
    private int solarType;
    private int energyPerDamage;
    private double damageAbsorptionRatio;
    private double baseAbsorptionRatio;
    private boolean initialized;
    private boolean sunIsUp;
    private boolean skyIsVisible;
    private boolean noSunWorld;
    private boolean wetBiome;

    public ItemAdvancedSolarHelmet(ItemArmor.ArmorMaterial par2EnumArmorMaterial, int par3, int par4, int htype) {
        super(par2EnumArmorMaterial, par3, par4);
        this.solarType = htype;
        this.maxCharge = 1000000.0;
        this.transferLimit = 3000.0;
        this.tier = 3;
        if (this.solarType == 1) {
            this.genDay = QuantumUpgrade.advGenDay;
            this.genNight = QuantumUpgrade.advGenNight;
            this.maxCharge = 1000000.0;
            this.transferLimit = 3000.0;
            this.tier = 3;
            this.energyPerDamage = 800;
            this.damageAbsorptionRatio = 0.9;
            this.baseAbsorptionRatio = 0.15;
        }

        if (this.solarType == 2) {
            this.genDay = QuantumUpgrade.hGenDay;
            this.genNight = QuantumUpgrade.hGenNight;
            this.maxCharge = 1.0E7;
            this.transferLimit = 10000.0;
            this.tier = 4;
            this.energyPerDamage = 2000;
            this.damageAbsorptionRatio = 1.0;
            this.baseAbsorptionRatio = 0.15;
        }

        if (this.solarType == 3) {
            this.genDay = QuantumUpgrade.uhGenDay;
            this.genNight = QuantumUpgrade.uhGenNight;
            this.maxCharge = 1.0E7;
            this.transferLimit = 10000.0;
            this.tier = 4;
            this.energyPerDamage = 2000;
            this.damageAbsorptionRatio = 1.0;
            this.baseAbsorptionRatio = 0.15;
        }

        this.setCreativeTab(QuantumUpgrade.tabQU);
        this.setMaxDamage(27);
    }

    public void onArmorTick(World worldObj, EntityPlayer player, ItemStack itemStack) {
        if (!worldObj.isRemote) {
            this.gainFuel(player);
            int j;
            if (this.solarType == 2 || this.solarType == 3) {
                j = player.getAir();
                if (ElectricItem.manager.canUse(itemStack, 1000.0) && j < 100) {
                    player.setAir(j + 200);
                    ElectricItem.manager.use(itemStack, 1000.0, (EntityPlayer)null);
                }
            }

            if (this.generating > 0) {
                int energyLeft = this.generating;

                double sentPacket;
                for(j = 0; j < player.inventory.armorInventory.length; ++j) {
                    if (energyLeft <= 0) {
                        return;
                    }

                    if (player.inventory.armorInventory[j] != null && player.inventory.armorInventory[j].getItem() instanceof IElectricItem) {
                        sentPacket = ElectricItem.manager.charge(player.inventory.armorInventory[j], (double)energyLeft, 4, false, false);
                        energyLeft = (int)((double)energyLeft - sentPacket);
                    }
                }

                for(j = 0; j < player.inventory.mainInventory.length; ++j) {
                    if (energyLeft <= 0) {
                        return;
                    }

                    if (player.inventory.mainInventory[j] != null && player.inventory.mainInventory[j].getItem() instanceof IElectricItem) {
                        sentPacket = ElectricItem.manager.charge(player.inventory.mainInventory[j], (double)energyLeft, 4, false, false);
                        energyLeft = (int)((double)energyLeft - sentPacket);
                    }
                }
            }

        }
    }

    public int gainFuel(EntityPlayer player) {
        if (this.ticker++ % tickRate() == 0) {
            this.updateVisibility(player);
        }

        if (this.sunIsUp && this.skyIsVisible) {
            this.generating = 0 + this.genDay;
            return this.generating;
        } else if (this.skyIsVisible) {
            this.generating = 0 + this.genNight;
            return this.generating;
        } else {
            this.generating = 0;
            return this.generating;
        }
    }

    public void updateVisibility(EntityPlayer player) {
        this.wetBiome = player.worldObj.getWorldChunkManager().getBiomeGenAt((int)player.posX, (int)player.posZ).getIntRainfall() > 0;
        this.noSunWorld = player.worldObj.provider.hasNoSky;
        Boolean rainWeather = this.wetBiome && (player.worldObj.isRaining() || player.worldObj.isThundering());
        if (player.worldObj.isDaytime() && !rainWeather) {
            this.sunIsUp = true;
        } else {
            this.sunIsUp = false;
        }

        if (player.worldObj.canBlockSeeTheSky((int)player.posX, (int)player.posY + 1, (int)player.posZ) && !this.noSunWorld) {
            this.skyIsVisible = true;
        } else {
            this.skyIsVisible = false;
        }

    }

    public boolean isMetalArmor(ItemStack itemstack, EntityPlayer player) {
        return true;
    }

    public ISpecialArmor.ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        if (source.isUnblockable()) {
            return new ISpecialArmor.ArmorProperties(0, 0.0, 0);
        } else {
            double absorptionRatio = this.getBaseAbsorptionRatio() * this.getDamageAbsorptionRatio();
            int energyPerDamage = this.getEnergyPerDamage();
            int damageLimit = (int)(energyPerDamage > 0 ? 25.0 * ElectricItem.manager.getCharge(armor) / (double)energyPerDamage : 0.0);
            return new ISpecialArmor.ArmorProperties(0, absorptionRatio, damageLimit);
        }
    }

    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return ElectricItem.manager.getCharge(armor) >= (double)this.getEnergyPerDamage() ? (int)Math.round(20.0 * this.getBaseAbsorptionRatio() * this.getDamageAbsorptionRatio()) : 0;
    }

    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
        ElectricItem.manager.discharge(stack, (double)(damage * this.getEnergyPerDamage()), Integer.MAX_VALUE, true, false, false);
    }

    public int getEnergyPerDamage() {
        return this.energyPerDamage;
    }

    public double getDamageAbsorptionRatio() {
        return this.damageAbsorptionRatio;
    }

    private double getBaseAbsorptionRatio() {
        return this.baseAbsorptionRatio;
    }

    public static int tickRate() {
        return 128;
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs var2, List var3) {
        ItemStack var4 = new ItemStack(this, 1);
        ElectricItem.manager.charge(var4, 2.147483647E9, Integer.MAX_VALUE, true, false);
        var3.add(var4);
        var3.add(new ItemStack(this, 1, this.getMaxDamage()));
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister IIconRegister) {
        if (this.solarType == 1) {
            this.itemIcon = IIconRegister.registerIcon("quantumupgrade:AdvSolarHelmet");
        }

        if (this.solarType == 2) {
            this.itemIcon = IIconRegister.registerIcon("quantumupgrade:HybridSolarHelmet");
        }

        if (this.solarType == 3) {
            this.itemIcon = IIconRegister.registerIcon("quantumupgrade:UltimateSolarHelmet");
        }

    }

    @SideOnly(Side.CLIENT)
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        if (this.solarType == 1) {
            return "quantumupgrade:textures/armor/advancedSolarHelmet.png";
        } else if (this.solarType == 2) {
            return "quantumupgrade:textures/armor/hybridSolarHelmet.png";
        } else {
            return this.solarType == 3 ? "quantumupgrade:textures/armor/ultimateSolarHelmet.png" : "";
        }
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack var1) {
        if (this.solarType == 1) {
            return EnumRarity.uncommon;
        } else {
            return this.solarType == 2 ? EnumRarity.rare : EnumRarity.epic;
        }
    }

    public boolean canProvideEnergy(ItemStack itemStack) {
        return false;
    }

    public Item getChargedItem(ItemStack itemStack) {
        return this;
    }

    public Item getEmptyItem(ItemStack itemStack) {
        return this;
    }

    public double getMaxCharge(ItemStack itemStack) {
        return this.maxCharge;
    }

    public int getTier(ItemStack itemStack) {
        return this.tier;
    }

    public double getTransferLimit(ItemStack itemStack) {
        return this.transferLimit;
    }
}
