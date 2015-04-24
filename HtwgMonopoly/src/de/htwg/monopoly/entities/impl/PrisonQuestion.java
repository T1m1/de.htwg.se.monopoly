package de.htwg.monopoly.entities.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PrisonQuestion {

	private static final Map<String, Boolean> QUESTIONMAP = new TreeMap<String, Boolean>();

	private static final List<String> QUESTIONS;

	static {
		QUESTIONMAP.put(
				"Gibt es einen Unterschied zwischen Kernel und User threads?",
				true);
		QUESTIONMAP
				.put("Gibt es einer Unterschied zwischen einer Seite und einem Segment?",
						true);
		QUESTIONMAP
				.put("Ist eine Leistungssteigerung zu erwarten, wenn statt eines einzelnen Pufferspeichers f&uuml;r die E/A zwei Pufferspeicher verwendet werden?",
						true);
		QUESTIONMAP
				.put("Gibt es einen Unterschiedd zwischen einem Trap und einer Unterbrechung (Interrupt)?",
						true);
		QUESTIONMAP
				.put("Das Client-Server-Modell ist bei verteilten Systemen sehr verbreitet. Kann es auch in einem Einplatz-Computer Anwendung finden?",
						true);
		QUESTIONMAP
				.put("Kann ein Benutzer einen eigenen Kommandozeileninterpreter entwickeln, indem er das Systemcall-Interface des Kernels benutzt?",
						true);
		QUESTIONMAP.put("Ist Multiprogramming und Multitasking das gleiche?",
				false);
		QUESTIONMAP
				.put("Sollte der Befehl die aktuelle Uhrzeit zu lesen nur im Kernmodus erlaubt sein?",
						false);
		QUESTIONMAP
				.put("Eine Bibliotheksfunktion hat den Namen read(). Der Systemaufruf des Betriebssystems hei&szligt auch read(). Ist es n&ouml;tig, dass beide Funktionen denselben Namen haben?",
						false);
		QUESTIONMAP.put("ISRs sollten eher lang sein.", false);
		QUESTIONMAP
				.put("Kann eine Multithreading-Anwendung mit mehreren User-Level Threads auf einem Multiprozessorsystem eine bessere Performance erreichen als auf einem Single-Prozessorsystem? ",
						false);

		QUESTIONS = new LinkedList<String>(QUESTIONMAP.keySet());
	}

	private String currentQuestion;

	public PrisonQuestion() {
		Collections.shuffle(QUESTIONS);
		drawNextQuestion();
	}

	public boolean isTrue(String prisonQuestion, boolean answer) {
		return (QUESTIONMAP.get(prisonQuestion) == answer);
	}

	/**
	 * Draws the first card. The Card can retrieved via
	 * {@link PrisonQuestion#getcurrentQuestion()}
	 * 
	 * @return
	 */
	public void drawNextQuestion() {
		String first = ((LinkedList<String>) QUESTIONS).pollFirst();
		((LinkedList<String>) QUESTIONS).offerLast(first);
		currentQuestion = first;
	}

	public String getCurrentQuestion() {
		return currentQuestion;
	}

}
