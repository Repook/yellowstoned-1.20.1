package repook.yellowstoned.entity.client;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import repook.yellowstoned.entity.custom.BeaverEntity;

public class BeaverModel<T extends BeaverEntity> extends AnimalModel<T> {
    private final ModelPart front_right_leg;
    private final ModelPart front_left_leg;
    private final ModelPart tail;
    private final ModelPart back_left_leg;
    private final ModelPart back_right_leg;
    private final ModelPart body;
    public final ModelPart head;
    private final ModelPart ear;
    private final ModelPart ear2;
    public BeaverModel(ModelPart root) {
        this.front_right_leg = root.getChild("front_right_leg");
        this.front_left_leg = root.getChild("front_left_leg");
        this.back_left_leg = root.getChild("back_left_leg");
        this.back_right_leg = root.getChild("back_right_leg");
        this.body = root.getChild("body");
        this.tail = this.body.getChild("tail");
        this.head = root.getChild("head");
        this.ear = this.head.getChild("ear");
        this.ear2 = this.head.getChild("ear2");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData front_right_leg = modelPartData.addChild("front_right_leg", ModelPartBuilder.create().uv(68, 16).cuboid(-1.5F, 0.0F, -1.0F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 21.0F, -3.5F));

        ModelPartData front_left_leg = modelPartData.addChild("front_left_leg", ModelPartBuilder.create().uv(56, 16).cuboid(-1.5F, 0.0F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 21.0F, -3.0F));

        ModelPartData back_left_leg = modelPartData.addChild("back_left_leg", ModelPartBuilder.create().uv(72, 12).cuboid(-1.5F, 3.5F, -4.5F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(16, 62).cuboid(-1.5F, -0.5F, -2.5F, 3.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 18.5F, 10.0F));

        ModelPartData back_right_leg = modelPartData.addChild("back_right_leg", ModelPartBuilder.create().uv(0, 62).cuboid(-2.0F, 0.0F, -2.5F, 3.0F, 6.0F, 5.0F, new Dilation(0.0F))
                .uv(66, 22).cuboid(-2.0F, 4.0F, -4.5F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(5.5F, 18.0F, 10.0F));

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-5.5F, -12.0F, -14.5F, 11.0F, 9.0F, 17.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 10.0F));

        ModelPartData tail = body.addChild("tail", ModelPartBuilder.create().uv(40, 26).cuboid(-5.0F, -2.0F, 0.5F, 8.0F, 2.0F, 14.0F, new Dilation(0.0F)), ModelTransform.pivot(1.0F, -7.5F, 2.0F));

        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 26).cuboid(-4.25F, -3.5F, -12.0F, 8.0F, 8.0F, 12.0F, new Dilation(0.0F))
                .uv(56, 22).cuboid(-1.75F, 4.5F, -12.0F, 3.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.25F, 12.0F, -4.5F));

        ModelPartData ear = head.addChild("ear", ModelPartBuilder.create().uv(0, 73).cuboid(4.95F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
                .uv(8, 73).cuboid(4.95F, -4.0F, -3.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.2F, -1.5F, 1.0F));

        ModelPartData ear2 = head.addChild("ear2", ModelPartBuilder.create().uv(0, 79).cuboid(4.95F, -4.0F, -1.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
                .uv(8, 79).cuboid(4.95F, -4.0F, -3.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(-9.2F, -1.5F, 1.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }


    @Override
    public void animateModel(T entity, float limbAngle, float limbDistance, float tickDelta) {

//        this.head.setPivot(-1.0F, 16.5F, -3.0F);
//        this.head.yaw = 0.0F;
//        this.head.roll = entity.getHeadRoll(tickDelta);

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay,
                       float red, float green, float blue, float alpha) {
        if (this.child) {
            // Render big head
            matrices.push();
            matrices.scale(0.85F, 0.85F, 0.85F);   // head size
            matrices.translate(0.0F, 0.35F, 0.35F); // move head down
            this.head.render(matrices, vertices, light, overlay, red, green, blue, alpha);
            matrices.pop();

            // Render small body
            matrices.push();
            matrices.scale(0.5F, 0.5F, 0.5F); // body size
            matrices.translate(0.0F, 1.5F, 0.0F); // adjust height

            this.body.render(matrices, vertices, light, overlay, red, green, blue, alpha);
            this.back_left_leg.render(matrices, vertices, light, overlay, red, green, blue, alpha);
            this.back_right_leg.render(matrices, vertices, light, overlay, red, green, blue, alpha);
            this.front_left_leg.render(matrices, vertices, light, overlay, red, green, blue, alpha);
            this.front_right_leg.render(matrices, vertices, light, overlay, red, green, blue, alpha);

            matrices.pop();
        } else {
            // Normal adult render
            super.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        }
    }

    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of(this.head);
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.body, this.back_right_leg, this.back_left_leg, this.front_right_leg, this.front_left_leg);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        //does head anims
        this.head.pitch = headPitch * 0.017453292F;
        this.head.yaw = headYaw * 0.017453292F;

        //gpt tail sway
        this.tail.yaw = MathHelper.cos(animationProgress * 0.1F) * 0.5F;
        this.tail.pitch = -6;

        //does leg anims
        this.front_left_leg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        this.front_right_leg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float)Math.PI) * 1.4F * limbDistance;
        this.back_left_leg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float)Math.PI) * 1.4F * limbDistance;
        this.back_right_leg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
    }
}
