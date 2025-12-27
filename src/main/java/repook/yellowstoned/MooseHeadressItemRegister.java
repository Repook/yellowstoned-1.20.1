package repook.yellowstoned;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import repook.yellowstoned.client.entity.HorizontalHeadModel;
import repook.yellowstoned.client.entity.piece.ModelPiece;
import repook.yellowstoned.item.custom.ModArmorMaterials;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public interface MooseHeadressItemRegister {



    public ModArmorMaterials MOOSE_HEADRESS_MATERIAL = new ModArmorMaterials("moose")
            .protectionAmount(2, 5, 6, 2)
            .durabilityMultiplier(15)
            .repairIngredient(() -> Ingredient.ofItems(Items.LEATHER))
            .toughness(1.0f)
            .enchantability(5)
            .equipSound(SoundEvents.ITEM_ARMOR_EQUIP_IRON);


    public static final Item MOOSE_HEADDRESS = registerItem("moose_headdress",
            new ArmorItem(MOOSE_HEADRESS_MATERIAL, net.minecraft.item.ArmorItem.Type.HELMET, new Item.Settings()));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Yellowstoned.MOD_ID,name),item);
    }

    public static void registerMooseItems(){
        Yellowstoned.LOGGER.info("Registering Moose Headress Items for"+ Yellowstoned.MOD_ID);
    }
}
