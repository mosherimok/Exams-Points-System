package ui_searchRecord;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.util.HashMap;
import java.awt.Component;

import javax.swing.Box;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;


import database.DatabaseActions;
import database.ResultSetManipulation;
import database.StatementHandle;
import tables.Table;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchRecordSQLBased extends JPanel {
	//All members:
	private final Table TABLE;
	private final HashMap<String,Class<?>> criterias;
	
	//All components:
	private JLabel label;
	private JComboBox<String> comboBox;
	private Component rigidArea;
	private JTextField textField;
	private Component rigidArea_1;
	private JButton button;
	private JButton button_1;
	
	

	/**
	 * Create the panel.
	 */
	public SearchRecordSQLBased(Table table) {
		TABLE = table;
		criterias = new HashMap<>();
		initGUI();
		initialCriterias(); // must be after combobox initialization.
	}
	private void initGUI() {
		
		label = new JLabel("Search column:");
		add(label);
		
		comboBox = new JComboBox<String>();
		add(comboBox);
		
		rigidArea = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea);
		
		textField = new JTextField();
		textField.setSize(new Dimension(80, 20));
		textField.setColumns(30);
		add(textField);
		
		rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_1);
		
		button = new JButton("Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String criteria = (String)comboBox.getSelectedItem();
				String toSearch = "";
				if(criterias.get((String)comboBox.getSelectedItem())==Integer.class)
					toSearch = (String)comboBox.getSelectedItem();
				else
					toSearch = "'" + (String)comboBox.getSelectedItem() + "'";
				
				String script = String.format("SELECT * FROM %s WHERE %s = %s", TABLE.getTableName(),
						criteria,toSearch);
				StatementHandle handle = new StatementHandle() {
					@Override
					public void handle(Statement stmt) throws SQLException {
						ResultSet result = stmt.executeQuery(script);
						result.last();
						 if(result.getRow()>1){
							 new FoundedRecords(TABLE,ResultSetManipulation.getResultSetDataArray(result));
						 }
					}
				};
				
				DatabaseActions.executeQuery(handle);
			}
		});
		add(button);
		
		button_1 = new JButton("Next record");
		add(button_1);
	}
	
	private void initialCriterias(){
		String[] labels = TABLE.getColumnsLabels();
		Class<?>[] types = TABLE.getColumnsType();
		for(int i=0;i<labels.length;i++){
			comboBox.addItem(labels[i]);
			criterias.put(labels[i],types[i]);
		}
		/*ResultSet set = null;
		try {
			set = TableDetails.getColumnsLabels(TABLE_NAME);
			ResultSetMetaData result = set.getMetaData();
			for(int index = 1;index<=result.getColumnCount();index++){
				comboBox.addItem(result.getColumnLabel(index));
				criterias.put(result.getColumnLabel(index),
						Class.forName(result.getColumnClassName(index)));
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			if(set!=null){
				try {
					
					 * Quote from offical docs:
					 * Note:When a Statement object is closed, its current ResultSet object,
					 * if one exists, is also closed.
					 
					set.getStatement().close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}*/
	}

}
