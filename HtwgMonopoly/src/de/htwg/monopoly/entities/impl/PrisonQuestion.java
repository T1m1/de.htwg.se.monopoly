package de.htwg.monopoly.entities.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class PrisonQuestion {

	private static final Map<String, Boolean> questionMap = new TreeMap<String, Boolean>();

	private static final LinkedList<String> questions;

	static {
		questionMap.put(
				"Gibt es einen Unterschied zwischen Kernel und User threads?",
				true);
		questionMap
				.put("Gibt es einer Unterschied zwischen einer Seite und einem Segment?",
						true);
		questionMap
				.put("Ist eine Leistungssteigerung zu erwarten, wenn statt eines einzelnen Pufferspeichers f&uuml;r die E/A zwei Pufferspeicher verwendet werden?",
						true);
		questionMap
				.put("Gibt es einen Unterschiedd zwischen einem Trap und einer Unterbrechung (Interrupt)?",
						true);
		questionMap
				.put("Das Client-Server-Modell ist bei verteilten Systemen sehr verbreitet. Kann es auch in einem Einplatz-Computer Anwendung finden?",
						true);
		questionMap
				.put("Kann ein Benutzer einen eigenen Kommandozeileninterpreter entwickeln, indem er das Systemcall-Interface des Kernels benutzt?",
						true);
		questionMap.put("Ist Multiprogramming und Multitasking das gleiche?",
				false);
		questionMap
				.put("Sollte der Befehl die aktuelle Uhrzeit zu lesen nur im Kernmodus erlaubt sein?",
						false);
		questionMap
				.put("Eine Bibliotheksfunktion hat den Namen read(). Der Systemaufruf des Betriebssystems hei&szligt auch read(). Ist es n&ouml;tig, dass beide Funktionen denselben Namen haben?",
						false);
		questionMap.put("ISRs sollten eher lang sein.", false);
		questionMap
				.put("Kann eine Multithreading-Anwendung mit mehreren User-Level Threads auf einem Multiprozessorsystem eine bessere Performance erreichen als auf einem Single-Prozessorsystem? ",
						false);

		questions = new LinkedList<String>(questionMap.keySet());
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
