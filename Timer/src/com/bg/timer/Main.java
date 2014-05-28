package com.bg.timer;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;

public class Main {

	public static JFrame frame;
	public static TimerPanel panel = new TimerPanel();
	
	public static void main(String[] args) {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setUndecorated(true);
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setBounds((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 250), 25, 250, 200);
		frame.setShape(new RoundRectangle2D.Double(0d, 0d, frame.getSize().getWidth(), frame.getSize().getHeight(), 20d, 20d));
		
		
		frame.setBackground(new Color(10, 200, 10));
		frame.setOpacity(0.7f);
		
		
		frame.setContentPane(panel);
		panel.addMouseMotionListener(panel);

		frame.setVisible(true);
	}

}
