package Werewolf;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.getspout.spoutapi.SpoutManager;

public class playerlistener extends PlayerListener {

	public boolean firstchosen;
	public static Map<Player, ItemStack[]> playersinventory = new HashMap<Player, ItemStack[]>();

	@Override
	public void onPlayerMove(PlayerMoveEvent event) {
		main.nighttimer = event.getPlayer().getWorld().getTime();

		if (event.getPlayer().getWorld().getTime() >= 13000) {
			if (main.isawearwolf.containsKey(event.getPlayer().getName())) {
				countup();
				firstchosen = true;
				if (main.nightcounter == 1) {
					if (!(SpoutManager.getPlayer(event.getPlayer()).getSkin() == "http://dl.dropbox.com/u/46495247/werewolf.png")) {
						Player player = event.getPlayer();
						SpoutManager
								.getPlayer(player)
								.setSkin(
										"http://dl.dropbox.com/u/46495247/werewolf.png");
						SpoutManager.getPlayer(player).setWalkingMultiplier(3);
						SpoutManager.getPlayer(player).setJumpingMultiplier(2);
						SpoutManager.getPlayer(player).setAirSpeedMultiplier(3);
						SpoutManager
								.getSoundManager()
								.playGlobalCustomSoundEffect(
										main.instance,
										"http://dl.dropbox.com/u/46495247/66398__robinhood76__00829-wolf-howl-one-shot.wav",
										true, player.getLocation(), 100);
						player.sendMessage("you are now a werewolf! "
								+ main.nighttimer);
						playersinventory.put(player, player.getInventory()
								.getContents());
						player.getInventory().clear();
					}
				}
			}

		}
		if (event.getPlayer().getWorld().getTime() < 13000) {
			if (main.isawearwolf.containsKey(event.getPlayer().getName())) {
				firstchosen = false;
				if (SpoutManager.getPlayer(event.getPlayer()).getSkin() == "http://dl.dropbox.com/u/46495247/werewolf.png") {
					Player player = event.getPlayer();
					SpoutManager.getPlayer(player).setSkin(
							"http://s3.amazonaws.com/MinecraftSkins/"
									+ player.getName() + ".png");
					SpoutManager.getPlayer(player).setWalkingMultiplier(1);
					SpoutManager.getPlayer(player).setJumpingMultiplier(1);
					SpoutManager.getPlayer(player).setAirSpeedMultiplier(1);
					player.sendMessage("you are no longer a wearwolf");
					player.getInventory().setContents(
							playersinventory.get(player));
				}
			}
		}
	}

	public void countup() {
		if (firstchosen == false) {
			if (main.nightcounter < 3) {
				main.nightcounter++;
				System.out.println("" + main.nightcounter);
			} else {
				main.nightcounter = 1;
				System.out.println("" + main.nightcounter);
			}
		}
	}
}