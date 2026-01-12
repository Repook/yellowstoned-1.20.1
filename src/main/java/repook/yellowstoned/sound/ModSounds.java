package repook.yellowstoned.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import repook.yellowstoned.Yellowstoned;

public class ModSounds {
    public static final SoundEvent ENTITY_MOOSE_DEATH = registerSoundEvent("entity_moose_death");
    public static final SoundEvent ENTITY_MOOSE_HURT = registerSoundEvent("entity_moose_hurt");
    public static final SoundEvent ENTITY_MOOSE_AMBIENT = registerSoundEvent("entity_moose_ambient");

    public static final SoundEvent ENTITY_BISON_AMBIENT = registerSoundEvent("entity_bison_ambient");
    public static final SoundEvent ENTITY_BISON_HURT = registerSoundEvent("entity_bison_hurt");
    public static final SoundEvent ENTITY_BISON_DEATH = registerSoundEvent("entity_bison_death");

    public static final SoundEvent ENTITY_BEAVER_AMBIENT = registerSoundEvent("entity_beaver_ambient");
    public static final SoundEvent ENTITY_BEAVER_HURT = registerSoundEvent("entity_beaver_hurt");
    public static final SoundEvent ENTITY_BEAVER_DEATH = registerSoundEvent("entity_beaver_death");

    public static final SoundEvent ENTITY_REINDEER_AMBIENT = registerSoundEvent("entity_reindeer_ambient");
    public static final SoundEvent ENTITY_REINDEER_HURT = registerSoundEvent("entity_reindeer_hurt");
    public static final SoundEvent ENTITY_REINDEER_DEATH = registerSoundEvent("entity_reindeer_death");


    private static SoundEvent registerSoundEvent(String name) {
        Identifier identifier = new Identifier(Yellowstoned.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static void registerSounds() {
        Yellowstoned.LOGGER.info("Registering Mod Sounds for " + Yellowstoned.MOD_ID);
    }
}
