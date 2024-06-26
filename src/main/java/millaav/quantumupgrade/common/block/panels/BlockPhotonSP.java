package millaav.quantumupgrade.common.block.panels;

import millaav.quantumupgrade.QuantumUpgrade;
import millaav.quantumupgrade.common.tiles.TilePhotonicSolarPanel;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockPhotonSP extends AbstractSSP {
    private IIcon[] icons;

    public BlockPhotonSP() {
        super(Material.rock);
        this.setBlockName("BlockPhotonicSP");
        this.setStepSound(soundTypeMetal);
        this.setHardness(3.0F);
        this.setCreativeTab(QuantumUpgrade.tabQU);
    }

    public TileEntity createNewTileEntity(World world, int meta) {
        switch (meta) {
            case 1:
                return new TilePhotonicSolarPanel();
            default:
                return new TilePhotonicSolarPanel();
        }
    }

    public void registerBlockIcons(IIconRegister reg) {
        this.icons = new IIcon[3];
        this.icons[0] = reg.registerIcon("quantumupgrade:phsp_side");
        this.icons[1] = reg.registerIcon("quantumupgrade:phsp_top");
        this.icons[2] = reg.registerIcon("quantumupgrade:phsp_bottom");
    }

    public IIcon getIcon(int side, int metadata) {
        switch (side) {
            case 0:
                return this.icons[2];
            case 1:
                return this.icons[1];
            case 2:
                if (metadata == 2) {
                    return this.icons[0];
                }

                return this.icons[0];
            case 3:
                return this.icons[0];
            case 4:
                return this.icons[0];
            case 5:
                return this.icons[0];
            default:
                return this.icons[1];
        }
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int s, float f1, float f2, float f3) {
        if (player.isSneaking()) {
            return false;
        } else if (world.isRemote) {
            return true;
        } else {
            TileEntity tileentity = world.getTileEntity(i, j, k);
            if (tileentity != null) {
                player.openGui(QuantumUpgrade.instance, 1, world, i, j, k);
            }

            return true;
        }
    }
}