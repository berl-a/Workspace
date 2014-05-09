package com.bg.arkanoid.level_creator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.bg.arkanoid.CoordReader;
import com.bg.arkanoid.etypes.ETypeOfBlock;

public class LevelCreatorPanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener, ChangeListener{

	private static final String CURSOR_URL = "res/empty.png";

	private static final int REPAINT_DELAY = 10;


	Point nowMouseCoord= new Point(0,0);
	Dimension nowSizeOfBlock = new Dimension(5,5);
	Dimension nowSizeOfBat = new Dimension(20,5);

	private Color nowColor;
	private ETypeOfBlock nowTypeOfBlock = ETypeOfBlock.NORMAL_BLOCK;
	JTextField widthField;
	JTextField heightField;
	
	JSlider redSl;
	JSlider greenSl;
	JSlider blueSl;
	JSlider alphaSl;
	
	JComboBox<String> typesOfBlock;
	
	JTextField batWidthField;
	JTextField batHeightField;
	
	JCheckBox visibleElem;
	
	List<Point> coords = new LinkedList<Point>();
	
	List<Rectangle> rectsToDraw = new LinkedList<Rectangle>();
	List<Color> colorsToDraw = new LinkedList<Color>();
	
	Timer repaintTimer = new Timer(REPAINT_DELAY, new ActionListener(){
		public void actionPerformed(ActionEvent e){
			repaint();
		}
	});
	
	LevelCreatorPanel(){
		addMouseMotionListener(this);
		addMouseListener(this);
		repaintTimer.start();
		try {
			setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ImageIO.read(getClass().getResource(CURSOR_URL)), new Point(0,0), ""));
		} catch (Exception e) {}
		
		widthField = new JTextField(3);
		heightField = new JTextField(3);
		widthField.addActionListener(this);
		heightField.addActionListener(this);
		add(widthField);
		add(heightField);
		
		redSl = new JSlider(0, 255, 50);
		greenSl = new JSlider(0, 255, 50);
		blueSl = new JSlider(0, 255, 50);
		alphaSl = new JSlider(0, 255, 200);
		
		redSl.setOrientation(SwingConstants.VERTICAL);
		greenSl.setOrientation(SwingConstants.VERTICAL);
		blueSl.setOrientation(SwingConstants.VERTICAL);
		alphaSl.setOrientation(SwingConstants.VERTICAL);
		
		redSl.setBackground(nowColor);
		greenSl.setBackground(nowColor);
		blueSl.setBackground(nowColor);
		alphaSl.setBackground(nowColor);
		
		redSl.addChangeListener(this);
		greenSl.addChangeListener(this);
		blueSl.addChangeListener(this);
		alphaSl.addChangeListener(this);
		
		add(redSl);
		add(greenSl);
		add(blueSl);
		add(alphaSl);
		
		typesOfBlock = new JComboBox<String>();
		for(ETypeOfBlock type : ETypeOfBlock.values())
			typesOfBlock.addItem(type.toString());
		
		typesOfBlock.setSelectedIndex(0);
		typesOfBlock.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				nowTypeOfBlock = ETypeOfBlock.valueOf((String)typesOfBlock.getSelectedItem());
				System.out.println(nowTypeOfBlock.toString());
			}
		});
		add(typesOfBlock);
		
		batWidthField = new JTextField(3);
		batWidthField.addActionListener(this);
		add(batWidthField);
		
		batHeightField = new JTextField(3);
		batHeightField.addActionListener(this);
		add(batHeightField);
		
		visibleElem = new JCheckBox("Show elements", true);
		visibleElem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(!visibleElem.isSelected()){
					widthField.setVisible(false);
					heightField.setVisible(false);
					redSl.setVisible(false);
					greenSl.setVisible(false);
					blueSl.setVisible(false);
					alphaSl.setVisible(false);
					batWidthField.setVisible(false);
					batHeightField.setVisible(false);
					typesOfBlock.setVisible(false);
				}else{
					widthField.setVisible(true);
					heightField.setVisible(true);
					redSl.setVisible(true);
					greenSl.setVisible(true);
					blueSl.setVisible(true);
					alphaSl.setVisible(true);
					batWidthField.setVisible(true);
					batHeightField.setVisible(true);
					typesOfBlock.setVisible(true);
				}
			}
		});
		add(visibleElem);
		
		Timer t = new Timer(1000, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!fileWritingIsStarted){
					startFile();
					continueWriteToFile();
				}else
					continueWriteToFile();
			}
		});
		t.start();
		
	}

	public void paintComponent(Graphics badG){
		super.paintComponent(badG);
		Graphics2D g = (Graphics2D)badG;
		g.setColor(nowColor);
		g.drawRoundRect(nowMouseCoord.x, nowMouseCoord.y, nowSizeOfBlock.width, nowSizeOfBlock.height, nowSizeOfBlock.width / 5, nowSizeOfBlock.height / 5);
	
		if(!rectsToDraw.isEmpty()){
			for(int i = 0; i < rectsToDraw.size(); i++){
				g.setColor(colorsToDraw.get(i));
				g.fillRoundRect(rectsToDraw.get(i).x, rectsToDraw.get(i).y, rectsToDraw.get(i).width, rectsToDraw.get(i).height, rectsToDraw.get(i).width / 5, rectsToDraw.get(i).height / 5);
			}
		}
	}
	
	public void stateChanged(ChangeEvent e) {
		nowColor = new Color(redSl.getValue(), greenSl.getValue(), blueSl.getValue(), alphaSl.getValue());
		redSl.setBackground(nowColor);
		greenSl.setBackground(nowColor);
		blueSl.setBackground(nowColor);
		alphaSl.setBackground(nowColor);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == widthField){
			if(CoordReader.isNumber(widthField.getText()))
				nowSizeOfBlock.width = Integer.parseInt(widthField.getText());
		}
		
		if(e.getSource() == heightField){
			if(CoordReader.isNumber(heightField.getText()))
				nowSizeOfBlock.height = Integer.parseInt(heightField.getText());
		}
		
		if(e.getSource() == batWidthField){
			if(CoordReader.isNumber(batWidthField.getText()))
				nowSizeOfBat.width = Integer.parseInt(batWidthField.getText());
		}
		
		if(e.getSource() == batHeightField){
			if(CoordReader.isNumber(batHeightField.getText()))
				nowSizeOfBat.height = Integer.parseInt(batHeightField.getText());
			
		}
	}
	
	File newLevelFile;
	FileWriter fw;
	boolean fileWritingIsStarted = false;
	
	private void startFile() {
		
		JFileChooser chooser = new JFileChooser();
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setApproveButtonText("Create a new level here");
		chooser.setCurrentDirectory(new File("").getAbsoluteFile());
		
		String choosedDirName = null;
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			choosedDirName = chooser.getSelectedFile().getAbsolutePath();
		}
		newLevelFile = new File(choosedDirName + "/" + JOptionPane.showInputDialog(null, "Choose file name") + ".txt");
		
		try {
			fw = new FileWriter(newLevelFile);
		} catch (IOException e) {}
		
		fileWritingIsStarted = true;
			
	}
	
	private void continueWriteToFile(){
		if(!coords.isEmpty()){
			try {
				fw.write("color " + nowColor.getRed() + " " + nowColor.getGreen() + " " + nowColor.getBlue() + " " + nowColor.getAlpha());
				fw.write(System.lineSeparator());
				
				fw.write("size " + (int)nowSizeOfBlock.getWidth() + " " + (int)nowSizeOfBlock.getHeight());
				fw.write(System.lineSeparator());
				
				fw.write("type " + " " + nowTypeOfBlock.toString());
				fw.write(System.lineSeparator());
				
				fw.write("coord");
				fw.write(System.lineSeparator());
				for(Point p : coords){
					fw.write(p.x + " " + p.y);
					fw.write(System.lineSeparator());
				}
				coords.clear();
			}catch(Exception e){}
		}
	}
	
	private void stopWriteToFile(){
		try {
				if(fileWritingIsStarted){
					if(    (CoordReader.isNumber(String.valueOf((int)nowSizeOfBat.getWidth())) && (CoordReader.isNumber(String.valueOf((int)nowSizeOfBat.getWidth()))))){
						fw.write("bat " + (int)nowSizeOfBat.getWidth() + " " + (int)nowSizeOfBat.getHeight());
						fw.close();
						System.exit(0);
					}
				}
			} catch (IOException e) {
			}
	}

	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1){
			coords.add(e.getLocationOnScreen());
			rectsToDraw.add(new Rectangle((int)nowMouseCoord.x, (int)nowMouseCoord.y, (int)nowSizeOfBlock.getWidth(), (int)nowSizeOfBlock.getHeight()));
			
			colorsToDraw.add(nowColor);
		}
		else if(e.getButton() == 3){
			if(!coords.isEmpty()){
				
				
				coords.remove(coords.size() - 1);
				rectsToDraw.remove(rectsToDraw.size() - 1);
				colorsToDraw.remove(colorsToDraw.size() - 1);
				repaint();
			}
		}
		else if(e.getButton() == 2){
			if(fileWritingIsStarted){
				continueWriteToFile();
				stopWriteToFile();
			}else{
				JOptionPane.showMessageDialog(null, "You haven't started a file" + System.lineSeparator() + "To start a file choose a width of bat");
			}
		}
		
	}
	
	public void mouseDragged(MouseEvent e) {
		nowMouseCoord = e.getLocationOnScreen();
	}

	public void mouseMoved(MouseEvent e) {
		nowMouseCoord = e.getLocationOnScreen();
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
