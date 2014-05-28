package com.bg.hole;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class HolePanel extends JPanel {
	
	private static final int DELAY_OF_REFR = 2;
	public double centerX = 200;
	public double centerY = 200;
	public double diameter = 300;
	Area a;
	public double deltaX = 0;
	public double deltaY = 0;
	public double deltaDiam = 1;
	
	HolePanel(){
		new Timer(DELAY_OF_REFR, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				centerX += deltaX;
				centerY += deltaY;
				diameter *= deltaDiam;
				repaint();
			}
		}).start();
		setBackground(new Color(0, 0, 0, 0));
		setOpaque(false);
		repaint();
	}
	
	
	public void paintComponent(Graphics badG){
		super.paintComponent(badG);
		Graphics2D g = (Graphics2D)badG;
		
		g.setColor(Color.BLUE.brighter().brighter().brighter());
		a = new Area (new Rectangle2D.Double(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
		a.subtract(new Area(new Ellipse2D.Double(centerX - diameter / 2, centerY - diameter / 2, diameter,diameter)));
		g.fill(a);
	}
	
}
