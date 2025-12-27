package repook.yellowstoned.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import repook.yellowstoned.Yellowstoned;
import repook.yellowstoned.entity.custom.MooseEntity;
import repook.yellowstoned.entity.custom.ReindeerEntity;
import repook.yellowstoned.entity.layer.ModModelLayers;

public class MooseRenderer extends MobEntityRenderer<MooseEntity,MooseModel<MooseEntity>> {
    private static final Identifier TEXTURE = new Identifier(Yellowstoned.MOD_ID,"textures/entity/moose.png");

    public MooseRenderer(EntityRendererFactory.Context context) {
        super(context, new MooseModel<>(context.getPart(ModModelLayers.MOOSE)), 0.4f);
    }

    @Override
    public Identifier getTexture(MooseEntity entity) {
        return TEXTURE;
    }
}
