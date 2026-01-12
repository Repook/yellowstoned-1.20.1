package repook.yellowstoned.entity.client;

// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports


import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import repook.yellowstoned.entity.custom.BisonEntity;
import repook.yellowstoned.entity.custom.MooseEntity;

public class BisonModel <T extends BisonEntity> extends AnimalModel<T> {
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart front_right_leg;
    private final ModelPart back_left_leg;
    private final ModelPart back_right_leg;
    private final ModelPart front_left_leg;
    private final ModelPart head;
    private final ModelPart bone2;
    private final ModelPart bone;
    public BisonModel(ModelPart root) {
        this.body = root.getChild("body");
        this.tail = root.getChild("tail");
        this.front_right_leg = root.getChild("front_right_leg");
        this.back_left_leg = root.getChild("back_left_leg");
        this.back_right_leg = root.getChild("back_right_leg");
        this.front_left_leg = root.getChild("front_left_leg");
        this.head = root.getChild("head");
        this.bone2 = this.head.getChild("bone2");
        this.bone = this.head.getChild("bone");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 42).cuboid(-7.0F, -32.0F, -14.0F, 14.0F, 20.0F, 18.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-9.0F, -37.0F, -30.0F, 18.0F, 26.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 26.0F, 14.0F));

        ModelPartData tail = modelPartData.addChild("tail", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.0F, 18.0F));

        ModelPartData cube_r1 = tail.addChild("cube_r1", ModelPartBuilder.create().uv(0, 80).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 0.0F, 7.0F, new Dilation(0.0F))
                .uv(40, 80).cuboid(-1.5F, 0.0F, 7.0F, 3.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -1.2654F, 0.0F, 0.0F));

        ModelPartData front_right_leg = modelPartData.addChild("front_right_leg", ModelPartBuilder.create().uv(68, 0).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, 15.0F, -10.0F));

        ModelPartData back_left_leg = modelPartData.addChild("back_left_leg", ModelPartBuilder.create().uv(64, 69).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 14.0F, 12.0F));

        ModelPartData back_right_leg = modelPartData.addChild("back_right_leg", ModelPartBuilder.create().uv(68, 28).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, 14.0F, 12.0F));

        ModelPartData front_left_leg = modelPartData.addChild("front_left_leg", ModelPartBuilder.create().uv(68, 14).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 14.0F, -10.0F));

        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(64, 42).cuboid(-5.0F, -8.1736F, -11.0152F, 10.0F, 16.0F, 11.0F, new Dilation(0.0F))
                .uv(0, 89).cuboid(-6.0F, -9.1736F, -12.0152F, 12.0F, 8.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 3.5F, -14.0F, -0.1745F, 0.0F, 0.0F));

        ModelPartData bone2 = head.addChild("bone2", ModelPartBuilder.create().uv(16, 80).cuboid(-1.5F, -3.0F, -2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.5F, -3.2344F, -4.8206F));

        ModelPartData bone = head.addChild("bone", ModelPartBuilder.create().uv(28, 80).cuboid(-1.5F, -3.0F, -2.0F, 3.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(6.5F, -3.2344F, -4.8206F));
        return TexturedModelData.of(modelData, 128, 128);
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        if (this.child) {
            matrices.push();

            // Custom head render
            matrices.scale(0.65F, 0.65F, 0.65F);
            matrices.translate(0.0F, 1.2F, 0.1F);
            this.getHeadParts().forEach(p ->
                    p.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha)
            );

            matrices.pop();
            matrices.push();

            // Custom body render
            matrices.scale(0.55F, 0.55F, 0.55F);
            matrices.translate(0.0F, 1.2F, 0.0F);
            this.getBodyParts().forEach(p ->
                    p.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha)
            );

            matrices.pop();
            return;
        }

        body.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        tail.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        front_right_leg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        back_left_leg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        back_right_leg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        front_left_leg.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
        head.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }

    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.body, this.back_right_leg, this.back_left_leg, this.front_right_leg, this.front_left_leg,this.tail);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        //does head anims
        this.head.pitch = headPitch * 0.017453292F;
        this.head.yaw = headYaw * 0.017453292F;

        //does leg anims
        this.front_left_leg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        this.front_right_leg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float)Math.PI) * 1.4F * limbDistance;
        this.back_left_leg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float)Math.PI) * 1.4F * limbDistance;
        this.back_right_leg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
    }
}
