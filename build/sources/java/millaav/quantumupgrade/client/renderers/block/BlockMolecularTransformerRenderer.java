package millaav.quantumupgrade.client.renderers.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import millaav.quantumupgrade.QuantumUpgrade;
import millaav.quantumupgrade.common.tiles.TileMolecularTransformer;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;

public class BlockMolecularTransformerRenderer implements ISimpleBlockRenderingHandler {
    public BlockMolecularTransformerRenderer() {
    }

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileMolecularTransformer(), 0.0, 0.0, 0.0, 0.0F);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    public int getRenderId() {
        return QuantumUpgrade.blockMolecularTransformerRenderID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }
}
