package net.brndnwlsh.explorationmotivation.entity.custom;

import net.brndnwlsh.explorationmotivation.entity.ai.AnuAttackGoal;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AnuEntity extends HostileEntity {

    private static final TrackedData<Boolean> LUNGING2 =
            DataTracker.registerData(AnuEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> LUNGING1 =
            DataTracker.registerData(AnuEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> BASIC_ATTACKING =
            DataTracker.registerData(AnuEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    private final ServerBossBar bossBar = (ServerBossBar)new ServerBossBar(this.getDisplayName(),
            BossBar.Color.BLUE, BossBar.Style.PROGRESS).setDarkenSky(true);

    public final AnimationState lungeAnimationState1 = new AnimationState();
    public int lungeAnimationTimeout1 = 0;
    public final AnimationState lungeAnimationState2 = new AnimationState();
    public int lungeAnimationTimeout2 = AnuAttackGoal.lungeDuration;
    public final AnimationState basicAttackAnimationState1 = new AnimationState();
    public final AnimationState basicAttackAnimationState2 = new AnimationState();
    public int basicAttackAnimationTimeout = 0;

    public AnuEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 50;
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0 && !isLunging2() && !isLunging1() && !isBasicAttacking() && !this.isNavigating()){
            //TODO find a way to stop idleAnimation during walkAnimation
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            this.idleAnimationTimeout = Math.max(this.idleAnimationTimeout - 1, 0);
        }
        if (isLunging2() || isLunging1() || isBasicAttacking() || this.isNavigating()) {
            idleAnimationState.stop();
        }
        if (!this.isBasicAttacking() && this.isLunging1() && lungeAnimationTimeout1 <= 0){
            idleAnimationState.stop();
            lungeAnimationTimeout1 = AnuAttackGoal.cooldown + AnuAttackGoal.lungeDuration - 1;
            lungeAnimationState1.start(this.age);
            lungeAnimationTimeout2 = AnuAttackGoal.lungeDuration;
        } else if (!isLunging2()){
            --this.lungeAnimationTimeout1;
        }
        if (this.isLunging2() && lungeAnimationTimeout2 <= 0){
            idleAnimationState.stop();
            lungeAnimationState1.stop();
            lungeAnimationTimeout2 = AnuAttackGoal.lungeDuration;
            lungeAnimationState2.start(this.age);
        } else if (this.isLunging1()) {
            --this.lungeAnimationTimeout2;
        }
        if (!this.isLunging2()){
            lungeAnimationState2.stop();
        }
        if (!this.isLunging1()){
            lungeAnimationState1.stop();
        }
        if (this.isBasicAttacking() && basicAttackAnimationTimeout <= 0){
            idleAnimationState.stop();
            lungeAnimationState1.stop();
            basicAttackAnimationState1.stop();
            basicAttackAnimationState2.stop();
            basicAttackAnimationTimeout = 39;
            if (random.nextBoolean()){
                basicAttackAnimationState1.start(this.age);
            } else {
                basicAttackAnimationState2.start(this.age);
            }
        } else {
            --basicAttackAnimationTimeout;
        }
        if (!this.isBasicAttacking()){
            basicAttackAnimationState1.stop();
            basicAttackAnimationState2.stop();
        }
    }

    public void setLunging2(boolean lunging2) {
        this.dataTracker.set(LUNGING2, lunging2);
    }

    public void setLunging1(boolean lunging1) {
        this.dataTracker.set(LUNGING1, lunging1);
    }

    public void setBasicAttacking(boolean basic_attacking) {
        this.dataTracker.set(BASIC_ATTACKING, basic_attacking);
    }

    public void setIdle() {
        this.idleAnimationTimeout = 0;
    }

    public boolean isLunging2() {
        return this.dataTracker.get(LUNGING2);
    }
    public boolean isLunging1() {
        return this.dataTracker.get(LUNGING1);
    }
    public boolean isBasicAttacking() {
        return this.dataTracker.get(BASIC_ATTACKING);
    }

    @Override
    public void checkDespawn() {
        if (this.getWorld().getDifficulty() == Difficulty.PEACEFUL && this.isDisallowedInPeaceful()) {
            this.discard();
            return;
        }
        this.despawnCounter = 0;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(LUNGING2, false);
        this.dataTracker.startTracking(LUNGING1, false);
        this.dataTracker.startTracking(BASIC_ATTACKING, false);
    }

    @Override
    protected void updateLimbs(float posDelta){
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0f, 1.0f): 0.0f;
        this.limbAnimator.updateLimbs(f, 0.2f);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()){
            setupAnimationStates();
        }
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new AnuAttackGoal(this, 1D, true));
        this.goalSelector.add(3, new WanderAroundFarGoal (this, 1D));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 4f));
        this.goalSelector.add(5, new LookAroundGoal(this));

        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, DwarfEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createAnuAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 300)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_ARMOR, 4.0f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 25)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (this.hasCustomName()) {
            this.bossBar.setName(this.getDisplayName());
        }
    }

    //@Override
    //MobEntity.ATTACK_RANGE = 2f
    //TODO find a way to extend ATTACK_RANGE
    //at a certain range, basic attack animation occurs but attack event does not

    @Override
    public void setCustomName(@Nullable Text name) {
        super.setCustomName(name);
        this.bossBar.setName(this.getDisplayName());
    }

    @Override
    protected void mobTick() {
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceSquared) {
        return false;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PARROT_IMITATE_ZOMBIE_VILLAGER;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_VILLAGER_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_VILLAGER_DEATH;
    }
}
