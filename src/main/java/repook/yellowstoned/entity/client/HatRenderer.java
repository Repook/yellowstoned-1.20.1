package repook.yellowstoned.entity.client;

import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.ArmorEntityModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import repook.yellowstoned.Yellowstoned;

public class HatRenderer implements ArmorRenderer {
    private final HatModel model; // my hat model
    private static final Identifier TEXTURE = new Identifier(Yellowstoned.MOD_ID,"textures/models/armor/hat.png");


    public HatRenderer(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, ItemStack itemStack, LivingEntity entity, EquipmentSlot equipmentSlot, int i, BipedEntityModel<LivingEntity> livingEntityBipedEntityModel) {
        this.model = new HatModel<>(HatModel.getTexturedModelData().createModel());
    }


    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, ItemStack itemStack, LivingEntity livingEntity, EquipmentSlot equipmentSlot, int i, BipedEntityModel<LivingEntity> bipedEntityModel) {
        model.setVisible(false);
        if (equipmentSlot.equals(EquipmentSlot.HEAD)) {
            System.out.println("Armor renderer called for slot: ");

            model.setVisible(true);
            model.getHead().copyTransform(bipedEntityModel.head); // hat following entity rotate
            // start scaling
            matrixStack.push();

            if (livingEntity.isBaby()) {
                matrixStack.scale(0.75F, 0.75F, 0.75F); // scaling down if it is a baby
                matrixStack.translate(0.0F, 0.9F, 0.0F); // move it down so the hat not floating
            } else matrixStack.scale(1.2F, 1.2F, 1.2F); // my model is small and I'm too lazy to remake it

            // render model, have to render before end scaling
            ArmorRenderer.renderPart(matrixStack, vertexConsumerProvider, i, itemStack, model, TEXTURE);
            // end scaling
            matrixStack.pop();
        }
    }
}



