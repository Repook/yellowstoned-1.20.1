package repook.yellowstoned.entity.client;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.VillagerEntityRenderer;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import repook.yellowstoned.entity.custom.MooseEntity;
import repook.yellowstoned.entity.custom.ReindeerEntity;

public class MooseModel <T extends MooseEntity> extends AnimalModel<T> {
    private final ModelPart front_left_leg;
    private final ModelPart front_right_leg;
    private final ModelPart back_left_leg;
    private final ModelPart back_right_leg;
    private final ModelPart head;
    private final ModelPart rightantler;
    private final ModelPart leftantler;
    private final ModelPart left_ear;
    private final ModelPart right_ear;
    private final ModelPart body;
    public MooseModel(ModelPart root) {
        this.front_left_leg = root.getChild("front_left_leg");
        this.front_right_leg = root.getChild("front_right_leg");
        this.back_left_leg = root.getChild("back_left_leg");
        this.back_right_leg = root.getChild("back_right_leg");
        this.head = root.getChild("head");
        this.rightantler = this.head.getChild("rightantler");
        this.leftantler = this.head.getChild("leftantler");
        this.left_ear = this.head.getChild("left_ear");
        this.right_ear = this.head.getChild("right_ear");
        this.body = root.getChild("body");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData front_left_leg = modelPartData.addChild("front_left_leg", ModelPartBuilder.create().uv(0, 96).cuboid(-2.0F, -3.0F, -2.0F, 4.0F, 22.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 5.0F, -6.0F));

        ModelPartData front_right_leg = modelPartData.addChild("front_right_leg", ModelPartBuilder.create().uv(47, 96).cuboid(-2.0F, -3.0F, -2.0F, 4.0F, 22.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 5.0F, -6.0F));

        ModelPartData back_left_leg = modelPartData.addChild("back_left_leg", ModelPartBuilder.create().uv(16, 96).cuboid(-2.0F, -3.0F, -2.0F, 4.0F, 22.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 5.0F, 14.0F));

        ModelPartData back_right_leg = modelPartData.addChild("back_right_leg", ModelPartBuilder.create().uv(32, 96).cuboid(-2.0F, -3.0F, -2.0F, 4.0F, 22.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, 5.0F, 14.0F));

        ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -7.0F, -8.0F));

        ModelPartData cube_r1 = head.addChild("cube_r1", ModelPartBuilder.create().uv(50, 46).cuboid(0.0F, 1.0F, -1.0F, 0.0F, 12.0F, 11.0F, new Dilation(0.0F))
                .uv(50, 31).cuboid(-4.5F, 0.0F, -9.0F, 9.0F, 7.0F, 8.0F, new Dilation(0.0F))
                .uv(48, 69).cuboid(-2.5F, 7.0F, -7.0F, 5.0F, 8.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        ModelPartData rightantler = head.addChild("rightantler", ModelPartBuilder.create(), ModelTransform.of(4.0F, -5.0F, -6.0F, 0.2182F, 0.0F, -0.2618F));

        ModelPartData cube_r2 = rightantler.addChild("cube_r2", ModelPartBuilder.create().uv(72, 56).cuboid(9.4735F, -2.2657F, -14.2709F, 2.0F, 0.0F, 6.0F, new Dilation(0.0F))
                .uv(80, 29).cuboid(8.4735F, 5.7343F, -8.2709F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(72, 50).cuboid(16.4735F, -2.2657F, -14.2709F, 2.0F, 0.0F, 6.0F, new Dilation(0.0F))
                .uv(80, 20).cuboid(8.4735F, 5.7343F, -13.2709F, 0.0F, 2.0F, 5.0F, new Dilation(0.0F))
                .uv(48, 83).cuboid(18.4735F, -0.2657F, -11.2709F, 0.0F, 6.0F, 3.0F, new Dilation(0.0F))
                .uv(72, 46).cuboid(11.4735F, -2.2657F, -12.2709F, 5.0F, 0.0F, 4.0F, new Dilation(0.0F))
                .uv(76, 12).cuboid(18.4735F, -2.2657F, -14.2709F, 0.0F, 2.0F, 6.0F, new Dilation(0.0F))
                .uv(60, 23).cuboid(8.4735F, -2.2657F, -8.2709F, 10.0F, 8.0F, 0.0F, new Dilation(0.0F))
                .uv(72, 66).cuboid(3.4735F, 1.7343F, -8.2709F, 5.0F, 2.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 7.0F, 4.0F, -1.5627F, -0.0636F, 0.0229F));

        ModelPartData leftantler = head.addChild("leftantler", ModelPartBuilder.create(), ModelTransform.of(-4.0F, -4.0F, -6.0F, 0.2182F, 0.0F, 0.2618F));

        ModelPartData cube_r3 = leftantler.addChild("cube_r3", ModelPartBuilder.create().uv(80, 27).cuboid(-8.4735F, 1.7343F, -8.2709F, 5.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(76, 6).cuboid(-18.4735F, -2.2657F, -14.2709F, 2.0F, 0.0F, 6.0F, new Dilation(0.0F))
                .uv(82, 66).cuboid(-11.4735F, 5.7343F, -8.2709F, 3.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(76, 0).cuboid(-11.4735F, -2.2657F, -14.2709F, 2.0F, 0.0F, 6.0F, new Dilation(0.0F))
                .uv(82, 77).cuboid(-8.4735F, 5.7343F, -13.2709F, 0.0F, 2.0F, 5.0F, new Dilation(0.0F))
                .uv(54, 83).cuboid(-18.4735F, -0.2657F, -11.2709F, 0.0F, 6.0F, 3.0F, new Dilation(0.0F))
                .uv(72, 62).cuboid(-16.4735F, -2.2657F, -12.2709F, 5.0F, 0.0F, 4.0F, new Dilation(0.0F))
                .uv(70, 77).cuboid(-18.4735F, -2.2657F, -14.2709F, 0.0F, 2.0F, 6.0F, new Dilation(0.0F))
                .uv(70, 69).cuboid(-18.4735F, -2.2657F, -8.2709F, 10.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, 6.0F, 4.0F, -1.5627F, 0.0636F, -0.0229F));

        ModelPartData left_ear = head.addChild("left_ear", ModelPartBuilder.create(), ModelTransform.pivot(-2.5F, -4.0F, -3.0F));

        ModelPartData cube_r4 = left_ear.addChild("cube_r4", ModelPartBuilder.create().uv(17, 85).cuboid(-1.5F, -4.0F, -1.0F, 3.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.0F, 0.0F, 0.3491F, 0.7418F, 0.0F));

        ModelPartData right_ear = head.addChild("right_ear", ModelPartBuilder.create(), ModelTransform.pivot(2.5F, -4.0F, -3.0F));

        ModelPartData cube_r5 = right_ear.addChild("cube_r5", ModelPartBuilder.create().uv(17, 85).cuboid(-1.5F, -4.0F, -1.0F, 3.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.0F, 0.0F, 0.3491F, -0.7418F, 0.0F));

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-11.0F, -16.0F, -1.0F, 12.0F, 13.0F, 18.0F, new Dilation(0.0F))
                .uv(0, 31).cuboid(-12.0F, -21.0F, -12.0F, 14.0F, 20.0F, 11.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, 5.0F, 2.0F));
        return TexturedModelData.of(modelData, 128, 128);
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
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {

        if (this.child) {
            matrices.push();

            // Custom head render
            matrices.scale(0.65F, 0.65F, 0.65F);
            matrices.translate(0.0F, 1.2F, 0.1F);
            this.getHeadParts().forEach(p ->
                    p.render(matrices, vertices, light, overlay, red, green, blue, alpha)
            );

            matrices.pop();
            matrices.push();

            // Custom body render
            matrices.scale(0.55F, 0.55F, 0.55F);
            matrices.translate(0.0F, 1.2F, 0.0F);
            this.getBodyParts().forEach(p ->
                    p.render(matrices, vertices, light, overlay, red, green, blue, alpha)
            );

            matrices.pop();
            return;
        }
        super.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        boolean hasAntlers = entity.hasAntlers();
        this.leftantler.visible = hasAntlers;
        this.rightantler.visible = hasAntlers;


        this.head.pitch = headPitch * 0.017453292F;
        this.head.yaw = headYaw * 0.017453292F;




        //does leg anims
        this.front_left_leg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        this.front_right_leg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float)Math.PI) * 1.4F * limbDistance;
        this.back_left_leg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float)Math.PI) * 1.4F * limbDistance;
        this.back_right_leg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
    }
}
