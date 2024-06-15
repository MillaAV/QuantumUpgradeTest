package millaav.quantumupgrade.common.info;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemBlockPhotonSP extends ItemBlock {
    public ItemBlockPhotonSP(Block b) {
        super(b);
    }

    public String getUnlocalizedName(ItemStack itemstack) {
        return itemstack != null ? this.field_150939_a.getUnlocalizedName() : this.field_150939_a.getUnlocalizedName();
    }

    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag) {
        if (!Keyboard.isKeyDown(42) && !Keyboard.isKeyDown(54)) {
            list.add(StatCollector.translateToLocal("supsolpans.all.PressShift"));
        } else {
            list.add(StatCollector.translateToLocal("PhotonSp-description"));
            list.add(StatCollector.translateToLocal("PhotonSP-GenerationDay"));
            list.add(StatCollector.translateToLocal("PhotonSP-GenerationNight"));
            list.add(StatCollector.translateToLocal("PhotonSP-Output"));
            list.add(StatCollector.translateToLocal("PhotonSP-Storage"));
        }

    }
}