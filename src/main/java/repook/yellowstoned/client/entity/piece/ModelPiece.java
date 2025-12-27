package repook.yellowstoned.client.entity.piece;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import repook.yellowstoned.client.entity.DecoModel;

public class ModelPiece extends Piece{
    private final DecoModel model;

    public ModelPiece(DecoModel model) {
        super();
        this.model = model;
    }

    @Override
    public <T extends LivingEntity, A extends BipedEntityModel<T>> void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, ItemStack itemStack, float tickDelta, EquipmentSlot armorSlot, A armorModel) {
        model.copyFromModel(armorModel, armorSlot);
        renderParts(matrices, vertexConsumers, light, itemStack, (ArmorItem)itemStack.getItem(), model, 1.0f, 1.0f, 1.0f, false);
    }
}
