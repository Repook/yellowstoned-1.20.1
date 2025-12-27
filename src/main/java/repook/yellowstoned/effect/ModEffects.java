package repook.yellowstoned.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import repook.yellowstoned.Yellowstoned;

public class ModEffects {
    public static final StatusEffect CHOPPED = registerStatusEffect("chopped",
            new ChoppedEffect(StatusEffectCategory.NEUTRAL, 0x7f410c));


    private static StatusEffect registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(Yellowstoned.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        Yellowstoned.LOGGER.info("Registering Mod Effects for " + Yellowstoned.MOD_ID);
    }
}
