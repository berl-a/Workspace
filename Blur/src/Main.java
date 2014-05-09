import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class Main {

	static BufferedImage bufIm;
	
	public static void main(String[] args){
		
		JFrame frame = new JFrame();
		frame.setBounds(0,0,800,800);
		
		Test t = new Test();
		
		frame.setContentPane(t);
		t.paintq();
		
		frame.setVisible(true);
	}
	
}
