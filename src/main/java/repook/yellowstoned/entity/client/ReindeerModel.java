package repook.yellowstoned.entity.client;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.util.math.MathHelper;
import repook.yellowstoned.entity.custom.ReindeerEntity;

public class ReindeerModel <T extends ReindeerEntity> extends AnimalModel<T> {
    private final ModelPart body;
    private final ModelPart front_right_leg;
    private final ModelPart front_left_leg;
    private final ModelPart back_left_leg;
    private final ModelPart back_right_leg;
    private final ModelPart neck;
    private final ModelPart head;
    private final ModelPart tounge;
    private final ModelPart left_antler;
    private final ModelPart right_antler;
    private final ModelPart left_ear;
    private final ModelPart right_ear;
    public ReindeerModel(ModelPart root) {
        this.body = root.getChild("body");
        this.front_right_leg = root.getChild("front_right_leg");
        this.front_left_leg = root.getChild("front_left_leg");
        this.back_left_leg = root.getChild("back_left_leg");
        this.back_right_leg = root.getChild("back_right_leg");
        this.neck = root.getChild("neck");
        this.head = this.neck.getChild("head");
        this.tounge = this.head.getChild("tounge");
        this.left_antler = this.head.getChild("left_antler");
        this.right_antler = this.head.getChild("right_antler");
        this.left_ear = this.head.getChild("left_ear");
        this.right_ear = this.head.getChild("right_ear");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-5.0F, -10.0F, -8.0F, 10.0F, 10.0F, 24.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 14.0F, -3.0F));

        ModelPartData front_right_leg = modelPartData.addChild("front_right_leg", ModelPartBuilder.create().uv(36, 68).cuboid(-1.0F, 0.0F, -2.0F, 3.0F, 10.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 14.0F, -8.0F));

        ModelPartData front_left_leg = modelPartData.addChild("front_left_leg", ModelPartBuilder.create().uv(22, 52).cuboid(-1.0F, 0.0F, -2.0F, 3.0F, 10.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 14.0F, -8.0F));

        ModelPartData back_left_leg = modelPartData.addChild("back_left_leg", ModelPartBuilder.create().uv(48, 69).cuboid(-1.0F, 0.0F, -2.0F, 3.0F, 10.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, 14.0F, 11.0F));

        ModelPartData back_right_leg = modelPartData.addChild("back_right_leg", ModelPartBuilder.create().uv(60, 69).cuboid(-1.0F, 0.0F, -1.0F, 3.0F, 10.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 14.0F, 10.0F));

        ModelPartData neck = modelPartData.addChild("neck", ModelPartBuilder.create().uv(0, 34).cuboid(-4.0F, -12.0F, -5.0F, 8.0F, 9.0F, 9.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 7.0F, -6.0F));

        ModelPartData head = neck.addChild("head", ModelPartBuilder.create().uv(64, 34).cuboid(-2.0F, -3.0F, -5.0F, 4.0F, 4.0F, 6.0F, new Dilation(0.0F))
                .uv(34, 34).cuboid(-4.0F, -5.0F, 1.0F, 8.0F, 6.0F, 7.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-2.5F, -3.7F, -5.6F, 5.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -13.0F, -5.0F));

        ModelPartData tounge = head.addChild("tounge", ModelPartBuilder.create().uv(8, 73).cuboid(-1.0F, -3.0F, -4.0F, 2.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 3.0F, -1.0F));

        ModelPartData left_antler = head.addChild("left_antler", ModelPartBuilder.create().uv(56, 47).cuboid(-4.0F, -8.0F, 10.0F, 2.0F, 2.0F, 9.0F, new Dilation(0.0F))
                .uv(8, 77).cuboid(-4.0F, -13.0F, 17.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F))
                .uv(34, 47).cuboid(-3.0F, -14.0F, 8.0F, 0.0F, 10.0F, 11.0F, new Dilation(0.0F))
                .uv(68, 28).cuboid(-10.0F, -8.0F, 11.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 73).cuboid(-10.0F, -15.0F, 11.0F, 2.0F, 7.0F, 2.0F, new Dilation(0.0F))
                .uv(78, 51).cuboid(-4.0F, -10.0F, 8.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
                .uv(72, 78).cuboid(-4.0F, -6.0F, 11.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F))
                .uv(68, 12).cuboid(-10.0F, -16.0F, 12.0F, 7.0F, 12.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.0F, -8.0F));

        ModelPartData right_antler = head.addChild("right_antler", ModelPartBuilder.create().uv(68, 24).cuboid(-8.0F, -8.0F, 11.0F, 6.0F, 2.0F, 2.0F, new Dilation(0.0F))
                .uv(72, 69).cuboid(-4.0F, -15.0F, 11.0F, 2.0F, 7.0F, 2.0F, new Dilation(0.0F))
                .uv(56, 58).cuboid(-10.0F, -8.0F, 10.0F, 2.0F, 2.0F, 9.0F, new Dilation(0.0F))
                .uv(78, 44).cuboid(-10.0F, -13.0F, 17.0F, 2.0F, 5.0F, 2.0F, new Dilation(0.0F))
                .uv(78, 57).cuboid(-10.0F, -10.0F, 8.0F, 2.0F, 4.0F, 2.0F, new Dilation(0.0F))
                .uv(78, 63).cuboid(-10.0F, -6.0F, 11.0F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F))
                .uv(0, 52).cuboid(-9.0F, -14.0F, 8.0F, 0.0F, 10.0F, 11.0F, new Dilation(0.0F))
                .uv(22, 68).cuboid(-9.0F, -16.0F, 12.0F, 7.0F, 12.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(12.0F, -2.0F, -8.0F));

        ModelPartData left_ear = head.addChild("left_ear", ModelPartBuilder.create().uv(16, 77).cuboid(-2.0F, -2.0F, -1.0F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -5.0F, 7.0F));

        ModelPartData right_ear = head.addChild("right_ear", ModelPartBuilder.create().uv(22, 80).cuboid(0.0F, -2.0F, -1.0F, 2.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, -5.0F, 7.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of(this.neck);
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.body, this.back_right_leg, this.back_left_leg, this.front_right_leg, this.front_left_leg);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        //does head anims
        this.neck.pitch = headPitch * 0.017453292F;
        this.neck.yaw = headYaw * 0.017453292F;

        //does leg anims
        this.front_left_leg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
        this.front_right_leg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float)Math.PI) * 1.4F * limbDistance;
        this.back_left_leg.pitch = MathHelper.cos(limbAngle * 0.6662F + (float)Math.PI) * 1.4F * limbDistance;
        this.back_right_leg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;

    }
}

