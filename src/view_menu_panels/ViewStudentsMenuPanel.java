package view_menu_panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;

import actions.MV_Factory.Views;
import tables.Table;
import tables.TblStudents;
import ui_components.CustomizedJTable;
import ui_components.DefaultSqlTableModel;
import ui_components.PanelManagingRecord;
import ui_components.ResultSetDefaultTableModel;
import ui_searchRecord.SearchRecordSQLBased;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JSpinner;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class ViewStudentsMenuPanel extends JPanel{
	
	//finals:
	private final String RECORD_NAME = "תלמיד";
	
	//Object members:
	private Table table = new TblStudents();
	
	//Components:
	private CustomizedJTable jtable = new CustomizedJTable();
	private PanelManagingRecord panelManagingRecord;
	private JSpinner spinnerClass;
	
	public ViewStudentsMenuPanel() {
		setJTableSelectionAction();
		initGUI();
	}
	
	protected void initGUI() {
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTop = new JPanel();
		pnlTop.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblPanelDescription = new JLabel("פרטי תלמידי כרם ביבנה");
		pnlTitle.add(lblPanelDescription);
		
		pnlTop.add(pnlTitle, BorderLayout.NORTH);
		
		panelManagingRecord = new PanelManagingRecord(jtable, RECORD_NAME, Views.Students);
		pnlTop.add(panelManagingRecord, BorderLayout.CENTER);
		
		JPanel panelSearchRecord = new SearchRecordSQLBased(table);
		pnlTop.add(panelSearchRecord, BorderLayout.SOUTH);
		
		add(pnlTop, BorderLayout.NORTH);
		
		JPanel pnlTable = new JPanel();
		pnlTable.setLayout(new BorderLayout(0, 0));
		
		add(pnlTable, BorderLayout.CENTER);
		
		JPanel panelShowByClass = new JPanel();
		panelShowByClass.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlTable.add(panelShowByClass, BorderLayout.NORTH);
		
		JButton button = new JButton("\u05D4\u05E6\u05D2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initTableModel();
			}
		});
		panelShowByClass.add(button);
		
		spinnerClass = new JSpinner(new SpinnerNumberModel(1,1,30,1));
		panelShowByClass.add(spinnerClass);
		
		JLabel labelShowStudentsBy = new JLabel("\u05D4\u05E6\u05D2 \u05EA\u05DC\u05DE\u05D9\u05D3\u05D9\u05DD \u05DC\u05E4\u05D9 \u05E9\u05D9\u05E2\u05D5\u05E8:");
		panelShowByClass.add(labelShowStudentsBy);
		
		
		JScrollPane scrollPane = new JScrollPane(jtable);
		pnlTable.add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initTableModel(){
		TblStudents table = new TblStudents(){
			@Override
			public String getSelectAllScript() {
				return "SELECT * FROM Students WHERE reception_year="+(Calendar.getInstance().get(Calendar.YEAR)-((int)spinnerClass.getValue()));
			}
		};
		jtable.setModel(new ResultSetDefaultTableModel(table));
		((DefaultSqlTableModel)jtable.getModel()).fireTableStructureChanged();
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