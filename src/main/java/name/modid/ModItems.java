package name.modid;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import xyz.nucleoid.packettweaker.PacketContext;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ModItems {
    public static final String MOD_ID = "borukva-test";
    public static Item SCYTHE;

    public static void registerAll() {
        Identifier id = Identifier.of(MOD_ID, "scythe");
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);

        Item.Settings settings = new Item.Settings().maxCount(1).registryKey(key);

        SCYTHE = Registry.register(Registries.ITEM, id, new PolymerScythe(settings));
    }

    public static class PolymerScythe extends Item implements PolymerItem {
        private final Identifier model;

        public PolymerScythe(Settings settings) {
            super(settings);

            // --- dynamic model detection ---
            String modelPath = findModelPath();
            this.model = Identifier.of(MOD_ID, modelPath);
        }

        @Override
        public Item getPolymerItem(ItemStack stack, PacketContext context) {
            return Items.NETHERITE_SWORD; // fallback
        }

        @Override
        public Identifier getPolymerItemModel(ItemStack stack, PacketContext context) {
            return this.model;
        }

        private static String findModelPath() {
            Path modelItem = Paths.get("src/main/generated/assets/" + MOD_ID + "/models/item/" + "scythe" + ".json");
            Path itemRoot = Paths.get("src/main/generated/assets/" + MOD_ID + "/items/" + "scythe" + ".json");

            if (Files.exists(modelItem)) {
                return "item/" + "scythe";
            } else if (Files.exists(itemRoot)) {
                return "items/" + "scythe";
            } else {
                return "item/" + "scythe"; // fallback
            }
        }
    }
}
