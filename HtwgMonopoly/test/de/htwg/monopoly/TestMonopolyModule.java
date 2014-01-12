package de.htwg.monopoly;


import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import de.htwg.monopoly.controller.IController;

public class TestMonopolyModule extends AbstractModule  {
	private static final int FIELDSIZE = 2;
	
	@Override
	protected void configure()  {
		
		bind(Integer.TYPE).annotatedWith(Names.named("FieldSize")).toInstance(2);
		//bindConstant().annotatedWith(Names.named("FieldSize")).to(FIELDSIZE);
		bind(IController.class).to(de.htwg.monopoly.controller.impl.Controller.class);
		
	}
}