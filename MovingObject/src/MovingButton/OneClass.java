package MovingButton;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class OneClass implements KeyListener, ActionListener{

	private static final int DELTA_OF_COORD = 2;
	private static final int SPEED_OF_REFRESHING = 5;
	
	private static final int DEF_SIZE_OF_BUTTON = 15;
	JFrame frame;
	JButton button1,button2,button_cel;
	String s="";
	Timer t;
	Random r = new Random();
	
	
	OneClass(){

		frame=new JFrame();
		frame.setFocusable(true);
		frame.addKeyListener(this);
		frame.setBackground(Color.YELLOW);
		frame.setUndecorated(true);
		button1=new JButton();
		button2=new JButton();
		button1.addActionListener(this);
		button2.addActionListener(this);
		//button_cel=new JButton();
		//button_cel.setSize(20, 20);
		button1.setFocusable(false);
		button2.setFocusable(false);
		frame.setLayout(null);
		frame.add(button1);
		frame.add(button2);
		button1.setBackground(Color.RED);
		button2.setBackground(Color.GREEN);
		button1.setBorder(null);
		button2.setBorder(null);
		frame.setLocation(0,0);
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height-35);
		button1.setSize(DEF_SIZE_OF_BUTTON, DEF_SIZE_OF_BUTTON);
		button2.setSize(DEF_SIZE_OF_BUTTON, DEF_SIZE_OF_BUTTON);
		button2.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/2-5);
		button1.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-DEF_SIZE_OF_BUTTON, Toolkit.getDefaultToolkit().getScreenSize().height/2-5);
		frame.setVisible(true);
		frame.getContentPane().setBackground(new Color(240,240,240));
		t = new Timer(SPEED_OF_REFRESHING, this);
		t.setActionCommand("timer");
		t.start();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new OneClass();
			}
		});

	}

	public int firstDeltaX = 0;
	public int firstDeltaY = 0;
	
	public int secoundDeltaX = 0;
	public int secoundDeltaY = 0;
	
	public boolean button1IsMoving;
	public boolean button2IsMoving;
	
	public void keyPressed(KeyEvent e) {
		button1.setSize(DEF_SIZE_OF_BUTTON, DEF_SIZE_OF_BUTTON);
		button1.setText("");
		
		if(e.getKeyCode()==68){firstDeltaX = DELTA_OF_COORD;}
		if(e.getKeyCode()==65){firstDeltaX = -DELTA_OF_COORD;}
		if(e.getKeyCode()==83){firstDeltaY = DELTA_OF_COORD;}
		if(e.getKeyCode()==87){firstDeltaY = -DELTA_OF_COORD;}
		
		if(e.getKeyCode()==39){secoundDeltaX = DELTA_OF_COORD;}
		if(e.getKeyCode()==37){secoundDeltaX = -DELTA_OF_COORD;}
		if(e.getKeyCode()==40){secoundDeltaY = DELTA_OF_COORD;}
		if(e.getKeyCode()==38){secoundDeltaY = -DELTA_OF_COORD;}
		
		if(e.getKeyChar() == KeyEvent.VK_SPACE){
			button2.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2, Toolkit.getDefaultToolkit().getScreenSize().height/2-5);
			button1.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-DEF_SIZE_OF_BUTTON, Toolkit.getDefaultToolkit().getScreenSize().height/2-5);
		}
	}


	public void keyReleased(KeyEvent e) {
		
		if((e.getKeyCode()==65)||(e.getKeyCode()==68))
			firstDeltaX = 0;
		if((e.getKeyCode()==83)||(e.getKeyCode()==87))
			firstDeltaY = 0;
		
		if((e.getKeyCode()==39)||(e.getKeyCode()==37))
			secoundDeltaX = 0;
		if((e.getKeyCode()==38)||(e.getKeyCode()==40))
			secoundDeltaY = 0;
	}


	public void keyTyped(KeyEvent e) {}

	public boolean firstIsLove = true;
	public boolean hadTouched = false;
	
	public void actionPerformed(ActionEvent e) {
		

		if(button1.getBounds().intersects(button2.getBounds())){
			
			if(!hadTouched){
				if(firstIsLove){
					firstIsLove = !firstIsLove;
					frame.getContentPane().setBackground(button2.getBackground());
					hadTouched = true;
				}else{
					firstIsLove = !firstIsLove;
					frame.getContentPane().setBackground(button1.getBackground());
					hadTouched = true;
				}
			}
			
		}
		
		if(!button1.getBounds().intersects(button2.getBounds())){
			frame.getContentPane().setBackground(null);
			hadTouched = false;
		}
		
		if(e.getActionCommand() == "timer"){
			button1.setLocation(button1.getLocation().x + firstDeltaX, button1.getLocation().y + firstDeltaY);
			button2.setLocation(button2.getLocation().x + secoundDeltaX, button2.getLocation().y + secoundDeltaY);
		}
		
		if((button1.getLocation().getY() < 0)||(button1.getLocation().getX() < 0)){
			button1.setBackground(new Color(r.nextInt(155) + 100, r.nextInt(155) + 100, r.nextInt(155) + 100));
		}
			
		
		if((button2.getLocation().getY() < 0)||(button2.getLocation().getX() < 0))
			button2.setBackground(new Color(r.nextInt(155) + 100, r.nextInt(155) + 100, r.nextInt(155) + 100));
		
		
		if(e.getSource().equals(button1)){
			s+="1";
			System.out.println(s);
		}
		if(e.getSource().equals(button2)){
			s+="2";
			System.out.println(s);
		}
				
		if(s.length() == 5)
			s = s.substring(1, s.length());
		
		if(s.equals("1112")){
			System.out.println("Hurray!");
			button1.setSize(200, 20);
			button1.setText("Developed by BubbleGum");
			s = "";
		}
			
	}
		

}
