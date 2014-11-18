package me.taylory5.theepicjourney.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;

public class WaterTurnToIce implements Listener{
	
	@EventHandler
	public void onBlockWaterFreeze(BlockFadeEvent event){
		event.setCancelled(true);
	}
}
