import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
 

public class Border implements ActionListener {
	javax.swing.border.Border[] bm;
	JButton b1,b2,b3,b4,b5;
	int i=0;
	JFrame frame;


	Border(){
		javax.swing.border.Border[] bm=new javax.swing.border.Border[3]; 
		frame=new JFrame("Borders");
		frame.setLayout(new BorderLayout());
		b1=new JButton("1");
		b2=new JButton("2");
		b3=new JButton("3");
		b4=new JButton("4");
		b5=new JButton("5");
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		frame.add(b1,BorderLayout.NORTH);
		frame.add(b2,BorderLayout.CENTER);
		frame.add(b3,BorderLayout.SOUTH);
		frame.add(b4,BorderLayout.WEST);
		frame.add(b5,BorderLayout.EAST);
		b1.setSize(100, 800);
		bm[0]=BorderFactory.createBevelBorder(BevelBorder.RAISED);
		bm[1]=BorderFactory.createBevelBorder(BevelBorder.LOWERED);
		bm[2]=BorderFactory.createEtchedBorder(Color.BLUE, Color.BLACK);
		b1.setBorder(bm[0]);
		b2.setBorder(bm[2]);
		b3.setBorder(bm[2]);
		b4.setBorder(bm[1]); 
		b5.setBorder(bm[2]);
		frame.setSize(100, 150);
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		
		
	}
	
	
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new Border();
			}
		});
	}

}
