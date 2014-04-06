package speed;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player {

	public static final int MAX_V = 30;
	public static final int MAX_TOP=45;
	public static final int MAX_BOTTOM=268;
	Image img_c=new ImageIcon("res/resources/Car.png").getImage();
	Image img_l=new ImageIcon("res/resources/Car_up.png").getImage();
	Image img_r=new ImageIcon("res/resources/Car_down.png").getImage();
	
	
	ImageIcon ii=new ImageIcon();
	
	boolean can_change=true;
	
	
	Image img=img_c;
	
	public Rectangle getRect(){
		return new Rectangle(x,y,110,80);
	}
	

	int v=0;
	int dv=0;
	int s=0;
	
	int x=50;
	int y=155;
	int dy=0;
	
	int layer1=0;
	int layer2=800;
	
	
	public void move(){
		s+=v;
		
		
		if(v<=0){
			v=0;
		}
		
		if(v>=MAX_V){
			v=MAX_V-1;
			dv=0;
		}
		
		if(layer2-v<=0){
			layer1=0;
			layer2=800;
		}
		v+=dv;
		
		if(y<=MAX_TOP) y=MAX_TOP;
		if(y>=MAX_BOTTOM) y=MAX_BOTTOM;
		
		y+=-dy;
		layer1-=v;
		layer2-=v;
		
	}

	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_RIGHT){
			dv=2;
			v+=5;
		}
		if(key==KeyEvent.VK_LEFT){
			dv=-2;
					}
		
		if(key==KeyEvent.VK_UP){
			dy=7;
			img=img_l;
			can_change=false;
			
		}
		
		if(key==KeyEvent.VK_DOWN){
			dy=-7;
			img=img_r;
			can_change=false;
			}
		
	}

	public void keyReleased(KeyEvent e) {
		int key=e.getKeyCode();
		if(key==KeyEvent.VK_RIGHT){
			dv=0;
			v-=5;
		}
		if(key==KeyEvent.VK_LEFT){
			dv=0;
		}
		if((key==KeyEvent.VK_UP)||(key==KeyEvent.VK_DOWN)){
			dy=0;
			img=img_c;
			can_change=true;
			
			
		}
	}
	

}
