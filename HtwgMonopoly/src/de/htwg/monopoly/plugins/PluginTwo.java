package de.htwg.monopoly.plugins;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.htwg.monopoly.controller.IController;
import de.htwg.monopoly.util.GameStatus;

public class PluginTwo extends JFrame implements MonopolyPlugin {

    private static final long serialVersionUID = 1L;
    public static final int MONEY = 100;

    private IController controller;

	private JLabel label;

	@Override
	public void update(GameStatus e) {
		// do nothing
	}

	@Override
	public String getName() {
		return "Cheat Plugin";
	}

	@Override
	public void enable(IController controller) {
		this.controller = controller;
		/** frame options **/
		setTitle("Cheat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		 
        // JButton mit Text "Dr�ck mich" wird erstellt
        JButton button = new JButton("Gib mir 100 GELD");
        
        button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PluginTwo.this.controller.cheatAndGetMoney(MONEY);
			}
		});
 
        // JButton wird dem Panel hinzugef�gt
        panel.add(button);
 
        this.add(panel);
        
        this.pack();

		setVisible(true);

	}

	@Override
	public void disable() {
		setVisible(false);

	}
}
