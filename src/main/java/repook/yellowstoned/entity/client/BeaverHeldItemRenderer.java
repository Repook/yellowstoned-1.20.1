package repook.yellowstoned.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;
import repook.yellowstoned.entity.custom.BeaverEntity;

@Environment(EnvType.CLIENT)
public class BeaverHeldItemRenderer extends FeatureRenderer<BeaverEntity, BeaverModel<BeaverEntity>> {
    private final HeldItemRenderer heldItemRenderer;

    public BeaverHeldItemRenderer(FeatureRendererContext<BeaverEntity, BeaverModel<BeaverEntity>> context, HeldItemRenderer heldItemRenderer) {
        super(context);
        this.heldItemRenderer = heldItemRenderer;
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, BeaverEntity beaverEntity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        boolean bl2 = beaverEntity.isBaby();
        matrixStack.push();
        float m;
        if (bl2) {
            m = 0.75F;
            matrixStack.scale(0.75F, 0.75F, 0.75F);
            matrixStack.translate(0.0F, 0.5F, 0.209375F);
        }

        matrixStack.translate((this.getContextModel()).head.pivotX / 16.0F, this.getContextModel().head.pivotY / 16.0F, this.getContextModel().head.pivotZ / 16.0F);
        m = beaverEntity.getHeadRoll(tickDelta);
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotation(m));
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(headYaw));
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(headPitch));
        if (beaverEntity.isBaby()) {
            matrixStack.translate(0.06F, 0.26F, -0.5F);
        }
        else {
            matrixStack.translate(-0.01F, 0.3F, -0.9F);
        }

        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0F));


        ItemStack itemStack = beaverEntity.getEquippedStack(EquipmentSlot.MAINHAND);
        this.heldItemRenderer.renderItem(beaverEntity, itemStack, ModelTransformationMode.GROUND, false, matrixStack, vertexConsumerProvider, light);
        matrixStack.pop();

    }
}
