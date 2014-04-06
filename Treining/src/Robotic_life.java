import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.MouseEvent;


public class Robotic_life {
	
	


	public static void main(String[] args) {
		try {
			Robot r=new Robot();
			r.mouseMove(25, 150);
			for(int i = 0 ; i < 5000; i++){
				r.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
				r.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
				Thread.sleep(50);
			}
			
			
			} catch (AWTException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}

}
