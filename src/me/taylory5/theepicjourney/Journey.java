package me.taylory5.theepicjourney;

import java.util.ArrayList;
import java.util.HashMap;

import me.taylory5.theepicjourney.challenges.Challenges;
import me.taylory5.theepicjourney.config.Config;
import me.taylory5.theepicjourney.config.MyConfig;
import me.taylory5.theepicjourney.runnables.Countdown;
import me.taylory5.theepicjourney.scoreboards.ScoreboardForLobby;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.bukkit.selections.Selection;

public class Journey {

	static Countdown countdown;
	
	Main plugin;
	
	//static HashMap<Integer, Selection> bob = new HashMap<Integer, Selection>(); //HashMap storing WorldEdit selections
	static Selection[] bob;
	
	static boolean started;
	static boolean isStarting;
	
	public Journey(Main plugin){
		this.plugin = plugin;
	}
	
	public static boolean hasStarted(){
		/*
		 * Gets whether or not the 'Journey' has started
		 */
		if(started == true){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean playerInJourney(Player player){
		/*
		 * Checks whether or not the player is in the ArrayList with all the Journey players, if they are they are in the 'Journey'
		 */
		if(ArrayLists.journeyPlayers.contains(player)){
			return true;
		}else{
			return false;
		}
	}

	public static boolean playerInJourney(String playerName) {
		/*
		 * Checks whether or not the player's name is in the ArrayList with all the Journey player's names, if they are they are in the 'Journey'
		 */
		if(ArrayLists.journeyPlayersNames.contains(playerName)){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean canStart(){
		/*
		 * Gets if the 'Journey' can start by comparing the amount of players online to the minimum players listed till start in the config
		 */
		if(Journey.getJourneyPlayers().size() >= Config.mainConfig.getInt("MinimumPlayers")){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isStarting(){
		/*
		 * Returns true if the Journey is already counting down to start
		 */	
		if(isStarting == true){
			return true;
		}else{
			return false;
		}
	}

	public static void start(ArrayList<Player> journeyPlayers) {
		/*
		 * Starts the journey with the arraylist of players
		 */
		countdown = new Countdown(Bukkit.getServer().getPluginManager().getPlugin("TheEpicJourney"));
		countdown.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("TheEpicJourney"), 0, 1 * 20);
		Main.setJourneyStatus(Status.STARTING);
		
		//choose teams and armor
		//randomly select a challenge

	}
	
	public static boolean isInLobby(){
		/*
		 * If the Journey is in lobby currently this will return true 
		 */
		if(Main.getJourneyStatus() == Status.INLOBBY){
			return true;
		}else{
			return false;
		}
	}
	
	public static void addPlayer(Player player){
		/*
		 * Adds a player to all the arrayLists and updates the contents of their scoreboard
		 */
		ArrayLists.journeyPlayers.add(player);
		ArrayLists.journeyPlayersNames.add(player.getName());
		ArrayLists.onlinePlayers.add(player);
		
		if(Journey.isInLobby()){
			ScoreboardForLobby.updateBoardContents();
		}
	}
	
	public static void removePlayer(Player player){
		/*
		 * Removes a player from all Journey related player lists, this is used when a player goes into Admin mode because we still keep them on the 'Online Player' arraylist so they can still get messages
		 */
		ArrayLists.journeyPlayers.remove(player);
		ArrayLists.journeyPlayersNames.remove(player.getName());
		
		if(Journey.isInLobby()){
			ScoreboardForLobby.updateBoardContents();
		}
	}
	
	public static void removePlayerTotally(Player player){
		/*
		 * removes a player from ALL arraylists, used for when the player logs out
		 */
		ArrayLists.journeyPlayers.remove(player);
		ArrayLists.journeyPlayersNames.remove(player.getName());
		ArrayLists.onlinePlayers.remove(player.getName());
		if(ArrayLists.adminModePlayers.contains(player)){
			ArrayLists.adminModePlayers.remove(player);
		}
		if(Journey.isInLobby()){
			ScoreboardForLobby.updateBoardContents();
		}
	}
	
	public static void setToStarting(){
		/*
		 * Sets the status of the Journey to starting
		 */
		isStarting = true;
		Main.setJourneyStatus(Status.STARTING);
	}
	
	public static void stopStarting(){
		/*
		 * Stops the countdown and sets the staus back to 'inLobby'
		 */
		countdown.cancel();
		isStarting = false;
		
		Main.setJourneyStatus(Status.INLOBBY);
	}
	
	public static ArrayList<Player> getJourneyPlayers(){
		/*
		 * Gets all the players participating in the Journey
		 */
		return ArrayLists.journeyPlayers;
	}
	
	public static ArrayList<Player> getOnlinePlayers(){
		/*
		 * Gets all players on the server, this includes those invisible (Players in Admin mode)
		 */
		return ArrayLists.onlinePlayers;
	}
	
	public static ArrayList<String> getJourneyPlayerNames(){
		/*
		 * Gets all the names of the players participating in the Journey
		 */
		return ArrayLists.journeyPlayersNames;
	}

	public static void createRegion(MyConfig configName, int parseInt, Selection weSelection) {
		/*
		 * Creates a new region by adding it to the config "ChallengesConfig.yml"
		 */
		if(configName.equals(Config.mainConfig)){
			configName.set("Hub.Location", weSelection.getRegionSelector());
			configName.saveConfig();
		}else{
			configName.set("Challenge." + parseInt + ".Location", weSelection.getRegionSelector());
			configName.saveConfig();
		}
		
	}
	
	public static int getRegion(Player player){
		for(int i = 0; i <= Challenges.totalChallenges; i++){
			if(i == 0){
				bob[i] = (Selection) Config.mainConfig.get("Hub.Location");
			}else{
				bob[i] = (Selection) Config.challengesConfig.get("Challenge." + i + ".Location");
			}
		}
		for(int a = 0; a <= Challenges.totalChallenges; a++){
			for(Selection diffRegions : bob){
				if(diffRegions.contains(player.getLocation())){
					return a;
				}
			}
		}
		return 404;
	}
	
}















