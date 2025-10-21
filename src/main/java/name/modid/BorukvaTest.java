package name.modid;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;

public class BorukvaTest implements ModInitializer {
    @Override
    public void onInitialize() {
        ModBlocks.registerAll();
        // BorukvaTest.onInitialize()
        ModItems.registerAll();

        net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
                .modifyEntriesEvent(net.minecraft.item.ItemGroups.COMBAT)
                .register(entries -> entries.add(ModItems.SCYTHE));

        // Додаємо блок
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
                .register(entries -> entries.add(ModBlocks.BORUKVA_BLOCK_ITEM));

        // Додаємо косу в розділ Combat
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT)
                .register(entries -> entries.add(ModItems.SCYTHE));
    }
}
