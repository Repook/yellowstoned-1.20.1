package repook.yellowstoned.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import repook.yellowstoned.effect.ModEffects;

public class ModFoodComponents {
    public static final FoodComponent WOOD_SOUP = new FoodComponent.Builder().hunger(3).saturationModifier(0.6f)
            .statusEffect(new StatusEffectInstance(ModEffects.CHOPPED,600, 1), 1f).alwaysEdible().build();
    public static final FoodComponent RAW_BISON = new FoodComponent.Builder().hunger(4).saturationModifier(1.8f).build();
    public static final FoodComponent COOKED_BISON = new FoodComponent.Builder().hunger(10).saturationModifier(9.4f).build();
}