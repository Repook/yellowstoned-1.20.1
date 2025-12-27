package repook.yellowstoned.client.entity.piece;

import com.google.common.collect.Maps;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.Map;

public abstract class Piece {
    private static final Map<String, Identifier> ARMOR_TEXTURE_CACHE = Maps.newHashMap();

    public Piece() {

    }

    protected void setVisible(BipedEntityModel bipedModel, EquipmentSlot slot) {
        bipedModel.setVisible(false);
        switch (slot) {
            case HEAD -> {
                bipedModel.head.visible = true;
                bipedModel.hat.visible = true;
            }
            case CHEST -> {
                bipedModel.body.visible = true;
                bipedModel.rightArm.visible = true;
                bipedModel.leftArm.visible = true;
            }
            case LEGS -> {
                bipedModel.body.visible = true;
                bipedModel.rightLeg.visible = true;
                bipedModel.leftLeg.visible = true;
            }
            case FEET -> {
                bipedModel.rightLeg.visible = true;
                bipedModel.leftLeg.visible = true;
            }
        }
    }

//    private static final String MOD_ID = "yellowstoned";
//
//    private Identifier getTexture(ArmorItem item, boolean overlay) {
//        String path =
//                "textures/models/armor/"
//                        + item.getMaterial().getName()
//                        + "/"
//                        + this.texture
//                        + (overlay ? "_overlay" : "")
//                        + ".png";
//
//        System.out.println(MOD_ID + ":" + path);
//
//        return ARMOR_TEXTURE_CACHE.computeIfAbsent(
//                path,
//                p -> new Identifier(MOD_ID, p)
//        );
//    }
    private Identifier getTexture(ArmorItem item, boolean overlay) {
        String string = "yellowstoned:textures/models/armor/" + item.getMaterial().getName() + "/" + getTexture() + (overlay ? "_overlay" : "") + ".png";
        System.out.println(string);
        return ARMOR_TEXTURE_CACHE.computeIfAbsent(string, Identifier::new);
    }

    protected void renderParts(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, ItemStack itemStack, ArmorItem item, EntityModel model, float red, float green, float blue, boolean overlay) {
        RenderLayer renderLayer;
        if (isTranslucent()) {
            renderLayer = RenderLayer.getEntityTranslucent(getTexture(item, overlay));
        } else if (isGlowing()) {
            renderLayer = RenderLayer.getBeaconBeam(getTexture(item, overlay), false);
        } else {
            renderLayer = RenderLayer.getArmorCutoutNoCull(getTexture(item, overlay));
        }
        VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, renderLayer, false, hasGlint() | itemStack.hasGlint());
        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, red, green, blue, 1.0F);
    }

    public abstract <T extends LivingEntity, A extends BipedEntityModel<T>> void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, ItemStack itemStack, float tickDelta, EquipmentSlot armorSlot, A armorModel);

    private boolean translucent;
    private boolean glint;
    private boolean colored;
    private boolean glowing;

    private String texture;

    public Piece translucent() {
        this.translucent = true;
        return this;
    }

    public Piece glint() {
        this.glint = true;
        return this;
    }

    public Piece colored() {
        this.colored = true;
        return this;
    }

    public Piece glowing() {
        this.glowing = true;
        return this;
    }

    public Piece texture(String texture) {
        this.texture = texture;

        return this;
    }

    public boolean isTranslucent() {
        return translucent;
    }

    public boolean hasGlint() {
        return glint;
    }

    public boolean isColored() {
        return colored;
    }

    public boolean isGlowing() {
        return glowing;
    }

    public String getTexture() {
        return texture;
    }
}
