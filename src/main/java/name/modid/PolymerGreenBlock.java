package name.modid;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import xyz.nucleoid.packettweaker.PacketContext;

public class PolymerGreenBlock extends Block implements PolymerTexturedBlock {
    private final BlockState polymerState;

    public PolymerGreenBlock(AbstractBlock.Settings settings) {
        super(settings);

        this.polymerState = PolymerBlockResourceUtils.requestBlock(
                BlockModelType.FULL_BLOCK,
                PolymerBlockModel.of(Identifier.of(BorukvaTest.MOD_ID, "block/borukva_block"))
        );
    }

    @Override
    public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
        return this.polymerState;
    }
}