import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;


public class CountNumbers implements ActionListener, CaretListener{
	JLabel label;
	JTextField field;
	JButton button;
	int i=0,n=0;
	CountNumbers() {
		JFrame frame=new JFrame("Adder");
		frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-150, Toolkit.getDefaultToolkit().getScreenSize().height/2-100, 300, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		field=new JTextField(20);
		field.addCaretListener(this);
		field.addActionListener(this);
		field.setToolTipText("Just click");
		button=new JButton("Clear");
		label=new JLabel("Write text");
		frame.add(field,BorderLayout.CENTER);
		field.setBackground(new Color(255,99,71));
		frame.add(button,BorderLayout.NORTH);
		frame.add(label,BorderLayout.SOUTH);
		button.setBackground(Color.RED);
		button.addActionListener(this);
		button.setVisible(false);
		label.setVisible(false);
		
	};

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new CountNumbers();
			}
		});

	}


	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="Clear"){
			field.setText("");
			button.setVisible(false);
		
		}
		
	}



	public void caretUpdate(CaretEvent e) {
		button.setVisible(true);
		label.setVisible(true);
		if(label.getText()!=""){
			char[] c=field.getText().toCharArray();
			int[] i=new int[c.length];
			int n=0,intout=0;
			while(n<c.length){
				if((c[n]=='0')||(c[n]=='1')||(c[n]=='2')||(c[n]=='3')||(c[n]=='4')||(c[n]=='5')||(c[n]=='6')||(c[n]=='7')||(c[n]=='8')||(c[n]=='9')){
					i[n]=Integer.parseInt(String.valueOf(c[n]));
				}
				n++;
			}
			n=0;
			while(n<c.length){
				intout=intout+i[n];
				n++;
			}		
			label.setText("Сума цифр у тексті = "+intout);
		}
	}
}