import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class Key_shitatch implements KeyListener{
	int i=0;
	JLabel label;
	Key_shitatch(){
		JFrame frame=new JFrame("Print!");
		label=new JLabel("");
		frame.addKeyListener(this);	
		frame.add(label,BorderLayout.CENTER);
		label.setBackground(Color.GREEN);
		frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-85, Toolkit.getDefaultToolkit().getScreenSize().height/2-25, 170, 50);
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){

			public void run() {
				new Key_shitatch();
			}
			
		});

	}

	@Override
	public void keyPressed(KeyEvent e) {
		i++;
		label.setText("Ви натиснули "+String.valueOf(i)+" разів");
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
