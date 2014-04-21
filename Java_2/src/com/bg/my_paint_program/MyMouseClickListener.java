package com.bg.my_paint_program;

import java.awt.Color;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseClickListener extends MouseAdapter{

	
	public static boolean leftButtonDown;
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		
		if(e.getButton() == 2){
			if(MainCycle.frame.getBackground().getAlpha() == 255){
				MainCycle.drawPanel.currentColor = pickAColor(e.getLocationOnScreen());
				MainCycle.drawPanel.repaint();
			}else{
				Color frameBack = MainCycle.frame.getBackground();
				Color panelBack = MainCycle.frame.getBackground();
				MainCycle.frame.setBackground(new Color(MainCycle.frame.getBackground().getRed(),MainCycle.frame.getBackground().getGreen(),MainCycle.frame.getBackground().getBlue(),255));
				MainCycle.drawPanel.currentColor = pickAColor(e.getLocationOnScreen());
				MainCycle.drawPanel.repaint();
				MainCycle.frame.setBackground(frameBack);
			}
		}
		
		if(e.getButton() == 3){
			MainCycle.drawPanel.undo();
		}
		
		if(e.getButton() == 1){
			MainCycle.drawPanel.startNextLine();
			MainCycle.drawPanel.addPointToThisLine(e.getLocationOnScreen(), MainCycle.drawPanel.currentColor);
			leftButtonDown = true;
		}
	}

	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == 1)
			leftButtonDown = false;
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(leftButtonDown)
			MainCycle.drawPanel.addPointToThisLine(e.getLocationOnScreen(), Color.BLUE);
	}
	
	public static Color pickAColor(Point location) {
		
		try{
			Robot r = new Robot();
			
			//Color frameBack = MainCycle.frame.getBackground();
			//MainCycle.frame.setOpacity(1);
			
			Color newColor = r.getPixelColor(location.x, location.y);
			System.out.println("picked " + newColor.toString());
			//MainCycle.frame.setBackground(frameBack);
			
			return newColor;
			
		}catch(Exception e){
			return null;
		}
	}


}
