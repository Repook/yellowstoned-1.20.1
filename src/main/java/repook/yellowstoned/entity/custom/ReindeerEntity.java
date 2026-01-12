package repook.yellowstoned.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import repook.yellowstoned.entity.ModEntities;
import repook.yellowstoned.sound.ModSounds;

public class ReindeerEntity extends AnimalEntity {
    private int idleAnimationCooldown = 0;
    public final AnimationState idlingAnimationState = new AnimationState();

    public ReindeerEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }


    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.REINDEER.create(world);
    }
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.GLOW_LICHEN);
    }
    @Override
    public EntityDimensions getDimensions(EntityPose pose) {
        if (this.isBaby()) {
            return EntityDimensions.fixed(0.6F, 1.1F);
        }
        return EntityDimensions.fixed(1.2F, 1.0F);
    }

    @Override
    protected void onGrowUp() {
        this.calculateDimensions();
    }


    //goals, lower number = higher priority
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new EscapeDangerGoal(this, 2.0));
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(3, new TemptGoal(this, 1.1, Ingredient.ofItems(Items.GLOW_LICHEN), false));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1f));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 24.0F));
    }

    public static DefaultAttributeContainer.Builder createReindeerAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 17.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.20000000298023224f);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.ENTITY_REINDEER_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_REINDEER_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_REINDEER_DEATH;
    }
    //    private void updateAnimations(){
//        if (this.idleAnimationCooldown <= 0) {
//            this.idleAnimationCooldown = this.random.nextInt(40) + 80;
//            this.idlingAnimationState.start(this.age);
//        } else {
//            --this.idleAnimationCooldown;
//        }
//    }
}
