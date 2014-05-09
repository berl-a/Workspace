package com.bg.arkanoid.menu_elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

public class MyMenuButton extends JButton implements ActionListener {

	private String text;
	private BufferedImage image;
	private BufferedImage pressedImage;
	private Color color;
	
	private MyMenuButton(){
		addActionListener(this);
	}
	
	MyMenuButton(String text){
		this();
		this.text = text;
	}
	
	MyMenuButton(BufferedImage image, BufferedImage pressedImage){
		this();
		this.image = image;
		this.pressedImage = pressedImage;
	}
	
	MyMenuButton(String text, BufferedImage image, BufferedImage pressedImage){
		this();
		this.text = text;
		this.image = image;
		this.pressedImage = pressedImage;
	}
	
	MyMenuButton(Color color, int transparency){
		this();
		this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), transparency);
	}
	
	MyMenuButton(String text, Color color, int transparency){
		this();
		this.text = text;
		this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), transparency);
	}
	
	@Override
	protected void paintComponent(Graphics badG){
		Graphics2D g = (Graphics2D) badG;
		
		if(color != null){
			g.setColor(getBackground());
			g.fillRoundRect(getBounds().x, getBounds().y, (int)getBounds().getWidth(), (int)getBounds().getHeight(), 100, 100);
		}
		
		if(image != null){
			g.drawImage(image, null, 0, 0);
		}
			
		if(text != null){
			g.setColor(getForeground());
			g.setFont(new Font("", Font.PLAIN, 273));
			g.drawString(text, 0,0);
		}
		}

	public void actionPerformed(ActionEvent e) {
		BufferedImage oldImage = image;
		image = pressedImage;
		repaint();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {}
		image = oldImage;
		
	}
}
