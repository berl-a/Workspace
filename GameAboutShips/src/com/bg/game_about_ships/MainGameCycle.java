package com.bg.game_about_ships;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainGameCycle {

	protected static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				JFrame frame = new JFrame();
				frame.setBounds(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
				GamePanel gp = new GamePanel();
				frame.setContentPane(gp);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setUndecorated(true);
				frame.addKeyListener(gp);
				frame.getContentPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
				frame.setVisible(true);
				
				Color backgroundColor = JColorChooser.showDialog(null, "Choose a color of background", null);
				if(backgroundColor == null)
					backgroundColor = DEFAULT_BACKGROUND_COLOR;
				frame.getContentPane().setBackground(backgroundColor);
				
				new TargetFactory().start();
			}
		});
	
	}

}
