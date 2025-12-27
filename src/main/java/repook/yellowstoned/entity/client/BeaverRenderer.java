package repook.yellowstoned.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import repook.yellowstoned.Yellowstoned;
import repook.yellowstoned.entity.custom.BeaverEntity;
import repook.yellowstoned.entity.layer.ModModelLayers;

public class BeaverRenderer extends MobEntityRenderer<BeaverEntity,BeaverModel<BeaverEntity>> {
    private static final Identifier TEXTURE = new Identifier(Yellowstoned.MOD_ID,"textures/entity/beaver.png");
    public BeaverRenderer(EntityRendererFactory.Context context) {
        super(context, new BeaverModel<>(context.getPart(ModModelLayers.BEAVER)), 0.4f);
        this.addFeature(new BeaverHeldItemRenderer(this, context.getHeldItemRenderer()));
    }


    @Override
    protected void scale(BeaverEntity entity, MatrixStack matrices, float amount) {
        float scale = 0.8F; // 50% size
        matrices.scale(scale, scale, scale);
        super.scale(entity, matrices, amount);
    }

    @Override
    public Identifier getTexture(BeaverEntity entity) {
        return TEXTURE;
    }
}