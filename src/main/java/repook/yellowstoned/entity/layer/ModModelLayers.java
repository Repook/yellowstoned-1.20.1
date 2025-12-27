package repook.yellowstoned.entity.layer;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import repook.yellowstoned.Yellowstoned;

public class ModModelLayers {
    public static final EntityModelLayer BEAVER =
            new EntityModelLayer(new Identifier(Yellowstoned.MOD_ID,"beaver"),"main");
    public static final EntityModelLayer REINDEER =
            new EntityModelLayer(new Identifier(Yellowstoned.MOD_ID,"reindeer"),"main");
    public static final EntityModelLayer MOOSE =
            new EntityModelLayer(new Identifier(Yellowstoned.MOD_ID,"moose"),"main");
    public static final EntityModelLayer BISON =
            new EntityModelLayer(new Identifier(Yellowstoned.MOD_ID,"bison"),"main");

    public static final EntityModelLayer HAT =
            new EntityModelLayer(new Identifier(Yellowstoned.MOD_ID,"hat"),"main");

}
