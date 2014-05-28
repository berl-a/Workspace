package com.bg.arkanoid.possible_menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JLabel;

public class MyMenuButton extends JLabel{

	private static final int X_GAP = 60;
	private static final int Y_GAP = 70;
	
	private String text;
	private BufferedImage normalImage;
	private BufferedImage pressedImage;
	private BufferedImage nowImage;
	
	private MyMenuButton(){
		setHorizontalAlignment(JLabel.CENTER);
	}
	
	MyMenuButton(String text){
		this();
		this.text = text;
	}
	
	MyMenuButton(BufferedImage normImage, BufferedImage pressedImage){
		this();
		this.normalImage = normImage;
		this.pressedImage = pressedImage;
		this.nowImage = normImage;
	}
	
	MyMenuButton(String text, BufferedImage normImage, BufferedImage pressedImage){
		this();
		this.text = text;
		this.normalImage = normImage;
		this.pressedImage = pressedImage;
		this.nowImage = normImage;
	}
	
	MyMenuButton(Color color, int transparency){
		this();
		
		setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), transparency));
	}
	
	MyMenuButton(String text, Color color, int transparency){
		this();
		this.text = text;
		
		setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), transparency));
	}
	
	protected void render(Graphics2D g){
		
		if(getBackground() != null){
			g.fillRoundRect(getBounds().x + X_GAP, getBounds().y + Y_GAP, (int)getBounds().getWidth() - X_GAP * 2, (int)getBounds().getHeight() - Y_GAP * 2, 100, 100);
		}
		
		if(nowImage != null){
			g.drawImage(nowImage, null, 0, 0);
		}
			
		if(text != null){
			g.setColor(getBackground());
			g.setFont(new Font("", Font.PLAIN, 50));
			g.drawString(text, 0,0);
		}
	}
	
	public void press(){
		
		setBackground(getBackground().darker());
		
		if(pressedImage != null)
			setImage(pressedImage);
		
		render((Graphics2D)getGraphics());
		
		System.out.println("press");
	}
	
	public void release(){
		setBackground(getBackground().brighter());
		
		if(pressedImage != null)
			setImage(normalImage);
		
		render((Graphics2D)getGraphics());
	
		System.out.println("release");
	}
	
	public void changeColorBit(){
		setBackground(new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)));
	}
	
	private void setImage(BufferedImage image) {
		this.nowImage = image;
	}

	public String getText(){
		return text;
	}

	/*
	public void actionPerformed(ActionEvent e) {
		BufferedImage oldImage = normalImage;
		normalImage = pressedImage;
		repaint();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {}
		normalImage = oldImage;
		
	}
	*/
}
