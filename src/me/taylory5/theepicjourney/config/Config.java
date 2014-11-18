package me.taylory5.theepicjourney.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import me.taylory5.theepicjourney.Main;
import me.taylory5.theepicjourney.challenges.Challenges;

public class Config {

	public static MyConfigManager manager = new MyConfigManager(Main.getPlugin(Main.class));
	
	public static MyConfig mainConfig = manager.getNewConfig("MainConfig.yml", new String[] {"Main config for simple settings"});; 
	
	public static void setupMainConfig(){
		/*
		 * Changes, sets or creates the MainConfig if necessary
		 */
		if(!(mainConfig.contains("MinimumPlayers"))){
			mainConfig.set("MinimumPlayers", 16);
		}
		if(!(mainConfig.contains("CountdownFrom"))){
			mainConfig.set("CountdownFrom", 10);
		}
		mainConfig.saveConfig();
	}
	
	public static MyConfig challengesConfig = manager.getNewConfig("ChallengesConfig.yml", new String[] {"The data for challenges is stored here"});
	
	public static void setupChallengesConfig(){
		/*
		 * Changes, sets or creates the ChallengesConfig if necessary
		 */
		for(int a = 1; a <= Challenges.totalChallenges; a++){
			if(!(challengesConfig.contains("Challenges." + a))){
				challengesConfig.createSection("Challenge." + a);
				challengesConfig.set("Challenge." + a + ".Name", Challenges.getName(a));
				challengesConfig.set("Challenge." + a + ".Location.VillagerWorld", "not set");
				challengesConfig.set("Challenge." + a + ".Location.VillagerX", "not set");
				challengesConfig.set("Challenge." + a + ".Location.VillagerY", "not set");
				challengesConfig.set("Challenge." + a + ".Location.VillagerZ", "not set");
				}
			}
		challengesConfig.saveConfig();
	}
	
	public static void setupPlayerConfig(Player player){
		MyConfig playerConfig = manager.getNewConfig(player.getUniqueId() + ".yml");
		if(!(playerConfig.contains("CurrentUsername"))){
			playerConfig.set("CurrentUsername", player.getName());
		}
		if(!(playerConfig.contains("PlayerNumber"))){
			playerConfig.set("PlayerNumber", statsFile.get("UniquePlayers" + 1));
		}
		if(!(playerConfig.contains("Coins"))){
			playerConfig.set("Coins", 0);
		}
		if(!(statsFile.contains("PlayerNumbers." + player.getName()))){
			statsFile.set("PlayerNumbers." + player.getName(), statsFile.get("UniquePlayers" + 1));
			statsFile.set("UniquePlayers", statsFile.get("UniquePlayer" + 1));
		}
		if(!(statsFile.get("CurrentUsername").equals(player.getName()))){
			statsFile.set("CurrentUsername", player.getName());
		}
		playerConfig.saveConfig();
	}
	
	static MyConfig statsFile = manager.getNewConfig("Stats.yml", new String[] {"Stats for the server itself!"});
	
	public static void setupStatsFile(){
		if(!(statsFile.contains("UniquePlayers"))){
			statsFile.set("UniquePlayers", 0);
		}
		statsFile.saveConfig();
	}
	
	public static Location getLocation(MyConfig configName, String worldPath, String xPath, String yPath, String zPath){
		/*
		 * A quicker way to get a location from a config, just specify the config's name and location information
		 */
		Location location;
		
		World world = Bukkit.getWorld(configName.getString(worldPath));
		double x = configName.getDouble(xPath);
		double y = configName.getDouble(yPath);
		double z = configName.getDouble(zPath);
		location = new Location(world, x, y, z);
		
		return location;
	}

}
