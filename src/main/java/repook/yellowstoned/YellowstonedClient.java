package repook.yellowstoned;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Vec3d;
import repook.yellowstoned.api.PlayerVariableInterface;
import repook.yellowstoned.block.ModBlocks;
import repook.yellowstoned.client.entity.HorizontalHeadModel;
import repook.yellowstoned.client.entity.piece.ModelPiece;
import repook.yellowstoned.entity.ModEntities;
import repook.yellowstoned.entity.client.*;
import repook.yellowstoned.entity.custom.BeaverEntity;
import repook.yellowstoned.entity.custom.BisonEntity;
import repook.yellowstoned.entity.custom.MooseEntity;
import repook.yellowstoned.entity.custom.ReindeerEntity;
import repook.yellowstoned.entity.layer.ModModelLayers;
import repook.yellowstoned.item.ModItems;
import repook.yellowstoned.toast.NewAnimalToast;

import static repook.yellowstoned.MooseHeadressItemRegister.*;
import static repook.yellowstoned.networking.ModMessages.BEAVER_SYNC_PACKET;
import static repook.yellowstoned.networking.ModMessages.BEAVER_UNLOCK_PACKET;


public class YellowstonedClient implements ClientModInitializer {
    private boolean seenEntityBeaver = false;
    private boolean seenEntityBison = false;
    private boolean seenEntityMoose = false;
    private boolean seenEntityReindeer = false;





    @Override
    public void onInitializeClient() {

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BEAVER, BeaverModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.BEAVER, BeaverRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.REINDEER, ReindeerModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.REINDEER, ReindeerRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MOOSE, MooseModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MOOSE, MooseRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BISON, BisonModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.BISON, BisonRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.HAT, HatModel::getTexturedModelData);

        setupPieces();
        ArmorRenderer.register(new MooseHeadressRenderer(), MOOSE_HEADDRESS);

//        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.REINDEER_ANTLER_DISPLAY, RenderLayer.getTranslucent());
//
//        ClientPlayNetworking.registerGlobalReceiver(BEAVER_SYNC_PACKET,
//                (client, handler, buf, responseSender) -> {
//
//                    int value = buf.readInt();
//
//                    client.execute(() -> {
//                        PlayerVariableInterface vars =
//                                (PlayerVariableInterface) client.player;
//
//                        vars.yellowstoned$setBeaverUnlock(value);
//
//                        System.out.println("Client synced beaver unlock: " + value);
//                    });
//                }
//        );
//
//        ClientTickEvents.END_CLIENT_TICK.register(client -> {
//            if (client.player != null) {
//                calculateSpyglassRay(client.player);
//            }
//        });
    }

    private void calculateSpyglassRay(PlayerEntity player) {
        if (player == null) return;

        if (!player.isUsingItem() || !player.getActiveItem().isOf(Items.SPYGLASS)) return;

        Vec3d start = player.getCameraPosVec(1.0f);
        Vec3d direction = player.getRotationVec(1.0f);
        Vec3d end = start.add(direction.multiply(64));

        EntityHitResult hit = ProjectileUtil.raycast(
                player,
                start,
                end,
                player.getBoundingBox().stretch(direction.multiply(64)).expand(1.0),
                entity -> entity != player && entity.isAlive(),
                64 * 64
        );

        if (hit != null && hit.getEntity() instanceof LivingEntity living) {
            handleDiscovery(player, living);
        }
    }

    private void handleDiscovery(PlayerEntity user, LivingEntity entity) {

        if (entity instanceof BeaverEntity && !seenEntityBeaver) {
            seenEntityBeaver = true;
//            PlayerVariableInterface variableInterface = (PlayerVariableInterface) user;
//            variableInterface.yellowstoned$setBeaverUnlock(1);
//            if (variableInterface.yellowstoned$getBeaverUnlock() == 1)
//                System.out.println("beaver unlock 1");
            System.out.println("Sending beaver packet...");
            ClientPlayNetworking.send(BEAVER_UNLOCK_PACKET, PacketByteBufs.empty());

            //unlockAnimal(book, "beaver");
            presentToast(user, "beaver");
        }

        if (entity instanceof MooseEntity && !seenEntityMoose) {
            seenEntityMoose = true;
            //nlockAnimal(book, "moose");
            presentToast(user, "moose");
        }

        if (entity instanceof ReindeerEntity && !seenEntityReindeer) {
            seenEntityReindeer = true;
            //unlockAnimal(book, "reindeer");
            presentToast(user, "reindeer");
        }

        if (entity instanceof BisonEntity && !seenEntityBison) {
            seenEntityBison = true;
            //unlockAnimal(book, "bison");
            presentToast(user, "bison");
        }
    }

    private void presentToast(PlayerEntity user, String animalButton){
        MinecraftClient.getInstance().getToastManager().add(new NewAnimalToast(
                Text.literal("New Animal Discovered!"),
                Text.literal("Guide book updated"),
                new Identifier("yellowstoned", "textures/gui/" + animalButton + "_button.png")
        ));
    }
    public static void setupPieces() {

        MOOSE_HEADRESS_MATERIAL.head(new ModelPiece(new HorizontalHeadModel()).texture("horizontal"));

    }
}
