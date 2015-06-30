/**
 * 
 */
package de.htwg.monopoly.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import de.htwg.monopoly.controller.IController;



/**
 * @author Steffen
 *
 */
public class MonopolyLoadDialog extends JDialog {


	private static final long serialVersionUID = 1L;
	private static final int BEVEL=10;
	private static final int BUTTONS=10;
	private JTable table;
	private IController controller;
	private String[][] savedGames;
	private String[] columnNames = { "Name", "Id" };
	
	public MonopolyLoadDialog(final IController controller) {
		
		// init
		this.controller = controller;
		this.savedGames = controller.getAllSavedGames();
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(BEVEL, BEVEL, BEVEL, BEVEL));
		
		JPanel tablePanel = new JPanel();
		table = new JTable();
		table.getColumnModel().setSelectionModel(
				new DefaultListSelectionModel() {
					private static final long serialVersionUID = 1L;

					@Override
					public int getLeadSelectionIndex() {
						return -1;
					}
				});
		
		tablePanel.setBorder(BorderFactory.createTitledBorder("Games"));
		tablePanel.add(new JScrollPane(table));
		mainPanel.add(tablePanel, BorderLayout.PAGE_END);
		
		// create button panel
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, BUTTONS,
				BUTTONS));
		
		// delete Button
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int rowIdx = MonopolyLoadDialog.this.table.getSelectedRow();
				if (rowIdx == -1) {
					JOptionPane.showMessageDialog(MonopolyLoadDialog.this,
							"Please choose an entry first.");
					return;
				}
				String id = (String) MonopolyLoadDialog.this.savedGames[rowIdx][1];
				controller.deleteGame(id);
				MonopolyLoadDialog.this.updateTable();
			}
		});
		buttonPanel.add(deleteButton);
		
		// load button
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int rowIdx = MonopolyLoadDialog.this.table.getSelectedRow();
				if (rowIdx == -1) {
					JOptionPane.showMessageDialog(MonopolyLoadDialog.this,
							"Please choose an entry first.");
					return;
				}
				String id = (String) MonopolyLoadDialog.this.savedGames[rowIdx][1];
				controller.loadGameFromDB(id);
				MonopolyLoadDialog.this.setVisible(false);
			}
		});
		buttonPanel.add(loadButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MonopolyLoadDialog.this.setVisible(false);
			}
		});
		buttonPanel.add(exitButton);
		
		// add all buttons
		mainPanel.add(buttonPanel, BorderLayout.PAGE_END);
		
		// retrieve data
		updateTable();
		
		this.setContentPane(mainPanel);
		this.setResizable(false);
		this.setTitle("Load a Game from Database");
		this.pack();
		this.setVisible(true);
		
		
	}

	private void updateTable() {		
		 savedGames = controller.getAllSavedGames();

		table.setModel(new DefaultTableModel(savedGames, columnNames ) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
	}
}
