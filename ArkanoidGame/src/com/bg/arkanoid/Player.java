package com.bg.arkanoid;

import java.awt.Point;

import javax.swing.JOptionPane;

public class Player {

	private static final int DEF_NUMBER_IF_LIFES = 3;
	
	private int lifes = DEF_NUMBER_IF_LIFES;;
	private int scores = 0;
	private String name;
	
	public Player(String name){
		setName(name);
	}
	
	public void winGame(){
		JOptionPane.showMessageDialog(null, "You won");
		System.exit(0);
	}
	
	public void loseGame(){
		JOptionPane.showMessageDialog(null, "You lose");
		System.exit(0);
	}
	
	public int getLifes() {
		return lifes;
	}
	public void setLifes(int lifes) {
		this.lifes = lifes;
	}
	public int getScores() {
		return scores;
	}
	public void setScores(int scores) {
		this.scores = scores;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void loseBall() {
		
		if(getLifes() == 0)
			loseGame();
		else{
			setLifes(getLifes() - 1);
			ArcanoidPanel.ball.holdNearBat = true;
		}
		
		if(Bonus.backCoordWhenBallFalls){
			ArcanoidPanel.bat.setCoord(Bonus.oldCoordOfBat);
			Bonus.backCoordWhenBallFalls = false;
		}
		
		ArcanoidPanel.ball.setDeltaX(Ball.DEFAULT_DELTA_X);
		ArcanoidPanel.ball.setDeltaY(Ball.DEFAULT_DELTA_Y);
		
		if(!ArcanoidPanel.ball.holdNearBat)
			ArcanoidPanel.ball.setCoord(new Point((int)(ArcanoidPanel.bat.getCoord().getX() + (ArcanoidPanel.bat.getSize().getWidth() + ArcanoidPanel.ball.getDiameter()) / 2), (int)ArcanoidPanel.bat.getCoord().getY() - 40));
		
		Bonus.looseEffect();
	}
	
}
