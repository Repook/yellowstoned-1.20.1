package repook.yellowstoned.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import repook.yellowstoned.effect.ModEffects;
import repook.yellowstoned.entity.ModEntities;
import repook.yellowstoned.item.ModItems;
import repook.yellowstoned.sound.ModSounds;

import java.util.*;
import java.util.function.Predicate;

public class BeaverEntity extends AnimalEntity {
    private float headRollProgress;
    private float lastHeadRollProgress;
    public boolean chopping;
    public int chopTime;
    static final Predicate<ItemEntity> PICKABLE_DROP_FILTER;

    public BeaverEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.setCanPickUpLoot(true);
    }


    //chopping logic
    public void setChopping(boolean chopping) {
        this.chopping = chopping;
    }
    public boolean isChopping() {
        return this.chopping;
    }

    //need to implement, also implement in model and renderer
    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
            return ModEntities.BEAVER.create(world);
    }
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(ItemTags.LOGS);
    }

    //goals, lower number = higher priority
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new TreeChopGoal(this));
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(3, new TemptGoal(this, 1.1, Ingredient.fromTag(ItemTags.LOGS), false));
        this.goalSelector.add(2, new EscapeDangerGoal(this, 2.0));
        this.goalSelector.add(4, new BeaverEntity.PickupItemGoal());
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 1f));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 24.0F));

    }



    public static DefaultAttributeContainer.Builder createBeaverAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.20000000298023224f);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.ENTITY_BEAVER_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ENTITY_BEAVER_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ENTITY_BEAVER_DEATH;
    }

    //head calc stuff WORKING NOW!
    @Override
    public int getMaxHeadRotation() {
        return super.getMaxHeadRotation();
    }
    public float getHeadRoll(float tickDelta) {
        return MathHelper.lerp(tickDelta, this.lastHeadRollProgress, this.headRollProgress) * 0.11F * 3.1415927F;
    }

    //stop choppin
    @Override
    public void tick() {
        super.tick();
        if (chopping) {
            if (chopTime-- <= 0) {
                setChopping(false);
            }
        }
        if (this.hasStatusEffect(ModEffects.CHOPPED) && !chopping){
            setChopping(true);
            chopTime = this.getStatusEffect(ModEffects.CHOPPED).getDuration();
        }
    }

    @Override
    public boolean canPickupItem(ItemStack stack) {
        ItemStack itemStack = this.getEquippedStack(EquipmentSlot.MAINHAND);
        return itemStack.isEmpty();
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(ModItems.WOOD_SOUP)) {
            this.getWorld().playSound(
                    null,
                    this.getBlockPos(),
                    SoundEvents.ENTITY_GENERIC_EAT,
                    SoundCategory.PLAYERS,
                    1.0F,
                    1.0F
            );
            itemStack.decrement(1);
            this.setStatusEffect(new StatusEffectInstance(ModEffects.CHOPPED,600,1),player);
            // Swing arm
            player.swingHand(hand);

//            setChopping(true);
//            chopTime = 20 * 300; // Chop for 300 seconds / 5mins
//            //player.sendMessage(Text.literal("Mob will now chop trees!"), true);
            return ActionResult.CONSUME;
        }
        return super.interactMob(player, hand);
    }

    static{
        PICKABLE_DROP_FILTER = (item) -> {
            return !item.cannotPickup() && item.isAlive();
        };
    }


    //the code for beavers to seek out dropped item, implement item filter later
    class PickupItemGoal extends Goal {
        public PickupItemGoal() {
            this.setControls(EnumSet.of(Control.MOVE));
        }

        public boolean canStart() {
            if (!BeaverEntity.this.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty()) {
                return false;
            } else {
                if (BeaverEntity.this.getRandom().nextInt(toGoalTicks(10)) != 0) {
                    return false;
                } else {
                    List<ItemEntity> list = BeaverEntity.this.getWorld().getEntitiesByClass(ItemEntity.class, BeaverEntity.this.getBoundingBox().expand(8.0, 8.0, 8.0), BeaverEntity.PICKABLE_DROP_FILTER);
                    return !list.isEmpty() && BeaverEntity.this.getEquippedStack(EquipmentSlot.MAINHAND).isEmpty();
                }
            }
        }

        public void tick() {
            List<ItemEntity> list = BeaverEntity.this.getWorld().getEntitiesByClass(ItemEntity.class, BeaverEntity.this.getBoundingBox().expand(8.0, 8.0, 8.0), BeaverEntity.PICKABLE_DROP_FILTER);
            ItemStack itemStack = BeaverEntity.this.getEquippedStack(EquipmentSlot.MAINHAND);
            if (itemStack.isEmpty() && !list.isEmpty()) {
                BeaverEntity.this.getNavigation().startMovingTo((Entity)list.get(0), 0.5);
            }

        }

        public void start() {
            List<ItemEntity> list = BeaverEntity.this.getWorld().getEntitiesByClass(ItemEntity.class, BeaverEntity.this.getBoundingBox().expand(8.0, 8.0, 8.0), BeaverEntity.PICKABLE_DROP_FILTER);
            if (!list.isEmpty()) {
                BeaverEntity.this.getNavigation().startMovingTo(list.get(0), 0.5);
            }

        }
    }

    public class TreeChopGoal extends Goal {
        private final BeaverEntity mob;
        private BlockPos targetTree;
        private int breakingTicks;

        public TreeChopGoal(BeaverEntity mob) {
            this.mob = mob;
        }

        @Override
        public boolean canStart() {
            // Only start if mob has the CHOPPED effect
            return mob.hasStatusEffect(ModEffects.CHOPPED) && isChopping();
        }

        @Override
        public boolean shouldContinue() {
            // Only continue if mob still has the effect
            return mob.hasStatusEffect(ModEffects.CHOPPED) && targetTree != null && isChopping();
        }

        @Override
        public void start() {
            findTree();
            breakingTicks = 0;
        }

        @Override
        public void tick() {
            if (targetTree == null) return;

            if (mob.getBlockPos().isWithinDistance(targetTree, 2.5)) {
                breakingTicks++;

                if (!mob.getWorld().isClient) {
                    int stage = Math.min(breakingTicks * 10 / 40, 9); // from 0 to 9
                    ((ServerWorld) mob.getWorld()).setBlockBreakingInfo(
                            mob.getId(), // unique entity ID
                            targetTree, stage
                    );
                }

                if (breakingTicks >= 40) {
                    if (!mob.getWorld().isClient) {
                        World world = mob.getWorld();
                        BlockState brokenState = world.getBlockState(targetTree);

                        world.breakBlock(targetTree, true);

                        // Play the block breaking sound
                        world.playSound(
                                null,                             // null = play to all nearby players
                                targetTree,                       // sound position
                                SoundEvents.BLOCK_WOOD_BREAK, // sound to play
                                SoundCategory.BLOCKS,             // sound category
                                1.0F,                             // volume
                                1.0F                              // pitch
                        );

                        chopConnectedLogs(world, targetTree.up());
                        ((ServerWorld) world).setBlockBreakingInfo(mob.getId(), targetTree, -1);
                    }
                    findTree();
                    breakingTicks = 0;
                }
            } else {
                mob.getNavigation().startMovingTo(targetTree.getX(), targetTree.getY(), targetTree.getZ(), 1.0);
            }
        }

        private void findTree() {
            BlockPos mobPos = mob.getBlockPos();
            for (BlockPos pos : BlockPos.iterateOutwards(mobPos, 10, 5, 10)) {
                if (isBottomLog(pos, mob.getWorld())) {
                    targetTree = pos.toImmutable();
                    return;
                }
            }
            targetTree = null;
        }

        private boolean isBottomLog(BlockPos pos, World world) {
            BlockState state = world.getBlockState(pos);
            BlockState below = world.getBlockState(pos.down());
            return state.isIn(BlockTags.LOGS) && (below.isOf(Blocks.DIRT) || below.isOf(Blocks.GRASS_BLOCK));
        }

        private void chopConnectedLogs(World world, BlockPos start) {
            Set<BlockPos> visited = new HashSet<>();
            Queue<BlockPos> toCheck = new ArrayDeque<>();
            toCheck.add(start);
            int maxBlocks = 200; // prevent infinite recursion

            while (!toCheck.isEmpty() && visited.size() < maxBlocks) {
                BlockPos pos = toCheck.poll();
                if (!visited.add(pos)) continue;

                BlockState state = world.getBlockState(pos);
                if (state.isIn(BlockTags.LOGS) || state.isIn(BlockTags.LEAVES)) {
                    world.breakBlock(pos, true);

                    for (BlockPos offset : BlockPos.iterate(pos.add(-1, -1, -1), pos.add(1, 1, 1))) {
                        if (!visited.contains(offset)) {
                            toCheck.add(offset.toImmutable());
                        }
                    }
                }
            }
        }

    }
}
