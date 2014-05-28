package com.bg.game_about_ships;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeListener;


public class MyColorChooser implements ColorSelectionModel, MouseListener {
	
	JFrame frame;

	private JPanel panel;
	
	private static final String URL_OF_IMAGE = "res/paletteBig.png";
	private static final String URL_OF_CURSOR_IMAGE = "res/cursor1.png";
	public static BufferedImage image;
	public static BufferedImage cursorImage;
	public static Color color;
	
	private static final int CLICKS_TO_THE_SAME_COLOR_TO_FINISH = 1;
	public static boolean finished;
	
	public MyColorChooser(){
		
		System.out.println("Creating frame...");
		
		frame = new JFrame();

		frame.setBounds(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		frame.setUndecorated(true);
		
		panel = new JPanel(){
			
			public void paint(Graphics g){
				
				System.out.println("paint");
				
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				try {
				    image = ImageIO.read(getClass().getResource(URL_OF_IMAGE));
				    cursorImage = ImageIO.read(getClass().getResource(URL_OF_CURSOR_IMAGE));
				} catch (IOException e) {}
				
				Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0,0), "Cursor");
				panel.setCursor(cursor);
				
				g2d.drawImage(image, frame.getWidth() / 2 - image.getWidth() / 2, frame.getHeight() / 2 - image.getHeight() / 2, null);
				g2d.setColor(getSelectedColor());
				
				g2d.setFont(new Font("MyFont", Font.BOLD, 80));
				g2d.drawString("Choose background color", (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - ("Choose background color".length() * 43 / 2) , 75);
				g2d.fillArc(0, 0, 200, 200, 0, 360);
				
			}
		};
		
		frame.setContentPane(panel);
		
		frame.addMouseListener(this);
		frame.setFocusable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

	public JPanel getPanel(){
		return panel;
	}
	
	public void addChangeListener(ChangeListener arg0) {
		// TODO Auto-generated method stub
		
	}

	public Color getSelectedColor() {
		return color;
	}

	public void removeChangeListener(ChangeListener arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setSelectedColor(Color c) {
		color = c;
		panel.repaint();
	}

	private int numberOfTheSameColors = 0;

	
	public void mouseClicked(MouseEvent e) {
		
		try {
			Color c = new Robot().getPixelColor(e.getXOnScreen(), e.getYOnScreen());
			
			if(c.equals(getSelectedColor()))
				numberOfTheSameColors++;
			setSelectedColor(c);
			
			if(numberOfTheSameColors == CLICKS_TO_THE_SAME_COLOR_TO_FINISH){
				finished = true;
				finish();
			}
				
			frame.setForeground(c);
			System.out.println("Color : " + new Robot().getPixelColor(e.getXOnScreen(), e.getYOnScreen()).getRed() + " " + new Robot().getPixelColor(e.getXOnScreen(), e.getYOnScreen()).getGreen() + " " + new Robot().getPixelColor(e.getXOnScreen(), e.getYOnScreen()).getBlue());
			} catch (AWTException e1) {}
	}

	void finish() {
		finished = true;
		frame.setVisible(false);
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
