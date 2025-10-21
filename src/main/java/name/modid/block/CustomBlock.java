package name.modid.block;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import xyz.nucleoid.packettweaker.PacketContext;

public class CustomBlock extends Block implements PolymerBlock {
    public CustomBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        // Те, що побачить клієнт без мода Polymer (fallback)
        return Blocks.STONE.getDefaultState();
    }
}
