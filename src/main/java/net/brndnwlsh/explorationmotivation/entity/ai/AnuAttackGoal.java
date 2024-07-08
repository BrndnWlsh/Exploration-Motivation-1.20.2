package net.brndnwlsh.explorationmotivation.entity.ai;

import net.brndnwlsh.explorationmotivation.entity.custom.AnuEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

import java.util.EnumSet;

public class AnuAttackGoal extends MeleeAttackGoal {

    private final AnuEntity entity;
    public static int lungeDuration = 36;
    public static final int lunge2Duration = 40;
    public static final int jumpStart = 15;
    public static final int strength = 1;
    public static final int cooldown = 100;
    public boolean attacked;
    public boolean lunging = false;
    public boolean basicAttacking;
    private int ticksUntilNextLunge = 0;
    private final int basicAttackDelay = 20;
    private int ticksUntilNextBasicAttack = 0;
    private boolean shouldCountTillNextLunge = false;
    private boolean shouldCountTillNextBasicAttack = false;

    public AnuAttackGoal(AnuEntity mob, double speed, boolean pauseWhenMobIdle) {
        super(mob, speed, pauseWhenMobIdle);
        entity = mob;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
    }

    @Override
    public void start() {
        super.start();
        ticksUntilNextBasicAttack = 0;
        ticksUntilNextLunge = 0;
        attacked = false;
    }

    @Override
    protected void attack(LivingEntity pEnemy) {
        if (isEnemyWithinLungeDistance(pEnemy) && ticksUntilNextLunge <= 0) {
            lunging = true;
            entity.setBasicAttacking(false);
            basicAttacking = false;
            attacked = false;
            resetLungeCooldown();
        } else if (!lunging && isEnemyWithinBasicAttackDistance(pEnemy) && ticksUntilNextBasicAttack <= 0) {
            basicAttacking = true;
            resetBasicAttackCooldown();
        }
        if (lunging){
            shouldCountTillNextLunge = true;
            this.mob.getLookControl().lookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());
            if(isTimeToStartLungeAnimation1()) {
                entity.setLunging1(true);
                this.mob.getNavigation().stop();
                if (ticksUntilNextLunge <= lungeDuration + lunge2Duration + cooldown - jumpStart && ticksUntilNextLunge >=  cooldown + lunge2Duration) {
                    this.mob.setVelocity(0.0, (ticksUntilNextLunge - lunge2Duration - cooldown) / 40.0, 0.0);
                }
            } else {
                    entity.setLunging1(false);
                }
            if(isTimeToStartLungeAnimation2() && !attacked) {
                entity.setLunging2(true);
                this.mob.setVelocity(strength * (pEnemy.getX()-mob.getX()) / this.mob.distanceTo(pEnemy),
                        strength * (pEnemy.getY()-mob.getY()) / this.mob.distanceTo(pEnemy),
                        strength * (pEnemy.getZ()-mob.getZ()) / this.mob.distanceTo(pEnemy));
            } else {
                entity.setLunging2(false);
            }

            if(isTimeToAttack(pEnemy) && entity.isLunging2()) {
                performAttack(pEnemy);
                attacked =  true;
                entity.setLunging2(false);
                lunging = false;
                shouldCountTillNextBasicAttack = true;
                ticksUntilNextBasicAttack = basicAttackDelay;
            }
        } else if (basicAttacking) {
            shouldCountTillNextBasicAttack = true;
            this.mob.getLookControl().lookAt(pEnemy.getX(), pEnemy.getEyeY(), pEnemy.getZ());

            if(isTimeToStartBasicAttackAnimation()) {
                entity.setBasicAttacking(true);
            }

            if(isTimeToAttack(pEnemy) && ticksUntilNextBasicAttack == basicAttackDelay) {
                performAttack(pEnemy);
            }
            if (ticksUntilNextBasicAttack <= 0) {
                entity.setBasicAttacking(false);
            }
        }
    }

    private boolean isEnemyWithinLungeDistance(LivingEntity pEnemy) {
        return this.entity.distanceTo(pEnemy) <= 10f && this.entity.distanceTo(pEnemy) >= 2f;
    }
    private boolean isEnemyWithinBasicAttackDistance(LivingEntity pEnemy) {
        return this.entity.distanceTo(pEnemy) <= 2f;
    }

    protected void resetLungeCooldown() {
        this.ticksUntilNextLunge = this.getTickCount(lungeDuration + lunge2Duration + cooldown);
        attacked = false;
    }
    protected void resetBasicAttackCooldown() {
        this.ticksUntilNextBasicAttack = this.getTickCount(basicAttackDelay * 2);
    }

    protected boolean isTimeToStartLungeAnimation1() {
        return this.ticksUntilNextLunge > lunge2Duration + cooldown;
    }

    private boolean isTimeToStartLungeAnimation2() {
        return (this.ticksUntilNextLunge <= lunge2Duration + cooldown && this.ticksUntilNextLunge >= cooldown);
    }

    protected boolean isTimeToStartBasicAttackAnimation() {
        return this.ticksUntilNextBasicAttack <= basicAttackDelay * 2;
    }

    protected boolean isTimeToAttack(LivingEntity entity) {
        return (this.mob.isInAttackRange(entity));
    }

    protected void performAttack(LivingEntity pEnemy) {
        this.mob.tryAttack(pEnemy);
    }



    @Override
    public void tick() {
        super.tick();
        if(shouldCountTillNextLunge) {
            this.ticksUntilNextLunge = Math.max(this.ticksUntilNextLunge - 1, 0);
        }
        if(shouldCountTillNextBasicAttack) {
            this.ticksUntilNextBasicAttack = Math.max(this.ticksUntilNextBasicAttack - 1, 0);
        }
    }

    @Override
    public void stop() {
        entity.setLunging1(false);
        entity.setLunging2(false);
        entity.setBasicAttacking(false);
        super.stop();
    }
}
