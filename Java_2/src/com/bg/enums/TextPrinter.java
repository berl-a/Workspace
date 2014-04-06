package com.bg.enums;

public class TextPrinter {

	public static void showText(String text, EStyle style) {
		
		if(style == EStyle.A_STYLE){
			System.out.println("@" + text + "@");
		}else if(style == EStyle.DOLLAR_STYLE){
			System.out.println("$" + text + "$");
		}
		
		
	}
	
	public static void main(String[] args){
		showText("SIMPLE TEXT", EStyle.valueOf("DOLLAR_STYLE"));
		
		showText("TEXT 2", EStyle.A_STYLE);
		
		String text = " TEEKKSST ";
		
		//showText("Stupid text", EStyle.valueOf("Stupiiiid!"));
		
		for(EStyle style : EStyle.values()){
			System.out.println(style.getStyleString() + text + style.getStyleString());
		}
		
		System.out.println("Supported styles: ");
		
		for(EStyle style : EStyle.values()){
			System.out.println("- " + style.toString() + "( type " + style.getStyleString() + " to use this style )");
		}
	}
}
