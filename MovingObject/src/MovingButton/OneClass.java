package MovingButton;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class OneClass implements KeyListener, ActionListener{

	JFrame frame;
	JButton button1,button2,button_cel;
	String s="";
	
	
	OneClass(){

		frame=new JFrame();
		frame.setFocusable(true);
		frame.addKeyListener(this);
		button1=new JButton();
		button2=new JButton();
		button1.addActionListener(this);
		button2.addActionListener(this);
		button_cel=new JButton();
		button_cel.setSize(20, 20);
		button1.setFocusable(false);
		button2.setFocusable(false);
		frame.setLayout(null);
		frame.add(button1);
		frame.add(button2);
		button1.setBackground(Color.RED);
		button2.setBackground(Color.GREEN);
		frame.setLocation(0,0);
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height-35);
		button1.setSize(10,10);
		button2.setSize(10, 10);
		button1.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/2-5);
		button2.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-10, Toolkit.getDefaultToolkit().getScreenSize().height/2-5);
		frame.setVisible(true);
		frame.getContentPane().setBackground(new Color(240,240,240));
	
		
		
		
		
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new OneClass();
			}
		});

	}

	
	public void keyPressed(KeyEvent e) {
		button1.setSize(10, 10);
		button1.setText("");
		if(e.getKeyCode()==87){button2.setLocation(button2.getLocation().x, button2.getLocation().y-10);}
		if(e.getKeyCode()==68){button2.setLocation(button2.getLocation().x+10, button2.getLocation().y);}
		if(e.getKeyCode()==83){button2.setLocation(button2.getLocation().x, button2.getLocation().y+10);}
		if(e.getKeyCode()==65){button2.setLocation(button2.getLocation().x-10, button2.getLocation().y);}
		if(e.getKeyCode()==38){button1.setLocation(button1.getLocation().x, button1.getLocation().y-10);}
		if(e.getKeyCode()==39){button1.setLocation(button1.getLocation().x+10, button1.getLocation().y);}
		if(e.getKeyCode()==40){button1.setLocation(button1.getLocation().x, button1.getLocation().y+10);}
		if(e.getKeyCode()==37){button1.setLocation(button1.getLocation().x-10, button1.getLocation().y);}
		
	}


	public void keyReleased(KeyEvent e) {
		System.out.println(e.getKeyCode());
		
	}


	public void keyTyped(KeyEvent e) {
		
		
	}


	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(button1)){s=s+"1";}
		else{s=s+"2";}
		System.out.println(s);
		if(s.equals("1212112")){button1.setBackground(Color.YELLOW);
		button1.setSize(200, 20);
		button1.setText("Developed by BubbleGum");
	
		}
		
	}

}
