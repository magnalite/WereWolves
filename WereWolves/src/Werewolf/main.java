package Werewolf;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

	public static main instance;
	public static long nighttimer;
	public static int nightcounter;
	public File pFolder = new File("plugins/Werewolf");
	public File Werewolvs = new File("plugins/Werewolf/Werewolves.txt");
	public static Map<String, Boolean> isawearwolf = new HashMap<String, Boolean>();
	private final playerlistener PlayerListener = new playerlistener();
	private final entitylistener EntityListener = new entitylistener();

	public final Logger log = Logger.getLogger("Minecraft");

	@Override
	public void onDisable() {
		this.log.info("Werewolf plugin has been disabled");

	}

	@Override
	public void onEnable() {

		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_MOVE, PlayerListener,
				Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, EntityListener,
				Event.Priority.Normal, this);

		this.log.info("Werewolf plugin has been enabled");

		createPluginFolder();
		createWerewolfList();

	}

	public void createPluginFolder() {
		if (!this.pFolder.exists()) {
			pFolder.mkdir();
		}
	}

	public void createWerewolfList() {
		if (!this.Werewolvs.exists()) {
			try {
				Werewolvs.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			Scanner s = new Scanner(Werewolvs);
			while (s.hasNextLine()) {
				String line = s.nextLine();
				isawearwolf.put(line, true);
			}
			s.close();
		} catch (IOException e) {
			this.getServer()
					.broadcastMessage(
							"**WARNING** failed to load werewolves closing werewolf plugin!!");
			System.out
					.println("**WARNING** failed to load werewolves closing werewolf plugin!!");
			this.getPluginLoader().disablePlugin(this);
		}

	}

}
