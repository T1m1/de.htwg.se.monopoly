package de.htwg.monopoly;


import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import de.htwg.monopoly.controller.IController;

public class TestMonopolyModule extends AbstractModule  {
	
	@Override
	protected void configure()  {
		
		bindConstant().annotatedWith(Names.named("FieldSize")).to("2");
		bind(IController.class).to(de.htwg.monopoly.controller.impl.Controller.class);

		
		
	}
}