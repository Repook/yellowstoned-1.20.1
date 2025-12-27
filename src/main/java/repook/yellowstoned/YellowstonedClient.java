package repook.yellowstoned;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import repook.yellowstoned.api.PlayerVariableInterface;
import repook.yellowstoned.client.entity.HorizontalHeadModel;
import repook.yellowstoned.client.entity.piece.ModelPiece;
import repook.yellowstoned.entity.ModEntities;
import repook.yellowstoned.entity.client.*;
import repook.yellowstoned.entity.custom.BeaverEntity;
import repook.yellowstoned.entity.layer.ModModelLayers;
import repook.yellowstoned.item.ModItems;
import repook.yellowstoned.toast.NewAnimalToast;
import static repook.yellowstoned.MooseHeadressItemRegister.*;

public class YellowstonedClient implements ClientModInitializer {
    private boolean seenEntity = false;

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


        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            if (client.player == null || seenEntity) return;

            PlayerEntity user = client.player;

            float tickDelta = 1.0f;

            Vec3d start = user.getCameraPosVec(tickDelta);
            Vec3d direction = user.getRotationVec(tickDelta);
            double maxDist = 64;

            Vec3d end = start.add(direction.multiply(maxDist));


                if (user.isUsingItem() && user.getActiveItem().isOf(Items.SPYGLASS)) {
                    EntityHitResult hit = ProjectileUtil.raycast(
                            user,
                            start,
                            end,
                            user.getBoundingBox().stretch(direction.multiply(maxDist)).expand(1.0),
                            (entity) -> entity != user && entity.isAlive(),
                            maxDist * maxDist
                    );
                    if (hit != null) {
                        if (hit.getEntity() instanceof BeaverEntity) {
                            seenEntity = true;
                            PlayerVariableInterface variableInterface = (PlayerVariableInterface) user;
                            variableInterface.yellowstoned$setMyValue(1);
                            int value = variableInterface.yellowstoned$getMyValue();
                            System.out.println(value);
                            MinecraftClient.getInstance().getToastManager().add(new NewAnimalToast(
                                    Text.literal("New Animal Discovered!"),
                                    Text.literal("Guide book updated"),
                                    new Identifier("yellowstoned", "textures/gui/beaver_button.png")
                            ));
                        }
                        System.out.println("Looking at: " + hit.getEntity());
                    }
                }
        });
    }
    public static void setupPieces() {

        MOOSE_HEADRESS_MATERIAL.head(new ModelPiece(new HorizontalHeadModel()).texture("horizontal"));
    }
}
