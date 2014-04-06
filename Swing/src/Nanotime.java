import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class Nanotime {

	public static JLabel jlbl;
	public Nanotime(){
		JFrame jfrm=new JFrame("���������кпкпкупук");
		jfrm.setBounds(500, 100, 200, 300);
		JButton Startbutton=new JButton("Start");
		JButton Stopbutton=new JButton("Stop");
		Nano_listener NL=new Nano_listener();
		Startbutton.addActionListener(NL);
		Stopbutton.addActionListener(NL);
		Startbutton.setBackground(Color.green);
		jfrm.setLayout(new FlowLayout());
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel jlbl=new JLabel("Press 'Start'");
		}
	public static void main(String[] args){ 
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new Nanotime();
				}
			});
		}
	}

			
		
	
		
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

