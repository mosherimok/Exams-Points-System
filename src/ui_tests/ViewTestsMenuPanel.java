package ui_tests;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import components_utility.MenuPanelView;
import components_utility.ResultSetDefaultTableModel;
import tables.TblTests;
import ui_records_management.PanelManagingRecord;
import ui_records_management.ModelViewFactory.Views;
import ui_searchRecord.ViewSearchRecord;

import javax.swing.UIManager;
import java.awt.Color;

public class ViewTestsMenuPanel extends MenuPanelView {

	//finals:
	private final static String RECORD_NAME = "מבחן";
		
	//Object members:
	private ModelMpTests model = new ModelMpTests(this);
	
	//Components:
	public JButton addExaminees;
	public PanelManagingRecord panelManagingRecord;
	public JButton buttonViewTested;
	
	
	
	public ViewTestsMenuPanel() {
		super(RECORD_NAME,new TblTests());
		initJTable();
		initGUI();
		handleButtonsAction();
	}
	
	/**
	 * Create the panel.
	 */
	private void initGUI(){
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelTop = new JPanel();
		add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitle = new JPanel();
		panelTop.add(panelTitle, BorderLayout.NORTH);
		
		JLabel label = new JLabel("\u05DE\u05D1\u05D7\u05E0\u05D9\u05DD \u05E9\u05E0\u05E2\u05E9\u05D5");
		panelTitle.add(label);
		label.setFont(new Font("Ariel", Font.BOLD, 14));
		
		JPanel panelSearch = new ViewSearchRecord(this);
		panelTop.add(panelSearch, BorderLayout.SOUTH);
		
		JPanel panelContent = new JPanel();
		add(panelContent, BorderLayout.CENTER);
		panelContent.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCenter = new JPanel();
	
		panelCenter.setLayout(new BorderLayout(0, 0));
		
		panelContent.add(panelCenter, BorderLayout.CENTER);
		
		JPanel panelTestActions = new JPanel();
		panelTestActions.setBorder(new TitledBorder(null, "\u05E4\u05E2\u05D5\u05DC\u05D5\u05EA \u05DE\u05D1\u05D7\u05DF", TitledBorder.RIGHT, TitledBorder.TOP, null, null));
		panelCenter.add(panelTestActions, BorderLayout.EAST);
		panelTestActions.setLayout(new BoxLayout(panelTestActions, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panelTestActions.add(verticalStrut);
		
		addExaminees = new JButton("\u05D4\u05D5\u05E1\u05E4\u05EA \u05E6\u05D9\u05D5\u05E0\u05D9\u05DD \u05DC\u05DE\u05D1\u05D7\u05DF \u05D6\u05D4");
		addExaminees.addActionListener(model.getAddGradesAction());
		addExaminees.setEnabled(false);
		addExaminees.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelTestActions.add(addExaminees);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panelTestActions.add(verticalStrut_1);
		
		buttonViewTested = new JButton("<html>\r\n\u05D4\u05E6\u05D2\u05EA \u05DB\u05DC \u05D4\u05E0\u05D1\u05D7\u05E0\u05D9\u05DD<br>\r\n\u05E2\u05D1\u05D5\u05E8 \u05DE\u05D1\u05D7\u05DF \u05D6\u05D4\r\n</html>");
		buttonViewTested.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelTestActions.add(buttonViewTested);
		
		JPanel panelTable = new JPanel();
		panelTable.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u05D8\u05D1\u05DC\u05EA \u05D7\u05DE\u05E9\u05EA \u05D4\u05DE\u05D1\u05D7\u05E0\u05D9\u05DD \u05D4\u05D0\u05D7\u05E8\u05D5\u05E0\u05D9\u05DD", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		panelTable.setLayout(new BorderLayout(0, 0));
		
		model.setJTableSelectionAction();
		JScrollPane scrollPane = new JScrollPane(super.getJtable());
		scrollPane.setBorder(null);
		
		panelTable.add(scrollPane);
		
		panelCenter.add(panelTable, BorderLayout.CENTER);
		
		panelManagingRecord = new PanelManagingRecord(getJtable(), RECORD_NAME, Views.Tests);
		panelTop.add(panelManagingRecord, BorderLayout.CENTER);
	}
	
	public void initJTable(){
		TblTests table = new TblTests(){
			@Override
			public String getSelectAllScript() {
				return "SELECT rowid,* FROM Tests ORDER BY rowid DESC LIMIT 5";
			};
		};
		
		getJtable().setDefaultTableModel((new ResultSetDefaultTableModel(table)));
	}

	private void handleButtonsAction() {
		model.viewTestedsButton();
	}
	
	
}
