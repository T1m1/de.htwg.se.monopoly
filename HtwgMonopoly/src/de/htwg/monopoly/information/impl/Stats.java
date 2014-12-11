/**
 * 
 */
package de.htwg.monopoly.information.impl;

import java.util.List;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.controller.IPlayerController;
import de.htwg.monopoly.controller.IPlayfield;
import de.htwg.monopoly.entities.IFieldObject;
import de.htwg.monopoly.entities.impl.Dice;
import de.htwg.monopoly.entities.impl.Player;
import de.htwg.monopoly.information.IStats;
import de.htwg.monopoly.observer.IObserver;
import de.htwg.monopoly.util.GameStatus;
import de.htwg.monopoly.util.UserAction;

/**
 * @author Steffen
 *
 */
public class Stats implements IStats {

	private IController controller;
	private IPlayerController players;
	private IPlayfield playfield;

	/**
	 * 
	 */
	public Stats(IController controller, IPlayerController players, IPlayfield playfield) {
		this.controller = controller;
		this.players = players;
		this.playfield = playfield;
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Player getCurrentPlayer() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getNumberOfPlayers() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public Player getPlayer(int i) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Dice getDice() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IFieldObject getCurrentField() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public GameStatus getPhase() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<UserAction> getOptions() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getFieldSize() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public IFieldObject getFieldAtIndex(int i) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IPlayfield getField() {
		// TODO Auto-generated method stub
		return null;
	}

}
