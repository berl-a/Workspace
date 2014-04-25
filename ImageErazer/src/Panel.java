import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Panel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private static final int MAX_WIDTH_OF_RECT = 20;

	private static final int MAX_SPEED = 3;
	private static final int MIN_SPEED = 1;
	private static final int SPEED_VARIETY = 5;

	private static final int MAX_NUMBER_OF_NUMBERS = 10;

	private static final String URL_OF_BLUE_SCREEN = "/res/bs1.jpg";

	private static final int NUMBER_OF_CYCLES_TO_FILL_SCREEN = 200;

	public Random r = new Random();
	
	public List<Rectangle> rectangles = new LinkedList<Rectangle>();	
	public List<Integer> speed = new LinkedList<Integer>();
	
	Timer exitTimer = new Timer(60000 * 10, new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	});
	
	Panel(){
		
		int nowWidth = 0;
		while(nowWidth <= Toolkit.getDefaultToolkit().getScreenSize().getWidth()){
			rectangles.add(new Rectangle(nowWidth, 0, r.nextInt(MAX_WIDTH_OF_RECT), 0));
			speed.add(r.nextInt(MAX_SPEED) + MIN_SPEED);
			nowWidth += rectangles.get(rectangles.size() - 1).getWidth();
		}
		
		try {
			Robot r = new Robot();
			screenshot = r.createScreenCapture(new Rectangle(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
		} catch (AWTException e1) {}
		
		try {
			blueScreen =  ImageIO.read(getClass().getResource(URL_OF_BLUE_SCREEN));
		} catch (IOException e1) {}
		
		Timer t = new Timer(50, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Frame.panel.repaint();
			}
		});
		t.start();
	}

	BufferedImage screenshot, blueScreen;
	public int numberOfCycles = 0;
	
	public void paintComponent(Graphics badG){
		super.paintComponent(badG);
		Graphics2D g = (Graphics2D) badG;
		g.drawImage(screenshot, null, 0, 0);
		g.setColor(Color.BLACK);
		
		if(!rectangles.isEmpty()){
			for(Rectangle r : rectangles){
				g.fillRect(r.x, r.y, r.width, r.height);
			}
		}
		
		numberOfCycles++;
		
		boolean allAreFinished = false;
		if(numberOfCycles > NUMBER_OF_CYCLES_TO_FILL_SCREEN){
			allAreFinished = true;
			for(Rectangle r : rectangles){
				if(r.getHeight() <= Toolkit.getDefaultToolkit().getScreenSize().getHeight()){
					allAreFinished = false;
				}
			}
		}
		if(!allAreFinished){
			increaceCoord();
			g.setColor(Color.GREEN);
			for(int i = 0; i < r.nextInt(MAX_NUMBER_OF_NUMBERS); i++){
				g.drawString(String.valueOf(r.nextInt(10)), r.nextInt((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()), r.nextInt((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
			}
		}
		else{
			rectangles.clear();
			g.setColor(Color.BLUE.darker());
			g.fillRect(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
			g.drawImage(blueScreen, null, 0, 0);
			exitTimer.start();
		}
	}
	
	public void increaceCoord(){
		for(int i = 0; i < rectangles.size(); i++){
			if(!rectangles.isEmpty()){
				rectangles.get(i).height = rectangles.get(i).height + speed.get(i) + r.nextInt(SPEED_VARIETY);
			}
		}
	}
	
	
}
