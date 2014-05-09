package com.bg.arkanoid.images;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;

import com.bg.arkanoid.etypes.ETypeOfBonus;

public class Images {

	String[] imageURLs = {
			"res/bonus0.png",
			"res/bonus1.png"
		};
	
	String[] names = {
			ETypeOfBonus.SUPER_BALL.toString(),
			ETypeOfBonus.EXTRA_LIFE.toString()
		};
	
	LinkedHashMap<String, BufferedImage> images = new LinkedHashMap<String, BufferedImage>(imageURLs.length); 
	
	public boolean loadImages(){
		try{
			
			for(int i = 0; i < imageURLs.length; i++){
				images.put(
					names[i],
					ImageIO.read(new File(imageURLs[i]))
				);
			}
			
			return true;
			
		}catch(Exception e){
			return false;
		}
	}
	
	public BufferedImage getImage(String string) {
		return images.get(string);
	}

}
