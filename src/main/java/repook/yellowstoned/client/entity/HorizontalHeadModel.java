package repook.yellowstoned.client.entity;

import net.minecraft.client.model.*;

public class HorizontalHeadModel extends DecoHeadModel {
    private final ModelPart part;

    public HorizontalHeadModel() {
        super();

        ModelData modelData = new ModelData();

        modelData.getRoot().addChild("part", ModelPartBuilder.create().uv(5, 34).cuboid(-7.0F, -6.0F, -4.0F, 20.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -30.6F, 1.1F, -0.0436F, 0.0F, 0.0F));
        ModelPart model = TexturedModelData.of(modelData, 64, 64).createModel();
        part = model.getChild("part");
    }

    @Override
    ModelPart getPart() {
        return part;
    }
}
