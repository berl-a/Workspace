import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class ForYasya implements ActionListener {

	private static final int DEFAULT_Y = 40;
	private static final int DEFAULT_X = 40;
	private static final int TIME_BEFORE_MOVE_THE_TARGET_MS = 2000;
	private static final int COLOR_CHANGE_TIME_MS = 10;
	private static final int NUMBER_OF_HITS_TO_FINISH = 5;
	private static final Color COLOR_OF_TAFGET = Color.GREEN;
	private static final Color COLOR_OF_TAFGET_IF_HITTED = Color.BLUE;
	private static final Color COLOR_OF_BACKGROUND = new Color(120,120,12);
	
	JButton target;
	Random randomX,randomY;
	int numberOfHits;
	Timer targetMoveTimer, colorChangeTimer;
	JFrame frame;
	boolean wasTheFirstHit;
	long currTimeMillsOfStart;
	int x,y;
	
	ForYasya(){
		
		numberOfHits=0;
		
		Timer tagretMoveTimer=new Timer(TIME_BEFORE_MOVE_THE_TARGET_MS ,this);
		tagretMoveTimer.setActionCommand("Change target location");
		
		frame=new JFrame("");
		target=new JButton("");
		target.setBackground(COLOR_OF_TAFGET);
		target.setActionCommand("Target hitted");
		target.addActionListener(this);
		frame.setLayout(null);
		frame.add(target);
		
		frame.setResizable(false);
		target.addActionListener(this);
		target.setBounds(42, 34, DEFAULT_X, DEFAULT_Y);
				
		colorChangeTimer=new Timer(COLOR_CHANGE_TIME_MS , this); //Додати сюди обробку подій
		colorChangeTimer.setActionCommand("Change color back");
		
		frame.getContentPane().setBackground(COLOR_OF_BACKGROUND);
		randomX=new Random(Toolkit.getDefaultToolkit().getScreenSize().width);
		randomY=new Random(Toolkit.getDefaultToolkit().getScreenSize().height);
		frame.setBounds(0,0,Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
		frame.setVisible(true);
		tagretMoveTimer.start();
		currTimeMillsOfStart = System.currentTimeMillis();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new ForYasya();
			}
		});
		

	}

	
	public void actionPerformed(ActionEvent e) {
	
		if(e.getActionCommand() == "Change target location"){
			target.setLocation(randomX.nextInt((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth())-x, randomY.nextInt((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight())-y);
		}
		else if(e.getActionCommand() == "Target hitted"){
			
			numberOfHits++;
			//frame.setTitle("You hitted the target "+String.valueOf(numberOfHits)+"  times");
			frame.setTitle(String.valueOf(numberOfHits));
			targetMoveTimer.start();
			target.setBackground(COLOR_OF_TAFGET_IF_HITTED);
			colorChangeTimer.start();
			
			if(numberOfHits == NUMBER_OF_HITS_TO_FINISH){
				endTheGame(System.currentTimeMillis() - currTimeMillsOfStart);
				System.out.println("fin");
			}
			else{
				System.out.println("else");
			}
		}
		else if(e.getActionCommand() == "Change color back"){
			frame.setTitle("Back");
			target.setBackground(COLOR_OF_TAFGET);
		}
		
	}
	
	public void endTheGame(long timeDelay){
		/*
		System.out.println("More");
		JOptionPane.showMessageDialog(null, ("You made " + NUMBER_OF_HITS_TO_FINISH + " hits for " + timeDelay / 1000 + " secounds"));
		System.exit(0);
		*/
		frame.setTitle("END");
	}

}
