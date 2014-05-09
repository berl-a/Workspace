package com.bg.arkanoid.menu_elements;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPanel extends JPanel{

	private static final int NUMBER_OF_LEVELS_HORIZ = 2;
	private static final int NUMBER_OF_LEVELS_VERT = 2;

	MenuPanel() throws IOException{
		
		
		GridLayout layout = new GridLayout(NUMBER_OF_LEVELS_HORIZ, NUMBER_OF_LEVELS_VERT);
		layout.setVgap(5);
		layout.setHgap(5);
		
		setLayout(layout);
		
		for(int i = 0 ; i < NUMBER_OF_LEVELS_HORIZ * NUMBER_OF_LEVELS_VERT ; i++){
			
			try {
				JButton b;
				
				BufferedImage bi0 = ImageIO.read(getClass().getResource("res/11.png"));
				b = new JButton(String.valueOf(i), new ImageIcon(bi0));
				
				BufferedImage bi1 = ImageIO.read(getClass().getResource("res/22.png"));
				b.setPressedIcon(new ImageIcon(bi1));
				
				b.setBorder(BorderFactory.createLineBorder(Color.GREEN));
				add(b);
				
			} catch (IOException e) {}
			
		}
			
	}
	
}
