package ui_tests_categories;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import actions.MV_Factory.Views;
import components_utility.MenuPanelView;
import components_utility.PanelManagingRecord;
import components_utility.ResultSetDefaultTableModel;
import tables.TblTestsCategories;

public class ViewTestsCategoriesPanel extends MenuPanelView {

	//finals:
	private static String RECORD_NAME = "סוג מבחן";
	
	//members:
	
	//Components:
	public PanelManagingRecord panelManagingRecord;
	private ModelMpTestsCategories model;
	
	
	/**
	 * Create the panel.
	 */
	public ViewTestsCategoriesPanel() {
		super(RECORD_NAME,new TblTestsCategories());
		initGUI();
		model = new ModelMpTestsCategories(this);
		model.setJTableSelectionAction();
	}
	
	private void initGUI(){
		setLayout(new BorderLayout(0, 0));
		
		getJtable().setDefaultTableModel(new ResultSetDefaultTableModel(super.getTable()));
		JPanel pnlTitle = new JPanel();
		add(pnlTitle, BorderLayout.NORTH);
		
		JLabel label = new JLabel("\u05E1\u05D5\u05D2\u05D9 \u05DE\u05D1\u05D7\u05E0\u05D9\u05DD");
		pnlTitle.add(label);
		
		JPanel pnlContent = new JPanel();
		pnlContent.setLayout(new BorderLayout(0, 0));
		
		pnlContent.add(new JScrollPane(super.getJtable()));
		
		add(pnlContent, BorderLayout.CENTER);
		
		panelManagingRecord = new PanelManagingRecord(getJtable(), RECORD_NAME, Views.TestsCategories);
		
		add(panelManagingRecord, BorderLayout.SOUTH);
	}

}
