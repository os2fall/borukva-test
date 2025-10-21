package name.modid.datagen;

import name.modid.ModBlocks;
import name.modid.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider{
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(ItemTags.SWORDS)
                .add(ModItems.SCYTHE);

        valueLookupBuilder(ItemTags.WEAPON_ENCHANTABLE)
                .add(ModItems.SCYTHE);

        valueLookupBuilder(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .add(ModItems.SCYTHE);
    }
}
