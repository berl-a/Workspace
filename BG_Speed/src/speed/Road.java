package speed;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Road extends JPanel implements ActionListener, Runnable{
	
	final int MAX_V_OF_ENEMIES=20;
	
	int whatcar=1;
	
	int collisions=0;
	
	int type_of_enemy=0;
	
	int points=0;
	
	int time=0;
	int s_v=0;
	
	

	Timer mainTimer=new Timer(20,this);
	Timer repainter=new Timer(70,new ActionListener(){
		public void actionPerformed(ActionEvent e){
			
			if((p.v!=0)&&(p.can_change==true)){
									
				if(whatcar==1){
					p.img=new ImageIcon("res/resources/Car.png").getImage();
					repaint();
					whatcar=2;
				}
				else if(whatcar==2){
					p.img=new ImageIcon("res/resources/Car_1.png").getImage();
					repaint();
					whatcar=1;
				}
			}
		}
	});
	
	Image img=new ImageIcon("res/resources/Road.jpg").getImage();
	
	Image img_e;
	Image img_e1=new ImageIcon("res/resources/Kushch.png").getImage();
	Image img_e2=new ImageIcon("res/resources/Enemy.png").getImage();

	
	Player p=new Player();
	
	Thread enemiesFactory=new Thread(this);
	
	List<Enemy> enemies=new ArrayList<Enemy>();
	
	public Road(){
		mainTimer.start();
		repainter.start();
		enemiesFactory.start();
		addKeyListener(new MyKeyAdapter());
		setFocusable(true);
	}
	
	private class MyKeyAdapter extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			p.keyPressed(e);
		}
		public void keyReleased(KeyEvent e){
			p.keyReleased(e);
		}
	}
	
	
	public void paint(Graphics g){
		g=(Graphics2D) g;
		g.drawImage(img,p.layer1,0,null);
		g.drawImage(img, p.layer2, 0, null);		
		g.drawImage(p.img, p.x, p.y, null);
		
		double v=p.v*50;
		g.setColor(new Color(115,171,247));
		g.setFont(new Font("BG_speed", Font.ITALIC, 35));
		g.drawString(("Швидкість= "+v+" пікселів/с"), 200, 30);
		g.setFont(new Font("BG_speed", Font.ITALIC, 25));
		g.setColor(Color.RED);
		g.drawString(("У вас "+points+" очок"), 500, 383);
		
		Iterator<Enemy> i=enemies.iterator();
		
		while(i.hasNext()){
			Enemy e=i.next();
			if((e.x<=-1240)||(e.x>=1240)){
				i.remove();
			}
			else{
				
				
				if((e.type_of_enemy==0)||(e.type_of_enemy==1)){
					img_e=img_e1;
				}
				if(e.type_of_enemy==2){
					img_e=img_e2;
				}
				g.drawImage(img_e, e.x, e.y, null);
				e.move();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			time++;
			s_v+=p.v;
			
			p.move();
			repaint();
			testCollisionWithEnemies();
			if(p.s>=40000){
				String s="";
				if(s_v/time>=28){s=" . Ви просто затиснули стрілку. Я правий?";}
				else if((s_v/time>=25)&&(s_v/time<32)){s=". Ви швидкий!";}
				else if((s_v/time<=15)&&(s_v/time>0)){s=". Ви обережний!";}
				else{s=" . Ви обережний!";}
				
				JOptionPane.showMessageDialog(null, " Вітаємо! Ваш рахунок: "+points+" "+s);
				System.exit(1);
			}

			}


	private void testCollisionWithEnemies() {
		
		Iterator<Enemy> i=enemies.iterator();
		while(i.hasNext()){
			Enemy e=i.next();
			if(p.getRect().intersects(e.getRect(e.type_of_enemy))){
				if((e.type_of_enemy==0)||(e.type_of_enemy==1)){
					points+=100;
				}
				if(e.type_of_enemy==2){
					points-=300;
				}
				i.remove();
				

			
			}
		}
		
		
	}

	public void run() {
		
		while(true){
			Random r=new Random();
			try {
				Thread.sleep(r.nextInt(2000));
				enemies.add(new Enemy(900,r.nextInt(300),r.nextInt(MAX_V_OF_ENEMIES),this,r.nextInt(3)));
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	

}
