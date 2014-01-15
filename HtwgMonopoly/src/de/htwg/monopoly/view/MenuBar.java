package de.htwg.monopoly.view;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1262745107060789415L;

	private JMenuBar menuBar;
	private JMenu mDatei;
	private JMenu mHelp;
	private JMenuItem subMenu, miSource, miRegeln;
	private JMenuItem miNewGame;
	private JMenuItem miExitGame;

	private static final String RULES = "http://de.wikipedia.org/wiki/Monopoly";
	private static final String SOURCE = "https://github.com/T1m1/de.htwg.se.monopoly";

	public MenuBar() {

		// Create the menu bar.
		menuBar = new JMenuBar();
		mDatei = new JMenu("Game");
		mHelp = new JMenu("Help");
		menuBar.add(mDatei);
		menuBar.add(mHelp);

		miNewGame = new JMenuItem("Neues Spiel starten...");
		miNewGame.addActionListener(this);
		mDatei.add(miNewGame);

		subMenu = new JMenuItem("Optionen Einstellen");
		mDatei.add(subMenu);

		mDatei.addSeparator();

		miExitGame = new JMenuItem("Spiel beenden");
		miExitGame.addActionListener(this);
		mDatei.add(miExitGame);

		miSource = new JMenuItem("Source Code");
		miSource.addActionListener(this);
		mHelp.add(miSource);
		miRegeln = new JMenuItem("Monopoly Rules");
		miRegeln.addActionListener(this);
		mHelp.add(miRegeln);

		this.add(menuBar);

	}

	public void actionPerformed(ActionEvent e) {
		Object event = e.getSource();
		if (event == miExitGame) {
			System.exit(0);
		} else if (event == miNewGame) {

		} else if (event == miSource) {
			Desktop desktop = Desktop.isDesktopSupported() ? Desktop
					.getDesktop() : null;
			try {
				desktop.browse(new URI(SOURCE));
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (event == miRegeln) {
			Desktop desktop = Desktop.isDesktopSupported() ? Desktop
					.getDesktop() : null;
			try {
				desktop.browse(new URI(RULES));
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}
