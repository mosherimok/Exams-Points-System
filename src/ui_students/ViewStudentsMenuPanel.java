package ui_students;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpinnerNumberModel;

import components_utility.MenuPanelView;
import pointsHistory.JButtonPointsHistory;
import tables.TblStudents;
import ui_records_management.PanelManagingRecord;
import ui_records_management.ModelViewFactory.Views;
import ui_searchRecord.ViewSearchRecord;

import javax.swing.border.EtchedBorder;
import javax.swing.JSpinner;

public class ViewStudentsMenuPanel extends MenuPanelView{
	
	//finals:
	private final static String RECORD_NAME = "תלמיד";
	
	//Object members:
	private ModelMpStudents model = new ModelMpStudents(this);
	
	//Components:
	public PanelManagingRecord panelManagingRecord;
	public JSpinner spinnerClass;
	public JButton buttonDisplayAllDoneTests;
	public JButton buttonViewAll;
	public JButton buttonViewByClass;
	
	public ViewStudentsMenuPanel() {
		super(RECORD_NAME,new TblStudents());
		initGUI();
		handleButtonsListeners();
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
		
		buttonDisplayAllDoneTests = new JButton("כל מבחני תלמיד זה");
		buttonDisplayAllDoneTests.setEnabled(false);
		
		panelManagingRecord.addJButton(buttonDisplayAllDoneTests);
		panelManagingRecord.addJButton(new JButtonPointsHistory(jtable, false));
		pnlTop.add(panelManagingRecord, BorderLayout.CENTER);
		
		JPanel panelSearchRecord = new ViewSearchRecord(this);
		pnlTop.add(panelSearchRecord, BorderLayout.SOUTH);
		
		add(pnlTop, BorderLayout.NORTH);
		
		JPanel pnlTable = new JPanel();
		pnlTable.setLayout(new BorderLayout(0, 0));
		
		add(pnlTable, BorderLayout.CENTER);
		
		JPanel panelShowByClass = new JPanel();
		panelShowByClass.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlTable.add(panelShowByClass, BorderLayout.NORTH);
		
		buttonViewByClass = new JButton("\u05D4\u05E6\u05D2");
		buttonViewAll = new JButton("\u05D4\u05E6\u05D2 \u05D0\u05EA \u05DB\u05DC \u05D4\u05EA\u05DC\u05DE\u05D9\u05D3\u05D9\u05DD");
		panelShowByClass.add(buttonViewAll);
		panelShowByClass.add(buttonViewByClass);
		
		spinnerClass = new JSpinner(new SpinnerNumberModel(1,1,30,1));
		panelShowByClass.add(spinnerClass);
		
		JLabel labelShowStudentsBy = new JLabel("\u05D4\u05E6\u05D2 \u05EA\u05DC\u05DE\u05D9\u05D3\u05D9\u05DD \u05DC\u05E4\u05D9 \u05E9\u05D9\u05E2\u05D5\u05E8:");
		panelShowByClass.add(labelShowStudentsBy);
		
		model.setJTableSelection();
		JScrollPane scrollPane = new JScrollPane(super.getJtable());
		pnlTable.add(scrollPane, BorderLayout.CENTER);
	}
	
	private void handleButtonsListeners() {
		model.displayAllDoneTestsAction();
		model.viewAllStudentsAction();
		model.viewByStudentsClassAction();
	}
	
}