package de.htwg.monopoly.game;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.entities.IDice;
import de.htwg.monopoly.entities.impl.Dice;
import de.htwg.monopoly.factory.IControllerFactory;

public class MonopolyModule extends AbstractModule {

	@Override
	protected void configure() {

		bindConstant().annotatedWith(Names.named("FieldSize")).to("28");
		// bind(Integer.class).annotatedWith(Names.named("Budget")).toInstance(1000);

		bind(IController.class).to(
				de.htwg.monopoly.controller.impl.Controller.class);

		bind(IControllerFactory.class).to(
				de.htwg.monopoly.factory.impl.MonopolyFactory.class);
		bind(IDice.class).to(de.htwg.monopoly.entities.impl.Dice.class);
	}

}
