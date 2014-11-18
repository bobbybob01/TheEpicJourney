package me.taylory5.theepicjourney.playerevents.inventorymenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Pets {

	public static ItemStack WOLF = new ItemStack(Material.MONSTER_EGGS, 1);
	
	public static Entity spawnPet(ItemStack clickedItem, Player player){
		if(clickedItem.equals(WOLF)){
			Entity wolf;
			wolf = Bukkit.getServer().getWorld("world").spawnEntity(player.getLocation(), EntityType.SHEEP);
			
			//player has pet, kill when they log out
			return wolf;
		}
		return null;
		
	}
}
