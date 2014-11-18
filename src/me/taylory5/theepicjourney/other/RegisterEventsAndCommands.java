package me.taylory5.theepicjourney.other;

import org.bukkit.Bukkit;

import me.taylory5.theepicjourney.Main;
import me.taylory5.theepicjourney.commands.AdminMode;
import me.taylory5.theepicjourney.commands.CreateCuboid;
import me.taylory5.theepicjourney.commands.SetHubSpawn;
import me.taylory5.theepicjourney.playerevents.PlayerJoin;
import me.taylory5.theepicjourney.playerevents.PlayerLeave;
import me.taylory5.theepicjourney.playerevents.inventorymenu.Inventory;

public class RegisterEventsAndCommands {

	public static void registerEvents(){
		/*
		 * Registers all these events in this method when called in the Main class
		 */
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin(), Bukkit.getPluginManager().getPlugin("TheEpicJourney"));
		Bukkit.getServer().getPluginManager().registerEvents(new PlayerLeave(), Bukkit.getPluginManager().getPlugin("TheEpicJourney"));
		Bukkit.getServer().getPluginManager().registerEvents(new Inventory(), Bukkit.getPluginManager().getPlugin("TheEpicJourney"));
	}
	
	public static void registerCommands(){
		/*
		 * Registers all commands in this method when called in the main class
		 */
		Main.getPlugin(Main.class).getCommand("sethubspawn").setExecutor(new SetHubSpawn());
		Main.getPlugin(Main.class).getCommand("admin").setExecutor(new AdminMode());
		Main.getPlugin(Main.class).getCommand("createcuboid").setExecutor(new CreateCuboid());
	}
}
