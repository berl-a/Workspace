package com.bg.my_paint_program;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MyMouseWheelListener implements MouseWheelListener{

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int rotation = - e.getWheelRotation();
		if(rotation < 0){
			MainCycle.drawPanel.setBackground(MyMouseClickListener.pickAColor(e.getPoint()));
			MainCycle.drawPanel.repaint();
		}
	}

}
