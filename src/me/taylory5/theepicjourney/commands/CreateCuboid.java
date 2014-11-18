package me.taylory5.theepicjourney.commands;

import me.taylory5.theepicjourney.Journey;
import me.taylory5.theepicjourney.Main;
import me.taylory5.theepicjourney.Strings;
import me.taylory5.theepicjourney.config.Config;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.bukkit.selections.Selection;

public class CreateCuboid implements CommandExecutor{

	Selection weSelection;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		
		if(sender instanceof Player){
			if(player.hasPermission("theepicjourney.createcuboid")){
				if(args.length == 1){
					weSelection = Main.getWorldEdit().getSelection(player);
					if(weSelection == null){
						player.sendMessage(Strings.ERROR + ChatColor.DARK_GRAY + "You must make a selection first");
					}else{
						if(args[0].equals("0")){
							Journey.createRegion(Config.mainConfig, Integer.parseInt(args[0]), weSelection);
							player.sendMessage(ChatColor.DARK_GRAY + "Set the area for the " + ChatColor.GOLD + "hub");
						}else{
							Journey.createRegion(Config.challengesConfig, Integer.parseInt(args[0]), weSelection);
							player.sendMessage(ChatColor.DARK_GRAY + "Set the area for " + ChatColor.GOLD + "challenge " + args[0]);
						}
					}
				}else{
					player.sendMessage(Strings.ERROR + ChatColor.DARK_GRAY + "You need arguments for the challenge number!");
				}
			}
		}
		return false;
	}
}