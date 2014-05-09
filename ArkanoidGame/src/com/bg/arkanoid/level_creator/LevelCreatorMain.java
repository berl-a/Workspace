package com.bg.arkanoid.level_creator;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class LevelCreatorMain {

	public static JFrame frame;
	
	public static void main(String[] args) {
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setUndecorated(true);
		frame.setBounds(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new LevelCreatorPanel());
		frame.setVisible(true);
	}

}
