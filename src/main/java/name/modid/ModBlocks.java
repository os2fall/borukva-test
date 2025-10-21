package name.modid;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import xyz.nucleoid.packettweaker.PacketContext;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;


public class ModBlocks {
    public static final String MOD_ID = "borukva-test";

    public static Block BORUKVA_BLOCK;
    public static Item BORUKVA_BLOCK_ITEM;

    public static void registerAll() {
        // === BLOCK ===
        Identifier id = Identifier.of(MOD_ID, "borukva_block");
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, id);

        AbstractBlock.Settings blockSettings = AbstractBlock.Settings.create()
                .registryKey(blockKey)
                .mapColor(MapColor.EMERALD_GREEN)
                .strength(2.0f, 3.0f);

        BORUKVA_BLOCK = new PolymerGreenBlock(blockSettings);
        Registry.register(Registries.BLOCK, blockKey, BORUKVA_BLOCK);

        // === BLOCK ITEM ===
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, id);
        Item.Settings itemSettings = new Item.Settings()
                .useBlockPrefixedTranslationKey()
                .registryKey(itemKey);

        BORUKVA_BLOCK_ITEM = new BlockItem(BORUKVA_BLOCK, itemSettings);
        Registry.register(Registries.ITEM, itemKey, BORUKVA_BLOCK_ITEM);
    }

    // --- Polymer Block Implementation ---
    public static class PolymerGreenBlock extends Block implements PolymerBlock {
        public PolymerGreenBlock(AbstractBlock.Settings settings) {
            super(settings);
        }

        @Override
        public BlockState getPolymerBlockState(BlockState state, PacketContext context) {
            // fallback для клієнтів без Polymer
            return Blocks.EMERALD_BLOCK.getDefaultState();
        }
    }
}
