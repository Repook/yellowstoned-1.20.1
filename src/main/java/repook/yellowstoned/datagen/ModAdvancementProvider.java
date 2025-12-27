package repook.yellowstoned.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.PlayerInteractedWithEntityCriterion;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
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
                        new Identifier(Yellowstoned.MOD_ID, "textures/item/yellowstoned_guide_book.png"), AdvancementFrame.TASK,
                        true, true, false))
                .criterion("has_yellowstoned_guide_book", InventoryChangedCriterion.Conditions.items(ModItems.YELLOWSTONED_GUIDE_BOOK))
                .build(consumer, Yellowstoned.MOD_ID + ":yellowstoned");

//        Advancement mooseSpotted = Advancement.Builder.create()
//                .display(new AdvancementDisplay(new ItemStack(ModItems.MOOSE_SPAWN_EGG),
//                        Text.literal("Moose"), Text.literal("A certainly dumb way to die"),
//                        new Identifier(Yellowstoned.MOD_ID, "item/moose_spawn_egg"), AdvancementFrame.TASK,
//                        true, true, false))
//                .criterion("spotted_moose", PlayerInteractedWithEntityCriterion.Conditions.create()
//                .parent(rootAdvancement)
//                .build(consumer, MCCourseMod.MOD_ID + ":metal_detector");
    }
}
