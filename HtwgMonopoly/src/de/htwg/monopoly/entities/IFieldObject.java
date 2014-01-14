package de.htwg.monopoly.entities;

public interface IFieldObject {
	
	int getPosition();
	/**
	 * Return the type of this field. For example: Street, train station etc.
	 * @return TODO: enum, string oder Object??
	 */
	char getType();
	
	@Override
	String toString();
}
