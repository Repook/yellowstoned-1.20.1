package repook.yellowstoned.client.entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.PiglinHeadEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;

public class HorizontalHeadModel extends DecoHeadModel {
    private final ModelPart part;

    public HorizontalHeadModel() {
        super();

        ModelData modelData = new ModelData();

//        modelData.getRoot().addChild("part",
//                ModelPartBuilder.create()
//                        .cuboid(-10.0F, -17.0F, 0.0F, 20.0F, 12.0F, 0.0F),
//                ModelTransform.NONE);

        modelData.getRoot().addChild("part", ModelPartBuilder.create().cuboid
                (-10.0F, -12.0F, -2.0F,
                20.0F, 6.0F, 5.0F).uv(0, 11).cuboid(-4.0F, -9.0F, -0.5F, 8.0F, 3.0F, 1.0F),ModelTransform.NONE);


        ModelPart model = TexturedModelData.of(modelData, 64, 64).createModel();
        part = model.getChild("part");
    }



    @Override
    ModelPart getPart() {
        return part;
    }
}
