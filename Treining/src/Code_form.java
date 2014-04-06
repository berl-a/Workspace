import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


public class Code_form implements ActionListener{
	
	JFrame frame;
	JButton b0,b1,bok;
	String s_password="1001",s_yourword;
	
	Code_form(){
		s_yourword="";
		frame=new JFrame("Code");
		b0=new JButton("0");
		b1=new JButton("1");
		bok=new JButton("OK");
		bok.setBackground(Color.RED);
		b0.addActionListener(this);
		b1.addActionListener(this);
		bok.addActionListener(this);
		frame.add(b0,BorderLayout.WEST);
		frame.add(b1,BorderLayout.EAST);
		frame.add(bok,BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-150, Toolkit.getDefaultToolkit().getScreenSize().height/2-100, 300, 200);

	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("1")){
			s_yourword=s_yourword+"1";
		}
		else if(e.getActionCommand().equals("0")){
			s_yourword=s_yourword+"0";
		}
		else{
			if(s_password.equals(s_yourword)){
				bok.setText("DONE!");
				bok.setBackground(Color.YELLOW);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				}
			else{
				b0.setVisible(false);
				b1.setVisible(false);
				bok.setVisible(false);
			}
			
		}
		
	}
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new Code_form();
			}
		});
	}
}
