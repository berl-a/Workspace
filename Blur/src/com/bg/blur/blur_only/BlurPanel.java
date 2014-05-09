package com.bg.blur.blur_only;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BlurPanel extends JPanel {

	private static final String STRING_TO_IDENTIFY_URL = "http";
	private static final String DEFAULT_EXPORT_LOCATION = "D://";
	//private static final String DEFAULT_EXPORT_LOCATION = BlurPanel.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	private String imageURL;
	private String locationOfFile;
	private String locationToExport = locationOfFile;
	private boolean isImageLocal = true;
	
	private BufferedImage startImage;
	private BufferedImage nowImage;
	
	private JSlider slider = new JSlider(1, 30, 1);


	
	public BlurPanel(){
		
		setLayout(new BorderLayout());
		
		JButton exportB = new JButton();
		exportB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				export();
			}
		});
		exportB.setBackground(Color.GREEN);
		add(exportB, BorderLayout.SOUTH);
		
		slider.setBounds(0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 30);
		slider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent ce) {
				blur();
			}
		});
		add(slider, BorderLayout.NORTH);
		
		try {
			if(Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor).toString().contains(STRING_TO_IDENTIFY_URL)){
				locationOfFile = Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor).toString();
				isImageLocal = false;
			}else{
				JFileChooser chooser = new JFileChooser();
				chooser.setMultiSelectionEnabled(false);
				if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					imageURL = chooser.getSelectedFile().getAbsolutePath();
					locationOfFile = chooser.getSelectedFile().getPath();
				}
			}
		} catch (HeadlessException e1) {
		} catch (UnsupportedFlavorException e1) {
		} catch (IOException e1) {}
		
		if(locationOfFile.contains(STRING_TO_IDENTIFY_URL)){
			locationToExport = DEFAULT_EXPORT_LOCATION;
		}
		try {
			if(isImageLocal)
				startImage = ImageIO.read(new File(imageURL));
			else{
				startImage = ImageIO.read(new URL(locationOfFile));
				final MediaTracker media_tracker = new MediaTracker(this);
				media_tracker.addImage(nowImage, 0);
				
				new Thread(){
					public void run(){
						try {
							media_tracker.waitForID(0);
							if(media_tracker.checkAll()){
								repaint();
								stop();
							}
						} catch (InterruptedException e) {}
					}
				}.start();
			}
			
		} catch (IOException e) {}
		nowImage = startImage;
		
		repaint();
	}
	
	public void blur(){
		
		float[] matrix = new float[slider.getValue() * slider.getValue()];
		
		for (int i = 0; i < slider.getValue() * slider.getValue(); i++)
			matrix[i] = 1.0f / (slider.getValue() * slider.getValue());
	    BufferedImageOp op = new ConvolveOp( new Kernel(slider.getValue(), slider.getValue(), matrix), ConvolveOp.EDGE_NO_OP, null );
	    nowImage = op.filter(startImage, null);
	
		final MediaTracker media_tracker = new MediaTracker(this);
		media_tracker.addImage(nowImage, 0);
		
		new Thread(){
			public void run(){
				try {
					media_tracker.waitForID(0);
					if(media_tracker.checkAll()){
						repaint();
						stop();
					}
				} catch (InterruptedException e) {}
			}
		}.start();
		
	}
	
	private void export(){
		//String name = JOptionPane.showInputDialog(null, "Choose name");
		try {
			Robot r = new Robot();
			ImageIO.write(r.createScreenCapture(new Rectangle((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - nowImage.getWidth() / 2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - nowImage.getHeight() / 2), nowImage.getWidth(), nowImage.getHeight())), "png", new File(locationToExport + " blured.png"));
		} catch (AWTException e1) {} catch (IOException e1) {}
	}
	
	public void paintComponent(Graphics badG){
		super.paintComponent(badG);
		Graphics2D g = (Graphics2D)badG;
		if(nowImage != null)
			g.drawImage(nowImage, (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - nowImage.getWidth() / 2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - nowImage.getHeight() / 2), null);
		
	}
}
