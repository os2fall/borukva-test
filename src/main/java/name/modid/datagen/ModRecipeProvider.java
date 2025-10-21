package name.modid.datagen;

import name.modid.ModBlocks;
import name.modid.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
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

    @Override
    public String getName() {
        return "My recipies";
    }
}
