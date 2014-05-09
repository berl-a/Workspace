import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Test extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	
	BufferedImage image;
	
	public void paintq(){
		try {
			image = ImageIO.read(new File("res/png.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		repaint();
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawImage(image, 10, 10, null);
		g.dispose();
		//Main part
		
		
		/*
		Kernel kernel = new Kernel(3, 3,new float[] {
			
						2f/9f, 2f/9f, 2f/9f,
						2f/9f, 2f/9f, 2f/9f,
						2f/9f, 2f/9f, 1f/9f});
		
		*/
		
		
		
		
		
		//float data[] = { 0.0625f, 0.125f, 0.0625f, 0.125f, 0.25f, 0.125f,
		//        0.0625f, 0.125f, 0.0625f };
		//Kernel kernel = new Kernel(3, 3, data);
		    
		float[] matrix = new float[225];
		for (int i = 0; i < 225; i++)
			matrix[i] = 1.0f/225.0f;

	    BufferedImageOp op = new ConvolveOp( new Kernel(15, 15, matrix), ConvolveOp.EDGE_NO_OP, null );
		
		image = op.filter(image, null);
		g.drawImage(image, 10, 10, null);
		//Finish main part
		//g.drawImage(image, 10, 10, null);
		
	}
}
