package repook.yellowstoned.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BisonEntity extends AnimalEntity {
    private int idleAnimationCooldown = 0;
    public final AnimationState idlingAnimationState = new AnimationState();

    public BisonEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }


    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    //goals, lower number = higher priority
    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new EscapeDangerGoal(this, 2.0));
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1f));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 24.0F));
    }

    public static DefaultAttributeContainer.Builder createBisonAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.20000000298023224f);
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
