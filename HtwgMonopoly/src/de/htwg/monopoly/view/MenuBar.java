package de.htwg.monopoly.view;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.plugins.MonopolyPlugin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.util.Date;
import java.util.Set;


public class MenuBar extends JMenuBar implements ActionListener {

	private final Logger logger = LogManager.getLogger("MenuBar");
	/**
	 * 
	 */
	private static final long serialVersionUID = 1262745107060789415L;

	private final JMenuItem miSource;
    private final JMenuItem miRule;
    private final JMenuItem miExitGame;
    private final JMenuItem miSaveGame;

	private static final String RULES = "http://de.wikipedia.org/wiki/Monopoly";
	private static final String SOURCE = "https://github.com/T1m1/de.htwg.se.monopoly";

    private IController controller;
	private JMenuItem miLoadGame;
    
	public MenuBar(IController controller, Set<MonopolyPlugin> plugins) {
        this.controller = controller;
        
		// Create the menu bar.
		JMenuBar menuBar = new JMenuBar();
		JMenu mDatei = new JMenu("Game");
		JMenu mHelp = new JMenu("Help");
		menuBar.add(mDatei);
		menuBar.add(mHelp);

        JMenuItem miNewGame = new JMenuItem("Neues Spiel starten...");
		miNewGame.addActionListener(this);
		mDatei.add(miNewGame);

		JMenuItem subMenu = new JMenuItem("Optionen Einstellen");
		mDatei.add(subMenu);

        mDatei.addSeparator();

        miSaveGame= new JMenuItem("Spiel speichern");
        miSaveGame.addActionListener(this);
        mDatei.add(miSaveGame);
        
        miLoadGame = new JMenuItem("Spiel laden");
        miLoadGame.addActionListener(this);
        mDatei.add(miLoadGame);

        mDatei.addSeparator();

		miExitGame = new JMenuItem("Spiel beenden");
		miExitGame.addActionListener(this);
		mDatei.add(miExitGame);
        
		miSource = new JMenuItem("Source Code");
		miSource.addActionListener(this);
		mHelp.add(miSource);
		miRule = new JMenuItem("Monopoly Rules");
		miRule.addActionListener(this);
		mHelp.add(miRule);
		
		// add plugin checkboxes
		if(!plugins.isEmpty()) {
			JMenu pluginMenu = new JMenu("Plugins");
			for (MonopolyPlugin current: plugins) {
				addPluginItem(current, pluginMenu);
			}
			menuBar.add(pluginMenu);
		}

		this.add(menuBar);

	}

	private void addPluginItem(final MonopolyPlugin plugin, JMenu pluginMenu) {
		final JCheckBoxMenuItem checkbox = new JCheckBoxMenuItem(plugin.getName());

		checkbox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (checkbox.isSelected()) {
					controller.addObserver(plugin);
					plugin.enable(controller);
					// TODO add something from the plugin to the gui
				} else {
					controller.removeObserver(plugin);
					// TODO: remove the added component from gui 
					plugin.disable();
				}
				
				// TODO: maybe update gui 
			}
		});
		
		// add to plugin menu
		pluginMenu.add(checkbox);
	}

	public void actionPerformed(ActionEvent e) {
		Object event = e.getSource();
		if (event.equals(miExitGame)) {
			System.exit(0);
		} else if (event.equals(miSource)) {
			showURL(SOURCE);
		} else if (event.equals(miRule)) {
			showURL(RULES);

		} else if (event.equals(miSaveGame)) {
			// open dialog
			String gameName = JOptionPane.showInputDialog(this,
					"Bitte Name f�r Spiel eingeben", "Spiel speichern",
					JOptionPane.QUESTION_MESSAGE);
			try {
				controller.saveGameToDB(gameName);
			} catch (IllegalAccessException e1) {
				logger.error(e1);
			}
		}

	}

	private void showURL(String source) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop()
				: null;
		try {
            assert desktop != null;
            desktop.browse(new URI(source));
		} catch (Exception e) {
			logger.info("Exception in MenuBar");

		}

	}
}