import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Searcher implements ActionListener {
	JTextField field;
	JFrame frame;
	
	Searcher(){
		frame=new JFrame("Search!");
		field=new JTextField(20);
		field.addActionListener(this);
		frame.add(field);
		frame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-150, Toolkit.getDefaultToolkit().getScreenSize().height/2-50, 300, 100);
		frame.setVisible(true);
		
		
	}

	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){new Searcher();
			}
		});
		

	}

	public void actionPerformed(ActionEvent ae) {
		Robot r;
		try {
			r = new Robot();
			r.setAutoDelay(10);
			r.mouseMove(1280,10);
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			r.mouseMove(50, 240);
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			r.setAutoDelay(3000);
			char[] c=field.getText().toCharArray();
			int i=0;
			r.keyPress(KeyEvent.VK_0);
			r.setAutoDelay(10);
			System.out.println(c.length);
			while(i!=c.length){
				r.keyPress(KeyEvent.VK_A);
				i++;
			}
			
			System.out.println(r.getAutoDelay());
						
		} catch (AWTException e) {			
			e.printStackTrace();
		}
		
	}

}
