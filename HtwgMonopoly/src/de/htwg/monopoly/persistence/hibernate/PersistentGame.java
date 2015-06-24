/**
 * 
 */
package de.htwg.monopoly.persistence.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Steffen
 *
 */
@Entity
@Table(name = "game")
@Data
public class PersistentGame implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1932693982664231288L;

	@Id
	@Column(name = "id")
	private String id;

	@OneToMany(mappedBy = "game")
	@Column(name = "player")
	List<PersistentPlayer> players;

	private String name;

	private Integer currentPlayerIndex;

	private boolean drawnCardFlag;

	private int diceFlag;

	private String message;

	private int parkingMoney;

	private String phase;

	private	int numberOfFields;
}
