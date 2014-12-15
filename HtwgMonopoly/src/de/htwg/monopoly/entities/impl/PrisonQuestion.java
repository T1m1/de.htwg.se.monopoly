package de.htwg.monopoly.entities.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class PrisonQuestion {

	private static Map<String,Boolean> questionMap = new TreeMap<String,Boolean>();
	
	private static LinkedList<String> questions;
	
	static {
		questionMap.put("", true);
		questionMap.put("", true);
		questionMap.put("", true);
		questionMap.put("", true);
		questionMap.put("", true);
		questionMap.put("", true);
		questionMap.put("", true);
		questionMap.put("", false);
		questionMap.put("", false);
		questionMap.put("", false);
		questionMap.put("", false);
		questionMap.put("", false);
		questionMap.put("", false);
		
		questions = new  LinkedList<String>(questionMap.keySet());
	}
	
	public PrisonQuestion() {
		Collections.shuffle(questions);
	}

	public boolean isTrue(String prisonQuestion, boolean answer) {
		return (questionMap.get(prisonQuestion) == answer);
	}

	public String getNextQuestion() {
		String first = questions.pollFirst();
		questions.offerLast(first);
		return first;
	}

}
