package com.bg.inform_quest;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Frame extends JFrame implements ActionListener, ChangeListener, KeyListener{

	private static final int DEFAULT_TIME_FOR_TEST = 60000 * 10;
	
	JLabel questionLabel = new JLabel();
	
	BufferedImage boxImage, boxImageP, boxImageTrue, answerB, answerBP, skipB, skipBP, endB, endBP;
	
	LinkedList<JCheckBox> boxes = new LinkedList<JCheckBox>();
	
	int timeForTest = DEFAULT_TIME_FOR_TEST;
	JLabel timerLabel = new JLabel();
	
	JLabel resultLabel = new JLabel();
	
	JButton answerButton = new JButton();
	JButton skipButton = new JButton();
	JButton endButton = new JButton();
	
	Timer timer = new Timer(1000, this);
	
	public Frame(){
		
		setName(MainHolder.reader.getNameOfTest());
		setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		//setLayout(new FlowLayout(FlowLayout.CENTER));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFocusable(true);
		addKeyListener(this);
		getContentPane().addKeyListener(this);
		
		timer.setActionCommand("timer");
		timer.start();
		
		int timeInSec = timeForTest / 1000;
		timerLabel.setText(String.valueOf(String.valueOf((timeInSec - timeInSec % 60) / 60)) + ":" + String.valueOf(timeInSec % 60));
		timerLabel.setFont(new Font("f", Font.ITALIC, 20));
		timerLabel.setForeground(Color.RED);
		add(timerLabel);
		
		resultLabel.setFont(new Font("f", Font.ITALIC, 20));
		resultLabel.setForeground(Color.BLUE.brighter());
		
		questionLabel.setFont(new Font("font", Font.TRUETYPE_FONT, 18));
		questionLabel.setText(MainHolder.globalQuestAndAnswers.get(MainHolder.user.getNowNumberOfQuestion()).keySet().toArray()[0].toString());
		questionLabel.setBackground(new Color(56, 180, 230, 150));
		questionLabel.setForeground(Color.DARK_GRAY.darker());
		add(questionLabel);
		
		List<String> shuffledQuestions = new LinkedList<String>();
		shuffledQuestions.addAll(MainHolder.globalQuestAndAnswers.get(MainHolder.user.getNowNumberOfQuestion()).keySet());
		//Removing title
		shuffledQuestions = shuffledQuestions.subList(1, shuffledQuestions.size());
		Collections.shuffle(shuffledQuestions);
		
		for(String question : shuffledQuestions)
			boxes.add(new JCheckBox(question));
			
		//Not for export
		
		try {
			boxImage = ImageIO.read(new File("res/CheckBox.png"));
		} catch (IOException e) {}
		try {
			boxImageP = ImageIO.read(new File("res/CheckBoxPress.png"));
		} catch (IOException e) {}
		try {
			boxImageTrue = ImageIO.read(new File("res/CheckBox+.png"));
		} catch (IOException e) {}
		
		try {
			answerB = ImageIO.read(new File("res/AnswerB.png"));
		} catch (IOException e) {}
		try {
			answerBP = ImageIO.read(new File("res/AnswerBP.png"));
		} catch (IOException e) {}
		
		try {
			skipB = ImageIO.read(new File("res/SkipB.png"));
		} catch (IOException e) {}
		try {
			skipBP = ImageIO.read(new File("res/SkipBP.png"));
		} catch (IOException e) {}
		
		try {
			endB = ImageIO.read(new File("res/EndB.png"));
		} catch (IOException e) {}
		try {
			endBP = ImageIO.read(new File("res/EndBP.png"));
		} catch (IOException e) {}
		
		
		//For export
		/*
		try {
			boxImage = ImageIO.read(getClass().getResource("res/CheckBox.png"));
		} catch (IOException e) {}
		
		try {
			boxImageP = ImageIO.read(getClass().getResource("res/CheckBoxPress.png"));
		} catch (IOException e) {}
		
		try {
			boxImageTrue = ImageIO.read(getClass().getResource("res/CheckBox+.png"));
		} catch (IOException e) {}
		
		try {
			answerB = ImageIO.read(getClass().getResource("res/AnswerB.png"));
		} catch (IOException e) {}
		try {
			answerBP = ImageIO.read(getClass().getResource("res/AnswerBP.png"));
		} catch (IOException e) {}
		
		try {
			skipB = ImageIO.read(getClass().getResource("res/SkipB.png"));
		} catch (IOException e) {}
		try {
			skipBP = ImageIO.read(getClass().getResource("res/SkipBP.png"));
		} catch (IOException e) {}
		
		try {
			endB = ImageIO.read(getClass().getResource("res/EndB.png"));
		} catch (IOException e) {}
		try {
			endBP = ImageIO.read(getClass().getResource("res/EndBP.png"));
		} catch (IOException e) {}
		*/
		
		for(JCheckBox box : boxes){
			box.setIcon(new ImageIcon(boxImage));
			box.setPressedIcon(new ImageIcon(boxImageP));
			box.setSelectedIcon(new ImageIcon(boxImageTrue));
			box.setFont(new Font("font", Font.ITALIC, 15));
			box.setForeground(Color.BLUE.brighter().brighter());
			box.addChangeListener(this);
			box.addKeyListener(this);
			add(box);
		}
		
		answerButton.addActionListener(this);
		skipButton.addActionListener(this);
		endButton.addActionListener(this);
		
		answerButton.setActionCommand("Answer");
		skipButton.setActionCommand("Skip");
		endButton.setActionCommand("End test");
		
		answerButton.setBorderPainted(false);
		answerButton.setContentAreaFilled(false);
		answerButton.setFocusPainted(false);
		answerButton.setOpaque(false);
		
		skipButton.setBorderPainted(false);
		skipButton.setContentAreaFilled(false);
		skipButton.setFocusPainted(false);
		skipButton.setOpaque(false);
		
		endButton.setBorderPainted(false);
		endButton.setContentAreaFilled(false);
		endButton.setFocusPainted(false);
		endButton.setOpaque(false);
		
		answerButton.setIcon(new ImageIcon(answerB));
		answerButton.setPressedIcon(new ImageIcon(answerBP));
		skipButton.setIcon(new ImageIcon(skipB));
		skipButton.setPressedIcon(new ImageIcon(skipBP));
		endButton.setIcon(new ImageIcon(endB));
		endButton.setPressedIcon(new ImageIcon(endBP));
		
		add(answerButton);
		add(skipButton);
		add(endButton);
		
		answerButton.setEnabled(false);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	public void nextQuestion(){
	
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand() == "Answer"){
			
				LinkedHashMap<String, boolean[]> usersAns = new LinkedHashMap<String, boolean[]>();
				for(JCheckBox box : boxes)
					usersAns.put(box.getText(), new boolean[]{box.isSelected()});
				MainHolder.user.answerQuestion(usersAns);
				
				refreshComponents();
				
		}else if(e.getActionCommand() == "Skip"){
			
			MainHolder.user.skipQuestion();
			refreshComponents();
			
		}else if(e.getActionCommand() == "timer"){
			
			timeForTest -= 1000;
			
			int timeInSec = timeForTest / 1000;
			timerLabel.setText(String.valueOf(String.valueOf((timeInSec - timeInSec % 60) / 60)) + ":" + String.valueOf(timeInSec % 60));
			
			if(timeInSec <= 0){
				MainHolder.user.timeEnd();
			}
			
		}
		
	}


	private void refreshComponents() {
		
		int timeInSec = timeForTest / 1000;
		timerLabel.setText(String.valueOf(String.valueOf((timeInSec - timeInSec % 60) / 60)) + ":" + String.valueOf(timeInSec % 60));
		timerLabel.setFont(new Font("f", Font.ITALIC, 20));
		timerLabel.setForeground(Color.RED);
		add(timerLabel);
		
		remove(questionLabel);
		add(questionLabel);
		
		for(JCheckBox box : boxes)
			remove(box);
		boxes.clear();
		
		List<String> shuffledQuestions = new LinkedList<String>();
		if(!MainHolder.user.isAnsweringSkippedQuestions())
			shuffledQuestions.addAll(MainHolder.globalQuestAndAnswers.get(MainHolder.user.getNowNumberOfQuestion()).keySet());
		else
			shuffledQuestions.addAll(MainHolder.globalQuestAndAnswers.get(MainHolder.user.getNumbersOfSkippedQuestions().get(MainHolder.user.getIndexOfSkippedQuestion())).keySet());
		
		shuffledQuestions = shuffledQuestions.subList(1, shuffledQuestions.size());
		
		Collections.shuffle(shuffledQuestions);
		
		for(String question : shuffledQuestions)
			boxes.add(new JCheckBox(question));
		
		for(JCheckBox box : boxes){
			box.setIcon(new ImageIcon(boxImage));
			box.setFont(new Font("font", Font.ITALIC, 15));
			box.setForeground(Color.BLUE.brighter().brighter());
			box.setPressedIcon(new ImageIcon(boxImageP));
			box.setSelectedIcon(new ImageIcon(boxImageTrue));
			box.addChangeListener(this);
			box.addKeyListener(this);
			add(box);
		}
		
		remove(resultLabel);
		DecimalFormat roundFormat = new DecimalFormat("#.#");
		resultLabel.setText(roundFormat.format(12.0 * MainHolder.user.getGlobalPartOfTruth() / MainHolder.user.getNumbersOfAnsweredQuestions().size()));
		add(resultLabel);
		
		//Placing buttons to the bottom
		remove(answerButton);
		remove(skipButton);
		remove(endButton);
		
		add(answerButton);
		add(skipButton);
		add(endButton);
		
		answerButton.setEnabled(false);
		
		if(MainHolder.user.isAnsweringSkippedQuestions())
			skipButton.setEnabled(false);
		
		//Refreshing text area
		if(!MainHolder.user.isAnsweringSkippedQuestions())
			questionLabel.setText(MainHolder.globalQuestAndAnswers.get(MainHolder.user.getNowNumberOfQuestion()).keySet().toArray()[0].toString());
		else
			questionLabel.setText(MainHolder.globalQuestAndAnswers.get(MainHolder.user.getNumbersOfSkippedQuestions().get(MainHolder.user.getIndexOfSkippedQuestion())).keySet().toArray()[0].toString());
		
		//Refreshing frame
		getContentPane().addKeyListener(this);
		pack();
		setLocationRelativeTo(null);
	}

	public void stateChanged(ChangeEvent e) {
		
		if(getNumberOfChoosedVariants() > MainHolder.user.getNumberOfRightAnswersInThisQuestion()){
			JCheckBox thisBox = (JCheckBox)e.getSource();
			thisBox.setSelected(false);
		}else if(getNumberOfChoosedVariants() == MainHolder.user.getNumberOfRightAnswersInThisQuestion())
			answerButton.setEnabled(true);
		else if(getNumberOfChoosedVariants() < MainHolder.user.getNumberOfRightAnswersInThisQuestion())
			answerButton.setEnabled(false);
		
	}
	
	public int getNumberOfChoosedVariants(){
		
		int numberOfChoosedVariants = 0;
		
		for(JCheckBox box : boxes)
			if(box.isSelected())
				numberOfChoosedVariants++;
		
		return numberOfChoosedVariants;
	}


	public void keyPressed(KeyEvent e) {
		char butChar = e.getKeyChar();
		switch(butChar){
			case 'a' : answerButton.doClick(); break;
			case 'ô' : answerButton.doClick(); break;
			
			case 's' : skipButton.doClick(); break;
			case '³' : skipButton.doClick(); break;
			case 'û' : skipButton.doClick(); break;
			
			case 'd' : endButton.doClick(); break;
			case 'â' : endButton.doClick(); break;
			
			case '1' : if(boxes.size() >= 1) boxes.get(0).setSelected(!boxes.get(0).isSelected()); break;
			case '2' : if(boxes.size() >= 2) boxes.get(1).setSelected(!boxes.get(1).isSelected()); break;
			case '3' : if(boxes.size() >= 3) boxes.get(2).setSelected(!boxes.get(2).isSelected()); break;
			case '4' : if(boxes.size() >= 4) boxes.get(3).setSelected(!boxes.get(3).isSelected()); break;
			case '5' : if(boxes.size() >= 5) boxes.get(4).setSelected(!boxes.get(4).isSelected()); break;
			case '6' : if(boxes.size() >= 6) boxes.get(5).setSelected(!boxes.get(5).isSelected()); break;
			case '7' : if(boxes.size() >= 7) boxes.get(6).setSelected(!boxes.get(6).isSelected()); break;
			case '8' : if(boxes.size() >= 8) boxes.get(7).setSelected(!boxes.get(7).isSelected()); break;
			case '9' : if(boxes.size() >= 9) boxes.get(8).setSelected(!boxes.get(8).isSelected()); break;
		}
	}


	public void keyReleased(KeyEvent e) {
	}


	public void keyTyped(KeyEvent e) {
		//System.out.println(e.getID());
	}
	
	
}
