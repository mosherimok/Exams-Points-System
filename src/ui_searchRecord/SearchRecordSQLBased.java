package ui_searchRecord;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;


import database.DatabaseActions;
import database.ResultSetManipulation;
import tables.Table;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;

public class SearchRecordSQLBased extends JPanel {
	//All members:
	private final Table table;
	private String[] criterias;
	
	//All components:
	private JLabel label_searchFor;
	private JComboBox<String> comboBox_primaryCriteria;
	private JTextField textField;
	private Component rigidArea_1;
	private JButton button_search;
	private JLabel label_secondarySearch;
	private JComboBox<String> comboBox_secondaryCriteria;
	private Component rigidArea_2;
	
	

	/**
	 * Create the panel.
	 */
	public SearchRecordSQLBased(Table table) {
		this.table = table;
		criterias = table.getColumnsLabels();
		initGUI();
//		initialCriterias(); // must be after combobox initialization.
	}
	private void initGUI() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		button_search = new JButton("\u05D7\u05E4\u05E9");
		button_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					searchingHandle();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		add(button_search);
		
		rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_2);
		rigidArea_2.setPreferredSize(new Dimension(10, 20));
		
		textField = new JTextField();
		add(textField);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setToolTipText("\u05D4\u05E7\u05DC\u05D3 \u05DC\u05D7\u05D9\u05E4\u05D5\u05E9");
		textField.setSize(new Dimension(80, 20));
		textField.setColumns(30);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
					try {
						searchingHandle();
						super.keyPressed(arg0);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
		
		rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_1);
		
		comboBox_secondaryCriteria = new JComboBox<String>();
		add(comboBox_secondaryCriteria);
		comboBox_secondaryCriteria.setModel(new DefaultComboBoxModel<>(criterias));
		comboBox_secondaryCriteria.setSelectedIndex(-1);
		
		label_secondarySearch = new JLabel("\u05D7\u05D9\u05E4\u05D5\u05E9 \u05DE\u05E9\u05E0\u05D9 (\u05DC\u05D0 \u05D7\u05D5\u05D1\u05D4):");
		add(label_secondarySearch);
		
		comboBox_primaryCriteria = new JComboBox<String>();
		add(comboBox_primaryCriteria);
		comboBox_primaryCriteria.setModel(new DefaultComboBoxModel<>(criterias));
		
		label_searchFor = new JLabel("\u05D7\u05E4\u05E9 \u05E2\u05D1\u05D5\u05E8:");
		add(label_searchFor);
	}
	
	private void searchingHandle() throws Exception{
		int selectedIndexPrimary = comboBox_primaryCriteria.getSelectedIndex();
		int selectedIndexSecondary = comboBox_secondaryCriteria.getSelectedIndex();
		
		String columnIdentifier1 = table.getAllColumnsIdentifiers()[selectedIndexPrimary];
		String columnIdentifier2 = selectedIndexSecondary==-1?null:table.getAllColumnsIdentifiers()[selectedIndexSecondary];
		
		String script = null;
		if(columnIdentifier2!=null){
			String[] inputs = textField.getText().split(" ");
			if(inputs.length<2)
				throw new Exception("Input to search is not valid. Two items to search " +
						"are required!");
			script = String.format(table.getSelectAllScript()+" WHERE %s='%s' AND %s='%s'",
				columnIdentifier1,inputs[0].toLowerCase(),columnIdentifier2,inputs[1].toLowerCase());
		}
		else
			script = String.format(table.getSelectAllScript()+" WHERE %s='%s'",
					columnIdentifier1,textField.getText());
		
		Object[][] fetchedData = DatabaseActions.getAllQueryData(script);

		
		new FoundedRecords(table, fetchedData);
		
	}
	

}
