import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;


public class WaitThread extends Thread{

	private BufferedImage image;
	
	private static MediaTracker media_tracker = new MediaTracker(Frame.panel);;
	
	public WaitThread(BufferedImage image){
		this.image = image;
	}

	public void run() {
		
		media_tracker.addImage(image, 0);
		
		try{
	        media_tracker.waitForID(0);
		}catch(InterruptedException e){}
		
	}

	public BufferedImage getImage(){
		return image;
	}

	
}
