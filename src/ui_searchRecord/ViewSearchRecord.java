package ui_searchRecord;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import components_utility.MenuPanelView;
import tables.Table;

public class ViewSearchRecord extends JPanel{

	
	//Objects:
	private Table table;
	private ModelSearchRecord model = new ModelSearchRecord(this);
	
	//Components:
	public JComboBox<String> comboBox_primaryCriteria;
	public JTextField textFieldSearch;
	public JButton button_search;
	public JComboBox<String> comboBox_secondaryCriteria;
	public JPanel panelSearchButton;
	
	
	public ViewSearchRecord(MenuPanelView view) {
		table = view.getTable();
		initGUI();
		initListeners();
	}
	
	
	private void initGUI() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panelCriterias = new JPanel();
		add(panelCriterias);
		
		comboBox_secondaryCriteria = new JComboBox<String>();
		panelCriterias.add(comboBox_secondaryCriteria);
		comboBox_secondaryCriteria.setModel(new DefaultComboBoxModel<>(table.getColumnsLabels()));
		comboBox_secondaryCriteria.setSelectedIndex(-1);
		
		JLabel label_secondarySearch = new JLabel("\u05D7\u05D9\u05E4\u05D5\u05E9 \u05DE\u05E9\u05E0\u05D9 (\u05DC\u05D0 \u05D7\u05D5\u05D1\u05D4):");
		panelCriterias.add(label_secondarySearch);
		
		comboBox_primaryCriteria = new JComboBox<String>();
		panelCriterias.add(comboBox_primaryCriteria);
		comboBox_primaryCriteria.setModel(new DefaultComboBoxModel<>(table.getColumnsLabels()));
		
		JLabel label_searchFor = new JLabel("\u05D7\u05E4\u05E9 \u05E2\u05D1\u05D5\u05E8:");
		panelCriterias.add(label_searchFor);
		
		JPanel panelTextfield = new JPanel();
		add(panelTextfield);
		
		textFieldSearch = new JTextField();
		panelTextfield.add(textFieldSearch);
		textFieldSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldSearch.setToolTipText("\u05D4\u05E7\u05DC\u05D3 \u05DC\u05D7\u05D9\u05E4\u05D5\u05E9");
		textFieldSearch.setSize(new Dimension(80, 20));
		textFieldSearch.setColumns(30);
		
		panelSearchButton = new JPanel();
		add(panelSearchButton);
		
		button_search = new JButton("\u05D7\u05E4\u05E9");
		panelSearchButton.add(button_search);
		
	}
	
	private void initListeners() {
		model.buttonSearch();
		model.textfieldSearchKeyListener();
	}


	public Table getTable() {
		return table;
	}
	
	
}
