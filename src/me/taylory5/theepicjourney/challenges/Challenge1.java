package me.taylory5.theepicjourney.challenges;

import java.util.Random;

import me.taylory5.theepicjourney.other.GenerateRandomNumber;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class Challenge1 {

	static Random random;
	static int bob;
	
	public static void setup(){
		Location pos1 = new Location(Bukkit.getWorld("world"), -100, 100, 310);
	    Location pos2 = new Location(Bukkit.getWorld("world"), -100, 100, 300);
	    //Cuboid area = new Cuboid(pos1, pos2);
	    
	    //for(Block blocks : area){
	    	bob = GenerateRandomNumber.between(1, 3);
	    	//switch(bob){
	    		//case 1: blocks.setType(Material.DIAMOND_ORE);break;
	    		//case 2: blocks.setType(Material.GOLD_ORE);break;
	    		//case 3: blocks.setType(Material.STONE);break;
	    		//default: blocks.setType(Material.BEDROCK);break;
	  		}
	   //	}
	//}
}
