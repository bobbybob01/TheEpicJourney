package me.taylory5.theepicjourney.runnables;

import java.util.ArrayList;
import java.util.Random;

import me.taylory5.theepicjourney.ArrayLists;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ColouredArmor extends BukkitRunnable{

	ArrayList<Player> allOnlinePlayers;
	Random randomNum = new Random();
	
	public ColouredArmor(Plugin plugin) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		allOnlinePlayers = ArrayLists.journeyPlayers;
		Color colour = Color.fromRGB(randomNum.nextInt(255), randomNum.nextInt(255), randomNum.nextInt(255));
		
//		if(Journey.hasStarted() == true){
//			for(Player players : allOnlinePlayers){
//				players.getInventory().setChestplate(new ItemStack(Material.AIR));
//				players.getInventory().setBoots(new ItemStack(Material.AIR));
//				players.getInventory().clear();
//			}
//		}else{
			for(Player players : allOnlinePlayers){
				players.getInventory().setChestplate(getColourArmor(Material.LEATHER_CHESTPLATE, colour));
				players.getInventory().setBoots(getColourArmor(Material.LEATHER_BOOTS, colour));
			}
//		}
		
	}
	
	 private ItemStack getColourArmor(Material material, Color j) {
         ItemStack i = new ItemStack(material, 1);
         LeatherArmorMeta meta = (LeatherArmorMeta) i.getItemMeta();
         meta.setColor(j);
         i.setItemMeta(meta);
         
         return i;
	}

	 
}
