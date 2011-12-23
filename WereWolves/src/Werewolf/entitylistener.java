package Werewolf;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;
import org.getspout.spoutapi.SpoutManager;

public class entitylistener extends EntityListener {

	public void onEntityDamage(EntityDamageEvent event) {
		int damage = event.getDamage() / 2;
		if (!(SpoutManager.getPlayerFromId(event.getEntity().getEntityId()) == null)) {
			if (SpoutManager.getPlayerFromId(event.getEntity().getEntityId())
					.getSkin() == "http://dl.dropbox.com/u/46495247/werewolf.png") {
				event.setDamage(damage);
			}
		}
		if (event instanceof EntityDamageByEntityEvent) {
			EntityDamageByEntityEvent event2 = (EntityDamageByEntityEvent) event;
			if (event2.getEntity() instanceof LivingEntity) {
				;
				if (event2.getDamager() instanceof Player) {
					if (!(SpoutManager.getPlayerFromId(event2.getDamager()
							.getEntityId()) == null)) {
						if (SpoutManager.getPlayerFromId(
								event2.getDamager().getEntityId()).getSkin() == "http://dl.dropbox.com/u/46495247/werewolf.png") {
						}
						if (SpoutManager
								.getPlayerFromId(
										event2.getDamager().getEntityId())
								.getItemInHand().getType().equals(Material.AIR)) {
							event.setDamage(5);
						} else {
							event.setDamage(1);
						}
					}
				}

			}
		}
	}
}
