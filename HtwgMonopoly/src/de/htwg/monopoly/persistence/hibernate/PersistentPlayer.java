package de.htwg.monopoly.persistence.hibernate;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import de.htwg.monopoly.util.PlayerIcon;
import lombok.Data;

@Entity
@Table(name = "player")
@Data
public class PersistentPlayer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer value = 0; // TODO: what? why?
	
	private String name;
	private Integer budget;
	private Integer position;
	private Integer prisonRound;
	private Boolean inPrison;
	// Save only position of ownerships
	private Collection<Integer> ownershipPositions;
	private Integer prisonFreeCard;
	private PlayerIcon icon;
}
