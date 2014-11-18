package me.taylory5.theepicjourney.playerevents;

import java.util.HashMap;

import me.taylory5.theepicjourney.ArrayLists;
import me.taylory5.theepicjourney.Hub;
import me.taylory5.theepicjourney.Journey;
import me.taylory5.theepicjourney.Main;
import me.taylory5.theepicjourney.Status;
import me.taylory5.theepicjourney.Strings;
import me.taylory5.theepicjourney.challenges.Challenges;
import me.taylory5.theepicjourney.commands.AdminMode;
import me.taylory5.theepicjourney.config.Config;
import me.taylory5.theepicjourney.joinitems.JoinItems;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener{

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		
		if(Main.getJourneyStatus() == Status.INLOBBY){
			Journey.addPlayer(player);
			event.setJoinMessage(ChatColor.GOLD + "\n» " + ChatColor.DARK_GRAY + player.getName() + " joined");
		
			player.getInventory().clear();
			player.getInventory().setItem(0, JoinItems.COMPASS());
			player.teleport(Hub.getSpawn(player));
			player.getInventory().addItem(Challenges.getBook(1));
		
			if(Journey.canStart() && Main.getJourneyStatus() != Status.STARTING){
				Journey.start(ArrayLists.journeyPlayers);
			}
		
			}else{
				if(player.isOp()){
					AdminMode.toggleAdminMode(player);		
					event.setJoinMessage(null);
				}else{
					player.kickPlayer(ChatColor.GOLD + "The Epic Journey has already started!");
				}
			}
		
		if(player.isOp()){	//Loops through all challenges and then if one of them is not setup correctly it adds to to an arraylist with the errors, then prints it out when they player logs in
			HashMap<Integer, String> temp = new HashMap<Integer, String>();
			for(int i = 1; i <= Challenges.totalChallenges; i++){
				for(String xyzWorld : Challenges.xyzWorld){//Change this if its the world thing, us if to check type (Double or World)
					if(Config.challengesConfig.get("Challenge." + i + ".Location." + xyzWorld).equals("not set")){
						if(temp.isEmpty()){
							temp.put(i, xyzWorld);
						}else{
							temp.put(i, temp.get(i) + ", " + xyzWorld);
						}
					}
				}
			}
			if(temp.isEmpty() == false){
				for(int i = 1; i <= temp.size(); i++){
					player.sendMessage(Strings.ERROR + ChatColor.DARK_GRAY + "The " + ChatColor.GOLD + temp.get(i) + ChatColor.DARK_GRAY + " for challenge " + ChatColor.GOLD + i + ChatColor.DARK_GRAY + " could not be found. Enter valid data into the config " + ChatColor.GOLD + "'Challenges.yml'" + ChatColor.DARK_GRAY + " to fix this!\n");
				}
			}
		}
		Config.setupPlayerConfig(player);
	}
	
	/*@EventHandler
	public void onPlayClick(PlayerInteractEvent event){
		Player player = event.getPlayer();
		
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
			ParticleEffect.sendParticleToAll(ParticleEffect.NOTE, player.getLocation(), 0, 0, 0, 0, 1);
		}
	}*/
	
}