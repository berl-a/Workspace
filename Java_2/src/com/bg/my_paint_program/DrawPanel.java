package com.bg.my_paint_program;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DrawPanel extends JPanel implements ActionListener, ChangeListener{
	
	
	private static final int MIN_BRIGHTNESS_OF_ELEMENTS = 100;
	private static final String[] URLsOfPalettes = {"res/palette0.png", "res/palette1.png",  "res/palette3.png", "res/palette5.png", "res/palette6.png","res/palette7.png"};
	private static final String[] namesOfPalettes = {"Мазки", "Усі кольори", "3 стовпчики", "Усе підряд", "Кружечки", "Пастель"};
	private static final String NAME_OF_DEFAULT_PALETTE = "Усе підряд";
	private static final String LOCATION_TO_EXPORT_TO = "D:\\";
	private BufferedImage paletteImage;
	private static final int TRANSPARRENCY_OF_ELEMENTS = 200;
	private static final int DIAMETER_OF_COLOR_RECT = MIN_BRIGHTNESS_OF_ELEMENTS;
	private static final int HEIGHT_OF_PALETTE_CHOOSER = 30;
	private static final int HEIGHT_OF_CLEAR_BUTTON = 30;
	protected static final int WIDTH_OF_SLIDER = 20;
	private static final int NUMBER_OF_SIZES = 20;
	private static final int DANGEROUS_SIZE = 10;
	private static final int WIDTH_OF_EXPORT_BUTTON = 80;
	private static final int HEIGHT_OF_EXPORT_BUTTON = 30;
	private static final int NUMBER_OF_MODE_BUTTONS = 7;
	private static final String[] namesOfModes = {"Line", "Filled Polygone", "Arc", "Filled arc", "Rect", "Filled rect", "Eraser rect"};
	
	public static MyMouseClickListener mouseClickListener = new MyMouseClickListener();
	public static MyMouseMotionListener mouseMotionListener = new MyMouseMotionListener();
	public static MyMouseWheelListener mouseWheelListener = new MyMouseWheelListener();
	
	public static LinkedList<LinkedList<Point>> lines = new LinkedList<>();
	public static LinkedList<Color> colors = new LinkedList<>();
	public static LinkedList<Integer> modes = new LinkedList<>();
	public static LinkedList<Integer> sizes = new LinkedList<>();
	
	public static int nowIndexInListOfLines = -1;
	public Color currentColor = Color.RED;
	public Integer currentMode = new Integer(0);
	public Integer currentSize = new Integer(1);
	
	private Map<String, BufferedImage> palettesWithNames = new HashMap<String, BufferedImage>();
	JComboBox<String> palettes;
	JSlider transparencySlider;
	JSlider sizeSlider;
	JButton clearButton;
	JButton exportButton;
	JRadioButton[] radioButtons;
	private int yOfImageOnScreen;
	private boolean paletteAndSliderAreDrawn;
	private boolean canDraw = true;
	
	
	DrawPanel(){
		//setBackground(new Color(100,100,100, 0));
		addMouseListener(mouseClickListener);
		addMouseMotionListener(mouseMotionListener);
		addMouseWheelListener(mouseWheelListener);
		try {
			for(int i = 0; i < namesOfPalettes.length; i++)
				palettesWithNames.put(namesOfPalettes[i], ImageIO.read(getClass().getResource(URLsOfPalettes[i])));
		} catch (IOException e) {}
		
		paletteImage = palettesWithNames.get(NAME_OF_DEFAULT_PALETTE);
		yOfImageOnScreen = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - paletteImage.getHeight();
	}
	
	
	public void paintComponent(Graphics badG){
		super.paintComponent(badG);
		Graphics2D g = (Graphics2D)badG;
		
		//But bicubic is better at big-width lines
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
		if(!lines.isEmpty()){
			if(lines.get(nowIndexInListOfLines).size() > 0){
				
				for(int thisLine = 0; thisLine < lines.size(); thisLine++){
					
					int mode = modes.get(thisLine);
					
					if(mode == 0){
						if(sizes.get(thisLine) == 1){
							int[] x_coordinates = getArrayOfXCoord(lines.get(thisLine));
							int[] y_coordinates = getArrayOfYCoord(lines.get(thisLine));
							g.setColor(colors.get(thisLine));
							g.drawPolyline(x_coordinates, y_coordinates, lines.get(thisLine).size());
						}else{
							
							int[] x_coordinates = getArrayOfXCoord(lines.get(thisLine));
							int[] y_coordinates = getArrayOfYCoord(lines.get(thisLine));
							g.setColor(colors.get(thisLine));
							g.drawPolyline(x_coordinates, y_coordinates, lines.get(thisLine).size());
							
							for(int adding = 1; adding < sizes.get(thisLine); adding++){
								
								x_coordinates = getArrayOfXCoord(lines.get(thisLine), adding);
								y_coordinates = getArrayOfYCoord(lines.get(thisLine));
								g.setColor(colors.get(thisLine));
								g.drawPolyline(x_coordinates, y_coordinates, lines.get(thisLine).size());
								
								x_coordinates = getArrayOfXCoord(lines.get(thisLine));
								y_coordinates = getArrayOfYCoord(lines.get(thisLine), adding);
								g.setColor(colors.get(thisLine));
								g.drawPolyline(x_coordinates, y_coordinates, lines.get(thisLine).size());
							}
						}
					
					}else if(mode == 1){
						
						int[] x_coordinates = getArrayOfXCoord(lines.get(thisLine));
						int[] y_coordinates = getArrayOfYCoord(lines.get(thisLine));
						g.setColor(colors.get(thisLine));
						g.fillPolygon(x_coordinates, y_coordinates, lines.get(thisLine).size());
					
					}else if(mode == 2){
						
						if(sizes.get(thisLine) == 1){
							g.setColor(colors.get(thisLine));
							g.drawArc(lines.get(thisLine).getFirst().x, lines.get(thisLine).getFirst().y, lines.get(thisLine).getLast().x - lines.get(thisLine).getFirst().x, lines.get(thisLine).getLast().y - lines.get(thisLine).getFirst().y, 0, 360);
						}else{
							g.setColor(colors.get(thisLine));
							g.drawArc(lines.get(thisLine).getFirst().x, lines.get(thisLine).getFirst().y, lines.get(thisLine).getLast().x - lines.get(thisLine).getFirst().x, lines.get(thisLine).getLast().y - lines.get(thisLine).getFirst().y, 0, 360);
						
							for(int adding = 1; adding < sizes.get(thisLine); adding++){
								g.setColor(colors.get(thisLine));
								g.drawArc(lines.get(thisLine).getFirst().x + adding, lines.get(thisLine).getFirst().y + adding, lines.get(thisLine).getLast().x - lines.get(thisLine).getFirst().x - 2 * adding, lines.get(thisLine).getLast().y - lines.get(thisLine).getFirst().y - 2 * adding, 0, 360);
							}
						}
						
					}else if(mode == 3){
						
						g.setColor(colors.get(thisLine));
						g.fillArc(lines.get(thisLine).getFirst().x, lines.get(thisLine).getFirst().y, lines.get(thisLine).getLast().x - lines.get(thisLine).getFirst().x, lines.get(thisLine).getLast().y - lines.get(thisLine).getFirst().y, 0, 360);
					
					}else if(mode == 4){
						
						if(sizes.get(thisLine) == 1){
							g.setColor(colors.get(thisLine));
							g.drawRoundRect(lines.get(thisLine).getFirst().x, lines.get(thisLine).getFirst().y, lines.get(thisLine).getLast().x - lines.get(thisLine).getFirst().x, lines.get(thisLine).getLast().y - lines.get(thisLine).getFirst().y, (lines.get(thisLine).getLast().x - lines.get(thisLine).getFirst().x) / 5, (lines.get(thisLine).getLast().y - lines.get(thisLine).getFirst().y) / 5);
						}else{
							g.setColor(colors.get(thisLine));
							g.drawRoundRect(lines.get(thisLine).getFirst().x, lines.get(thisLine).getFirst().y, lines.get(thisLine).getLast().x - lines.get(thisLine).getFirst().x, lines.get(thisLine).getLast().y - lines.get(thisLine).getFirst().y, (lines.get(thisLine).getLast().x - lines.get(thisLine).getFirst().x) / 5, (lines.get(thisLine).getLast().y - lines.get(thisLine).getFirst().y) / 5);
							
							for(int adding = 1; adding < sizes.get(thisLine); adding++){
								g.setColor(colors.get(thisLine));
								g.drawRoundRect(lines.get(thisLine).getFirst().x + adding, lines.get(thisLine).getFirst().y + adding, lines.get(thisLine).getLast().x - lines.get(thisLine).getFirst().x - 2 * adding, lines.get(thisLine).getLast().y - lines.get(thisLine).getFirst().y - 2 * adding, (lines.get(thisLine).getLast().x - lines.get(thisLine).getFirst().x) / 5, (lines.get(thisLine).getLast().y - lines.get(thisLine).getFirst().y) / 5);
							}
						}
						
					}else if(mode == 5){
						
						g.setColor(colors.get(thisLine));
						g.fillRoundRect(lines.get(thisLine).getFirst().x, lines.get(thisLine).getFirst().y, lines.get(thisLine).getLast().x - lines.get(thisLine).getFirst().x, lines.get(thisLine).getLast().y - lines.get(thisLine).getFirst().y, (lines.get(thisLine).getLast().x - lines.get(thisLine).getFirst().x) / 5, (lines.get(thisLine).getLast().y - lines.get(thisLine).getFirst().y) / 5);
					
					}else if(mode == 6){
						
						g.setColor(getBackground());
						g.fillRect(lines.get(thisLine).getFirst().x, lines.get(thisLine).getFirst().y, lines.get(thisLine).getLast().x - lines.get(thisLine).getFirst().x, lines.get(thisLine).getLast().y - lines.get(thisLine).getFirst().y);
						
					}
				}
			}
		}
		
		if(canDraw){
			g.setColor(currentColor);
			g.fillArc(0, 0, DIAMETER_OF_COLOR_RECT, DIAMETER_OF_COLOR_RECT, 0, 360);
			g.drawImage(paletteImage, null, 0, yOfImageOnScreen);
		}
		
		if(!paletteAndSliderAreDrawn){
			paintElements();
			paletteAndSliderAreDrawn = true;
		}
	}

	private void paintElements() {
		palettes = new JComboBox<String>();
		palettes.setBounds(0, (int)yOfImageOnScreen - HEIGHT_OF_PALETTE_CHOOSER, paletteImage.getWidth(), HEIGHT_OF_PALETTE_CHOOSER);
		palettes.setAlignmentX(CENTER_ALIGNMENT);
		palettes.setAlignmentY(CENTER_ALIGNMENT);
		palettes.setBackground(new Color(200, 200, 200));
		palettes.setForeground(Color.RED);
		int index = 0;
		for(String name : namesOfPalettes){
			palettes.insertItemAt(name, index);
			index++;
		}
		palettes.setSelectedItem(NAME_OF_DEFAULT_PALETTE);
		
		palettes.setActionCommand("new palette is chosen");
		palettes.addActionListener(this);
		add(palettes);
		
		transparencySlider = new JSlider(SwingConstants.VERTICAL, 0, MIN_BRIGHTNESS_OF_ELEMENTS, MIN_BRIGHTNESS_OF_ELEMENTS);
		transparencySlider.setBounds(paletteImage.getWidth(), yOfImageOnScreen - HEIGHT_OF_PALETTE_CHOOSER, WIDTH_OF_SLIDER,paletteImage.getHeight() + HEIGHT_OF_PALETTE_CHOOSER);
		transparencySlider.setToolTipText("Change transperensy");
		transparencySlider.addChangeListener(this);
		transparencySlider.setBackground(new Color(new Random().nextInt(255 - MIN_BRIGHTNESS_OF_ELEMENTS) + MIN_BRIGHTNESS_OF_ELEMENTS, new Random().nextInt(255 - MIN_BRIGHTNESS_OF_ELEMENTS) + MIN_BRIGHTNESS_OF_ELEMENTS, new Random().nextInt(255 - MIN_BRIGHTNESS_OF_ELEMENTS) + MIN_BRIGHTNESS_OF_ELEMENTS, new Random().nextInt(TRANSPARRENCY_OF_ELEMENTS)));
		add(transparencySlider);
		
		sizeSlider = new JSlider(SwingConstants.VERTICAL, 1, NUMBER_OF_SIZES, 1);
		sizeSlider.setBounds(paletteImage.getWidth() + transparencySlider.getWidth(), yOfImageOnScreen - HEIGHT_OF_PALETTE_CHOOSER, WIDTH_OF_SLIDER,paletteImage.getHeight() + HEIGHT_OF_PALETTE_CHOOSER);
		sizeSlider.setToolTipText("Change size");
		sizeSlider.addChangeListener(this);
		sizeSlider.setBackground(new Color(new Random().nextInt(255 - MIN_BRIGHTNESS_OF_ELEMENTS) + MIN_BRIGHTNESS_OF_ELEMENTS, new Random().nextInt(255 - MIN_BRIGHTNESS_OF_ELEMENTS) + MIN_BRIGHTNESS_OF_ELEMENTS, new Random().nextInt(255 - MIN_BRIGHTNESS_OF_ELEMENTS) + MIN_BRIGHTNESS_OF_ELEMENTS, new Random().nextInt(TRANSPARRENCY_OF_ELEMENTS)));
		add(sizeSlider);
		
		clearButton = new JButton("Clear");
		clearButton.setBounds(0, (int)yOfImageOnScreen - HEIGHT_OF_PALETTE_CHOOSER - HEIGHT_OF_CLEAR_BUTTON, paletteImage.getWidth(), HEIGHT_OF_CLEAR_BUTTON);
		clearButton.setBackground(new Color(255, 0, 0, TRANSPARRENCY_OF_ELEMENTS));
		clearButton.setForeground(Color.WHITE);
		clearButton.addActionListener(this);
		add(clearButton);
		
		exportButton = new JButton("Export");
		exportButton.setBounds((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - WIDTH_OF_EXPORT_BUTTON, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - HEIGHT_OF_EXPORT_BUTTON, WIDTH_OF_EXPORT_BUTTON, HEIGHT_OF_EXPORT_BUTTON);
		exportButton.setBackground(new Color(0, 0, 255, TRANSPARRENCY_OF_ELEMENTS));
		exportButton.setForeground(Color.WHITE);
		exportButton.addActionListener(this);
		add(exportButton);
		repaint();
		
		radioButtons = new JRadioButton[NUMBER_OF_MODE_BUTTONS];
		ButtonGroup bg = new ButtonGroup();
		int i = 0;
		for(JRadioButton button : radioButtons){
			button = new JRadioButton();
			button.setBounds((clearButton.getWidth() / NUMBER_OF_MODE_BUTTONS) * i, clearButton.getY() - (clearButton.getWidth() / NUMBER_OF_MODE_BUTTONS), clearButton.getWidth() / NUMBER_OF_MODE_BUTTONS, clearButton.getWidth() / NUMBER_OF_MODE_BUTTONS);
			button.setBackground(new Color(new Random().nextInt(255 - MIN_BRIGHTNESS_OF_ELEMENTS) + MIN_BRIGHTNESS_OF_ELEMENTS, new Random().nextInt(255 - MIN_BRIGHTNESS_OF_ELEMENTS) + MIN_BRIGHTNESS_OF_ELEMENTS, new Random().nextInt(255 - MIN_BRIGHTNESS_OF_ELEMENTS) + MIN_BRIGHTNESS_OF_ELEMENTS, new Random().nextInt(TRANSPARRENCY_OF_ELEMENTS)));
			button.setHorizontalAlignment(SwingConstants.CENTER);
			button.setVerticalAlignment(SwingConstants.CENTER);
			button.setToolTipText(namesOfModes[i]);
			button.addActionListener(this);
			button.setActionCommand(String.valueOf(i));
			radioButtons[i] = button;
			add(button);
			bg.add(button);
			repaint();
			i++;
		}
		
	}
	
	public void addPointToThisLine(Point p, Color c){
		lines.get(nowIndexInListOfLines).add(p);
		repaint();
	}
	
	public void startNextLine(){
		nowIndexInListOfLines++;
		lines.add(new LinkedList<Point>());
		colors.add(currentColor);
		modes.add(currentMode);
		sizes.add(currentSize);
	}
	
	private int[] getArrayOfXCoord(LinkedList<Point> points){
		int[] newPoints = new int[points.size()];
		for(int i = 0; i < points.size(); i++){
			newPoints[i] = points.get(i).x;
		}
		return newPoints;
	}
	
	private int[] getArrayOfYCoord(LinkedList<Point> points){
		int[] newPoints = new int[points.size()];
		for(int i = 0; i < points.size(); i++){
			newPoints[i] = points.get(i).y;
		}
		return newPoints;
	}
	
	private int[] getArrayOfXCoord(LinkedList<Point> points, int increacingNumber) {
		
		int[] newPoints = new int[points.size()];
		for(int i = 0; i < points.size(); i++){
			newPoints[i] = points.get(i).x + increacingNumber;
		}
		return newPoints;
	}

	
	private int[] getArrayOfYCoord(LinkedList<Point> points, int increacingNumber) {
		int[] newPoints = new int[points.size()];
		for(int i = 0; i < points.size(); i++){
			newPoints[i] = points.get(i).y + increacingNumber;
		}
		return newPoints;
	}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "new palette is chosen"){
			paletteImage = palettesWithNames.get(palettes.getSelectedItem().toString());
			refreshComponentsBounds();
		}
		if(e.getActionCommand() == "Clear"){
			/*
			Color frameBack = MainCycle.frame.getBackground();
			Color panelBack = getBackground();
			MainCycle.frame.setBackground(Color.RED);
			setBackground(Color.RED);
			*/
			lines.clear();
			colors.clear();
			modes.clear();
			sizes.clear();
			nowIndexInListOfLines = -1;
			repaint();
			/*
			MainCycle.frame.setBackground(frameBack);
			setBackground(panelBack);
			*/
			refreshComponentsBounds();
			
		}
		
		if(e.getActionCommand() == "Export"){
			
			for(Component component : getComponents()){
				component.setSize(0, 0);
			}
			canDraw = false;
			repaint();
			//Color frameBack = MainCycle.frame.getBackground();
			//Color panelBack = getBackground();;
			//MainCycle.frame.setBackground(new Color(255,255,255,255));
			//setBackground(new Color(255,255,255,255));
			String name = JOptionPane.showInputDialog(null, "Choose name");
			try {
				Thread.sleep(MIN_BRIGHTNESS_OF_ELEMENTS);
			} catch (InterruptedException e2) {}
			
			try {
				Robot r = new Robot();
				ImageIO.write(r.createScreenCapture(getBounds()), "png", new File(LOCATION_TO_EXPORT_TO + name + ".png"));
			} catch (AWTException e1) {} catch (IOException e1) {}
			
			canDraw = true;
			repaint();
			//MainCycle.frame.setBackground(frameBack);
			//setBackground(panelBack);
			
			refreshComponentsBounds();
		}
		try{
			int parsedInt = Integer.parseInt(e.getActionCommand());
			//Allright, it's number
			
			for(int i = 0; i < radioButtons.length; i++){
				if(parsedInt == i){
					currentMode = new Integer(i);
				}
			}
			
		}catch(Exception exception){}
	}

	public void undo() {
		/*
		Color frameBack = MainCycle.frame.getBackground();
		Color panelBack = getBackground();
		MainCycle.frame.setBackground(new Color(0,0,0,0));
		setBackground(new Color(0,0,0,0));
		*/
		lines.remove(nowIndexInListOfLines);
		colors.remove(nowIndexInListOfLines);
		modes.remove(nowIndexInListOfLines);
		sizes.remove(nowIndexInListOfLines);
		nowIndexInListOfLines --;
		repaint();
		
		//MainCycle.frame.setBackground(frameBack);
		//setBackground(panelBack);
	}
	
	private void refreshComponentsBounds() {
		yOfImageOnScreen = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - paletteImage.getHeight();
		palettes.setBounds(0, (int)yOfImageOnScreen - HEIGHT_OF_PALETTE_CHOOSER, paletteImage.getWidth(), HEIGHT_OF_PALETTE_CHOOSER);
		transparencySlider.setBounds(paletteImage.getWidth(), yOfImageOnScreen - HEIGHT_OF_PALETTE_CHOOSER, WIDTH_OF_SLIDER,paletteImage.getHeight() + HEIGHT_OF_PALETTE_CHOOSER);
		sizeSlider.setBounds(paletteImage.getWidth() + transparencySlider.getWidth(), yOfImageOnScreen - HEIGHT_OF_PALETTE_CHOOSER, WIDTH_OF_SLIDER,paletteImage.getHeight() + HEIGHT_OF_PALETTE_CHOOSER);
		clearButton.setBounds(0, (int)yOfImageOnScreen - HEIGHT_OF_PALETTE_CHOOSER - HEIGHT_OF_CLEAR_BUTTON, paletteImage.getWidth(), HEIGHT_OF_CLEAR_BUTTON);
		exportButton.setBounds((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - WIDTH_OF_EXPORT_BUTTON, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - HEIGHT_OF_EXPORT_BUTTON, WIDTH_OF_EXPORT_BUTTON, HEIGHT_OF_EXPORT_BUTTON);
		int i = 0;
		for(JRadioButton button : radioButtons){
			button.setBounds((clearButton.getWidth() / NUMBER_OF_MODE_BUTTONS) * i, clearButton.getY() - (clearButton.getWidth() / NUMBER_OF_MODE_BUTTONS), clearButton.getWidth() / NUMBER_OF_MODE_BUTTONS, clearButton.getWidth() / NUMBER_OF_MODE_BUTTONS);
			i++;
		}
		repaint();
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		try{
			for(int i = 0; i < radioButtons.length; i++){
				if(e.getSource() == radioButtons[i]){
					currentMode = new Integer(i);
				}
			}
		}catch(Exception exception){}
		if(e.getSource() == transparencySlider){
			currentColor = new Color(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), transparencySlider.getValue() * (255 / MIN_BRIGHTNESS_OF_ELEMENTS));
			repaint(transparencySlider.getBounds());
		}
		if(e.getSource() == sizeSlider){
			currentSize = sizeSlider.getValue();
			if(currentSize >= DANGEROUS_SIZE){
				sizeSlider.setToolTipText("Big size decreaces productivity!");
			}else{
				sizeSlider.setToolTipText("Change size");
			}
			repaint(sizeSlider.getBounds());
		}
	}
	
}
