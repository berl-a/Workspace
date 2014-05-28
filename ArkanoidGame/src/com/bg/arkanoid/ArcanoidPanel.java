package com.bg.arkanoid;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.bg.arkanoid.etypes.ETypeOfBlock;
import com.bg.arkanoid.loaders.ImageLoader;
import com.bg.arkanoid.loaders.SoundLoader;

public class ArcanoidPanel extends JPanel implements ActionListener, KeyListener {
	
	
	private static final long serialVersionUID = 1L;

	private static final int DELAY_OF_REFRESHING = 2;
	
	private static final int BLUR_NUMBER = 5;

	private static final int DEFAULT_BAT_DELTA_X = 7;

	private static final int SIZE_OF_TEXT = 50;
	
	private static final int SCORES_FOR_BONUS = 20;
	
	Timer mainTimer;
	public static SoundLoader soundLoader;
	public static ImageLoader imageLoader;
	
	public static LinkedList<Brick> bricks = new LinkedList<Brick>();
	private static LinkedList<Bonus> bonuses = new LinkedList<Bonus>();
	private static LinkedList<SavingLine> lines = new LinkedList<SavingLine>();
	public static Player player;
	public static Ball ball = new Ball();
	public static Bat bat = new Bat();
	public static Line2D.Double up = new Line2D.Double(0 ,0, Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 0);
	public static Line2D.Double down = new Line2D.Double(0 ,Toolkit.getDefaultToolkit().getScreenSize().getHeight(), Toolkit.getDefaultToolkit().getScreenSize().getWidth(), Toolkit.getDefaultToolkit().getScreenSize().getHeight());
	public static Line2D.Double left = new Line2D.Double(0 ,0, 0, Toolkit.getDefaultToolkit().getScreenSize().getHeight());
	public static Line2D.Double right = new Line2D.Double(Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 0, Toolkit.getDefaultToolkit().getScreenSize().getWidth(), Toolkit.getDefaultToolkit().getScreenSize().getHeight());
	
	BufferedImage backImage;
	
	ArcanoidPanel(){
		imageLoader = new ImageLoader();
		imageLoader.loadImages();
		
		new CoordReader().readCoordinates();
		
		//setBackground(bricks.get(bricks.size() - 2).getColor());
		//setBackground(Color.WHITE);
		
		player = new Player("Roman");
		player.setLifes(10);
		
		ball = new Ball();
		//ball.setColor(bricks.get(bricks.size() - 1).getColor());
		ball.setAddDeltaX(0);
		ball.setAddDeltaY(0);
		
		bat.setCoord(new Point(   (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - bat.getSize().getWidth() / 2),  (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() - bat.getSize().getHeight())));
		bat.setColor(Color.RED);
		
		ball.setCoord(new Point((int)(ArcanoidPanel.bat.getCoord().getX() + (ArcanoidPanel.bat.getSize().getWidth() + ArcanoidPanel.ball.getDiameter()) / 2), (int)ArcanoidPanel.bat.getCoord().getY() - 40));
		
		mainTimer = new Timer(DELAY_OF_REFRESHING, this);
		
		soundLoader = new SoundLoader();
		soundLoader.loadSounds();
		
		//Loading background
		
		try {
			backImage = imageLoader.getImage("game background");
			
			float[] matrix = new float[BLUR_NUMBER * BLUR_NUMBER];
			for (int i = 0; i < BLUR_NUMBER * BLUR_NUMBER; i++)
				matrix[i] = 1.0f / (BLUR_NUMBER * BLUR_NUMBER);
		    BufferedImageOp op = new ConvolveOp( new Kernel(BLUR_NUMBER, BLUR_NUMBER, matrix), ConvolveOp.EDGE_NO_OP, null );
		    backImage = op.filter(backImage, null);
		
			MediaTracker media_tracker = new MediaTracker(this);
			media_tracker.addImage(backImage, 0);
			try{
		        media_tracker.waitForID(0);
			}catch(InterruptedException e){}
		}catch(Exception e){}
		
		mainTimer.start();
		
		addKeyListener(this);
	}
	
	private boolean hadTouchedBat = false;
	public void actionPerformed(ActionEvent e) {
		
		//Ball placing
		Point newLocation = new Point();
		
		if((!ball.holdNearBat) && (!ball.isMagnetted)){
			newLocation.setLocation(ball.getCoord().getX() + ball.getDeltaX() + ball.getAddDeltaX() ,  ball.getCoord().getY() + ball.getDeltaY() + ball.getAddDeltaY());
			ball.setCoord(newLocation);
		}else if(ball.holdNearBat){
			newLocation.setLocation(bat.getCoord().getX() + (bat.getSize().getWidth() / 2 - ball.getDiameter() / 2), bat.getCoord().getY() - ball.getDiameter() - 1);
			ball.setCoord(newLocation);
		}
			//else if(ball.isMagnetted)
		//	newLocation.setLocation(bat.getCoord().getX() + ball.getCoordBeforeMagnating().getX(), bat.getCoord().getY() + ball.getCoordBeforeMagnating().getY());
		
		//Checking blocks' intersections
		List<Brick> brickToDelete = new LinkedList<Brick>();
		for(Brick b : bricks){
			if(b.getRect().intersects(ball.getRect())){
				if(  (ball.getRect().intersectsLine(b.getUpSide())) || (ball.getRect().intersectsLine(b.getDownSide()))) {
					if(!ball.superBall)
						ball.setDeltaY( - ball.getDeltaY());
					ball.hit();
					brickToDelete.add(b);
				}else if(  (ball.getRect().intersectsLine(b.getLeftSide()))  ||  (ball.getRect().intersectsLine(b.getRightSide()))   ){
					if(!ball.superBall)
						ball.setDeltaX( - ball.getDeltaX());
					ball.hit();
					brickToDelete.add(b);
				}
			}
		}
		for(Brick b : brickToDelete)
			Brick.remove(b);
			//bricks.remove(b);
		
		//Bonus placing
		for(Bonus bonus : bonuses)
			bonus.setCoord(new Point((int)(bonus.getCoord().getX() + bonus.getDeltaX()), (int)(bonus.getCoord().getY() + bonus.getDeltaY())));
		
		//Checking bonuses' intersection
		List<Bonus> bonusesToGetEffect = new LinkedList<Bonus>();
		Rectangle batR = new Rectangle((int)bat.getCoord().getX(), (int)bat.getCoord().getY(), (int)bat.getSize().getWidth(), (int)bat.getSize().getHeight());
		for(Bonus b : bonuses){
			if(new Rectangle((int)b.getCoord().getX(), (int)b.getCoord().getY(), (int)b.getSize().getWidth(), (int)b.getSize().getHeight()).intersects(batR)){
				bonusesToGetEffect.add(b);
				player.setScores(player.getScores() + SCORES_FOR_BONUS);
			}
		}
		for(Bonus b : bonusesToGetEffect)
			b.getEffect();
		
		//Placing bat
		if(bat.getDeltaX() != 0){
			if(new Rectangle(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()).contains(new Rectangle((int)(bat.getCoord().getX() + bat.getDeltaX()), (int)bat.getCoord().getY(), (int)bat.getSize().getWidth(), (int)bat.getSize().getHeight()))){
				bat.setCoord(new Point( (int)(bat.getCoord().getX() + bat.getDeltaX()), (int)bat.getCoord().getY()));
				if(ball.isMagnetted)
					ball.setCoord(new Point((int)(ball.getCoord().getX() + bat.getDeltaX()), (int)ball.getCoord().getY()));
			}
		}
		
		//Getting bat away from walls
		if(new Rectangle((int)(bat.getCoord().getX() + bat.getDeltaX()), (int)bat.getCoord().getY(), (int)bat.getSize().getWidth(), (int)bat.getSize().getHeight()).intersectsLine(left)){
			bat.setCoord(new Point((int)bat.getCoord().getX() + 2, (int)bat.getCoord().getY()));	
			if(ball.isMagnetted)
				ball.setCoord(new Point((int)ball.getCoord().getX() + 2, (int)ball.getCoord().getY()));
		}
		if(new Rectangle((int)(bat.getCoord().getX() + bat.getDeltaX()), (int)bat.getCoord().getY(), (int)bat.getSize().getWidth(), (int)bat.getSize().getHeight()).intersectsLine(right)){
			bat.setCoord(new Point((int)bat.getCoord().getX() - 2, (int)bat.getCoord().getY()));
			if(ball.isMagnetted)
				ball.setCoord(new Point((int)ball.getCoord().getX() - 2, (int)ball.getCoord().getY()));
		}
		
		
		//Checking bat's intersections
		if(ball.getRect().intersectsLine(bat.getUp0())){
			
			if(!hadTouchedBat){
				if(ball.getDeltaX() > 0){
					ball.setDeltaX( - ball.getDeltaX());
					ball.setDeltaY( - ball.getDeltaY());
				}else if(ball.getDeltaX() < 0){
					ball.setDeltaX( + ball.getDeltaX() * 1.35);
					ball.setDeltaY( - ball.getDeltaY() / 1.35);
				}else{
					ball.setDeltaX( - Ball.DEFAULT_DELTA_X * 1.35);
					ball.setDeltaY( - ball.getDeltaY() / 1.35);
				}
				hadTouchedBat = true;
			}
			
			if(bat.magnetBat)
				ball.isMagnetted = true;
			
		}else if(ball.getRect().intersectsLine(bat.getUp1())){
			
			if(!hadTouchedBat){
				if(ball.getDeltaX() > 0){
					ball.setDeltaX( - ball.getDeltaX() / 1.25);
					ball.setDeltaY( - ball.getDeltaY() * 1.25);
				}else if(ball.getDeltaX() < 0){
					ball.setDeltaX( + ball.getDeltaX() * 1.25);
					ball.setDeltaY( - ball.getDeltaY() / 1.25);
				}else{
					ball.setDeltaX( - Ball.DEFAULT_DELTA_X * 1.25);
					ball.setDeltaY( - ball.getDeltaY() / 1.25);
				}
				hadTouchedBat = true;
			}
			
			if(bat.magnetBat)
				ball.isMagnetted = true;
			
		}else if(ball.getRect().intersectsLine(bat.getUp2())){
			
			if(!hadTouchedBat){
				if(ball.getDeltaX() > 0){
					ball.setDeltaX( - ball.getDeltaX() / 1.1);
					ball.setDeltaY( - ball.getDeltaY() * 1.1);
				}else if(ball.getDeltaX() < 0){
					ball.setDeltaX( + ball.getDeltaX() * 1.1);
					ball.setDeltaY( - ball.getDeltaY() / 1.1);
				}else{
					ball.setDeltaX( - Ball.DEFAULT_DELTA_X * 1.1);
					ball.setDeltaY( - ball.getDeltaY() / 1.1);
				}
				hadTouchedBat = true;
			}
			
			if(bat.magnetBat)
				ball.isMagnetted = true;
		
		
		}else if(ball.getRect().intersectsLine(bat.getUp3())){
			
			if(!hadTouchedBat){
				ball.setDeltaY( - ball.getDeltaY());
				hadTouchedBat = true;
			}
			
			if(bat.magnetBat)
				ball.isMagnetted = true;
			
		}else if(ball.getRect().intersectsLine(bat.getUp4())){

			if(!hadTouchedBat){
				if(ball.getDeltaX() > 0){
					ball.setDeltaX( + ball.getDeltaX() * 1.1);
					ball.setDeltaY( - ball.getDeltaY() / 1.1);
				}else if(ball.getDeltaX() < 0){
					ball.setDeltaX( - ball.getDeltaX() / 1.1);
					ball.setDeltaY( - ball.getDeltaY() * 1.1);
				}else{
					ball.setDeltaX( + Ball.DEFAULT_DELTA_X * 1.1);
					ball.setDeltaY( - ball.getDeltaY() / 1.1);
				}
				hadTouchedBat = true;
			}
			
			if(bat.magnetBat)
				ball.isMagnetted = true;
			
		}else if(ball.getRect().intersectsLine(bat.getUp5())){

				if(!hadTouchedBat){
					if(ball.getDeltaX() > 0){
						ball.setDeltaX( + ball.getDeltaX() * 1.25);
						ball.setDeltaY( - ball.getDeltaY() / 1.25);
					}else if(ball.getDeltaX() < 0){
						ball.setDeltaX( - ball.getDeltaX() / 1.25);
						ball.setDeltaY( - ball.getDeltaY() * 1.25);
					}else{
						ball.setDeltaX( + Ball.DEFAULT_DELTA_X * 1.25);
						ball.setDeltaY( - ball.getDeltaY() / 1.25);
					}
					hadTouchedBat = true;
				}
				
				if(bat.magnetBat)
					ball.isMagnetted = true;
			
		}else if(ball.getRect().intersectsLine(bat.getUp6())){
			
			if(!hadTouchedBat){
				if(ball.getDeltaX() > 0){
					ball.setDeltaX( + ball.getDeltaX() * 1.35);
					ball.setDeltaY( - ball.getDeltaY() / 1.35);
				}else if(ball.getDeltaX() < 0){
					ball.setDeltaX( - ball.getDeltaX());
					ball.setDeltaY( - ball.getDeltaY());
				}else{
					ball.setDeltaX( + Ball.DEFAULT_DELTA_X * 1.35);
					ball.setDeltaY( - ball.getDeltaY() / 1.35);
				}
				hadTouchedBat = true;
			}
			
			if(bat.magnetBat)
				ball.isMagnetted = true;
			
		}else if(   (ball.getRect().intersectsLine(bat.getLeft()))  ||  (ball.getRect().intersectsLine(bat.getRight()))   ){
			if(!hadTouchedBat){
				ball.setDeltaX( - ball.getDeltaX());
				player.loseBall();
			}
		}else
			hadTouchedBat = false;
		
		
		//Ball's intersection with saving line
		LinkedList<SavingLine> linesToRemove = new LinkedList<SavingLine>();
		for(SavingLine line : lines){
			if(ball.getRect().intersects(line.getRect())){
				ball.setDeltaY( - ball.getDeltaY());
				linesToRemove.add(line);
			}
		}
		for(SavingLine line : linesToRemove)
			lines.remove(line);
		
		
		//Out-of-screen block
		if(ball.getCoord().getX() + ball.getDiameter() >= Toolkit.getDefaultToolkit().getScreenSize().getWidth()){
			ball.setDeltaX( - ball.getDeltaX());
		}if(ball.getCoord().getY() <= 0){
			ball.setDeltaY( - ball.getDeltaY());
		}if(ball.getCoord().getX() < 0){
			ball.setDeltaX( - ball.getDeltaX());
		}
		
		if(!new Rectangle(0,0,(int)getWidth(), (int)getHeight()).intersects(ball.getRect()))
			player.loseBall();
		
		//Checking lose
		if( ball.getRect().intersectsLine(down) )
			player.loseBall();
		
		//Checking win
		if(bricks.isEmpty())
			player.winGame();
		
		repaint();
	}
	
	public void paintComponent(Graphics badG){
		super.paintComponent(badG);
		Graphics2D g = (Graphics2D)badG;
		
		//Low speed with antialiasing
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.drawImage(backImage, 0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight(), null);
		
		for(Brick brick : bricks){
			g.setColor(brick.getColor());			
			g.fillRoundRect(brick.getCoord().x, brick.getCoord().y, (int)brick.getSize().getWidth(), (int)brick.getSize().getHeight(), (int)brick.getSize().getWidth() / 5, (int)brick.getSize().getHeight() / 5);
			
			if(brick.getTypeOfBlock() == ETypeOfBlock.ARMORED_BLOCK){
				if(brick.getHitsToDestroy() > 1){
					g.setColor(brick.getColor().darker().darker());
					g.drawLine(brick.getCoord().x, (int)(brick.getCoord().y + brick.getSize().getHeight() / 2), (int)(brick.getCoord().x + brick.getSize().getWidth()), (int)(brick.getCoord().y + brick.getSize().getHeight() / 2));
				}
			}else if(brick.getTypeOfBlock() == ETypeOfBlock.BONUS_BLOCK){
				g.setColor(brick.getColor().brighter().brighter());
				g.fillArc((int)(brick.getCoord().getX() + brick.getSize().getWidth() / 2 - brick.getSize().getHeight() / 2) + 1, (int)brick.getCoord().getY() + 1, (int)brick.getSize().getHeight() - 2, (int)brick.getSize().getHeight() - 2, 0, 360);
			}
		}
		
		for(Bonus bonus : bonuses)
			g.drawImage(bonus.getImage(), null, (int)bonus.getCoord().getX(), (int)bonus.getCoord().getY());
			
		
		g.setColor(ball.getColor());
		g.fillArc((int)ball.getCoord().getX(), (int)ball.getCoord().getY(), (int)ball.getDiameter(), (int)ball.getDiameter(), 0, 360);
		
		if(ball.superBall){
			
			Random r = new Random();
			
			int red = ball.getColor().getRed(), green = ball.getColor().getGreen(), blue = ball.getColor().getBlue(), alpha = ball.getColor().getAlpha();
			
			int whatParamToChange = r.nextInt(4);
			
			if(whatParamToChange == 0)
				red = ball.getColor().getRed() + r.nextInt(60) - 30;
			if(whatParamToChange == 1)
				green = ball.getColor().getGreen() + r.nextInt(60) - 30;
			if(whatParamToChange == 2)
				blue = ball.getColor().getBlue() + r.nextInt(60) - 30;
			if(whatParamToChange == 3)
				alpha = ball.getColor().getAlpha() + r.nextInt(60) - 30;
			
			if((red < 0) || (red > 255))
				red = ball.getColor().getRed();
			if((green < 0) || (green > 255))
				green = ball.getColor().getGreen();
			if((blue < 0) || (blue > 255))
				blue = ball.getColor().getBlue();
			if((alpha < 0) || (alpha > 255))
				alpha = ball.getColor().getAlpha();
			
			g.setColor(new Color(red, green, blue, alpha));
			g.fillArc((int)ball.getCoord().getX(), (int)ball.getCoord().getY(), (int)(ball.getDiameter()), (int)(ball.getDiameter()), 0, 360);
		}
		
		if(bat.magnetBat){
			g.setColor(new Color(0, 0, 150, 50));
			g.fillOval((int)bat.getCoord().getX(), (int)(bat.getCoord().getY() - bat.getSize().getHeight() / 2), (int)bat.getSize().getWidth(), (int)bat.getSize().getHeight());
		}
		
		g.setColor(bat.getColor());
		g.fillRoundRect(bat.getCoord().x, bat.getCoord().y, (int)bat.getSize().getWidth(), (int)bat.getSize().getHeight(), (int)bat.getSize().getWidth() / 3, (int)bat.getSize().getHeight() / 3);
	
		
			
		
		for(SavingLine line : lines){
			g.setColor(line.getColor());
			g.fill(line.getRect());
		}
		
		//g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setFont(new Font("font", Font.BOLD, SIZE_OF_TEXT));
		
		g.setColor(new Color(200, 200, 0, 150));
		g.drawString(String.valueOf(player.getScores()), 10, 50);
		
		g.setColor(new Color(20, 200, 20, 150));
		g.drawString(String.valueOf(player.getLifes()), 10, 100);
	}
	
	public static void addLine(SavingLine line){
		lines.add(line);
	}
	
	public static void removeLine(SavingLine line){
		lines.remove(line);
	}
	
	public static int getNumberOfLines(){
		return lines.size();
	}

	public static void addBonus(Bonus bonus) {
		bonuses.add(bonus);
	}

	public static void removeBonus(Bonus bonus) {
		bonuses.remove(bonus);
	}
	
	public void keyPressed(KeyEvent e) {
		//System.out.println(e.getKeyCode());
		
		if(e.getKeyCode() == 37){
			bat.setDeltaX( - DEFAULT_BAT_DELTA_X);
		}else if(e.getKeyCode() == 39){
			bat.setDeltaX(DEFAULT_BAT_DELTA_X);
		}
		
		if(e.getKeyCode() == 38){
			ball.holdNearBat = false;
			ball.isMagnetted = false;
		}
		
		
		if(e.getKeyCode() == 40){
			ball.setAddDeltaX(ball.getDeltaX() * 2);
			ball.setAddDeltaY(ball.getDeltaY() * 2);
		}
		
	}

	public void keyReleased(KeyEvent e) {
		
		if( (e.getKeyCode() == 37 ) || (e.getKeyCode() == 39) ){
			bat.setDeltaX(0);
		}
		if(e.getKeyCode() == 40){
			ball.setAddDeltaX(0);
			ball.setAddDeltaY(0);
		}
		
	}

	public void keyTyped(KeyEvent e) {}

	
}
