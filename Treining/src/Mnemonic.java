import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



public class Mnemonic {

	Mnemonic(){
		JFrame frame=new JFrame("FrameMne");
		frame.setLayout(new FlowLayout());
		frame.setLocation(0, 0);
		frame.setSize(260, 140);
		JLabel l1=new JLabel("Name:");
		JLabel l2=new JLabel("Age:");
		l1.setDisplayedMnemonic('n');
		l2.setDisplayedMnemonic('a');
		JTextField f1=new JTextField(20);
		JTextField f2=new JTextField(20);
		l1.setLabelFor(f1);
		l2.setLabelFor(f2);
		frame.add(l1);
		frame.add(f1);
		frame.add(l2);
		frame.add(f2);
		frame.setVisible(true);
		
	}
		
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new Mnemonic();
			}
		});

	}

}
