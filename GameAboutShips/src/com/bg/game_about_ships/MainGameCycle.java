package com.bg.game_about_ships;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class MainGameCycle {

	protected static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	
	private static Timer t;

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				
				final JFrame frame = new JFrame();
				frame.setBounds(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
				GamePanel gp = new GamePanel();
				frame.setContentPane(gp);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setUndecorated(true);
				frame.addKeyListener(gp);
				frame.getContentPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
				frame.setVisible(true);
				
				
				final MyColorChooser colorChooser = new MyColorChooser();
				t = new Timer(10, new ActionListener(){
					public void actionPerformed(ActionEvent ae){
						if(MyColorChooser.finished){
							frame.getContentPane().setBackground(colorChooser.getSelectedColor());
							t.stop();
						}
					}
				});
				t.setRepeats(true);
				t.start();
				
				new TargetFactory().start();
			}
		});
	
	}

}
