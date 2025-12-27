package repook.yellowstoned.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import repook.yellowstoned.entity.custom.MooseEntity;

import java.util.Collections;

public class HatModel <T extends LivingEntity>extends BipedEntityModel<T> {
    private final ModelPart skib;
    private final ModelPart head;

    public HatModel(ModelPart root) {
        super(root);

        this.skib = root.getChild("head").getChild("skib");
        this.head = root.getChild("head");

    }


    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();

        ModelPartData root = modelData.getRoot();

        // REQUIRED vanilla parts (can be empty)
        root.addChild("head", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("hat", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("body", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.NONE);
        root.addChild("right_leg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(-0.1F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));
        root.addChild("left_leg", ModelPartBuilder.create().uv(0, 16).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(-0.1F)), ModelTransform.pivot(1.9F, 12.0F, 0.0F));
        // Hat attached to head
        root.getChild("head").addChild(
                "skib",
                ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(
                                -3.0F, -30.0F, -1.0F,
                                7.0F, 8.0F, 2.0F
                        ),
                 ModelTransform.pivot(0.0F, -12.0F, 0.0F));


        return TexturedModelData.of(modelData, 32, 32);
    }
}
