import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;


public class FB implements CaretListener {
	
	int i=0;
	JLabel[] labels;
	JTextField field;

	FB(){
		JFrame frame=new JFrame("Falling bricks");
		labels=new JLabel[10];
		field=new JTextField(10);
		field.addCaretListener(this);
		field.setHorizontalAlignment(JTextField.CENTER);
		field.setBackground(Color.GREEN);
		field.setText("");
		frame.add(field);
		field.copy();
		
		frame.setLayout(new GridLayout(11,1,0,0));                           
				for(int i=9;i>-1;i--){
			labels[i]=new JLabel(String.valueOf(i),10);
			labels[i].setHorizontalAlignment(JLabel.CENTER);
			frame.add(labels[i]);
			}
			
		frame.pack();
		frame.setVisible(true);
		
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new FB();
				}
		});

	}
	@Override
	public void caretUpdate(CaretEvent e) {
		if((field.getText().length()==10)&&(i!=10)&&(i!=11)){
			labels[i].setText(field.getText());
			field.setText("");
			
			i++;
		}
		else if(i==10){field.setBackground(Color.BLACK);
		Timer t=new Timer(1000, new ActionListener(){
			public void actionPerformed(ActionEvent e){
			field.setBackground(Color.RED);
			i=11;
			}
		});
		t.start();
		}
		
	}

}
