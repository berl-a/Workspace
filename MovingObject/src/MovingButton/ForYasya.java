package MovingButton;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class ForYasya implements ActionListener {

	JButton b1;
	Random rx,ry;
	int i;
	Timer t2;
	JFrame frame;
	int x,y;
	
	ForYasya(int x, int y){
		i=0;
		t2=new Timer(40,this);
		t2.setActionCommand("t2");
		frame=new JFrame("");
		b1=new JButton("");
		frame.setLayout(null);
		frame.add(b1);
		b1.addActionListener(this);
		b1.setBounds(42, 34, x, y);
		Timer t=new Timer(5000,this);
		t.setActionCommand("timer");
		frame.getContentPane().setBackground(Color.GREEN);
		rx=new Random(Toolkit.getDefaultToolkit().getScreenSize().width);
		ry=new Random(Toolkit.getDefaultToolkit().getScreenSize().height);
		frame.setBounds(0,0,Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
		frame.setVisible(true);
		t.start();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new ForYasya(40,40);
			}
		});
		

	}

	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="timer"){
			b1.setLocation(rx.nextInt((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth())-x, ry.nextInt((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight())-y);
		}
		else if(e.getActionCommand()==""){
			i++;
			frame.setTitle("Ти натиснула "+String.valueOf(i)+" разів");
			frame.getContentPane().setBackground(Color.RED);
			t2.start();
			}
		else if(e.getActionCommand()=="t2"){
			frame.getContentPane().setBackground(Color.GREEN);
		}
		
	}

}
