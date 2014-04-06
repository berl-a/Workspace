import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class Main_c {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Robot r=new Robot();
			r.mouseMove((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 92 ,  5 );
			r.mousePress(MouseEvent.BUTTON1_DOWN_MASK); 
			r.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
			r.mouseMove((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 120 ,  25 );
			r.mousePress(MouseEvent.BUTTON1_DOWN_MASK); 
			r.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
			int x=0;
			while(x<100){
				r.keyPress(KeyEvent.VK_SPACE);
				r.keyRelease(KeyEvent.VK_SPACE);
				x++;
			}
			
			while(true){
				r.keyPress(KeyEvent.VK_SPACE);
				return;
			}
			
		} catch (AWTException e) {
			e.printStackTrace();
		}
		

	}

}
