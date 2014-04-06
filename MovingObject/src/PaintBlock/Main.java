package PaintBlock;


import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;



public class Main implements ActionListener{

	public JFrame frame;
	public JButton button;
	public Random r=new Random();
	int napravlenie,number_of_dots;
	int XOFBLOCK,YOFBLOCK;
	String have_to;
		
	
	public Main(){
		have_to="";
		frame=new JFrame();
		frame.setBounds(0,0,456,454);
		frame.setLocation(0, 0);
		frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frame.setLayout(null);
		
		button=new JButton("");
		button.setSize(30, 30);
		button.setLocation(145, 78);
		button.setActionCommand("b");
		frame.add(button);
		frame.setVisible(true);
		Timer t=new Timer(10,this);
		t.addActionListener(this);
		t.setActionCommand("time");
		t.start();
		randomMoving();


	}
		
	public void move(String have_to){
		System.out.println("2");
		StringTokenizer st=new StringTokenizer(have_to);
		int napr=Integer.valueOf(st.nextToken());
		int number=Integer.valueOf(st.nextToken());
		int i=0;
		if(napr==0){
			while(i<number){
			button.setLocation(button.getLocation().x+1,button.getLocation().y);
			i++;
			try {
				wait(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		else if(napr==1){
			while(i<number){
				button.setLocation(button.getLocation().x,button.getLocation().y+1);
				i++;
				try {
					wait(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
		}
		else if(napr==2){
			while(i<number){
				button.setLocation(button.getLocation().x-1,button.getLocation().y);
				i++;
				try {
					wait(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
		}
		else{
			while(i<number){
				button.setLocation(button.getLocation().x+1,button.getLocation().y-1);
				i++;
				try {
					wait(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			i=0;
			randomMoving();
		}
	}
	
		
	
	public void randomMoving(){
		System.out.println("1");
		String s1,s2;
			napravlenie=r.nextInt(4);
			if(napravlenie==0){
				s1="0";
				s2=String.valueOf(r.nextInt((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()-(button.getX()+XOFBLOCK))));
			}
			else if(napravlenie==1){
				s1="1";
				s2=String.valueOf(r.nextInt((int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()-(button.getY()+XOFBLOCK))));
			}
			else if(napravlenie==2){
				s1="3";
				s2=String.valueOf(r.nextInt((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()-button.getX())));
			}
			else{
				s1="4";
				s2=String.valueOf(r.nextInt((int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()-button.getY())));
			}
			have_to=s1+" "+s2;
			move(have_to);
			System.out.println(have_to);
			s1="";
			s2="";
			have_to="";
			
		
	}
	
	
	
		
	
			
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
		public void run(){
			new Main();
		}
	});

}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
