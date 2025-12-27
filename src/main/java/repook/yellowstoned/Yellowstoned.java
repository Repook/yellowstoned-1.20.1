package repook.yellowstoned;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repook.yellowstoned.effect.ModEffects;
import repook.yellowstoned.entity.ModEntities;
import repook.yellowstoned.entity.custom.BeaverEntity;
import repook.yellowstoned.entity.custom.BisonEntity;
import repook.yellowstoned.entity.custom.MooseEntity;
import repook.yellowstoned.entity.custom.ReindeerEntity;
import repook.yellowstoned.item.ModItemGroup;
import repook.yellowstoned.item.ModItems;
import repook.yellowstoned.recipe.ModRecipes;
import repook.yellowstoned.sound.ModSounds;
import repook.yellowstoned.world.gen.ModEntitySpawns;

public class Yellowstoned implements ModInitializer {
	public static final String MOD_ID = "yellowstoned";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		MooseHeadressItemRegister.registerMooseItems();
		ModItemGroup.registerItemGroup();
		ModSounds.registerSounds();
		FabricDefaultAttributeRegistry.register(ModEntities.BEAVER, BeaverEntity.createBeaverAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.REINDEER, ReindeerEntity.createReindeerAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.BISON, BisonEntity.createBisonAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.MOOSE, MooseEntity.createMooseAttributes());
		ModEntitySpawns.addSpawns();
		ModEffects.registerEffects();
		ModRecipes.registerRecipes();


		LOGGER.info("Hello Fabric world!");
	}
}