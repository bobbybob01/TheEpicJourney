package me.taylory5.theepicjourney.other;

import java.util.Random;

public class GenerateRandomNumber {

	public static int between(int lowNum, int highNum){
		Random random = new Random();
		String keepGoing = "yes";
		int num;
		
		do{
			num = random.nextInt();
			
			if(num >= lowNum && num <= highNum){
				keepGoing = "no";
			}
		}while(keepGoing.equalsIgnoreCase("yes"));
		return num;
	}
}
