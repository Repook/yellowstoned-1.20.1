package repook.yellowstoned.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import repook.yellowstoned.MooseHeadressItemRegister;
import repook.yellowstoned.api.PlayerVariableInterface;
import repook.yellowstoned.effect.ModEffects;

@Mixin(PlayerEntity.class)
public class PlayerMixin implements PlayerVariableInterface {

    @Inject(method = "getBlockBreakingSpeed", at = @At("RETURN"), cancellable = true)
    private void addChopSpeed(BlockState state, CallbackInfoReturnable<Float> cir) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        // Does the player have your custom effect?
        if (player.hasStatusEffect(ModEffects.CHOPPED)) {

            // Is the block a log?
            if (state.isIn(BlockTags.LOGS)) {
                StatusEffectInstance effect = player.getStatusEffect(ModEffects.CHOPPED);
                int amplifier = effect.getAmplifier();
                float original = cir.getReturnValue();
                float bonus = 4.0f * amplifier; // Same as Haste I (rough example)

                cir.setReturnValue(original + bonus);
            }
        }
    }
    @Inject(method = "tick", at = @At("TAIL"))
    private void tick(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack itemStack = player.getEquippedStack(EquipmentSlot.HEAD);
        if (itemStack.isOf(MooseHeadressItemRegister.MOOSE_HEADDRESS)) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 1, false, false, true));
        }
    }

    @Unique
    private int beaverUnlock = 0;  // the value tied to the player

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    private void writeCustomData(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("yellowstoned_beaverUnlock", beaverUnlock);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    private void readCustomData(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("yellowstoned_beaverUnlock")) {
            beaverUnlock = nbt.getInt("yellowstoned_beaverUnlock");
        }
    }
//    @Unique
//    public void updateMooseHeaddress() {
//       PlayerEntity thisObject = (PlayerEntity) (Object) this;
//
//
//    }

    // accessors
@Override
    public int yellowstoned$getBeaverUnlock() { return beaverUnlock; }
@Override
    public void yellowstoned$setBeaverUnlock(int newVal) { beaverUnlock = newVal; }
}
