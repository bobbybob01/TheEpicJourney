package me.taylory5.theepicjourney.challenges;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class Books {
	
	public static ItemStack setBookContents(int challengeNumber){
		/*
		 * Sets the books contents given the challenge number and returns the book in an ItemStack also
		 */
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
		if(book.getItemMeta() instanceof BookMeta){
			BookMeta meta = (BookMeta) book.getItemMeta();
		
			meta.setTitle(getDisplayName(challengeNumber));
			meta.setAuthor("taylory5");
			meta.addPage(getPageContents(challengeNumber, 1));
			book.setItemMeta(meta);
		
			return book;
		}
		return null;
	}
	
	private static String getPageContents(int challengeNumber, int pageNumber) {
		/*
		 * Sets the contents of the book's pages given the challenge number and page number
		 */
		switch(challengeNumber){
		case 1: 
			switch(pageNumber){
			case 1: return ChatColor.DARK_GRAY + "Blah blah blah blah blah.";
			}
		}

		return null;
	}

	private static String getDisplayName(int challengeNumber){
		/*
		 * Returns the challenges name, used as the book's title
		 */
		return Challenges.getName(challengeNumber);
	}
}
