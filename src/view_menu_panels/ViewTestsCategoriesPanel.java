package view_menu_panels;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import actions.MV_Factory.Views;
import tables.Table;
import tables.TblTestsCategories;
import ui_components.CustomizedJTable;
import ui_components.PanelManagingRecord;
import ui_components.ResultSetDefaultTableModel;

public class ViewTestsCategoriesPanel extends JPanel {

	//finals:
	private String RECORD_NAME = "סוג מבחן";
	
	//members:
	private Table table  = new TblTestsCategories();
	
	//Components:
	private PanelManagingRecord panelManagingRecord;
	private CustomizedJTable jtable;
	
	
	/**
	 * Create the panel.
	 */
	public ViewTestsCategoriesPanel() {
		initJTable();
		initGUI();
	}
	
	private void initGUI(){
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTitle = new JPanel();
		add(pnlTitle, BorderLayout.NORTH);
		
		JLabel label = new JLabel("\u05E1\u05D5\u05D2\u05D9 \u05DE\u05D1\u05D7\u05E0\u05D9\u05DD");
		pnlTitle.add(label);
		
		JPanel pnlContent = new JPanel();
		pnlContent.setLayout(new BorderLayout(0, 0));
		
		pnlContent.add(new JScrollPane(jtable));
		
		add(pnlContent, BorderLayout.CENTER);
		
		panelManagingRecord = new PanelManagingRecord(jtable, RECORD_NAME, Views.TestsCategories);
		add(panelManagingRecord, BorderLayout.SOUTH);
	}
	
	private void initJTable(){
		jtable = new CustomizedJTable(new ResultSetDefaultTableModel(table));
		setJTableSelectionAction();
	}
	
	private void setJTableSelectionAction(){
		jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				boolean isSelected = jtable.getSelectionModel().isSelectionEmpty();
				panelManagingRecord.setDeleteAndModifyEnable(!isSelected);
			}
		});
	}
}
