package name.modid;

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;

public class BorukvaTest implements ModInitializer {
    @Override
    public void onInitialize() {
        // Робить ресурс-пак обов’язковим (сервер надсилає його клієнту)
        PolymerResourcePackUtils.markAsRequired();

        // Додає assets твого моду до серверного ресурс-паку
        PolymerResourcePackUtils.addModAssets("borukva-test");

        ModBlocks.registerAll();
        ModItems.registerAll();
    }
}
