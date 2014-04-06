package speed;


import java.awt.Toolkit;

import javax.swing.JFrame;


public class Main {
	


	
	public static void main(String[] args) {
		JFrame frame=new JFrame("BG_Speed");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-400, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-217, 800, 434);
		frame.add(new Road());
		frame.setVisible(true);

	}

}
