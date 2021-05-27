import java.awt.image.BufferedImage;

public class DecodeParallel implements Runnable {
	
	// Variables
	BufferedImage img;
	int len;
	int start;
	
	// Constructor
	public DecodeParallel(BufferedImage img, int len, int start) {
		this.img = img;
		this.len = len;
		this.start = start;
	}
	
	public int limit(BufferedImage image) {
		// Bitmask to get every digit from each character
		int bitMask = 0x00000001;
		
		// Recovery start position
		int x = 0;					
		int y = 0;			
		int flag;
		int count = 0;
		
		for(int i = 0; i < 1000000; i++) {	
			int bit = 0;
			
			// Join 8 digits to form a character
			for(int j = 0; j < 8; j++) {	
				
				// Traverse the image from left to right
				// When reaching the right side of the image, go down a row
				// Get the last digit of the pixel
				if(x < image.getWidth()) {
					flag = image.getRGB(x, y) & bitMask;
					x++;
				} else {
					x = 0;
					y++;
					flag = image.getRGB(x, y) & bitMask;
				}
				
				// Store the extracted digits into an integer as a ASCII number
				if(flag == 1) {					
					bit = bit >> 1;	
					bit = bit | 0x80;
				} else {					
					bit = bit >> 1;
				}				
			}
			
			// Transform the ASCII from decimal to character
			char c = (char) bit;
			if(c == ';') {
				return count;
			} else {
				count++;
			}
		}
		return count;
	}
	
	// Message recovery
	public static char[] recover(BufferedImage image, int len, int start) {
		// Bitmask to get every digit from each character
		int bitMask = 0x00000001;
		
		// Recovery start position
		int x = start;					
		int y = 0;			
		int flag;
		
		// Character array to store the message characters
		char[] c = new char[len];	
		
		for(int i = 0; i < c.length; i++) {	
			int bit = 0;
			
			// Join 8 digits to form a character
			for(int j = 0; j < 8; j++) {	
				
				// Traverse the image from left to right
				// When reaching the right side of the image, go down a row
				// Get the last digit of the pixel
				if(x < image.getWidth()) {
					flag = image.getRGB(x, y) & bitMask;
					x++;
				} else {
					x = 0;
					y++;
					flag = image.getRGB(x, y) & bitMask;
				}
				
				// Store the extracted digits into an integer as a ASCII number
				if(flag == 1) {					
					bit = bit >> 1;	
					bit = bit | 0x80;
				} else {					
					bit = bit >> 1;
				}				
			}
			
			// Transform the ASCII from decimal to character
			c[i] = (char) bit;
			if(c[i] == ';') {
				break;
			} 
		}
		return c;
	}
	
	// Thread main function
	@Override
	public void run() {
		recover(img, len, start);
	}
}