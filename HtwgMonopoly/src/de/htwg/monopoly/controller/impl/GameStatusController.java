/**
 * 
 */
package de.htwg.monopoly.controller.impl;

import java.util.LinkedList;
import java.util.List;

import de.htwg.monopoly.util.GameStatus;
import de.htwg.monopoly.util.UserAction;

/**
 * @author stgorenf
 *
 */
public final class GameStatusController {
	
	private GameStatus phase;
	private List<UserAction> options;

	/**
	 * 
	 */
	public GameStatusController() {
		// TODO Auto-generated constructor stub
	}
	
	public List<UserAction> getOptions() {
		return options;
	}
	
	public GameStatus getPhase() {
		return phase;
	}
	
	public void update() {
		
	}

}
