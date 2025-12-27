package repook.yellowstoned.recipe;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import repook.yellowstoned.Yellowstoned;
import repook.yellowstoned.recipe.custom.WoodSoupRecipe;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER,
                new Identifier(Yellowstoned.MOD_ID, "wood_soup_recipe"),
                new SpecialRecipeSerializer<>(WoodSoupRecipe::new));

    }
}
