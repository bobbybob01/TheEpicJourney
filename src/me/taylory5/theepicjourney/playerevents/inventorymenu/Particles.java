package me.taylory5.theepicjourney.playerevents.inventorymenu;

import me.taylory5.theepicjourney.Hub;
import me.taylory5.theepicjourney.config.MyConfig;
import me.taylory5.theepicjourney.other.ParticleEffect;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Particles {
	
	static MyConfig particlesConfig;
	
	public static org.bukkit.inventory.Inventory particleInv;
	public static ItemStack noteItem = new ItemStack(Material.NOTE_BLOCK, 1);
	public static ItemStack heartItem = new ItemStack(Material.INK_SACK, 1, (short) 1);
	public static ItemStack clearItem = new ItemStack(Material.MILK_BUCKET, 1);
	
	public static void openInventory(Player player){
		/*
		 * Creates and opens the particle inventory
		 */
		particleInv = Bukkit.createInventory(null, 9);
		particleInv.setContents(getContents());
		
		player.openInventory(particleInv);
	}
	
	private static ItemStack[] getContents(){
		/*
		 * Sets and returns the contents of the particle inventory
		 */
		ItemMeta noteMeta = noteItem.getItemMeta();
		ItemMeta heartMeta = heartItem.getItemMeta();
		ItemMeta clearMeta = clearItem.getItemMeta();
		ItemStack[] contents = new ItemStack[9];
		
		noteMeta.setDisplayName(ChatColor.GOLD + "Note particles!");
		noteItem.setItemMeta(noteMeta);
		heartMeta.setDisplayName(ChatColor.GOLD + "Lovely heart particles!");
		heartItem.setItemMeta(heartMeta);
		clearMeta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Remove current particles");
		clearItem.setItemMeta(clearMeta);
		
		contents[0] = noteItem;
		contents[1] = heartItem;
		contents[8] = clearItem;
		
		return contents;
	}

	public static ParticleEffect getParticle(Player player) {
		/*
		 * gets the particle for the specified player
		 */
		return Hub.PlayersAndParticles.get(player);
	}

	public static ParticleEffect getItemClicked(InventoryClickEvent event) {
		/*
		 * Returns the right particle effect (to be added to the player) depending on the item clicked
		 */
		if(event.getCurrentItem().equals(noteItem)){
			return ParticleEffect.NOTE;
		}else if(event.getCurrentItem().equals(heartItem)){
			return ParticleEffect.HEART;
		}

		return null;
	}
}
