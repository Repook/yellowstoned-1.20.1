package repook.yellowstoned.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import repook.yellowstoned.Yellowstoned;

import repook.yellowstoned.client.entity.piece.ModelPiece;
import repook.yellowstoned.entity.ModEntities;
import repook.yellowstoned.item.custom.ModArmorMaterials;
import repook.yellowstoned.item.custom.YellowstonedGuideBookItem;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModItems {
    Map<String, Supplier<Item>> items = new HashMap<>();
    public static final Item BEAVER_SPAWN_EGG = registerItem("beaver_spawn_egg",
            new SpawnEggItem(ModEntities.BEAVER,0x77380a,0x150e18, new FabricItemSettings()));

    public static final Item REINDEER_SPAWN_EGG = registerItem("reindeer_spawn_egg",
            new SpawnEggItem(ModEntities.REINDEER,0x783825,0xf3e9d6, new FabricItemSettings()));
    public static final Item MOOSE_SPAWN_EGG = registerItem("moose_spawn_egg",
            new SpawnEggItem(ModEntities.MOOSE,0x61381c,0x200f0d, new FabricItemSettings()));
    public static final Item BISON_SPAWN_EGG = registerItem("bison_spawn_egg",
            new SpawnEggItem(ModEntities.BISON,0x4b362e,0x926130, new FabricItemSettings()));
    public static final Item WOOD_SOUP = registerItem("wood_soup",
            new StewItem(new FabricItemSettings().food(ModFoodComponents.WOOD_SOUP).maxCount(1)));
    public static final Item RAW_BISON = registerItem("raw_bison",
            new Item(new FabricItemSettings().food(ModFoodComponents.RAW_BISON)));

    public static final Item COOKED_BISON = registerItem("cooked_bison",
            new Item(new FabricItemSettings().food(ModFoodComponents.COOKED_BISON)));
    public static final Item MOOSE_ANTLER = registerItem("moose_antler",
            new Item(new FabricItemSettings()));

    public static final Item REINDEER_ANTLER = registerItem("reindeer_antler",
            new Item(new FabricItemSettings()));

    public static final Item YELLOWSTONED_GUIDE_BOOK = registerItem("yellowstoned_guide_book",
            new YellowstonedGuideBookItem(new FabricItemSettings().maxCount(1)));



    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(Yellowstoned.MOD_ID,name),item);
    }
    public static void registerModItems(){
        Yellowstoned.LOGGER.info("Registering Mod Items for"+ Yellowstoned.MOD_ID);
    }
}
