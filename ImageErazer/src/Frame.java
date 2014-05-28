import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class Frame extends JFrame implements WindowStateListener{

	public static Panel panel = new Panel();
	
	Frame(){
		setBounds(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setContentPane(panel);
		setAutoRequestFocus(true);
		getContentPane().setCursor(new Cursor(Cursor.WAIT_CURSOR));
		BufferedImage cursor;
		try {
			cursor = ImageIO.read(getClass().getResource("res/empty.png"));
			getContentPane().setCursor(Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0,0), "cursor"));
		} catch (IOException e) {}
		
		addWindowStateListener(this);
		setVisible(true);
	}

	public void windowStateChanged(WindowEvent arg0) {
		setState(JFrame.NORMAL);
	}

}
