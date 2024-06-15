package millaav.quantumupgrade.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import millaav.quantumupgrade.QuantumUpgrade;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class ItemEnderQuantumComponent extends Item {
    private IIcon[] IIconsList = new IIcon[1];

    public ItemEnderQuantumComponent() {
        this.setCreativeTab(QuantumUpgrade.tabQU);
        this.setUnlocalizedName("Ender-QuantumComponent");
        this.setMaxStackSize(64);
        this.setTextureName("quantumupgrade:enderquantumcomponent");
    }

    @SideOnly(Side.CLIENT)
    public void registericons(IIconRegister IIconRegister) {
        this.IIconsList[0] = IIconRegister.registerIcon("quantumupgrade:enderquantumcomponent");
    }
}
