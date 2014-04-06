import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JTextField;


public class Helper {
	JFrame frame;
	JTextField f1,f2;
	char[][] c;

	public Helper(){
		frame=new JFrame("I'll help you!");
		frame.setLayout(new FlowLayout());
		f1=new JTextField("",10);
		f2=new JTextField("",10);
		frame.add(f1);
		frame.add(f2);
		frame.setVisible(true);
		
		c=new char[33][2];
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
