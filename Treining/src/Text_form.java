import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.*;


public class Text_form implements ActionListener{
	
	JLabel label;
	JTextField field;
	JButton button;
		
	Text_form(){
		JFrame frame=new JFrame("Text_field");
		label=new JLabel("Я порахую кількість слів");
		field=new JTextField("Write_text",10);
		button=new JButton("clear");
		field.setActionCommand("fieldExplored");
		field.addActionListener(this);
		field.setBackground(new Color(253,245,230));
		button.addActionListener(this);
		button.setBackground(Color.RED);
		frame.add(field,BorderLayout.CENTER);
		frame.add(label,BorderLayout.SOUTH);
		frame.add(button,BorderLayout.NORTH);
		frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-100,Toolkit.getDefaultToolkit().getScreenSize().height/2-50,200,100);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("fieldExplored")){
			StringTokenizer st=new StringTokenizer(field.getText());
			String s;
			if(String.valueOf(st.countTokens()).endsWith("1")){s="слово";}
			else if((String.valueOf(st.countTokens()).endsWith("2"))||(String.valueOf(st.countTokens()).endsWith("3"))||(String.valueOf(st.countTokens()).endsWith("4"))){s="cлова";}
			else{s="слів";}
			label.setText(String.valueOf(st.countTokens())+" "+s);
		}
		if(e.getActionCommand()=="clear"){field.setText("");
		
		}
				
	}
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new Text_form();
			}
		});
	}
		
}
