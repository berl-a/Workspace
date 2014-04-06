package com.bg.enums;

public enum EStyle1 {
	
	DOLLAR_STYL('$'), BOB_STYL('b');
	
	private char znak;
	
	EStyle1(char c){
		setZnak(c);
	}

	public char getZnak() {
		return znak;
	}

	public void setZnak(char znak) {
		this.znak = znak;
	}
	
}
