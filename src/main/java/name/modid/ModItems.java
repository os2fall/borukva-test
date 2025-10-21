package name.modid;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import xyz.nucleoid.packettweaker.PacketContext; // для Polymer 0.13.13

public class ModItems {
    public static final String MOD_ID = "borukva-test";

    public static Item SCYTHE;

    public static void registerAll() {
        Identifier id = Identifier.of(MOD_ID, "scythe");
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, id);

        Item.Settings settings = new Item.Settings()
                .maxCount(1)
                .registryKey(key);

        SCYTHE = new PolymerScythe(settings);
        Registry.register(Registries.ITEM, key, SCYTHE);

        // Додаємо +9 додаткового урону (разом ≈10 = 5 сердець) при ударі цією косою
        AttackEntityCallback.EVENT.register((PlayerEntity player, World world, Hand hand, net.minecraft.entity.Entity target, net.minecraft.util.hit.EntityHitResult hit) -> {
            if (hand != Hand.MAIN_HAND) return ActionResult.PASS;
            ItemStack held = player.getMainHandStack();
            if (held.getItem() != SCYTHE) return ActionResult.PASS;
            if (!(target instanceof net.minecraft.entity.LivingEntity living)) return ActionResult.PASS;

            // Базовий удар гравця без модифікаторів ≈ 1.0f → додаємо ще 9.0f, щоб в сумі вийшло 10.0f (5 сердець)
            living.damage((ServerWorld) player.getWorld(), player.getDamageSources().playerAttack(player), 9.0f);
            return ActionResult.PASS; // даємо ванілі відпрацювати базовий удар
        });
    }

    // Простий Polymer-айтем з fallback на Netherite Sword для клієнтів без мода
    public static class PolymerScythe extends Item implements PolymerItem {
        public PolymerScythe(Settings settings) {
            super(settings);
        }

        // Сигнатура для Polymer 0.13.13 (через PacketTweaker)
        @Override
        public Item getPolymerItem(ItemStack stack, PacketContext context) {
            return Items.NETHERITE_SWORD;
        }

    }
}
