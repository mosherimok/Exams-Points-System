package ui_searchRecord;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ui_components.ResultSetDefaultTableModel;

import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.UIManager;

public class SearchRecord extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_search;
	private JTable table;
	
	private ResultSetDefaultTableModel rsdt;
	private Vector<String> columnsNames;
	private String[] complexCombinations;
	private Vector<Integer> indexes; // founded indexes as {row,column}
	private int lastIndexFound = -1; // Last index in indexes vector.
	
	/**
	 * 
	 * @param table The table that this panel's functionality will be.
	 * @param columnsNames Columns names of the table.
	 * @wbp.parser.constructor
	 */
	public SearchRecord(JTable table){
		
		init_members(table,null);
		init_ui();
	}
	
	public SearchRecord(JTable table,String[] complexCombinations){
		init_members(table,complexCombinations);
		init_ui();
	}
	
	
	public void init_members(JTable table,String[] complexCombinations){
		this.table = table;
		
		rsdt= (ResultSetDefaultTableModel)table.getModel();
		
		columnsNames = new Vector<>();
		for(int col=0;col<rsdt.getColumnCount();col++){
			columnsNames.addElement(rsdt.getColumnName(col));
		}
		
		this.complexCombinations = complexCombinations;
		
		indexes = new Vector<>();
	}
	
	
	/**
	 * Create the panel.
	 */
	public void init_ui() {
		setBorder(new TitledBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u05D7\u05D9\u05E4\u05D5\u05E9 \u05E0\u05EA\u05D5\u05E0\u05D9\u05DD", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(128, 128, 128)), "\u05D7\u05D9\u05E4\u05D5\u05E9 \u05E0\u05EA\u05D5\u05E0\u05D9\u05DD", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel lblNewLabel = new JLabel("Search column:");
		add(lblNewLabel);
		
		columnsNames.add(0, "All");
		JComboBox<String> comboBox = new JComboBox<>(columnsNames);
		addComplexCombinations(comboBox);
		comboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
//				System.out.println("Changed");
				clearLastSearch();
			}
		});
		add(comboBox);
		
		textField_search = new JTextField();
		textField_search.setSize(new Dimension(80, textField_search.getPreferredSize().height));
		textField_search.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
//				System.out.println("removeUpdate");
				clearLastSearch();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
//				System.out.println("insertUpdate");
				clearLastSearch();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
//				System.out.println("changedUpdate");
				clearLastSearch();
			}
		});
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea_1);
		
		add(textField_search);
		textField_search.setColumns(30);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		add(rigidArea);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String colName = comboBox.getSelectedItem().toString();
				routeSearching(colName);
			}
		});
		add(btnSearch);
		
		JButton btnNextRecord = new JButton("Next record");
		btnNextRecord.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(lastIndexFound==-1){
					JOptionPane.showMessageDialog(null, "No more matched result is found");
					return;
				}
				if(lastIndexFound==indexes.size())
					lastIndexFound = 0;
				int row = indexes.get(lastIndexFound);
				table.setRowSelectionInterval(row, row);
				
				lastIndexFound++;
			}
		});
		add(btnNextRecord);
	}
	
	private void routeSearching(String colName){
		
		int rowsCount = rsdt.getRowCount();
		String expected = textField_search.getText();
		
		// Handle "All" option.
		if(colName.equals("All")){
			searchAction(expected, rowsCount);
		}
		// Handle Complex Combinations
		else if(colName.contains("+")){
			colName = colName.replace(" ", "");
			String colName1 = colName.substring(0,colName.lastIndexOf('+'));
			String colName2 = colName.substring(colName.lastIndexOf('+')+1,colName.length());
//			System.out.println("col1 " + colName1 + " | col2 " + colName2);
			int colIndex1 = rsdt.findColumn(colName1);
			int colIndex2 = rsdt.findColumn(colName2);
//			System.out.println("col1 " + colIndex1 + " | col2 " + colIndex2);
			searchAction(expected, rowsCount, colIndex1,colIndex2);
		}
		// Handle rest of options.
		else{
			int colIndex = rsdt.findColumn(colName);
			searchAction(expected, rowsCount, colIndex);
		}
		// Select the founded row.
		if(indexes.size()>0){
			int row = indexes.get(0);
			table.setRowSelectionInterval(row, row);
			lastIndexFound=1;
		}
		else
			JOptionPane.showMessageDialog(null, "No matched result is found");
	}
	
	/**
	 * 
	 * @param expected Value to find.
	 * @param rowsCount Count of rows in the table.
	 * @param colsIndexes Which indexes to check in. Leave empty mean check all columns.
	 */
	public void searchAction(String expected,int rowsCount,int ... colsIndexes){
		if(colsIndexes.length==0){
			for(int col=0;col<rsdt.getColumnCount();col++){
				for(int row=0;row<rowsCount;row++){
					if(textField_search.getText().equals(rsdt.getValueAt(row, col).toString())){
//						System.out.println("Added");
						indexes.addElement(row);
					}
				}
			}
			return;
		}
		
		for(int row = 0;row<rowsCount;row++){
			String actual = "";
			int i;
			for(i = 0;i<colsIndexes.length;i++){
//				System.out.println("row: " + row + " col: " + colsIndexes[i]);
				actual += rsdt.getValueAt(row, colsIndexes[i]).toString() + " ";
			}
			System.out.println("Expected: " + expected + " | Actual: " + actual);
			if(textField_search.getText().equals(actual.trim()))// trim() remove trail whitespaces. Needed beacuse the for loop add a " "
			{
//				System.out.println("Added");
				indexes.addElement(row);
			}
		}
	}
	
	public Vector<Integer> getFoundedRows(){
		return indexes;
	}
	
	private void clearLastSearch(){
		if(indexes.size()>0){
			indexes = new Vector<>();
			lastIndexFound = -1;
			table.clearSelection();
		}
	}
	
	private void addComplexCombinations(JComboBox<String> combo){
		if(complexCombinations==null)
			return;
		for(int i=0;i<complexCombinations.length;i++)
			combo.addItem(complexCombinations[i]);
	}
	

}
