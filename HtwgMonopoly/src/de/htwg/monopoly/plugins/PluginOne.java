/**
 *
 */
package de.htwg.monopoly.plugins;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.util.GameStatus;

/**
 * @author Steffen
 */
public class PluginOne extends JFrame implements MonopolyPlugin {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 400;
    public static final int HEIGHT = 200;
    public static final int SIZE = 30;

    private JLabel label;
	private IController controller;

    @Override
    public void update(GameStatus e) {
        label.setText("<html><body>" + controller.getMessage()+ "</body></html>");
    }

    @Override
    public String getName() {
        return "Größere Ausgabe";
    }

    @Override
    public void enable(IController controller) {
    	this.controller = controller;
        /** frame options **/
        setTitle("HTWG Monopoly");
        /* set minimum size */
        Dimension dimension = new Dimension(WIDTH, HEIGHT);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setPreferredSize(dimension);

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        label = new JLabel();

        label.setFont(new Font("Arial", Font.CENTER_BASELINE, SIZE));

        add(label, BorderLayout.CENTER);

        label.setText("Text in schön groß");

        setVisible(true);


    }

    @Override
    public void disable() {
        setVisible(false);

    }

}
