package repook.yellowstoned.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
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

    @Unique
    private int myCustomValue = 0;  // the value tied to the player

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    private void writeCustomData(NbtCompound nbt, CallbackInfo ci) {
        nbt.putInt("yellowstoned_myValue", myCustomValue);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    private void readCustomData(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("yellowstoned_myValue")) {
            myCustomValue = nbt.getInt("yellowstoned_myValue");
        }
    }

    // accessors
@Override
    public int yellowstoned$getMyValue() { return myCustomValue; }
@Override
    public void yellowstoned$setMyValue(int newVal) { myCustomValue = newVal; }
}
