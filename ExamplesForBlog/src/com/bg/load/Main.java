package com.bg.load;

import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

	static BufferedImage image;
	static File file;
	
	public static void main(String[] args) {
		file = new Loader().load("bang.wav");
		System.out.println(file.getUsableSpace());
	} 
	

}
