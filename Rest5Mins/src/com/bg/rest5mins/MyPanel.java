package com.bg.rest5mins;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class MyPanel extends JPanel{
	
	private static final int    BRIGHTNESS_OF_GREY    = 12;
	private static final int    SIZE_OF_ONE_LETTER    = 36;
	private static final int    SIZE_OF_TEXT          = 70;
	private static final int    HEIGHT_OVER_TEXT      = 100;
	private static final int    HEIGHT_OVER_ARC      = 40;
	
	private static final int LENGTH_OF_HVIST = 10;
	private static final int WIDTH_LEFT_FROM_TIME = 50;
	private static final int HEIGTH_UNDER_LEFT_CORNER_OF_TIME = 50;
	public Color    colorFirstOfArc   = Color.RED;
	public Color    colorOfSecoundArc = Color.BLUE;
	public int      arcDiameter       = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.7);
	public int      arcX              = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - arcDiameter/2);
	public int      arcY       	      = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - arcDiameter/2 + HEIGHT_OVER_ARC);
	public int      startAngleFirst   = 0;
	public int      startAngleSecound = 0;
	public int      angleDelay 	      = 0;
	public double   XOfLine           = 0;
	public int      YOfLine           = 2;
	public int      estimatedTimeMilis= MainCycle.BLOCKED_TIME_MILIS - (int)LineRepaintThread.GALMING_OF_LINE;
		
	
	
	public void paint(Graphics g) {
		
		g = (Graphics2D) g;
		
		super.paint(g);//як репейнт, т≥льки краще
		
		MainCycle.frame.panel.setBackground(Color.BLACK);
				
		g.setFont(new Font("MyFont", Font.PLAIN, SIZE_OF_TEXT));
		g.setColor(new Color(BRIGHTNESS_OF_GREY,BRIGHTNESS_OF_GREY,BRIGHTNESS_OF_GREY));
		g.drawString(StopFrame.PRINTED_TEXT, (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - StopFrame.PRINTED_TEXT.length()*SIZE_OF_ONE_LETTER/2), HEIGHT_OVER_TEXT);
		g.drawString(String.valueOf((int)estimatedTimeMilis / 1000), WIDTH_LEFT_FROM_TIME, (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - HEIGTH_UNDER_LEFT_CORNER_OF_TIME));	
		g.setColor(colorFirstOfArc);
		//                                              —рака з≥ стартом
		g.drawArc(arcX, arcY, arcDiameter, arcDiameter, -(startAngleFirst-125), -angleDelay);
		//                                                                 ≤накше рахуЇ проти годинниковой стр≥лки
		for(int i = 0; i<LENGTH_OF_HVIST; i++){
			g.setColor(new Color(0, 0, 255-i*20));
			g.drawArc(arcX, arcY, arcDiameter, arcDiameter, -(startAngleFirst - 125 - i), - angleDelay);
		}
		
		g.setColor(colorOfSecoundArc );
		
		for(int i = 0; i<LENGTH_OF_HVIST; i++){
			g.setColor(new Color(255 - i * 20, 0, 0));
			g.drawArc(arcX, arcY, arcDiameter, arcDiameter, +(startAngleSecound - 25 - i), + angleDelay);
		}
		
		g.setColor(Color.RED);
		g.drawLine(0, 2, (int)XOfLine, YOfLine);
	
	}
	
	
}
