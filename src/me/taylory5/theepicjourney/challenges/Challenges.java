package me.taylory5.theepicjourney.challenges;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.inventory.ItemStack;

public class Challenges {

	public static final int totalChallenges = 1;
	static int challengeNumber;
	static Random random;
	public static String[] xyzWorld = {"X1", "Y1", "Z1", "World1", "X2", "Y2", "Z2", "World2", "VillagerX", "VillagerY", "VillagerZ"};
	public static String[] regionStuff = {"X1", "Y1", "Z1", "World1", "X2", "Y2", "Z2", "World2"};
	
	private static HashMap<Integer, String> numAndNames = new HashMap<Integer, String>();
	
	public static int selectRandomChallenge(){
		/*
		 * Randomly selects a challenge and returns the challenge number randomly selected
		 */
		random = new Random();
		challengeNumber = random.nextInt(totalChallenges + 1);
		
		return challengeNumber;
	}
	
	public static void startChallenge(int challengeNumber){
		/*
		 * Starts the given challenge
		 */
		switch(challengeNumber){
		  	case 1: Challenge1.setup();
		  			
		  	default: Challenge1.setup();
		}
					
	}
	
	public static ItemStack getBook(int challengeNumber){
		/*
		 * Returns the book for the specified challenge as an ItemStack
		 */
		return Books.setBookContents(challengeNumber);
	}

	public static String getName(int challengeNumber) {
		/*
		 * Gets the name for the challenge given
		 */
		String name = null;
		
		numAndNames.clear();
		addChallenges();
		name = numAndNames.get(challengeNumber);
		
		return name;
	}
	
	private static void addChallenges(){ // Make sure to change the "totalChallenges" variable above!
		/*
		 * Adds the challenges below to the Journey
		 */
		numAndNames.put(1, "Mining");
	}
	
}
