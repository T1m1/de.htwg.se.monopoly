package de.htwg.monopoly.entities;

import de.htwg.monopoly.util.FieldType;

public interface IFieldObject {
	
	int getPosition();
	/**
	 * Return the type of this field. For example: Street, train station etc.
	 * @return TODO: enum, string oder Object??
	 */
	FieldType getType();
	
	@Override
	String toString();
}
