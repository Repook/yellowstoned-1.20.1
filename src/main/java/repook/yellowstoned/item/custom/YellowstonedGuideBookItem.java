package repook.yellowstoned.item.custom;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.render.entity.RabbitEntityRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Equipment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import repook.yellowstoned.client.hud.GuideBookScreen;
import repook.yellowstoned.api.PlayerVariableInterface;

import java.util.List;

public class YellowstonedGuideBookItem extends Item implements Equipment {
    public YellowstonedGuideBookItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) return TypedActionResult.success(user.getStackInHand(hand));
        MinecraftClient.getInstance().setScreen(new GuideBookScreen(user, user.getStackInHand(hand)));
        PlayerVariableInterface variableInterface = (PlayerVariableInterface) user;
        int value = variableInterface.yellowstoned$getMyValue();
        System.out.println(value);
        System.out.println(isOwner(user.getStackInHand(hand),user));
//        if (user.getStackInHand(hand).hasNbt()) {
//            System.out.println(user.getStackInHand(hand).getNbt().getString("yellowstoned.owner_name"));
//        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }

    boolean isOwner(ItemStack stack, PlayerEntity player) {
        if (!stack.hasNbt())return false;
        System.out.println("has nbt");
        NbtCompound nbt = stack.getNbt();
        if (!stack.getNbt().contains("yellowstoned.owner")) return false;

        return nbt.getString("yellowstoned.owner").equals(player.getGameProfile().getId().toString());
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        super.onCraft(stack, world, player);

        if (!world.isClient) {
            stack.getOrCreateNbt().putString("yellowstoned.owner", player.getUuid().toString());
            stack.getOrCreateNbt().putString("yellowstoned.owner_name", player.getName().getString());
        }
    }


    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.hasNbt()) {
            assert stack.getNbt() != null;
            if (stack.getNbt().contains("yellowstoned.owner_name")) {
                tooltip.add(Text.literal(stack.getNbt().getString("yellowstoned.owner_name"))
                        .formatted(Formatting.GRAY));
            }
        }
    }


    public static void unlockInBook(ItemStack book, String id) {
        NbtCompound main = book.getOrCreateNbt();

        // Make sure the book has our namespace
        NbtCompound ys = main.getCompound("yellowstoned");
        if (ys == null) ys = new NbtCompound();

        // Get or create the unlocked list
        NbtCompound unlocked = ys.getCompound("unlocked");
        if (unlocked == null) unlocked = new NbtCompound();

        unlocked.putBoolean(id, true);
        ys.put("unlocked", unlocked);

        main.put("yellowstoned", ys);
    }

    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.HEAD;
    }
}
