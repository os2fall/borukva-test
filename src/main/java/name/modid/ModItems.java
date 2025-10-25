package name.modid;

import eu.pb4.factorytools.api.item.FactoryBlockItem;
import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;
import java.util.function.Function;

public class ModItems {
    public static Item SCYTHE = register("scythe", PolymerScythe::new);
    public static Item BORUKVA_BLOCK = register(ModBlocks.BORUKVA_BLOCK);

    public static void registerAll() {

    }

    public static <T extends Item> T register(String path, Function<Item.Settings, T> function) {
        var id = Identifier.of(BorukvaTest.MOD_ID, path);
        var item = function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, id)));
        Registry.register(Registries.ITEM, id, item);
        return item;
    }

    public static <E extends Block & PolymerBlock> BlockItem register(E block) {
        return register(block, (s) -> {});
    }
    public static <E extends Block & PolymerBlock> BlockItem register(E block, Consumer<Item.Settings> settingsConsumer) {
        var id = Registries.BLOCK.getId(block);
        BlockItem item;
        var settings = new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, id)).useBlockPrefixedTranslationKey();
        settingsConsumer.accept(settings);
        item = new FactoryBlockItem(block, settings);

        Registry.register(Registries.ITEM, id, item);
        return item;
    }
}
