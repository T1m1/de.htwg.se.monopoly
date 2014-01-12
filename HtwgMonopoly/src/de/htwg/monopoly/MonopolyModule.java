package de.htwg.monopoly;

import java.lang.annotation.*;

import com.google.inject.BindingAnnotation;

import de.htwg.monopoly.controller.IController;

public class MonopolyModule extends AbstractModule  {
	
	@Override
	protected void configure()  {
		
		bindConstant().annotatedWith(@Names.named("FieldSize")).to("40");		
		bind(IController.class).to(de.htwg.monopoly.controller.impl.Controller.class);
	}

}
