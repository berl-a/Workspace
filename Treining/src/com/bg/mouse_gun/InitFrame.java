package com.bg.mouse_gun;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InitFrame extends JFrame implements FocusListener{
	
	private static final int TICK_DELAY = 1000;
	private static final int TIME_BEFORE_SHOOTING = 5000;
	JTextField field1 = new JTextField(5);
	JTextField field2 = new JTextField(5);

	
	private static final long serialVersionUID = 1L;

	InitFrame(){
		
		setTitle("MouseGun");
		setLayout(new FlowLayout());
		field1.setToolTipText("Shoot delay in milisecounds");
		field2.setToolTipText("Number of shoots");
		add(field1);
		add(field2);
		field2.addFocusListener(this);
		pack();
		setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - getWidth()/2, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - getHeight()/2);
		setVisible(true);
	}

	@Override
	public void focusGained(FocusEvent e) {
		
		if(goodNumber(field1))
			field1.setBackground(Color.GREEN);
		else
			field1.setBackground(Color.RED);
		
		if(goodNumber(field2))
			field2.setBackground(Color.GREEN);
		else
			field2.setBackground(Color.RED);
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource() == field2){
			if(goodNumber(field1) && goodNumber(field2)){
				setVisible(false);
				Gun g = new Gun();
				g.shootDelay = Long.parseLong(field1.getText());
				g.numberOfShoots = Long.parseLong(field2.getText());
				JOptionPane.showMessageDialog(null, "Count to five");
				g.playTicks(TIME_BEFORE_SHOOTING, TICK_DELAY);
				
			}
		}
		
	}

	private boolean goodNumber(JTextField field) {
		try{
			int i = Integer.parseInt(field.getText());
			return true;
		}catch(Exception e){
			
		}
		return false;
	}
}
