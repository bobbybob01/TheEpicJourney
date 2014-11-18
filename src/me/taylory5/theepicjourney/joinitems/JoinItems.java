package me.taylory5.theepicjourney.joinitems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JoinItems {

	static ItemStack compass = new ItemStack(Material.COMPASS, 1);
	static ItemMeta compassMeta = compass.getItemMeta();
	
	public static ItemStack COMPASS(){
		compassMeta.setDisplayName(ChatColor.GOLD + "The Epic Journey");
		compass.setItemMeta(compassMeta);
		
		return compass;
	}
}
