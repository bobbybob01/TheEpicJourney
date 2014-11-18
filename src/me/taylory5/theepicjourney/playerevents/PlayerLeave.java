package me.taylory5.theepicjourney.playerevents;

import me.taylory5.theepicjourney.Journey;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener{

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event){
		Player player = event.getPlayer();
		
		if(Journey.hasStarted() || Journey.isStarting()){
			event.setQuitMessage(ChatColor.GOLD + "» " + ChatColor.DARK_GRAY + player.getName() + " quit");
		}else{
			Journey.removePlayerTotally(player);
			
			event.setQuitMessage(ChatColor.GOLD + "» " + ChatColor.DARK_GRAY + player.getName() + " quit");
		}
		
	}
	
	
}
