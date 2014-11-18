package me.taylory5.theepicjourney;

import java.util.HashMap;

import me.taylory5.theepicjourney.config.Config;
import me.taylory5.theepicjourney.other.ParticleEffect;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Hub {

	public static HashMap<Player, ParticleEffect> PlayersAndParticles = new HashMap<Player, ParticleEffect>();
	
	public static Location getSpawn(Player player){
		/*
		 * Gets the hub location in the file "MainConfig.yml"
		 */
		if(Config.mainConfig.contains("Hub.X")){
			double x = Config.mainConfig.getDouble("Hub.X");
			double y = Config.mainConfig.getDouble("Hub.Y");
			double z = Config.mainConfig.getDouble("Hub.Z");
			World world = Bukkit.getWorld(Config.mainConfig.getString("Hub.World"));
			
			Location hubLocation = new Location(world, x, y, z);
			
			return hubLocation;
		}else{
			player.sendMessage(Strings.ERROR + ChatColor.DARK_GRAY + "The hub spawn location could not be found, you have spawned where you last logged off!" + "\n ");
			
			return player.getLocation();
		}
	}
	
	public static void addParticles(Player player, ParticleEffect ParticleEffect){
		if(PlayersAndParticles.containsKey(player)){
			PlayersAndParticles.remove(player);
		}
		PlayersAndParticles.put(player, ParticleEffect);
	}
	
	 
}
