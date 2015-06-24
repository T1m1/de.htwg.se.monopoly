package de.htwg.monopoly.persistence.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "player")
@Data
public class PersistentPlayer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1082407840689454944L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	private Integer value = 0; // TODO: what? why?
	
	@ManyToOne
	@JoinColumn(name = "gameid")
	private PersistentGame game;
	
	private String name;
	private Integer budget;
	private Integer position;
	private Integer prisonRound;
	private Boolean inPrison;
	// Save only position of ownerships
	private Integer[] ownershipPositions;
	private Integer prisonFreeCard;
	private String icon;
}
