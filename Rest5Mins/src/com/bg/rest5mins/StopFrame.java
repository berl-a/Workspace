package com.bg.rest5mins;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class StopFrame extends JFrame implements ComponentListener {

	
	public static final String PRINTED_TEXT = "Наталіє Іванівно, відпочиньте!";

	public static volatile boolean goCycle = true;
	
	public MyPanel panel = new MyPanel();

	public int unblockedTime;
	public int blockedTime;
	
	private static final String unblockingCombination = "000";
	private static final String finishCombination     = "111";
	protected static final int COMBINATION_LENGTH = unblockingCombination.length();
	public String usersCombination = "";
		
	public StopFrame(){
		
		setTitle(PRINTED_TEXT);
				
		setBounds(0,0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		this.getRootPane().setBackground(Color.BLACK);
		
		add(panel);
				
		setResizable(false);		
		setAlwaysOnTop(true);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		addWindowStateListener(new WindowStateListener(){
			public void windowStateChanged(WindowEvent e) {
				setState(NORMAL);
			}
		});
		
		addComponentListener(this);
		
		addMouseWheelListener(new MouseWheelListener(){
			
			public void mouseWheelMoved(MouseWheelEvent e) {
				if(e.getWheelRotation() < 0){
					usersCombination += "1";
				}
				if(e.getWheelRotation() > 0){
					usersCombination += "0";
				}
				
				if(usersCombination.length() > COMBINATION_LENGTH){
					usersCombination = usersCombination.substring(1);
				}
				
				if(usersCombination.equals(unblockingCombination)){
					unblock();
					goCycle = false;
				}
				
				if(usersCombination.equals(finishCombination)){
					unblock();
					goCycle = false;
					System.exit(0);
				}
			}
		});
	
	}
		
	FirstCircleRepaintThread firstCircleThr;
	SecoundCircleRepaintThread secoundCircleThr;
	LineRepaintThread   lineThr;
		
	public void block() {
		setVisible(true);
		
		firstCircleThr = new FirstCircleRepaintThread();
		firstCircleThr.start();
		secoundCircleThr = new SecoundCircleRepaintThread();
		secoundCircleThr.start();
		lineThr = new LineRepaintThread();
		lineThr.start();
	}
	
	public void unblock(){
		
		setVisible(false);
		
		firstCircleThr.stop();
		secoundCircleThr.stop();
		lineThr.stop();
	}

	
	public void componentHidden(ComponentEvent arg0) {
	}

	public void componentMoved(ComponentEvent arg0) {
		setLocation(0,0);
	}

	public void componentResized(ComponentEvent arg0) {
	}

	public void componentShown(ComponentEvent arg0) {		
	}

	
	


}
