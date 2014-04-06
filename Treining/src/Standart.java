import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Standart implements ActionListener{
	JButton But1,But2,But3;
	String s;
	
	Standart(String name,int w, int h){
		JFrame jfrm=new JFrame(name);
		
		But1=new JButton("B1");
		But1.addActionListener(this);
		jfrm.add(But1,BorderLayout.NORTH);
				
		But2=new JButton("B2");
		But2.addActionListener(this);
		jfrm.add(But2,BorderLayout.CENTER);
		
		But3=new JButton("B3");
		But3.addActionListener(this);
		jfrm.add(But3,BorderLayout.SOUTH);
		
		
		
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrm.setVisible(true);
		But1.setBorderPainted(true);
		But1.setBackground(Color.YELLOW);	
		But1.setSize(200, 230);
		jfrm.pack();
		jfrm.setLocation(w, h);
		s=new String("");
		
		
	}

	public void actionPerformed(ActionEvent ae){
		if(ae.getActionCommand().equals("B1")){
			
		}
			
			
	}
	
	public static void main(String[] args){
	SwingUtilities.invokeLater(new Runnable(){
		public void run(){
			new Standart("Button",0,0);
			new Standart("Button1",89,123);
			new Standart("Button2",500,600);
		}
		});
	}
	

}
