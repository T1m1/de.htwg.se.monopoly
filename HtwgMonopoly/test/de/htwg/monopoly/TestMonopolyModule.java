package de.htwg.monopoly;

import java.lang.annotation.*;

import com.google.inject.BindingAnnotation;

import de.htwg.monopoly.controller.IController;

public class TestMonopolyModule extends AbstractModule  {
	
	@Override
	protected void configure()  {
		
		bindContant().annotatedWith(@Names.named("FieldSize")).to("1");
		bind(IController.class).to(de.htwg.monopoly.controller.impl.Controller.class);
		
	}
}
