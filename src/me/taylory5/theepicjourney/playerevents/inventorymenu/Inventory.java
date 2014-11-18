package me.taylory5.theepicjourney.playerevents.inventorymenu;

import java.util.Arrays;
import java.util.List;

import javax.swing.text.PlainView;

import me.taylory5.theepicjourney.Hub;
import me.taylory5.theepicjourney.Journey;
import me.taylory5.theepicjourney.Strings;
import me.taylory5.theepicjourney.joinitems.JoinItems;
import me.taylory5.theepicjourney.other.ParticleEffect;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Inventory implements Listener{
	private static ItemStack particleEffectItem = new ItemStack(Material.BLAZE_POWDER, 1);
	private static ItemStack challengesItem = new ItemStack(Material.BOOK);
	
	public static org.bukkit.inventory.Inventory inventoryMenu;
	
	public static org.bukkit.inventory.Inventory mainInventory(){
		/*
		 * Returns the main inventory for the server after it sets the meta and contents
		 */
		inventoryMenu = Bukkit.getServer().createInventory(null, 9);
		
		changeMeta();
		inventoryMenu.setContents(getContents());
		
		return inventoryMenu;
	}

	private static void changeMeta() {
		/*
		 * Changes the meta of the items for the main inventory
		 */
		ItemMeta particleMeta = particleEffectItem.getItemMeta();
		particleMeta.setDisplayName(ChatColor.GOLD + "Particle Effects");
		List<String> particleLore = Arrays.asList("The selected particle effect will show when your character moves locations"); 
		particleMeta.setLore(particleLore);
		
		ItemMeta challengesMeta = challengesItem.getItemMeta();
		challengesMeta.setDisplayName(ChatColor.GOLD + "Challenges");
		List<String> challengesLore = Arrays.asList("Here you will find a list and information on ALL of the challenges The Epic Journey has to offer!"); 
		challengesMeta.setLore(challengesLore);
		
		particleEffectItem.setItemMeta(particleMeta);
		challengesItem.setItemMeta(challengesMeta);
	}

	public static ItemStack[] getContents() {
		/*
		 * Gets and sets the contents in the form of an itemstack array
		 */
		ItemStack[] bob = new ItemStack[9];
		
		bob[0] = challengesItem;
		bob[1] = particleEffectItem;
		
		return bob;
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		/*
		 * Called ONLY when a player clicks the special compass given when you login
		 */
		Player player = event.getPlayer();
		if(event.getItem().equals(JoinItems.COMPASS())){
			if(event.getAction().name().contains("RIGHT")){
				player.openInventory(mainInventory());
			}
		}
	}
	
	@EventHandler
	public void onPlayerClickInv(InventoryClickEvent event){
		/*
		 * Called when a player clicks the itemstack particleEffectItem in the inventory inventoryMenu and opens the particle effect inventory
		 */
		if(event.getInventory().equals(inventoryMenu)){
			if(event.getCurrentItem().equals(particleEffectItem)){
				event.setResult(Result.DENY);
				Particles.openInventory((Player) event.getWhoClicked());
			}
		}
		/*
		 * Called when a player clicks an item, then activates or deactivates particles depending on the item clicked
		 */
		else if(event.getInventory().equals(Particles.particleInv)){
				if(!(event.getCurrentItem().equals(Particles.clearItem)) && (!(event.getCurrentItem().equals(null)))){
					Hub.addParticles((Player) event.getWhoClicked(), Particles.getItemClicked(event));
					Player player = (Player) event.getWhoClicked();
					player.sendMessage(ChatColor.DARK_GRAY + "Activated your " + ChatColor.GOLD + Particles.getItemClicked(event) + ChatColor.DARK_GRAY + " particles!\n");
					event.setResult(Result.DENY);
				}else{
					Player player = (Player) event.getWhoClicked();
					if(Hub.PlayersAndParticles.containsKey((Player) event.getWhoClicked())){
						player.sendMessage(ChatColor.DARK_GRAY + "Your " + ChatColor.GOLD + Particles.getParticle(player) + ChatColor.DARK_GRAY + " particles were removed");
						Hub.PlayersAndParticles.remove((Player) event.getWhoClicked());
						event.setResult(Result.DENY);
					}else{
						player.sendMessage(Strings.ERROR + ChatColor.DARK_GRAY + "You have no particles to remove silly billy!");
						event.setResult(Result.DENY);
					}
				}
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		/*
		 * Spawn particles when the player moves! (Only when the block changes though)
		 */
		if(!(event.getTo().getBlock().equals(event.getFrom().getBlock()))){
			if(Hub.PlayersAndParticles.containsKey(event.getPlayer())){
				Player player = event.getPlayer();
				Location playerLocation = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
				ParticleEffect.sendParticleToAll(Particles.getParticle(event.getPlayer()), playerLocation, 1F, 1F, 1F, 1F, 1);
				
				player.sendMessage("Your region: " + Journey.getRegion(player));
			}
		}
	}
	
	@EventHandler
	public void onPlayerBreak(PlayerInteractEvent event){
		Player player = event.getPlayer();
		if(event.getAction().name().contains("LEFT")){
			if(Journey.getRegion(player) == 0){
				event.setCancelled(true);
			}
		}
	}
	
}
