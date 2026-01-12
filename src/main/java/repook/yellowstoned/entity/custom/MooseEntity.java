package repook.yellowstoned.entity.custom;

import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import repook.yellowstoned.entity.ModEntities;
import repook.yellowstoned.item.ModItems;
import repook.yellowstoned.sound.ModSounds;

import java.util.UUID;

public class MooseEntity extends AnimalEntity implements Angerable{
    private static final TrackedData<Integer> ANGER_TIME;
    private static final UniformIntProvider ANGER_TIME_RANGE;
    private static final TrackedData<Boolean> HAS_ANTLERS;
    private int idleAnimationCooldown = 0;
    public final AnimationState idlingAnimationState = new AnimationState();
    @Nullable
    private UUID angryAt;

    public MooseEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }



    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.MOOSE.create(world);
    }
    @Override
    public EntityDimensions getDimensions(EntityPose pose) {
        if (this.isBaby()) {
            return EntityDimensions.fixed(0.9F, 1.3F);
        }
        return EntityDimensions.fixed(1.F, 2.0F);
    }



    //goals, lower number = higher priority
    @Override
    protected void initGoals() {
        //this.goalSelector.add(1, new EscapeDangerGoal(this, 2.0));
        this.goalSelector.add(1,new RevengeGoal(this));

        this.goalSelector.add(1, new SwimGoal(this));

        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0));

        this.goalSelector.add(3, new TemptGoal(this, 1.1, Ingredient.ofItems(Items.FERN), false));

        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.5f, false));

        this.targetSelector.add(3, new ActiveTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::shouldAngerAt));

        this.goalSelector.add(4, new WanderAroundFarGoal(this,1f,0.001F));

        this.goalSelector.add(3, new LookAroundGoal(this));

        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 24.0F));

        this.targetSelector.add(2, new UniversalAngerGoal<>(this, false));

    }
    public static DefaultAttributeContainer.Builder createMooseAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 25.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 7)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 4);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        EntityData data = super.initialize(world, difficulty, spawnReason, entityData, entityNbt);

        // If this moose is an adult, make sure it has antlers
        if (!this.isBaby() && !this.hasAntlers()) {
            this.addAntlers();
        }

        return data;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ANGER_TIME, 0);
        this.dataTracker.startTracking(HAS_ANTLERS, false); // babies start antler-less
    }
    @Override
    public void tick() {
        super.tick();

        if (this.isBaby() && this.hasAntlers()) {
            this.removeAntlers();
        }
    }

    @Override
    public void onGrowUp() {
        super.onGrowUp();
        this.calculateDimensions();

        if (!this.hasAntlers()) {
            this.addAntlers();
        }
    }
    public boolean hasAntlers() {
        return (Boolean)this.dataTracker.get(HAS_ANTLERS);
    }

    public void addAntlers() {
        this.dataTracker.set(HAS_ANTLERS, true);
    }

    public void removeAntlers() {
        this.dataTracker.set(HAS_ANTLERS, false);

    }


    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        this.writeAngerToNbt(nbt);
        nbt.putBoolean("HasAntlers", this.hasAntlers());

    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.readAngerFromNbt(this.getWorld(), nbt);
        this.dataTracker.set(HAS_ANTLERS, nbt.getBoolean("HasAntlers"));
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.SEAGRASS) && this.hasAntlers()) {
            if (!this.getWorld().isClient) {

                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                }
                this.getWorld().playSoundFromEntity(null, this, SoundEvents.ENTITY_GENERIC_EAT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                int random = this.random.nextInt(5);
                System.out.println(random);
                if (random == 2) {
                    this.getWorld().playSoundFromEntity(null, this, SoundEvents.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    for (int i = 0; i < 2; i++) {
                        ItemEntity itemEntity = this.dropItem(ModItems.MOOSE_ANTLER, 1);
                        if (itemEntity != null) {
                            itemEntity.setVelocity(itemEntity.getVelocity().add((double) ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F), (double) (this.random.nextFloat() * 0.05F), (double) ((this.random.nextFloat() - this.random.nextFloat()) * 0.1F)));
                        }
                    }
                    removeAntlers();
                }
                else {
                    for(int i = 0; i < 7; ++i) {
                        double d = this.random.nextGaussian() * 0.02;
                        double e = this.random.nextGaussian() * 0.02;
                        double f = this.random.nextGaussian() * 0.02;
                        ((ServerWorld)this.getWorld()).spawnParticles(
                                ParticleTypes.SMOKE,
                                this.getParticleX(1.0),
                                this.getRandomBodyY() + 0.5,
                                this.getParticleZ(1.0),
                                7,
                                0.1, 0.1, 0.1,
                                0.02
                        );
                    }
                }
                    return ActionResult.SUCCESS;

            } else {
                return ActionResult.CONSUME;
            }
        } else {
            return super.interactMob(player, hand);
        }
    }


    public int getAngerTime() {
        return (Integer)this.dataTracker.get(ANGER_TIME);
    }

    public void setAngerTime(int angerTime) {
        this.dataTracker.set(ANGER_TIME, angerTime);
    }

    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }

    @Nullable
    public UUID getAngryAt() {
        return this.angryAt;
    }

    public void setAngryAt(@Nullable UUID angryAt) {
        this.angryAt = angryAt;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.ENTITY_MOOSE_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_MOOSE_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_MOOSE_DEATH;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.FERN);
    }

    static {
        HAS_ANTLERS = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
        ANGER_TIME = DataTracker.registerData(MooseEntity.class, TrackedDataHandlerRegistry.INTEGER);
        ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
    }
}
