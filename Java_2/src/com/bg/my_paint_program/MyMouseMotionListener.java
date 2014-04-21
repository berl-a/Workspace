package com.bg.my_paint_program;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MyMouseMotionListener extends MouseMotionAdapter{

	
	@Override
	public void mouseDragged(MouseEvent e) {
		if(MyMouseClickListener.leftButtonDown){
			MainCycle.drawPanel.addPointToThisLine(e.getLocationOnScreen(), MainCycle.drawPanel.currentColor);
		}
	}

}
