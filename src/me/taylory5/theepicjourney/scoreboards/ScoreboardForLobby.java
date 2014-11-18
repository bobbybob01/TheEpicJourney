package me.taylory5.theepicjourney.scoreboards;

import me.taylory5.theepicjourney.Journey;
import me.taylory5.theepicjourney.config.Config;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardForLobby {

	static Scoreboard board;
	static Objective objective;
	static Score score1;
	static Score score2;
	
	
	@SuppressWarnings("deprecation")
	public static void setupBoard(){
		board = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
		objective = board.registerNewObjective(ChatColor.GOLD + "TheEpicJourney", "dummy");
		objective.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + " TheEpicJourney ");
		objective.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		score1 = objective.getScore(Bukkit.getServer().getOfflinePlayer(ChatColor.DARK_GRAY + "Players: "));
		score1.setScore(0);
		score2 = objective.getScore(Bukkit.getServer().getOfflinePlayer(ChatColor.DARK_GRAY + "Coins: "));
		score2.setScore(0);
		
		//spawnboard(Journey.getOnlinePlayers(), board, objective, score1, score2);
	}	
	
	/*@SuppressWarnings("deprecation")
	public static void spawnBoard(Scoreboard board, Objective objective, Score score1, Score score2){
		score1 = objective.getScore(Bukkit.getServer().getOfflinePlayer(ChatColor.DARK_GRAY + "Players: "));
		score1.setScore(0);
	
		score2 = objective.getScore(Bukkit.getServer().getOfflinePlayer(ChatColor.DARK_GRAY + "Coins: "));
		score2.setScore(0);
	}*/
	
	@SuppressWarnings("deprecation")
	public static void updateBoardContents(){
		for(Player player : Journey.getOnlinePlayers()){
			score1 = objective.getScore(Bukkit.getServer().getOfflinePlayer(ChatColor.DARK_GRAY + "Players: "));
			score1.setScore(Journey.getJourneyPlayers().size());
	
			score2 = objective.getScore(Bukkit.getServer().getOfflinePlayer(ChatColor.DARK_GRAY + "Coins: "));
			score2.setScore(Config.manager.getNewConfig(player.getUniqueId().toString() + ".yml").getInt("Stats.Coins"));
	
			//Main.manager.getNewConfig(players.getUniqueId().toString() + ".yml").getInt("Stats.Coins")
		
			player.setScoreboard(board);
		}
	}
}
