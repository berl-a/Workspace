package com.bg.my_paint_program;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class MainCycle {
	public static JFrame frame;
	public static DrawPanel drawPanel = new DrawPanel();
	private static final String CURSOR_IMAGE_URL = "res/cursor1.png";
	
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				frame = new JFrame();
				//frame.setAlwaysOnTop(true);
				JFrame.setDefaultLookAndFeelDecorated(true);
				
				//GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				//GraphicsDevice gd = ge.getDefaultScreenDevice();
				//System.out.println(gd.isWindowTranslucencySupported(WindowTranslucency.TRANSLUCENT));
				frame.setUndecorated(true);
				//frame.setBackground(new Color(255,255,255,1));
				//frame.setBackground(new Color(255,255,255,255));
				
				try {
					BufferedImage cursorImage;
				    cursorImage = ImageIO.read(getClass().getResource(CURSOR_IMAGE_URL));
					frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0,0), "Cursor"));
				} catch (IOException e) {}
				
				frame.setBounds(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
				frame.setContentPane(drawPanel);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
