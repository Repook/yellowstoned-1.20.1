package repook.yellowstoned.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.ConsumeItemCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.PlayerInteractedWithEntityCriterion;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import repook.yellowstoned.MooseHeadressItemRegister;
import repook.yellowstoned.Yellowstoned;
import repook.yellowstoned.item.ModItems;

import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        Advancement rootAdvancement = Advancement.Builder.create()
                .display(new AdvancementDisplay(new ItemStack(ModItems.YELLOWSTONED_GUIDE_BOOK),
                        Text.literal("Yellowstoned"), Text.literal("Discover the beauties of nature"),
                        new Identifier(Yellowstoned.MOD_ID, "textures/block/dirt.png"), AdvancementFrame.TASK,
                        true, true, false))
                .criterion("has_yellowstoned_guide_book", InventoryChangedCriterion.Conditions.items(ModItems.YELLOWSTONED_GUIDE_BOOK))
                .build(consumer, Yellowstoned.MOD_ID + ":yellowstoned");


//        Advancement woodSoup = Advancement.Builder.create()
//                .display(new AdvancementDisplay(new ItemStack(ModItems.WOOD_SOUP),
//                        Text.literal("Who Put The Pikachu In The Stew?"), Text.literal("obtain Wood Soup"),
//                        new Identifier(Yellowstoned.MOD_ID, "block/dirt"), AdvancementFrame.TASK,
//                        true, true, false))
//                .criterion("has_wood_soup", InventoryChangedCriterion.Conditions.items(ModItems.WOOD_SOUP))
//                .parent(rootAdvancement).build(consumer,Yellowstoned.MOD_ID + ":has_wood_soup");
//        Advancement pureProtein = Advancement.Builder.create()
//                .display(new AdvancementDisplay(new ItemStack(ModItems.RAW_BISON),
//                        Text.literal("Pure Protein"), Text.literal("glugluglugluglug"),
//                        new Identifier(Yellowstoned.MOD_ID, "block/dirt"), AdvancementFrame.TASK,
//                        true, true, false))
//                .criterion("eaten_raw_bison", ConsumeItemCriterion.Conditions.item(ModItems.RAW_BISON))
//                .parent(rootAdvancement).build(consumer,Yellowstoned.MOD_ID + ":eat_bison_meat");
    }

}
