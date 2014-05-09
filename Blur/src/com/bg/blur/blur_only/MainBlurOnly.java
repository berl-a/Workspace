package com.bg.blur.blur_only;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainBlurOnly {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setBounds(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		frame.setUndecorated(true);
		frame.setContentPane(new BlurPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}
