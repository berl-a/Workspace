package com.bg.inform_quest;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class User {

	private LinkedList<LinkedHashMap<String, boolean[]>> globalQuestAndAnswers;
	
	private LinkedList<Integer> numbersOfAnsweredQuestions = new LinkedList<Integer>();
	private LinkedList<Integer> numbersOfSkippedQuestions = new LinkedList<Integer>();
	
	
	private boolean answeringSkippedQuestions = false;

	private int nowNumberOfQuestion = 0;
	private int indexOfSkippedQuestion = 0;
	
	private double globalPartOfTruth = 0.0;
	
	public User(LinkedList<LinkedHashMap<String, boolean[]>> glQuestAndAns){
		setGlobalQuestAndAnswers(glQuestAndAns);
		shuffleGlobalAnsAndQuest();
	}
	
	public void shuffleGlobalAnsAndQuest(){
		Collections.shuffle(globalQuestAndAnswers);
		setGlobalQuestAndAnswers(globalQuestAndAnswers);
	}
	
	public void answerQuestion(LinkedHashMap<String, boolean[]> usersAnswers){
		
		LinkedHashMap<String, boolean[]> realAnswers;
		
		getNumbersOfAnsweredQuestions().add(nowNumberOfQuestion);
		
		if(!answeringSkippedQuestions){	
			realAnswers = globalQuestAndAnswers.get(nowNumberOfQuestion);	
		}else{
			realAnswers = globalQuestAndAnswers.get(numbersOfSkippedQuestions.get(indexOfSkippedQuestion));
		}
		
		double partOfTruth = 0.0;
		
		//Removing question
		realAnswers.remove(realAnswers.keySet().toArray()[0]);
		
		int numberOfRightAnswersInThisQuestion = 0;
		for(boolean[] b : realAnswers.values())
			if(b[0] == true)
				numberOfRightAnswersInThisQuestion++;
		
		//Checking the answers out
		for(String key : realAnswers.keySet()){
			if((realAnswers.get(key)[0] == usersAnswers.get(key)[0]) && (realAnswers.get(key)[0] == true))
				partOfTruth += 1.0 / (double)numberOfRightAnswersInThisQuestion;
		}
		
		System.out.println("PartOfTruth for this question is " + partOfTruth);
		
		setGlobalPartOfTruth(getGlobalPartOfTruth() + partOfTruth);
		
		DecimalFormat roundFormat = new DecimalFormat("#.#");
		System.out.println("Your mark for now is " + roundFormat.format(12.0 * globalPartOfTruth / getNumbersOfAnsweredQuestions().size()));
		
		increaceNumberOfQuestion();
		
	}
	
	private void increaceNumberOfQuestion() {
		
		if(!answeringSkippedQuestions){
			if(!setNowNumberOfQuestion(nowNumberOfQuestion + 1)){
				if(!numbersOfSkippedQuestions.isEmpty())
					setAnsweringSkippedQuestions(true);
				else
					endTest();
			}
		}else{
			setIndexOfSkippedQuestion(getIndexOfSkippedQuestion() + 1); 
		}
	}

	public void skipQuestion(){
		numbersOfSkippedQuestions.add(nowNumberOfQuestion);
		increaceNumberOfQuestion();
	}
	
	public int getNumberOfRightAnswersInThisQuestion(){
		
		int numberOfRightAns = 0;
		
		Collection<boolean[]> values;
		
		if(!answeringSkippedQuestions)
			values = globalQuestAndAnswers.get(nowNumberOfQuestion).values();
		else
			values = globalQuestAndAnswers.get(numbersOfSkippedQuestions.get(indexOfSkippedQuestion)).values();
		
		for(boolean[] boolArr : values)
			if(boolArr[0] == true)
				numberOfRightAns++;
		
		//                        Removing state of question 
		return numberOfRightAns - 1;
	}
	
	public LinkedHashMap<String, boolean[]> getQuestion (int indexOfQuestion){
		return globalQuestAndAnswers.get(indexOfQuestion);
	}
	
	public LinkedList<LinkedHashMap<String, boolean[]>> getGlobalQuestAndAnswers() {
		return globalQuestAndAnswers;
	}

	public void setGlobalQuestAndAnswers(LinkedList<LinkedHashMap<String, boolean[]>> globalQuestAndAnswers) {
		this.globalQuestAndAnswers = globalQuestAndAnswers;
	}

	public int getNowNumberOfQuestion() {
		return nowNumberOfQuestion;
	}

	public boolean setNowNumberOfQuestion(int newNumberOfQuestion) {
		
		if(newNumberOfQuestion < globalQuestAndAnswers.size()){
			this.nowNumberOfQuestion = newNumberOfQuestion;
			return true;
		}else
			return false;
		
	}

	public double getGlobalPartOfTruth() {
		return globalPartOfTruth;
	}

	public void setGlobalPartOfTruth(double globalPartOfTruth) {
		this.globalPartOfTruth = globalPartOfTruth;
	}

	public void endTest() {
		DecimalFormat roundFormat = new DecimalFormat("#.#");
		System.out.println("Your mark is " + roundFormat.format(12.0 * globalPartOfTruth / getNumbersOfAnsweredQuestions().size()));
		
		JOptionPane.showMessageDialog(null, "Вітаємо, ваша оцінка - " + roundFormat.format(12.0 * globalPartOfTruth / getNumbersOfAnsweredQuestions().size()) + " балів" + System.lineSeparator() + "Повідомте вчителя");
		
		System.exit(0);
	}

	LinkedList<Integer> getNumbersOfAnsweredQuestions() {
		return numbersOfAnsweredQuestions;
	}

	public LinkedList<Integer> getNumbersOfSkippedQuestions() {
		return numbersOfSkippedQuestions;
	}

	public void setNumbersOfSkippedQuestions(
			LinkedList<Integer> numbersOfSkippedQuestions) {
		this.numbersOfSkippedQuestions = numbersOfSkippedQuestions;
	}

	public boolean isAnsweringSkippedQuestions() {
		return answeringSkippedQuestions;
	}

	public void setAnsweringSkippedQuestions(boolean answeringSkippedQuestions) {
		this.answeringSkippedQuestions = answeringSkippedQuestions;
	}

	public int getIndexOfSkippedQuestion() {
		return indexOfSkippedQuestion;
	}

	public void setIndexOfSkippedQuestion(int indexOfSkippedQuestion) {
		
		if(indexOfSkippedQuestion < numbersOfSkippedQuestions.size())
			this.indexOfSkippedQuestion = indexOfSkippedQuestion;
		else
			endTest();
	}

	public void timeEnd() {
		JOptionPane.showMessageDialog(null, "Time end!");
		System.exit(0);
	}


}
