package me.taylory5.theepicjourney.runnables;

import java.util.ArrayList;

import me.taylory5.theepicjourney.ArrayLists;
import me.taylory5.theepicjourney.Journey;
import me.taylory5.theepicjourney.Main;
import me.taylory5.theepicjourney.Status;
import me.taylory5.theepicjourney.config.Config;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable{

	int counter = 0;
	int seconds = Config.mainConfig.getInt("Settings.Countdown");
	ArrayList<Player> allOnlinePlayers;
	Plugin plugin;

	public Countdown(Plugin plugin2) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		allOnlinePlayers = ArrayLists.onlinePlayers;
		
		if(counter <= 10){
			if(Journey.canStart() == true){
				for(Player players : allOnlinePlayers){
					if(seconds != 0){
						players.sendMessage(ChatColor.DARK_GRAY + "» " + ChatColor.GOLD + "The Epic Journey is starting in " + seconds + "...");
						players.playSound(players.getLocation(), Sound.NOTE_PIANO, 1, 1);
					}else{
						players.sendMessage(ChatColor.DARK_GRAY + "» " + ChatColor.GOLD + "The Epic Journey is starting!");
						players.playSound(players.getLocation(), Sound.WITHER_SPAWN, 1, 1);
						//Main.colouredArmor.cancel();
						players.getInventory().clear();
						players.getInventory().setChestplate(new ItemStack (Material.AIR));
						players.getInventory().setBoots(new ItemStack(Material.AIR));
						
						Main.setJourneyStatus(Status.INGAME);
						
						//Challenges.startChallenge(1, ArrayLists.onlinePlayers);
					}
				}
				seconds--;
				counter++;
			}else{
				Journey.stopStarting();
				counter = 0;
			}
		}else{
			seconds = Config.mainConfig.getInt("Settings.Countdown");
			counter = 0;
	
			this.cancel();
		}
	}

	
}
