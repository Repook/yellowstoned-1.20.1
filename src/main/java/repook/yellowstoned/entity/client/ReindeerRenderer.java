package repook.yellowstoned.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import repook.yellowstoned.Yellowstoned;
import repook.yellowstoned.entity.custom.ReindeerEntity;
import repook.yellowstoned.entity.layer.ModModelLayers;

public class ReindeerRenderer extends MobEntityRenderer<ReindeerEntity,ReindeerModel<ReindeerEntity>> {
    private static final Identifier TEXTURE = new Identifier(Yellowstoned.MOD_ID,"textures/entity/reindeer.png");
    private static final Identifier RUDOLPH_TEXTURE = new Identifier(Yellowstoned.MOD_ID,"textures/entity/reindeer_rudolph.png");

    public ReindeerRenderer(EntityRendererFactory.Context context) {

        super(context, new ReindeerModel<>(context.getPart(ModModelLayers.REINDEER)), 0.4f);
    }

    @Override
    public Identifier getTexture(ReindeerEntity entity) {
        String string = Formatting.strip(entity.getName().getString());
        if ("Rudolph".equals(string)) {
            return RUDOLPH_TEXTURE;
        } else {
            return TEXTURE;
        }
    }
}
