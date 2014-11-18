package me.taylory5.theepicjourney.commands;

import me.taylory5.theepicjourney.config.Config;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHubSpawn implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(label.equalsIgnoreCase("sethubspawn")){
			if(sender instanceof Player){
				Player player = (Player) sender;
				
				if(player.hasPermission("epicjourney.sethubspawn")){
					Config.mainConfig.set("Hub.X", player.getLocation().getX());
					Config.mainConfig.set("Hub.Y", player.getLocation().getY());
					Config.mainConfig.set("Hub.Z", player.getLocation().getZ());
					Config.mainConfig.set("Hub.World", player.getWorld().getName());
					
					Config.mainConfig.saveConfig();
					
					player.sendMessage(ChatColor.GOLD + "Hub spawn set to your location");
				}else{
					return false;
				}
			}
		}
		
		return false;
	}
}
