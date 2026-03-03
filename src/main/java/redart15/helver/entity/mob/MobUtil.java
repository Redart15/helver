package redart15.helver.entity.mob;

import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import redart15.helver.entity.DamageInstance;

public class MobUtil {

    private MobUtil(){/* no need to initiate*/}

	/**
	 * @param 	attacker	the attacker/cause
	 * @param	victim	the victim the attacked entity
	 * @param 	instances an array of damage with potential different damage types
	 * @return 	true if all damage instances were successful, false otherwise
	 * */
    public static boolean multiHit(Entity attacker, Entity victim, DamageInstance... instances){
        if(instances == null){
            return false;
        }
        if(instances.length < 2){
            DamageInstance instance = instances[0];
            return victim.hurt(attacker, instance.getDamage(), instance.getType());
        }
        boolean cumulativeAccept = true;
        int cumulativeDamage = 0;
		for (DamageInstance instance : instances) {
			cumulativeDamage += instance.getDamage();
			cumulativeAccept = victim.hurt(attacker, cumulativeDamage, instance.getType());
		}
        return cumulativeAccept;
    }

	/**
	 * @param 		mob the victim to be killed
	 * @return	 	if the victim successfully was killed
	 *
	 * @implNote 	entities do not have a kill function, by default the entities take 100 typeless damage.
	 * this might not always enough. These function are used to force that behavior.
	 * */
    public static boolean killMob(Mob mob) {
        return MobUtil.killMob(mob, null);
    }

	/**
	 * @param 		mob the victim to be killed
	 * @param 		attack, the cause of the damage
	 * @return	 	if the victim successfully was killed
	 *
	 * @implNote 	entities do not have a kill function, by default the entities take 100 typeless damage.
	 * this might not always enough. These function are used to force that behavior.
	 * */
    public static boolean killMob(Mob mob, Entity attack) {
        mob.setHealthRaw(0);
        mob.playDeathSound();
        mob.onDeath(attack);
        return true;
    }
}
