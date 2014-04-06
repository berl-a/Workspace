import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;


public class Tymer implements ActionListener, FocusListener{

	JFrame frame;
	JTextField field1,field2;
	Timer t;
	JLabel label; 
	JButton button;
	
	
	Tymer(){
		frame=new JFrame("Timer");
		field1=new JTextField(2);
		field2=new JTextField(2);
		label=new JLabel("Виставте хвилини і секунди");
		button=new JButton("Stop");
		button.addFocusListener(this);
		frame.setBackground(new Color(255,255,0));
		frame.setSize(300, 100);
		frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-(frame.getSize().width/2), Toolkit.getDefaultToolkit().getScreenSize().height/2-(frame.getSize().height/2));
		frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(field1);
		frame.add(field2);
		frame.add(label);
		frame.add(button);
		button.setBackground(Color.RED);
		button.setVisible(false);
		frame.setVisible(true);
		field1.setActionCommand("f1");
		field2.setActionCommand("f2");
		field1.addFocusListener(this);
		field2.addFocusListener(this);
		t=new Timer(1000,this);
		t.setActionCommand("Time!");
		t.setRepeats(false);
		frame.setAlwaysOnTop(true);
						
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new Tymer();
			}
		});

	}

	public void focusGained(FocusEvent e) {
		if(e.getSource().equals(button)){
			t.stop();
			label.setText("Виставте хвилини і секунди");
			frame.setSize(300, 100);
			frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-(frame.getSize().width/2), Toolkit.getDefaultToolkit().getScreenSize().height/2-(frame.getSize().height/2));
			button.setVisible(false);
		}

	}


	public void focusLost(FocusEvent e) {
					if(e.getComponent().equals(field2)){
					if(field1.getText().equals("")){field1.setText("0");}
					if(field2.getText().equals("")){field2.setText("0");}
					t.setInitialDelay((Integer.valueOf(field1.getText())*60+Integer.valueOf(field2.getText()))*1000);
					t.start();
					label.setText("Counting");
					button.setVisible(true);
					}
			}
	

	
	public void actionPerformed(ActionEvent e) {

		label.setText("Finished!");	
		System.out.println("fin");
		frame.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		button.setText("Hide");
		frame.setBackground(Color.GRAY);
	}

}