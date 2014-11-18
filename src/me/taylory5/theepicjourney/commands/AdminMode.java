package me.taylory5.theepicjourney.commands;

import me.taylory5.theepicjourney.ArrayLists;
import me.taylory5.theepicjourney.Journey;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminMode implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		
		if(sender instanceof Player){
			if(label.equalsIgnoreCase("admin")){
				toggleAdminMode(player);
			}
			return false;
		}
		return false;
	}
	
	public static void toggleAdminMode(Player player){
		/*
		 * "Toggles" the player in our out of Admin mode automatically when this method is run
		 */
		if(ArrayLists.adminModePlayers.contains(player)){
			Journey.removePlayerTotally(player);
			Journey.addPlayer(player);
			
			player.setGameMode(GameMode.SURVIVAL);
			player.sendMessage(ChatColor.DARK_GRAY + "You are now in " + ChatColor.GOLD + "PLAYER " + ChatColor.DARK_GRAY + "mode");
		}else{
			if(Journey.getOnlinePlayers().contains(player)){
				Journey.removePlayer(player);
			}
			ArrayLists.adminModePlayers.add(player);
			
			player.setGameMode(GameMode.CREATIVE);
			player.sendMessage(ChatColor.DARK_GRAY + "You are now in " + ChatColor.GOLD + "ADMIN " + ChatColor.DARK_GRAY + "mode");
		}
	}

}
