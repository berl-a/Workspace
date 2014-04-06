import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.*;

import javax.swing.*;


public class Secound implements ActionListener {
	JLabel label1,label2;
	JButton button;
	long starttime,time;
	Hashtable hash1,hash2;
	double dtime;
	
	public Secound(){
		
		hash1=new Hashtable();
		hash1.put(0, "husein");
		hash1.put(1, "rocket");
		hash1.put(2, "light");
		hash1.put(3, "metro");
		
		
		hash2=new Hashtable();
		hash2.put("husein", (double)10.4);
		hash2.put("rocket",(double)8000);
		hash2.put("light", (double)299792458);
		hash2.put("metro", (double)37.5);
		
		JFrame frame=new JFrame("Scanda");
		label1=new JLabel("Press 'Start'");
		label2=new JLabel("BubbleGum corp");
		button=new JButton("Start");
		button.setBackground(Color.GREEN);
		button.addActionListener(this);
		frame.add(button,BorderLayout.CENTER);
		frame.add(label1,BorderLayout.SOUTH);
		frame.add(label2,BorderLayout.NORTH);
		
		frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-240, Toolkit.getDefaultToolkit().getScreenSize().height/2-150, 480, 300);
		frame.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().toString().equals("Start")){
			starttime=System.nanoTime();
			button.setText("Stop");
			button.setBackground(Color.RED);
			label1.setText("Counting...");
		}
		else if(e.getActionCommand().toString().equals("Stop")){
			String s1,s2,s3;
			s3="";
			time=(System.nanoTime()-starttime)/1000000;
			double dtime=(double)time/1000;
			label1.setText("Time is "+String.valueOf(dtime)+" sec");
			button.setText("Start");
			button.setBackground(Color.GREEN);
			starttime=0;
			Random r=new Random();
			int i=r.nextInt(4);
			if(hash1.get(i).equals("husein")){s1="За цей час Хусейн Болт пробіг би ";}
			else if(hash1.get(i).equals("rocket")){s1="Ракета, яка виходить на орбіту пролетіла би ";}
			else if(hash1.get(i).equals("light")){s1="У космосі світло подолало би ";}
			else {s1="Поїзд метро міста Гуанчжоу пройшов би ";}
			double d=dtime*(Double.valueOf(hash2.get(hash1.get(i)).toString()));
			d=Math.rint(d*1000)/1000;
			s2=String.valueOf(d);
			if((i==1)||(i==2)){s3=" (Е-степінь десяти)";}
			label2.setText(s1+" "+s2+" метрів"+s3);
			
			
			
		}
		
	}
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new Secound();
			}
		});
	}
}
