/**
 * 
 */
package de.htwg.monopoly.persistence.hibernate;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Steffen
 *
 */
@Entity
@Table(name = "game")
@Data
public class PersistentGame {

	@Id
	@Column(name = "id")
	String id;

	@OneToMany(mappedBy = "game")
	@Column(name = "player")
	List<PersistentPlayer> players;

	@OneToOne(mappedBy = "game")
	@Column(name = "field")
	PersistentPlayfield playfield;

	String name;

	Integer currentPlayerIndex;

	boolean drawnCardFlag;

	int diceFlag;

	String message;

	int parkingMoney;

	String phase;
}
