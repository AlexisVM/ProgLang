import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class EncodeParallel implements Runnable {
	
	// Variables
	BufferedImage img;
	private static String message;
	private int start;
	static String route;
	
	// Constructor
	public EncodeParallel(BufferedImage img, String message, int start, String route) {
		this.img = img;
		this.message = message;
		this.start = start;
		EncodeParallel.route = route;
	}

	// Add garbage information to the message
		public String annex() {
			message = message.concat(";"); 
	        return message;
		}
	
	public static BufferedImage hide(BufferedImage image, String text, int start) {
		// Bitmask to extract every digit from each character
		int bitMask = 0x00000001;	
		
		// Hiding start position
		int bit;					
		int x = start;					
		int y = 0;	
		
		for(int i = 0; i < text.length(); i++) {
			// Get the ASCII of a character
			bit = (int) text.charAt(i);	
			
			// The ASCII will be split into its 8 bits
			for(int j = 0; j < 8; j++) {
				
				// Get the LSB of the character
				int flag = bit & bitMask;	
				
				// If the bit equals 1, add it to the pixel LSB
				if(flag == 1) {	
					if(x < image.getWidth()) {
						image.setRGB(x, y, image.getRGB(x, y) | 0x00000001); 	
						x++;
					} else {
						x = 0;
						y++;
						image.setRGB(x, y, image.getRGB(x, y) | 0x00000001); 	
					}
				// If the bit equals 0, remove the pixel LSB value
				} else {	
					if(x < image.getWidth()) {
						image.setRGB(x, y, image.getRGB(x, y) & 0xFFFFFFFE);	
						x++;
					} else {
						x = 0;
						y++;
						image.setRGB(x, y, image.getRGB(x, y) & 0xFFFFFFFE);	
					}
				}
				// Get the next digit from the character
				bit = bit >> 1;				
			}			
		}	
		return image;
	}
	
	// Image writing method
	public static BufferedImage newImage(BufferedImage image) {
		//System.out.print(route);
		try {
			ImageIO.write(image, "png", new File(route + "\\steg1.png"));	
		} catch (IOException e) {
			
		}		
		return image;
	}

	@Override
	public void run() {
		hide(img, message, start);
	}
}