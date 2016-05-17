package view_menu_panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import actions.AddGradesActions;
import actions.MV_Factory.Views;
import tables.Table;
import tables.TblTests;
import ui_components.CustomizedJTable;
import ui_components.PanelManagingRecord;
import ui_components.ResultSetDefaultTableModel;
import ui_donetests_components.DisplayAllExamineesJDialog;
import ui_searchRecord.SearchRecordSQLBased;
import javax.swing.UIManager;
import java.awt.Color;

public class ViewTestsMenuPanel extends JPanel {

	//finals:
	private final String RECORD_NAME = "מבחן";
		
	//Object members:
	private Table table = new TblTests();
	
	//Components:
	private CustomizedJTable jtable;
	private JButton addExaminees;
	private PanelManagingRecord panelManagingRecord;
	
	
	public ViewTestsMenuPanel() {
		initJTable();
		initGUI();
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
		
		
		
		JPanel panelSearch = new SearchRecordSQLBased(table);
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
		addExaminees.addActionListener(AddGradesActions.getAddGradesAction(jtable));
		addExaminees.setEnabled(false);
		addExaminees.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelTestActions.add(addExaminees);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panelTestActions.add(verticalStrut_1);
		
		JButton buttonViewTested = new JButton("<html>\r\n\u05D4\u05E6\u05D2\u05EA \u05DB\u05DC \u05D4\u05E0\u05D1\u05D7\u05E0\u05D9\u05DD<br>\r\n\u05E2\u05D1\u05D5\u05E8 \u05DE\u05D1\u05D7\u05DF \u05D6\u05D4\r\n</html>");
		buttonViewTested.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int testid = (int) jtable.getValueAt(jtable.getSelectedRow(), 0);
				new DisplayAllExamineesJDialog(testid);
			}
		});

		buttonViewTested.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelTestActions.add(buttonViewTested);
		
		JPanel panelTable = new JPanel();
		panelTable.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u05D8\u05D1\u05DC\u05EA \u05D7\u05DE\u05E9\u05EA \u05D4\u05DE\u05D1\u05D7\u05E0\u05D9\u05DD \u05D4\u05D0\u05D7\u05E8\u05D5\u05E0\u05D9\u05DD", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		panelTable.setLayout(new BorderLayout(0, 0));
		
		
		
		JScrollPane scrollPane = new JScrollPane(jtable);
		scrollPane.setBorder(null);
		
		panelTable.add(scrollPane);
		
		panelCenter.add(panelTable, BorderLayout.CENTER);
		
		panelManagingRecord = new PanelManagingRecord(jtable, RECORD_NAME, Views.Tests);
		panelTop.add(panelManagingRecord, BorderLayout.CENTER);
	}
	
	private void initJTable(){
		TblTests table = new TblTests(){
			@Override
			public String getSelectAllScript() {
				return "SELECT rowid,* FROM Tests ORDER BY rowid DESC LIMIT 5";
			};
		};
		
		jtable = new CustomizedJTable(new ResultSetDefaultTableModel(table));
		setJTableSelectionAction();
	}
	
	private void setJTableSelectionAction(){
		jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				boolean isSelectionEmpty = jtable.getSelectionModel().isSelectionEmpty();
				panelManagingRecord.setDeleteAndModifyEnable(!isSelectionEmpty);
				addExaminees.setEnabled(!isSelectionEmpty);
			}
		});
	}

}
