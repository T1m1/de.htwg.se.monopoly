/**
 * 
 */
package de.htwg.monopoly.database;

/**
 * @author Steffen
 *
 */
public interface IMonopolyDAO {
	
	void create();
	
	Object read();
	
	void update();
	
	void delete();

}
