import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class Frame extends JFrame {

	private static final Rectangle DEFAULT_BOUNDS = new Rectangle(0+100, 0+100, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-300, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-300);
	
	private static final int DEFAULT_SHOOTING_TIME = 500;

	public static Shooter shooter = new Shooter(DEFAULT_BOUNDS, DEFAULT_SHOOTING_TIME); 
	public static MyPanel panel= new MyPanel(DEFAULT_BOUNDS); 
	
	public Frame(){
		
		setBounds(DEFAULT_BOUNDS);
		
		setUndecorated(true);
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBackground(new Color(0, 0, 0, 0));
		setContentPane(new MyPanel(DEFAULT_BOUNDS));
		setAlwaysOnTop(true);
		setVisible(true);
		
	}
}




































