import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;


public class ShowTime implements ActionListener{

	JLabel label;
	JButton button,buttonClear;
	double d,sd;
	Timer t;
	
	ShowTime(){
		
		JFrame frame=new JFrame("Timer");
		frame.setVisible(true);
		frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-150, Toolkit.getDefaultToolkit().getScreenSize().height/2-100, 300, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		label=new JLabel("Press 'Start'");
		label.setToolTipText("Час у секундах");
		button=new JButton("Start");
		button.setBackground(Color.GREEN);
		button.setToolTipText("Натисніть для старту");
		buttonClear=new JButton("Clear");
		buttonClear.setBackground(Color.RED);
		buttonClear.addActionListener(this);
		button.addActionListener(this);
		frame.add(button,BorderLayout.CENTER);
		frame.add(label,BorderLayout.SOUTH);
		frame.add(buttonClear,BorderLayout.NORTH);
		buttonClear.setVisible(false);
		t=new Timer(10,this);
		d=0;	
				
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new ShowTime();
			}
		});

	}

	public void actionPerformed(ActionEvent e) {
		String s1="",s2="",s3="";
		if(e.getActionCommand()=="Start"){
			t.start();
			button.setText("Stop");
			button.setToolTipText("Натисніть щоб зупинити");
			button.setBackground(Color.RED);
		}
		else if(e.getActionCommand()=="Stop"){
			t.stop();
			button.setText("Continue");
			buttonClear.setVisible(true);
			buttonClear.setToolTipText("Натисніть сюди щоб почати з початку");
			button.setToolTipText("Натисніть щоб продовжити");
			button.setBackground(Color.YELLOW);
			}
		else if(e.getActionCommand()=="Continue"){
			t.start();
			button.setText("Stop");
			buttonClear.setVisible(false);
			button.setToolTipText("Натисніть щоб зупинити");
			button.setBackground(Color.RED);
		}
		else if(e.getActionCommand()=="Clear"){
			t.stop();
			buttonClear.setVisible(false);
			button.setText("Start");
			d=0;
			label.setText("Press 'Start'");
			button.setToolTipText("Натисніть для старту");
			button.setBackground(Color.GREEN);
		}
		else{
			Double dforint=new Double(0);
			d+=0.01;
			if((d>60.0)&&(d<3600.0)){
				dforint=(d-d%60)/60;
				s1=String.valueOf(dforint.intValue());
				s2=" хв ";
				s3=" секунд";
			}
			sd=Math.rint((d*100/100-(d-d%60))*100)/100;
			label.setText("Ваш час: "+s1+s2+String.valueOf(sd)+s3);
		}		
		
	}

}
