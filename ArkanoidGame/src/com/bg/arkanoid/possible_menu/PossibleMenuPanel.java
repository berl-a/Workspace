package com.bg.arkanoid.possible_menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Calendar;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PossibleMenuPanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener{
	private static final long serialVersionUID = 1L;
	
	
	private static final int DELAY_OF_REFRESHING = 20;
	
	private LinkedList<MyMenuButton> buttons = new LinkedList<MyMenuButton>();

	public PossibleMenuPanel(){
		
		GridLayout layout = new GridLayout(3, 1);
		
		setLayout(layout);
		
		
		//setBackground(Color.DARK_GRAY);
		
		
		buttons.add(new MyMenuButton("Help", Color.GREEN, 150));
		buttons.add(new MyMenuButton("Start", Color.YELLOW, 150));
		buttons.add(new MyMenuButton("End", Color.BLUE, 150));
		
		for(MyMenuButton b : buttons){
			add(b);
		}
		
		Timer t = new Timer(DELAY_OF_REFRESHING, this);
		//t.start();
		repaint();
	}


	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void paintComponent(Graphics badG){
		super.paintComponent(badG);
		Graphics2D g = (Graphics2D) badG;
		
		for(MyMenuButton b : buttons){
			b.render(g);
		}
		
	}
	
	
	public void mousePressed(MouseEvent e) {
		for(MyMenuButton b : buttons)
			if(b.getBounds().contains(e.getLocationOnScreen()))
				b.press();
	}


	public void mouseReleased(MouseEvent e) {
		for(MyMenuButton b : buttons)
			if(b.getBounds().contains(e.getLocationOnScreen()))
				b.release();
	}
	
	public void mouseMoved(MouseEvent e) {
		for(MyMenuButton b : buttons)
			if(b.getBounds().contains(e.getLocationOnScreen()))
				b.changeColorBit();
	}
	
	public void mouseDragged(MouseEvent e) {
		
	}
	
	
	
	public void mouseClicked(MouseEvent arg0) {
	}
	
	public void mouseEntered(MouseEvent e) {
	}
	
	public void mouseExited(MouseEvent e) {
	}


	

	
}
