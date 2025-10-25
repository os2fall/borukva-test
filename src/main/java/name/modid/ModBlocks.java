package name.modid;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock;
import eu.pb4.polymer.core.api.item.PolymerBlockItem;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import xyz.nucleoid.packettweaker.PacketContext;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ModBlocks {
    public static PolymerGreenBlock BORUKVA_BLOCK = register("borukva_block", settings -> new PolymerGreenBlock(settings
            .mapColor(MapColor.EMERALD_GREEN).instrument(NoteBlockInstrument.BASEDRUM).strength(2.0f, 3.0f).nonOpaque()));

    public static void registerAll() {
    }

    public static <T extends Block> T register(String path, Function<AbstractBlock.Settings, T> function) {
        return register(path, AbstractBlock.Settings.create(), function);
    }

    public static <T extends Block, Y extends Block> T register(String path, Y copyFrom, BiFunction<AbstractBlock.Settings, Y, T> function) {
        return register(path, AbstractBlock.Settings.copy(copyFrom), (settings) -> function.apply(settings, copyFrom));
    }

    @NotNull
    public static <T extends Block> T register(String path, AbstractBlock.Settings settings, Function<AbstractBlock.Settings, T> function) {
        var id = Identifier.of(BorukvaTest.MOD_ID, path);
        var item = function.apply(settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, id)));
        if (item == null) {
            //noinspection DataFlowIssue
            return null;
        }
        return Registry.register(Registries.BLOCK, id, item);
    }
}
