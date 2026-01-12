package repook.yellowstoned.client.entity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;

import java.util.Collections;

public abstract class DecoHeadModel extends DecoModel {
    public DecoHeadModel() {
        super();
    }

    abstract ModelPart getPart();

    public void copyFromModel(BipedEntityModel model, EquipmentSlot slot) {
        ModelPart part = getPart();
        getPart().copyTransform(model.head);
        // add your own rotation offsets
        part.pitch += 0.15F; // tilt forward
        part.yaw   += 0.0F;
        part.roll  += 0.0F;


        super.copyFromModel(model, slot);

    }

    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return Collections.singletonList(getPart());
    }
}
