import java.awt.image.BufferedImage;

public class DecodeSequential {
	
	// Variables
	BufferedImage img;
	
	// Constructor
	public DecodeSequential(BufferedImage img) {
		this.img = img;
	}
	
	// Message recovery
	public static char[] recover(BufferedImage image) {
		// Bitmask to get every digit from each character
		int bitMask = 0x00000001;
		
		// Recovery start position
		int x = 0;					
		int y = 0;			
		int flag;
		
		// Character array to store the message characters
		char[] c = new char[1000000];	
		
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
			// System.out.print(c[i]);
		}
		return c;
	}
}

