package me.taylory5.theepicjourney;

public enum Status {
	INLOBBY(true),
	STARTING(false),
	INGAME(false);
	
	private boolean canJoin;

	Status(boolean canJoin){
		this.canJoin = canJoin;
	}
	
}
