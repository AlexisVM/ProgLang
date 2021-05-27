import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;  
import javax.imageio.ImageIO;

public class Main implements ActionListener {
	JTextArea msg;
	static JTextArea mrc;
	JTextArea rte;
	JTextArea rtd;
	JTextField nme, nmd;
	JButton sendE, sendD, close;
	static JLabel timeEncPar, timeEncSeq, timeDecPar, timeDecSeq;
	static Boolean waiting = true;
	static Boolean running = true;
	static Boolean encrypting;
	static String messageSeq, messagePar, route, name;
	static JFrame f = new JFrame("Image Steganography");
	
	//Build GUI
	Main(){

		// IMAGE HIDING FIELDS
		// ---------------------------------------------
		JLabel enc, e1, e2, e3;
		
		// Message hiding title
		enc = new JLabel(); 
		enc.setBounds(50,50,200,30); 
		enc.setText("Message hiding");
		enc.setFont(new Font("Lato", Font.BOLD, 24));
		
		// Message to hide
	    e1 = new JLabel(); 
	    e1.setBounds(50,100,200,30); 
	    e1.setText("Enter message to hide");
	    e1.setFont(new Font("Lato", Font.BOLD, 16));
		msg = new JTextArea(""); 
		msg.setLineWrap(true);
		msg.setBounds(50,130,600,70);
		
		// Image route
		e2 = new JLabel(); 
		e2.setBounds(50,210,200,30); 
		e2.setText("Enter image route");
		e2.setFont(new Font("Lato", Font.BOLD, 16));
		rte = new JTextArea(""); 
		rte.setLineWrap(true);
		rte.setBounds(50,240,600,40);
		
		// Image name
		e3 = new JLabel(); 
		e3.setBounds(50,290,200,30); 
		e3.setText("Enter image name");
		e3.setFont(new Font("Lato", Font.BOLD, 16));
		nme = new JTextField(""); 
		nme.setBounds(50,320,200,30);
		
		// Hide button
		sendE = new JButton("Hide");
		sendE.setBounds(50,370,200,30);
		sendE.setFont(new Font("Lato", Font.BOLD, 16));
		
		// Encryption execution results
		timeEncPar = new JLabel();
		timeEncSeq = new JLabel(); 
		timeEncPar.setBounds(50,440,400,60); 
		timeEncPar.setFont(new Font("Lato", Font.BOLD, 16));
		timeEncSeq.setBounds(50,460,400,60); 
		timeEncSeq.setFont(new Font("Lato", Font.BOLD, 16));
		
		// IMAGE RECOVERY FIELDS
		// ---------------------------------------------
		JLabel dec, d1, d2, d3;
		
		// Message recovery title
		dec = new JLabel(); 
		dec.setBounds(700,60,600,30); 
		dec.setText("Message recovery");
		dec.setFont(new Font("Lato", Font.BOLD, 24));
		
		// Image route
		d1 = new JLabel(); 
		d1.setBounds(700,100,600,30); 
		d1.setText("Enter image route");
		d1.setFont(new Font("Lato", Font.BOLD, 16));
		rtd = new JTextArea(""); 
		rtd.setBounds(700,130,600,40);
				
		d2 = new JLabel(); 
		d2.setBounds(700,180,200,30); 
		d2.setText("Enter image name");
		d2.setFont(new Font("Lato", Font.BOLD, 16));
		nmd = new JTextField(""); 
		nmd.setBounds(700,210,200,30);
		
		// Message hidden
	    d3 = new JLabel(); 
	    d3.setBounds(700,250,200,30); 
	    d3.setText("Message recovered");
	    d3.setFont(new Font("Lato", Font.BOLD, 16));
	    mrc = new JTextArea(""); 
	    mrc.setLineWrap(true);
	    mrc.setBounds(700,280,600,70);
				
		//Add button
		sendD = new JButton("Recover");
		sendD.setBounds(700,370,200,30);
		sendD.setFont(new Font("Lato", Font.BOLD, 16));
		
		//Add close button
		close = new JButton("Exit");
		close.setBounds(50,420,200,30);
		close.setFont(new Font("Lato", Font.BOLD, 16));
		
		//Decrypt execution results
		timeDecPar = new JLabel();
		timeDecSeq = new JLabel(); 
		timeDecPar.setBounds(700,440,400,60); 
		timeDecPar.setFont(new Font("Lato", Font.BOLD, 16));
		timeDecSeq.setBounds(700,460,400,60); 
		timeDecSeq.setFont(new Font("Lato", Font.BOLD, 16));
		
		//Add action listener
		sendE.addActionListener(this);
		sendD.addActionListener(this);
		close.addActionListener(this);
		
		//Add components to frame
		f.add(enc);f.add(e1);f.add(msg);f.add(e2);f.add(rte);f.add(e3);f.add(nme);f.add(sendE);f.add(timeEncPar);f.add(timeEncSeq);
		f.add(dec);f.add(d1);f.add(rtd);f.add(d2);f.add(nmd);f.add(d3);f.add(mrc);f.add(sendD);f.add(timeDecPar);f.add(timeDecSeq);
		f.add(close);
		
		//Start the frame
		f.setSize(1380,600);  
		f.setLayout(null);  
		f.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {  
		if(e.getSource() == sendE) {
			messageSeq = msg.getText();
			messagePar = msg.getText();
	        route = rte.getText(); 
	        name = nme.getText();
	        encrypting = true;
	        waiting = false;
		} else if(e.getSource() == sendD) {
	        route = rtd.getText(); 
	        name = nmd.getText();
	        encrypting = false;
	        waiting = false;
		} else if(e.getSource() == close){
			System.out.println("close");
			timeEncPar.setText("hola");
			running = false;
		}
    } 
	
	public static void main(String[] args) throws IOException, InterruptedException {
		new Main(); 
		System.out.println("waiting");
		while(waiting) {
			Thread.sleep(200);
		}
		
		if(encrypting) {		
			// ---------------------------------------------------------------------------
			// MESSAGE HIDING (PARALLEL)
			// ---------------------------------------------------------------------------
			
			// Read the image
			BufferedImage original = ImageIO.read(new File(route + "\\" + name));
			int rowsOriginal = original.getHeight();
	        
	        System.out.println("Image = " + route + "\\" + name + "\n");
	        System.out.println("Message = " + messagePar);
	        
	        // Divide the image into parts
	        int len = messagePar.length();
	        String p1 = messagePar.substring(0, Math.floorDiv(len, 2));
	        String p2 = messagePar.substring(Math.floorDiv(len, 2));
	        
	        // Class instances, one per message part
	        EncodeParallel ep1 = new EncodeParallel(original, p1, 0, route);
	        EncodeParallel ep2 = new EncodeParallel(original, p2, rowsOriginal/2, route);
	        
	        // Thread creation, assign one per instance
	        System.out.println("p2 = " + p2);
	        Thread t1 = new Thread(ep1);
			Thread t2 = new Thread(ep2);
			
			// Start thread execution
			long ep_start = System.nanoTime();
			t1.start();
			t2.start();
			long ep_end = System.nanoTime();
			t1.join();
			t2.join();
			
	        System.out.println("Encode Parallel Time = " + (ep_end - ep_start) + " ns");
	        timeEncPar.setText("Encode Parallel Time = " + (ep_end - ep_start) + " ns");
	        
	        // Generate the new image with the message hidden
			EncodeParallel.newImage(original);
		
			// ---------------------------------------------------------------------------
			// MESSAGE HIDING (SEQUENTIAL)
			// ---------------------------------------------------------------------------
			
			// Class instance
	        EncodeSequential es = new EncodeSequential(original, messageSeq, route);
	        
	        // Hide the message
	        long es_start = System.nanoTime();
	        messageSeq = es.annex();
	        es.hide(original, messageSeq);
	        long es_end = System.nanoTime();
	        System.out.println("Encode Sequential Time = " + (es_end - es_start) + " ns");
	        timeEncSeq.setText("Encode Sequential Time = " + (es_end - es_start) + " ns");
	
	        // Generate the new image with the message hidden
	        es.newImage(original);
	        
		} else { 
			
		
	        // ---------------------------------------------------------------------------
	        // MESSAGE RECOVERY (PARALLEL)
			// ---------------------------------------------------------------------------
	        // Read the image
			BufferedImage hiddenParallel = ImageIO.read(new File(route + "\\steg1.png"));
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
			System.out.println("Auxlen = " + auxlen);
			System.out.println("Auxpos = " + auxpos);
			
			
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
						
			System.out.println("");
	        System.out.println("Decode Parallel Time = " + (dp1_time + dp2_time) + " ns");
	        timeDecPar.setText("Decode Parallel Time = " + (dp1_time + dp2_time) + " ns");

	        // ---------------------------------------------------------------------------
	        // MESSAGE RECOVERY (SEQUENTIAL)
			// ---------------------------------------------------------------------------
	        
	        // Read the image
			BufferedImage hiddenSequential = ImageIO.read(new File(route + "\\steg1.png"));
			
			// Class instance
	        DecodeSequential ds = new DecodeSequential(hiddenSequential);
	        
	        // Recover the message
	        System.out.print("Hidden message = ");
	        long ds_start = System.nanoTime();
	        char charseq[] = ds.recover(hiddenSequential);
	        String recoverSeq = String.valueOf(charseq);
	        System.out.println(charseq);
	        mrc.setText(recoverSeq);
	        long ds_end = System.nanoTime();
	        System.out.println("Decode Sequential Time = " + (ds_end - ds_start) + " ns");
	        timeDecSeq.setText("Decode Sequential Time = " + (ds_end - ds_start) + " ns");
		}
		
        // mrc.setText(message);
        Thread.sleep(10000);
		f.setVisible(false);
	}
}