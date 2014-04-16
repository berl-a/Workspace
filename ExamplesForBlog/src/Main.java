import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main {

	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setBounds(0, 0, 500, 500);
		frame.setVisible(true);
		frame.setContentPane(new JPanel(){
			public void paint(Graphics g){
				g.setColor(Color.GREEN);
				g.fillArc(0, 0, 400, 400, 0, 360);
			}   //Привіт блогу!
		});

	}

}
