package com.bg.arkanoid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JFileChooser;

import com.bg.arkanoid.etypes.ETypeOfBlock;

public class CoordReader {

	public void readCoordinates() {
		
		int nowIndexOfBlock = 0;
		
		try {
			
			JFileChooser chooser = new JFileChooser();
			chooser.setApproveButtonText("I want to play this level");
			chooser.setMultiSelectionEnabled(false);
			chooser.setCurrentDirectory(new File("").getAbsoluteFile());
			Scanner scanner = null;
			
			if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
				scanner = new Scanner(chooser.getSelectedFile());
			}
			
			String scannerWord = "";
			
			int red = 0, green = 0, blue = 0, alpha = 0;
			ETypeOfBlock nowType = null;
			int nowWidth = 0;
			int nowHeight = 0;
			
			if(scanner.hasNext()){
				scannerWord = scanner.next();
			}
			
			while(scanner.hasNext()){
				if(scannerWord.equalsIgnoreCase("color")){
					red = Integer.parseInt(scanner.next());
					green = Integer.parseInt(scanner.next());
					blue = Integer.parseInt(scanner.next());
					alpha = Integer.parseInt(scanner.next());
					scannerWord = scanner.next();
				}
				if(scannerWord.equalsIgnoreCase("size")){
					nowWidth = Integer.parseInt(scanner.next());
					nowHeight = Integer.parseInt(scanner.next());
					scannerWord = scanner.next();
				}
				if(scannerWord.equalsIgnoreCase("type")){
					nowType = ETypeOfBlock.valueOf(scanner.next());
					scannerWord = scanner.next();
				}
				if(scannerWord.equalsIgnoreCase("coord")){
					//Adding coordinates to list of coord
					LinkedList<Point> coord = new LinkedList<Point>();
					while(scanner.hasNext()){
						int x = 0;
						int y = 0;
						
						String nextCoord = scanner.next();
						if(isNumber(nextCoord))	
							x = Integer.parseInt(nextCoord);
						else{
							scannerWord = nextCoord;
							break;
						}
						
						nextCoord = scanner.next();
						if(isNumber(nextCoord))	
							y = Integer.parseInt(nextCoord);
						
						coord.add(new Point(x, y));
					}
					for(Point nowCoord : coord){
						//For every point create a brick
						ArcanoidPanel.bricks.add(new Brick());
						ArcanoidPanel.bricks.get(nowIndexOfBlock).setColor(new Color(red, green, blue, alpha));
						ArcanoidPanel.bricks.get(nowIndexOfBlock).setSize(new Dimension(nowWidth, nowHeight));
						ArcanoidPanel.bricks.get(nowIndexOfBlock).setTypeOfBlock(nowType);
						ArcanoidPanel.bricks.get(nowIndexOfBlock).setCoord(nowCoord);
						nowIndexOfBlock++;
					}
					
				}
				if(scannerWord.equals("bat")){
					break;
				}
			}
			if(scannerWord.equalsIgnoreCase("bat")){
				ArcanoidPanel.bat.setSize(new Dimension(Integer.parseInt(scanner.next()), Integer.parseInt(scanner.next())));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
		}
	}
	
	public static boolean isNumber(String nextCoord) {
		try{
			Integer.parseInt(nextCoord);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
}
