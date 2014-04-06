package main;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class STimer implements MouseListener, KeyListener {

	
	public STimer(){
		JFrame frame=new JFrame("1");
		Image icon=new ImageIcon("res/Icon.jpg").getImage();
		frame.setIconImage(icon);
		frame.setLocation(0, 0);
		frame.pack();
		frame.setVisible(true);
		frame.addMouseListener(this);
		frame.addKeyListener(this);
		frame.setFocusable(true);
		frame.setAlwaysOnTop(true);
		frame.setF
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(e.getButton());
		if(e.getButton()==1){}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode()+"kl");
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
