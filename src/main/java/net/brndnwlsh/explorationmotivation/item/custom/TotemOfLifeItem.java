package net.brndnwlsh.explorationmotivation.item.custom;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.particle.TotemParticle;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;


public class TotemOfLifeItem extends Item {
    public TotemOfLifeItem(Settings settings) {
        super(settings);
    }
//      gives 20s regen, cooldown of 2m
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_TOTEM_USE,
                SoundCategory.NEUTRAL, 0.5F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        user.getItemCooldownManager().set(this, 2400);
        if (!world.isClient) {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 400));
        }
 //       if (world.isClient) {
 //           new TotemParticle.Factory();
 //           particleManager.addEmitter(entity, ParticleTypes.TOTEM_OF_UNDYING, 30);}
 //     https://stackoverflow.com/questions/69000437/how-do-i-make-a-totem-of-undying-like-item-with-fabric
 //       trouble getting a totem particle

        if (user != null) {
            user.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!user.getAbilities().creativeMode) {
                itemStack.damage(1, user, playerEntity -> playerEntity.sendToolBreakStatus(user.getActiveHand()));
            }}

            return TypedActionResult.success(itemStack, world.isClient());
        }
}
