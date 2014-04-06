package speed;

import java.awt.Rectangle;



public class Enemy {

	int x;
	int y;
	int v;
	int type_of_enemy;
	
	
	
	Road road;
	
	public Rectangle getRect(int type_of_enemy){
		if((type_of_enemy==0)||(type_of_enemy==1)){
			return new Rectangle(x,y,40,40);
		}
		else{
			return new Rectangle(x,y,110,80);
		}
	}
	
	public Enemy(int x,int y,int v, Road road,int type_of_enemy){
		this.x=x;
		this.y=y;
		if(type_of_enemy==2) this.v=v;
		else v=0;
		this.road=road;
		this.type_of_enemy=type_of_enemy;
				

		
	}
	
	public void move(){
		//Стоїть =>  v=0
		x=x-road.p.v+v;
		
	}
	
	public static void main(String[] args) {
		

	}

}
