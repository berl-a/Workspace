package com.bg.enums;


public enum EStyle{
	
	DOLLAR_STYLE("$"),
	A_STYLE("@"),
	HASHTAG_STYLE("#"),
	PERCENT_STYLE("%"),
	C_STYLE("C"),
	NUMBER_STYLE(121);
	
	private final String styleString;

	EStyle(String styleString){
		this.styleString = styleString;
	}
	
	EStyle(int number){
		this.styleString = String.valueOf(number);
	}
	
	public String getStyleString() {
		return styleString;
	}



}
