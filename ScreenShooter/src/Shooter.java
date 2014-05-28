import java.awt.AWTException;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Timer;


public class Shooter implements ActionListener {

	private Rectangle rectangle;
	private Timer timer;
	private Robot robot;
	private List<WaitThread> threads = new LinkedList<WaitThread>();
	private List<BufferedImage> shots = new LinkedList<BufferedImage>();
	private boolean usingTimer = true;
	public boolean finished = false;
	private boolean done;
		
	public Shooter(Rectangle rect, int delayOfTimer){
		setRectangle(rect);
		
		if(delayOfTimer != 0)
			setTimer(new Timer(delayOfTimer, this));
		else
			setUsingTimer(false);
		
		try {
			robot = new Robot();
		} catch (AWTException e) {}
		
	}
	
	public void startShooting(){
		if(usingTimer)
			timer.start();
	}
	
	public void stopShooting(){
		timer.stop();
		finish();
	}
	
	public void actionPerformed(ActionEvent e) {
		shoot();
	}
	
	public void shoot(){
		BufferedImage i = robot.createScreenCapture(rectangle);
		
		WaitThread t = new WaitThread(i);
		t.start();
		threads.add(t);
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	public void setTimerDelay(int delay){
		timer.setDelay(delay);
	}

	public List<BufferedImage> getShots() {
		return shots;
	}

	public void setShots(List<BufferedImage> shots) {
		this.shots = shots;
	}

	public boolean isUsingTimer() {
		return usingTimer;
	}

	public void setUsingTimer(boolean usingTimer) {
		this.usingTimer = usingTimer;
	}
	
	public void finish(){
		
		for(int i = 0; i < threads.size(); i++){
			
			try {
				if(threads.get(i).isAlive())
					threads.get(i).join();
			} catch (InterruptedException e) {}
			
			shots.add(i, threads.get(i).getImage());
		}
		
		setDone(true);
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}
		
}

