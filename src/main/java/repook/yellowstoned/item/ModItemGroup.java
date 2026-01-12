package repook.yellowstoned.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import repook.yellowstoned.MooseHeadressItemRegister;
import repook.yellowstoned.Yellowstoned;
import repook.yellowstoned.block.ModBlocks;

public class ModItemGroup {
    public static final ItemGroup YELLOWSTONED_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Yellowstoned.MOD_ID, "yellowstoned"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.yellowstoned_group"))
                    .icon(() -> new ItemStack(ModItems.YELLOWSTONED_GUIDE_BOOK)).entries((displayContext, entries) -> {
                        entries.add(ModItems.YELLOWSTONED_GUIDE_BOOK);
                        entries.add(MooseHeadressItemRegister.MOOSE_HEADDRESS);
                        entries.add(ModItems.MOOSE_ANTLER);
                        entries.add(ModItems.REINDEER_ANTLER);
                        entries.add(ModBlocks.REINDEER_ANTLER_DISPLAY);
                        entries.add(ModItems.RAW_BISON);
                        entries.add(ModItems.COOKED_BISON);
                        entries.add(ModItems.WOOD_SOUP);


                        entries.add(ModItems.BEAVER_SPAWN_EGG);
                        entries.add(ModItems.BISON_SPAWN_EGG);
                        entries.add(ModItems.MOOSE_SPAWN_EGG);
                        entries.add(ModItems.REINDEER_SPAWN_EGG);


                    }).build());


    public static void registerItemGroup() {

    }
}
