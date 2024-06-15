package millaav.quantumupgrade.common.info;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;

import java.util.List;

public class ItemBlockAdmSP extends ItemBlock {
    public ItemBlockAdmSP(Block b) {
        super(b);
    }

    public String getUnlocalizedName(ItemStack itemstack) {
        return itemstack != null ? this.field_150939_a.getUnlocalizedName() : this.field_150939_a.getUnlocalizedName();
    }

    public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag) {
        if (!Keyboard.isKeyDown(42) && !Keyboard.isKeyDown(54)) {
            list.add(StatCollector.translateToLocal("supsolpans.all.PressShift"));
        } else {
            list.add(StatCollector.translateToLocal("AdmSP-GenerationDay"));
            list.add(StatCollector.translateToLocal("AdmSP-GenerationNight"));
            list.add(StatCollector.translateToLocal("AdmSP-Output"));
            list.add(StatCollector.translateToLocal("AdmSP-Storage"));
        }

    }
}
