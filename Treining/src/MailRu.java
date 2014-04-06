import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


public class MailRu {

	
	public static void main(String[] args) {
		try {
			
			Robot r=new Robot();
			
			r.mouseMove(255,250);			
			r.mousePress(InputEvent.BUTTON1_MASK);
			r.mouseRelease(InputEvent.BUTTON1_MASK);
			
			r.keyPress(KeyEvent.VK_Q);
			r.keyPress(KeyEvent.VK_Q);
			r.keyPress(KeyEvent.VK_Q);
			
			r.keyPress(KeyEvent.VK_TAB);
			
			r.keyPress(KeyEvent.VK_Q);
			r.keyPress(KeyEvent.VK_Q);
			r.keyPress(KeyEvent.VK_Q);
			
			r.keyPress(KeyEvent.VK_TAB);
			
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyPress(KeyEvent.VK_DOWN);
			
			r.keyPress(KeyEvent.VK_TAB);
			
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyPress(KeyEvent.VK_DOWN);
			
			r.keyPress(KeyEvent.VK_TAB);
			
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyPress(KeyEvent.VK_DOWN);
			
			r.keyPress(KeyEvent.VK_TAB);
			
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyPress(KeyEvent.VK_DOWN);
			
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyPress(KeyEvent.VK_DOWN);
			
			r.keyPress(KeyEvent.VK_TAB);
			
			r.keyPress(KeyEvent.VK_TAB);
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		} catch (AWTException e) {

			e.printStackTrace();
		}

	}

}
