import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Paint{

	public Paint(){
	System.out.println("Ps");
	dosmth();
	}


	private void dosmth() {
		System.out.println("DONE!");
		
	}


	public void paint(Graphics g){
		g=(Graphics2D)g;
		System.out.println("p");
		g.setColor(Color.PINK);
		g.fillRect(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		g.setColor(Color.GREEN);
		g.drawString(Main.text, 0, 0);
	}


}
