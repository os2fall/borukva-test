package name.modid.datagen;

import name.modid.BorukvaTest;
import name.modid.ModBlocks;
import name.modid.ModItems;

import net.minecraft.data.recipe.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModRecipeGenerator extends RecipeGenerator {
    public final RegistryEntryLookup<Item> itemLookup;
    public ModRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
        itemLookup = registries.getOrThrow(RegistryKeys.ITEM);
    }

    @Override
    public void generate() { {
        createShaped(RecipeCategory.MISC, ModBlocks.BORUKVA_BLOCK)
                .pattern("ll")
                .pattern("ll")
                .pattern("ll")
                .input('l', ItemTags.LOGS) // 'l' means "any log"
                .group("multi_bench") // Put it in a group called "multi_bench" - groups are shown in one slot in the recipe book
                .criterion(hasItem(Items.CRAFTING_TABLE), conditionsFromItem(Items.CRAFTING_TABLE))
                .offerTo(exporter);

        createShaped(RecipeCategory.MISC, ModItems.SCYTHE)
                .pattern("NNN")
                .pattern(" SN")
                .pattern("S N")
                .input('N', Items.NETHERITE_INGOT)
                .input('S', Items.STICK)
                .criterion(hasItem(Items.NETHERITE_INGOT), conditionsFromItem(Items.NETHERITE_INGOT)) // ✅ теж додаємо
                .offerTo(exporter);
    }
    };
}