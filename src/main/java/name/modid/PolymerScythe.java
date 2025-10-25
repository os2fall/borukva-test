package name.modid;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import xyz.nucleoid.packettweaker.PacketContext;

public class PolymerScythe extends Item implements PolymerItem {
    private final Identifier model;

    public PolymerScythe(Settings settings) {
        super(settings);

        this.model = Identifier.of(BorukvaTest.MOD_ID, "scythe");
    }

    @Override
    public Item getPolymerItem(ItemStack stack, PacketContext context) {
        return Items.NETHERITE_SWORD;
    }

    @Override
    public Identifier getPolymerItemModel(ItemStack stack, PacketContext context) {
        return this.model;
    }
}
