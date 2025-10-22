package name.modid;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import eu.pb4.polymer.core.api.item.PolymerBlockItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import xyz.nucleoid.packettweaker.PacketContext;

public class ModBlocks {
    public static final String MOD_ID = "borukva-test";

    public static Block BORUKVA_BLOCK;
    public static Item BORUKVA_BLOCK_ITEM;

    public static void registerAll() {
        Identifier id = Identifier.of(MOD_ID, "borukva_block");
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, id);

        AbstractBlock.Settings settings = AbstractBlock.Settings.create()
                .registryKey(blockKey)
                .mapColor(MapColor.EMERALD_GREEN)
                .strength(2.0f, 3.0f);

        // === Polymer Block ===
        BORUKVA_BLOCK = Registry.register(
                Registries.BLOCK,
                id,
                new PolymerGreenBlock(settings)
        );

        // === Polymer BlockItem ===
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, id);
        Item.Settings itemSettings = new Item.Settings()
                .registryKey(itemKey)
                .maxCount(64);

        BORUKVA_BLOCK_ITEM = Registry.register(
                Registries.ITEM,
                id,
                new PolymerBlockItem(
                        BORUKVA_BLOCK,
                        itemSettings,
                        ((PolymerGreenBlock) BORUKVA_BLOCK).getPolymerBlockState(
                                BORUKVA_BLOCK.getDefaultState(),
                                PacketContext.create()
                        ).getBlock().asItem() // fallback item
                )
        );
    }

    // --- Polymer Block Implementation ---
    public static class PolymerGreenBlock extends Block implements PolymerTexturedBlock {
        private final BlockState polymerState;

        public PolymerGreenBlock(AbstractBlock.Settings settings) {
            super(settings);
            // клієнт бачить Emerald Block, але сервер має власний блок
            this.polymerState = PolymerBlockResourceUtils.requestBlock(
                    BlockModelType.FULL_BLOCK,
                    PolymerBlockModel.of(Identifier.of(MOD_ID, "src/main/generated/assets/borukva-test/models/block/borukva_block.json"))
            );
        }

        @Override
        public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
            return this.polymerState;
        }
    }
}
