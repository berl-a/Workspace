package com.bg.arkanoid.loaders;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;

import com.bg.arkanoid.etypes.ETypeOfBonus;

public class ImageLoader {

	String[] imageURLs = {
			"res/superBall.png",
			"res/extraLife.png",
			"res/incWidth.png",
			"res/decWidth.png",
			"res/incSpeed.png",
			"res/decSpeed.png",
			"res/bigBall.png",
			"res/smallBall.png",
			"res/rocket.png",
			"res/usual.png",
			"res/lines.png", 
			"res/magnet.png",
			"res/background.png"
		};
	
	String[] names = {
			ETypeOfBonus.SUPER_BALL.toString(),
			ETypeOfBonus.EXTRA_LIFE.toString(),
			ETypeOfBonus.INC_BAT_WIDTH.toString(),
			ETypeOfBonus.DEC_BAT_WIDTH.toString(),
			ETypeOfBonus.INC_BALL_SPEED.toString(),
			ETypeOfBonus.DEC_BALL_SPEED.toString(),
			ETypeOfBonus.INC_BALL_SIZE.toString(),
			ETypeOfBonus.DEC_BALL_SIZE.toString(),
			ETypeOfBonus.DESTROYING_ROCKET.toString(),
			ETypeOfBonus.USUAL_BALL.toString(),
			ETypeOfBonus.SAVING_LINE.toString(),
			ETypeOfBonus.MAGNET_BAT.toString(),
			"game background"
		};
	
	LinkedHashMap<String, BufferedImage> images = new LinkedHashMap<String, BufferedImage>(imageURLs.length); 
	
	public boolean loadImages(){
		try{
			for(int i = 0; i < imageURLs.length; i++){
				images.put(
					names[i],
					//ImageIO.read(new File(imageURLs[i]))
					ImageIO.read(getClass().getResource(imageURLs[i]))
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
