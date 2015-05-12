package de.htwg.monopoly.persistence.hibernate;

import lombok.Data;

@Data
public class PersistentPlayfield {

	int numberOfFields;
	
	String currentPlayer;
}
