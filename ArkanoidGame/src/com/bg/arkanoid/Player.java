package com.bg.arkanoid;

import javax.swing.JOptionPane;

public class Player {

	private static final int DEF_NUMBER_IF_LIFES = 3;
	
	private int lifes = DEF_NUMBER_IF_LIFES;;
	private int scores = 0;
	private String name;
	
	public Player(String name){
		setName(name);
	}
	
	public void endGame(){
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
	
}
