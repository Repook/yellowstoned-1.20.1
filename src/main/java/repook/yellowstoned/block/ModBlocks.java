package repook.yellowstoned.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import repook.yellowstoned.Yellowstoned;
import repook.yellowstoned.block.custom.ReindeerAntlerDisplayBlock;

public class ModBlocks {


    public static final Block REINDEER_ANTLER_DISPLAY = registerBlock("reindeer_antler_display"
            ,new ReindeerAntlerDisplayBlock(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS).breakInstantly()));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Yellowstoned.MOD_ID, name),block);
    }

    private static Item registerBlockItem(String name, Block block){
        return Registry.register(Registries.ITEM, new Identifier(Yellowstoned.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks(){
        Yellowstoned.LOGGER.info("Registering Mod Blocks for" + Yellowstoned.MOD_ID);
    }
}
