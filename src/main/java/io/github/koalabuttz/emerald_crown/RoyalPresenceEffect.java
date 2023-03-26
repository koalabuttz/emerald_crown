package io.github.koalabuttz.emerald_crown;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.List;

import static io.github.koalabuttz.emerald_crown.EmeraldCrown.LOGGER;

public class RoyalPresenceEffect extends StatusEffect {


	public static final RoyalPresenceEffect INSTANCE = new RoyalPresenceEffect(StatusEffectType.BENEFICIAL, 0xFF9C00);

	public RoyalPresenceEffect(StatusEffectType type, int color) {
		super(StatusEffectType.BENEFICIAL, 0xFF9C00);
	}

	//public static final RoyalPresenceEffect INSTANCE = new RoyalPresenceEffect;

	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) {
		LOGGER.debug("Effect is affecting");

		if (!(entity instanceof PlayerEntity)) {
			LOGGER.debug("Effect is not on player");

			return;
		}

		PlayerEntity player = (PlayerEntity) entity;
		if (player.getAttacker() == null || player.world.random.nextDouble() >= .50) {
			LOGGER.debug("the OR condition is being met (no attacker or it was greater than .50)");
			return;
		}

		List<MobEntity> nearbyMobs = player.world.getEntitiesByClass(MobEntity.class, player.getBoundingBox().expand(16), (mob) -> mob.getTarget() == player);
		if (nearbyMobs.isEmpty()) {

			LOGGER.debug("There are no nearby mobs");

			return;
		}

		MobEntity newTarget = nearbyMobs.get(player.world.random.nextInt(nearbyMobs.size()));
		List<LivingEntity> nearbyHostiles = player.world.getEntitiesByClass(LivingEntity.class, player.getBoundingBox().expand(16), (hostile) -> hostile instanceof HostileEntity && hostile != newTarget);
		if (nearbyHostiles.isEmpty()) {
			LOGGER.debug("When selecting new mobs to target, there were none to target.");

			return;
		}

		LivingEntity newAttacker = nearbyHostiles.get(player.world.random.nextInt(nearbyHostiles.size()));
		newTarget.setTarget(newAttacker);
		LOGGER.debug("Selected new target: " + newAttacker.getType().toString());
	}

}



