package repook.yellowstoned.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import repook.yellowstoned.Yellowstoned;
import repook.yellowstoned.entity.custom.BisonEntity;
import repook.yellowstoned.entity.custom.MooseEntity;
import repook.yellowstoned.entity.layer.ModModelLayers;

public class BisonRenderer extends MobEntityRenderer<BisonEntity,BisonModel<BisonEntity>>{

        private static final Identifier TEXTURE = new Identifier(Yellowstoned.MOD_ID,"textures/entity/bison.png");

        public BisonRenderer(EntityRendererFactory.Context context) {
            super(context, new BisonModel<>(context.getPart(ModModelLayers.BISON)), 0.4f);
        }

        @Override
        public Identifier getTexture(BisonEntity entity) {
            return TEXTURE;
        }

}
