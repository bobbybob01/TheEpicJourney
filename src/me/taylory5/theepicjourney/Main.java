package me.taylory5.theepicjourney;

import me.taylory5.theepicjourney.config.Config;
import me.taylory5.theepicjourney.other.RegisterEventsAndCommands;
import me.taylory5.theepicjourney.runnables.ColouredArmor;
import me.taylory5.theepicjourney.scoreboards.ScoreboardForLobby;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

public class Main extends JavaPlugin{

	private static Status journeyStatus;
	
	public static ColouredArmor colouredArmor;
	
	public void onEnable(){
		setJourneyStatus(Status.INLOBBY);
		ScoreboardForLobby.setupBoard();
		
		Journey.started = false;
		Journey.isStarting = false;
		
		//colouredArmor = new ColouredArmor(Bukkit.getServer().getPluginManager().getPlugin("TheEpicJourney"));
		//colouredArmor.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("TheEpicJourney"), 0, (long) (0.5 * 20)); 
		
		Config.setupMainConfig();
		Config.setupChallengesConfig();
		Config.setupStatsFile();
		
		RegisterEventsAndCommands.registerEvents();
		RegisterEventsAndCommands.registerCommands();
	}
	
	public void onDisable(){
		
	}

	/**
	 * @return the journeyStatus
	 */
	public static Status getJourneyStatus() {
		return journeyStatus;
	}

	/**
	 * @param journeyStatus the journeyStatus to set
	 */
	public static void setJourneyStatus(Status journeyStatus) {
		Main.journeyStatus = journeyStatus;
	}
	
	public static WorldEditPlugin getWorldEdit() {
        Plugin pluginWE = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        if (pluginWE instanceof WorldEditPlugin) return (WorldEditPlugin) pluginWE;
        else return null;
	}
	
}
