package me.taylory5.theepicjourney;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class ArrayLists {

	public static ArrayList<Player> journeyPlayers = new ArrayList<Player>(); //All the players online in "play" mode
	public static ArrayList<String> journeyPlayersNames = new ArrayList<String>(); //All the players online in "play" mode
	public static ArrayList<Player> onlinePlayers = new ArrayList<Player>(); //All total players, including those not in "play" mode
	public static ArrayList<Player> adminModePlayers = new ArrayList<Player>();//All the players in "Admin" mode, these players in this ArrayList cannot play in the game
	
}
