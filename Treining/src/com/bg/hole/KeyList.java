package com.bg.hole;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyList implements KeyListener {

	private static final int DEF_DELTA = 10;
	public static final double DEF_DELTA_DIAM = 1.1;

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 37)
			Main.panel.deltaX = -DEF_DELTA;
		if(e.getKeyCode() == 40)
			Main.panel.deltaY = DEF_DELTA;
		if(e.getKeyCode() == 39)
			Main.panel.deltaX = DEF_DELTA;
		if(e.getKeyCode() == 38)
			Main.panel.deltaY = -DEF_DELTA;
		
		if(e.getKeyCode() == 107)
			Main.panel.deltaDiam = 1.05;
		if(e.getKeyCode() == 109)
			Main.panel.deltaDiam = 0.95;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 37)
			Main.panel.deltaX = 0;
		if(e.getKeyCode() == 40)
			Main.panel.deltaY = 0;
		if(e.getKeyCode() == 39)
			Main.panel.deltaX = 0;
		if(e.getKeyCode() == 38)
			Main.panel.deltaY = 0;
		
		if(e.getKeyCode() == 107)
			Main.panel.deltaDiam = 1;
		if(e.getKeyCode() == 109)
			Main.panel.deltaDiam = 1;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
