package de.htwg.monopoly.view;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

import de.htwg.monopoly.controller.impl.Controller;
import de.htwg.monopoly.util.IMonopolyUtil;

public class TextUITest {
	TextUI tui;
	Controller controller;
	ByteArrayInputStream testStream;
	@Before
	public void setUp() throws Exception {
		testStream = new ByteArrayInputStream(
				IMonopolyUtil.TEST_INPUT_STREAM.getBytes());
		controller = new Controller();
		 tui = new TextUI(controller);
		 System.setIn(System.in);
			controller.initGame(IMonopolyUtil.TUI_FIELD_SIZE);
		
	}

	@Test
	public void testStartGame() throws AWTException {
		// TODO automatische eingabe von Zahlen notwednig
		
	}

	@Test
	public void testTextUI() {
		// TODO automatische eingabe von Zahlen notwednig
	}

	@Test
	public void testUpdateEvent() {
		// TODO automatische eingabe von Zahlen notwednig
	}

	@Test
	public void testUpdateInt() {
		// TODO automatische eingabe von Zahlen notwednig
	}

	@Test
	public void testOnField() {
		// TODO automatische eingabe von Zahlen notwednig
	}

	@Test
	public void testPrintAction() {
		tui.printAction();
	}

	@Test
	public void testPrintInitialisation() {
		// TODO automatische eingabe von Zahlen notwednig
	}

	@Test
	public void testStartTurn() {
		// TODO automatische eingabe von Zahlen notwednig
	}

	@Test
	public void testProcessInputLine() {
		// TODO automatische eingabe von Zahlen notwednig
	}

}
