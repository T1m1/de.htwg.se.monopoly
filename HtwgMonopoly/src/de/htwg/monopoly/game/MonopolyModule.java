package de.htwg.monopoly.game;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.persistence.IMonopolyDAO;
import de.htwg.monopoly.factory.IControllerFactory;

public class MonopolyModule extends AbstractModule {

	@Override
	protected void configure() {

		// define the size of the game
		bindConstant().annotatedWith(Names.named("FieldSize")).to("28");

		// define the main controller of the game
		bind(IController.class).to(
				de.htwg.monopoly.controller.impl.Controller.class);

		// define the factory for the underlying model
		bind(IControllerFactory.class).to(
				de.htwg.monopoly.factory.impl.MonopolyFactory.class);

		// define the database of the game

		// hibernate
		//bind(IMonopolyDAO.class).to(de.htwg.monopoly.persistence.hibernate.MonopolyHibernateDAO.class);

		// couchdb
		// bind(IMonopolyDAO.class).to(de.htwg.monopoly.persistence.couchdb.MonopolyCouchdbDAO.class);

		// db40
		bind(IMonopolyDAO.class).to(de.htwg.monopoly.persistence.db4o.MonopolyDb4oDAO.class);

	}

}
