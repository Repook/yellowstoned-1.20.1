package repook.yellowstoned.recipe.custom;

import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import repook.yellowstoned.item.ModItems;
import repook.yellowstoned.recipe.ModRecipes;

import java.util.HashSet;
import java.util.Set;

public class WoodSoupRecipe extends SpecialCraftingRecipe {


    public WoodSoupRecipe(Identifier id, CraftingRecipeCategory category) {
        super(id, category);
    }

    @Override
    public boolean matches(RecipeInputInventory inventory, World world) {
        Set<Item> uniqueLogs = new HashSet<>();
        int logCount = 0;
        int bowlCount = 0;

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);

            if (!stack.isEmpty()) {

                // Check for logs
                if (stack.isIn(ItemTags.LOGS)) {
                    uniqueLogs.add(stack.getItem());
                    logCount++;
                    continue;
                }

                // Check for the bowl
                if (stack.getItem() == Items.BOWL) {
                    bowlCount++;
                    continue;
                }

                // If anything else appears, recipe fails
                return false;
            }
        }

        // REQUIREMENTS:
        //  - Exactly 6 logs
        //  - All 6 must be different
        //  - Exactly 1 bowl
        return logCount == 6 && uniqueLogs.size() == 6 && bowlCount == 1;
    }

    @Override
    public ItemStack craft(RecipeInputInventory inventory, DynamicRegistryManager registryManager) {
        return new ItemStack(ModItems.WOOD_SOUP);
    }

    @Override
    public boolean fits(int width, int height) {
        // allow any crafting grid size (shapeless-like)
        return width * height >= 6;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }


}
