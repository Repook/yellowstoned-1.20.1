package repook.yellowstoned;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repook.yellowstoned.api.PlayerVariableInterface;
import repook.yellowstoned.block.ModBlocks;
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

import static repook.yellowstoned.networking.ModMessages.BEAVER_SYNC_PACKET;
import static repook.yellowstoned.networking.ModMessages.BEAVER_UNLOCK_PACKET;


public class Yellowstoned implements ModInitializer {
	public static final String MOD_ID = "yellowstoned";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
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
//		ServerPlayNetworking.registerGlobalReceiver(BEAVER_UNLOCK_PACKET,
//				(server, player, handler, buf, responseSender) ->
//						server.execute(() -> {
//
//							PlayerVariableInterface vars = (PlayerVariableInterface) player;
//							vars.yellowstoned$setBeaverUnlock(1);
//
//							PacketByteBuf syncBuf = PacketByteBufs.create();
//							syncBuf.writeInt(1);
//
//							ServerPlayNetworking.send(player, BEAVER_SYNC_PACKET, syncBuf);
//
//							System.out.println("Server set beaver unlock to 1");
//						})
//		);
//		ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
//			ServerPlayerEntity player = handler.player;
//			PlayerVariableInterface vars = (PlayerVariableInterface) player;
//
//			PacketByteBuf buf = PacketByteBufs.create();
//			System.out.println(vars.yellowstoned$getBeaverUnlock());
//			buf.writeInt(vars.yellowstoned$getBeaverUnlock());
//
//			ServerPlayNetworking.send(player, BEAVER_SYNC_PACKET, buf);
//		});


		LOGGER.info("Hello Fabric world!");
	}
}