import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Main implements ActionListener {
	
	// GUI Variables
	JFileChooser select_original_image; 
	JFileChooser select_modified_image;
	
	JButton hide_button;
	JButton recover_button; 
	JButton close_button; 
	
	JButton button_original_image; 
	JButton button_modified_image;
	
	JTextArea route_original_image;
	JTextArea route_modified_image;
	
	JTextArea field_original_message;
	static JTextArea field_recover_message;
	
	static JLabel time_hide_parallel;
	static JLabel time_hide_sequential; 
	static JLabel time_recover_parallel; 
	static JLabel time_recover_sequential;
	
	static Boolean waiting = true;
	static Boolean encrypting;
	
	static File file_original_image;
	static File file_modified_image;
	
	static String message_parallel;
	static String message_sequential;
	static JFrame f = new JFrame("Image Steganography");
	
	// Build GUI
	Main(){

		// ---------------------------------------------------------------------------------------------------------------------------
		// IMAGE HIDING FIELDS
		// ---------------------------------------------------------------------------------------------------------------------------
		JLabel hide_title, set_message, set_image;
		
		// Message hiding title
		hide_title = new JLabel(); 
		hide_title.setBounds(50,50,200,30); 
		hide_title.setText("Message hiding");
		hide_title.setFont(new Font("Lato", Font.BOLD, 24));
		
		// Message to hide
	    set_message = new JLabel(); 
	    set_message.setBounds(50,100,200,30); 
	    set_message.setText("Enter message to hide");
	    set_message.setFont(new Font("Lato", Font.BOLD, 16));
		field_original_message = new JTextArea(""); 
		field_original_message.setLineWrap(true);
		field_original_message.setBounds(50,130,600,70);
		
		// Select image
		set_image = new JLabel(); 
		set_image.setBounds(50,210,200,30); 
		set_image.setText("Select image");
		set_image.setFont(new Font("Lato", Font.BOLD, 16));
		button_original_image = new JButton("Examine");
		button_original_image.setBounds(50,240,120,30);
		button_original_image.setFont(new Font("Lato", Font.BOLD, 16));
		route_original_image = new JTextArea(); 
		route_original_image.setLineWrap(true);
		route_original_image.setBounds(50,280,600,40);
		select_original_image = new JFileChooser();
		
		// Hide button
		hide_button = new JButton("Hide");
		hide_button.setBounds(50,340,120,30);
		hide_button.setFont(new Font("Lato", Font.BOLD, 16));
		
		// Encryption execution results
		time_hide_parallel = new JLabel();
		time_hide_sequential = new JLabel(); 
		time_hide_parallel.setBounds(50,400,400,60); 
		time_hide_parallel.setFont(new Font("Lato", Font.BOLD, 16));
		time_hide_sequential.setBounds(50,420,400,60); 
		time_hide_sequential.setFont(new Font("Lato", Font.BOLD, 16));
		
		// ---------------------------------------------------------------------------------------------------------------------------
		// IMAGE RECOVERY FIELDS
		// ---------------------------------------------------------------------------------------------------------------------------
		JLabel recover_title, get_image, get_message;
		
		// Message recovery title
		recover_title = new JLabel(); 
		recover_title.setBounds(700,50,600,30); 
		recover_title.setText("Message recovery");
		recover_title.setFont(new Font("Lato", Font.BOLD, 24));
		
		// Select image
		get_image = new JLabel(); 
		get_image.setBounds(700,100,600,30); 
		get_image.setText("Select image");
		get_image.setFont(new Font("Lato", Font.BOLD, 16));		
		button_modified_image = new JButton("Examine");
		button_modified_image.setBounds(700,130,100,30);
		button_modified_image.setFont(new Font("Lato", Font.BOLD, 16));
		route_modified_image = new JTextArea(); 
		route_modified_image.setLineWrap(true);
		route_modified_image.setBounds(700,170,600,40);
		select_modified_image = new JFileChooser();
				
		// Message hidden
	    get_message = new JLabel(); 
	    get_message.setBounds(700,220,200,30); 
	    get_message.setText("Message recovered");
	    get_message.setFont(new Font("Lato", Font.BOLD, 16));
	    field_recover_message = new JTextArea(""); 
	    field_recover_message.setLineWrap(true);
	    field_recover_message.setBounds(700,250,600,70);
				
		// Add button
		recover_button = new JButton("Recover");
		recover_button.setBounds(700,340,120,30);
		recover_button.setFont(new Font("Lato", Font.BOLD, 16));
		
		// Add close button
		close_button = new JButton("Exit");
		close_button.setBounds(50,390,120,30);
		close_button.setFont(new Font("Lato", Font.BOLD, 16));
		
		//Decrypt execution results
		time_recover_parallel = new JLabel();
		time_recover_sequential = new JLabel(); 
		time_recover_parallel.setBounds(700,400,400,60); 
		time_recover_parallel.setFont(new Font("Lato", Font.BOLD, 16));
		time_recover_sequential.setBounds(700,420,400,60); 
		time_recover_sequential.setFont(new Font("Lato", Font.BOLD, 16));
		
		//Add action listener
		button_original_image.addActionListener(this);
		button_modified_image.addActionListener(this);
		hide_button.addActionListener(this);
		recover_button.addActionListener(this);
		close_button.addActionListener(this);
		
		// Add Hide Components to Frame
		f.add(hide_title);
		f.add(set_message);
		f.add(field_original_message);
		f.add(set_image);
		f.add(route_original_image);
		f.add(button_original_image);
		f.add(select_original_image);
		f.add(hide_button);
		f.add(time_hide_parallel);
		f.add(time_hide_sequential);
		
		// Add Recover Components to Frame
		f.add(recover_title);
		f.add(get_image);
		f.add(route_modified_image);
		f.add(button_modified_image);
		f.add(get_message);
		f.add(field_recover_message);
		f.add(recover_button);
		f.add(time_recover_parallel);
		f.add(time_recover_sequential);
		f.add(close_button);
		
		//Start the frame
		f.setSize(1380,550);  
		f.setLayout(null);  
		f.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {  
		if(e.getSource() == button_original_image) {
			int return_original_image = select_original_image.showOpenDialog(f);
			if(return_original_image == JFileChooser.APPROVE_OPTION) {
                file_original_image = select_original_image.getSelectedFile();
                route_original_image.setText(file_original_image.getAbsolutePath());
			}
			
		} else if(e.getSource() == button_modified_image) {
			int return_modified_image = select_modified_image.showOpenDialog(f);
			if(return_modified_image == JFileChooser.APPROVE_OPTION) {
                file_modified_image = select_modified_image.getSelectedFile();
                route_modified_image.setText(file_modified_image.getAbsolutePath());
			}
			
		} else if(e.getSource() == hide_button) {
			message_sequential = field_original_message.getText();
			message_parallel = field_original_message.getText();
	        encrypting = true;
	        waiting = false;
	        
		} else if(e.getSource() == recover_button) {
	        encrypting = false;
	        waiting = false;
	        
		} else if(e.getSource() == close_button){
			System.exit(0);
		}
    } 
	
	public static void main(String[] args) throws IOException, InterruptedException {
		new Main(); 
		System.out.println("waiting");
		while(waiting) {
			Thread.sleep(200);
		}
		
		if(encrypting) {	
			
			// ---------------------------------------------------------------------------------------------------------------------------
			// MESSAGE HIDING (PARALLEL)
			// ---------------------------------------------------------------------------------------------------------------------------
			
			// Read the image
			BufferedImage original = ImageIO.read(file_original_image);
			int rowsOriginal = original.getHeight();
	        
	        System.out.println("Image = " + file_original_image.getAbsolutePath() + "\n");
	        
	        // Divide the image into parts
	        int len = message_parallel.length();
	        String p1 = message_parallel.substring(0, Math.floorDiv(len, 2));
	        String p2 = message_parallel.substring(Math.floorDiv(len, 2));
	        
	        // Class instances, one per message part
	        EncodeParallel ep1 = new EncodeParallel(original, p1, 0);
	        EncodeParallel ep2 = new EncodeParallel(original, p2, rowsOriginal/2);
	        
	        // Thread creation, assign one per instance
	        Thread t1 = new Thread(ep1);
			Thread t2 = new Thread(ep2);
			
			// Start thread execution
			long ep_start = System.nanoTime();
			t1.start();
			t2.start();
			long ep_end = System.nanoTime();
			t1.join();
			t2.join();
			
			// Print execution time
	        System.out.println("Encode Parallel Time = " + (ep_end - ep_start) + " ns");
	        time_hide_parallel.setText("Encode Parallel Time = " + (ep_end - ep_start) + " ns");
	        
	        // Generate the new image with the message hidden
			EncodeParallel.newImage(original);
		
			// ---------------------------------------------------------------------------------------------------------------------------
			// MESSAGE HIDING (SEQUENTIAL)
			// ---------------------------------------------------------------------------------------------------------------------------
			
			// Class instance
	        EncodeSequential es = new EncodeSequential(original, message_sequential);
	        
	        // Hide the message
	        long es_start = System.nanoTime();
	        message_sequential = es.annex();
	        es.hide(original, message_sequential);
	        long es_end = System.nanoTime();
	        
	        // Print execution time
	        System.out.println("Encode Sequential Time = " + (es_end - es_start) + " ns");
	        time_hide_sequential.setText("Encode Sequential Time = " + (es_end - es_start) + " ns");
	
	        // Generate the new image with the message hidden
	        es.newImage(original);
	        
		} else { 
			
			// ---------------------------------------------------------------------------------------------------------------------------
	        // MESSAGE RECOVERY (PARALLEL)
			// ---------------------------------------------------------------------------------------------------------------------------
			
	        // Read the image
			BufferedImage hiddenParallel = ImageIO.read(file_modified_image);
			int rowsHidden = hiddenParallel.getHeight();
			
			// Define the length of the 2° part of the message to retrieve
			DecodeParallel dplimit = new DecodeParallel(hiddenParallel, 0, 0);
			int auxlen = dplimit.limit(hiddenParallel);
			int auxpos;
			if(auxlen % 2 == 0) {
				auxpos = auxlen/2;
			} else {
				auxpos = auxlen/2 + 1;
			}
			
			// Class instances, one per message part
			DecodeParallel dp1 = new DecodeParallel(hiddenParallel, auxlen/2, 0);
	        DecodeParallel dp2 = new DecodeParallel(hiddenParallel, auxpos, rowsHidden/2);
	        
	        // Thread creation, assign one per instance
	        Thread t3 = new Thread(dp1);
			Thread t4 = new Thread(dp2);
			
			// Start thread execution
			System.out.print("Hidden message = ");
			long dp1_start = System.nanoTime();
			t3.start();
			long dp1_end = System.nanoTime();
			long dp1_time = dp1_end - dp1_start;
			t3.join();
			
			long dp2_start = System.nanoTime();
			t4.start();
			long dp2_end = System.nanoTime();
			long dp2_time = dp2_end - dp2_start;
			t4.join();
			
			// Print execution time
			System.out.println("");
	        System.out.println("Decode Parallel Time = " + (dp1_time + dp2_time) + " ns");
	        time_recover_parallel.setText("Decode Parallel Time = " + (dp1_time + dp2_time) + " ns");

			// ---------------------------------------------------------------------------------------------------------------------------
	        // MESSAGE RECOVERY (SEQUENTIAL)
			// ---------------------------------------------------------------------------------------------------------------------------
	        
	        // Read the image
			BufferedImage hiddenSequential = ImageIO.read(file_modified_image);
			
			// Class instance
	        DecodeSequential ds = new DecodeSequential(hiddenSequential);
	        
	        // Recover the message
	        System.out.print("Hidden message = ");
	        long ds_start = System.nanoTime();
	        char charseq[] = ds.recover(hiddenSequential);
	        long ds_end = System.nanoTime();
	        
	        // Print recovered message in GUI
	        String recoverSeq = String.valueOf(charseq);
	        System.out.println(charseq);
	        field_recover_message.setText(recoverSeq);
	        
	        // Print execution time
	        System.out.println("Decode Sequential Time = " + (ds_end - ds_start) + " ns");
	        time_recover_sequential.setText("Decode Sequential Time = " + (ds_end - ds_start) + " ns");
		}
	}
}