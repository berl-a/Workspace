package bubblegum.anekdot;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Read {
	
	JFrame frame;
	JTextField field;

	public void read(){
		
		frame=new JFrame("Add new");
		frame.setLayout(new FlowLayout());
		frame.getGlassPane().setBackground(new Color(255,228,181));
		field=new JTextField(40);
		field.setToolTipText("Замість переносу рядка використовуйте знак  ;  ");
		field.setBackground(new Color(240,255,255));
		frame.add(field);
		frame.pack();
		frame.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2-frame.getWidth()/2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2-frame.getHeight()/2));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		field.addKeyListener(new KeyListener(){

			public void keyPressed(KeyEvent e) {}

			public void keyReleased(KeyEvent e) {
				
				if((e.getKeyCode() == 10)&&(field.getText()!="")){
					Add.strings.add(field.getText());
					field.setText("");
				}
				
				if(e.getKeyCode() == 17){
					Add.strings.add(field.getText());
					Add.writeToFile();
					System.exit(0);
				}
				
				
				
			}

			public void keyTyped(KeyEvent e) {
				
				
			}
			
		});
		field.addMouseWheelListener(new MouseWheelListener(){

			
			public void mouseWheelMoved(MouseWheelEvent e) {
				
				Show show=new Show();
				
				if(e.isShiftDown()){
					JOptionPane.showMessageDialog(null, "Created by BubbleGum in 2014");
				}
				
				else if(e.isAltDown()){
					JOptionPane.showMessageDialog(null, "U R COOL!");
				}
				
				else if(show.show()=="Error"){
					JOptionPane.showMessageDialog(null, "Анекдотів ще немає", "Брак анекдотів", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			
		});
	}

}

