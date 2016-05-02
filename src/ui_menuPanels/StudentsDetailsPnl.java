package ui_menuPanels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.GridLayout;

import ui_components.AbstractJPanel;
import ui_searchRecord.SearchRecord;

import javax.swing.JTable;

import actionListeners.MapActionListener;
import actions.StudentsActions;
import tables.TableGetter;
import tables.TblStudents;

public class StudentsDetailsPnl extends AbstractJPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the panel.
	 */
	
	public StudentsDetailsPnl(){
		super(TableGetter.getTable(TblStudents.class));
	}
	
	@Override
	protected void initButtonsActionCommand() {
		btnAdd.setActionCommand(ADD);
		btnModify.setActionCommand(MODIFY);
		btnDelete.setActionCommand(Delete);
	}

	@Override
	protected void initActionListeners() {
		StudentsActions actions = new StudentsActions(table);
		actionListener.addAction(ADD, actions.getAddAction());
		actionListener.addAction(MODIFY,actions.getModifyAction());
//		actionListener.addAction(Delete,  actions.getDeleteAction(table));
	}

	@Override
	protected void initGUI() {
		actionListener = new MapActionListener();
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlAbove = new JPanel();
		add(pnlAbove, BorderLayout.NORTH);
		pnlAbove.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel pnlTitle = new JPanel();
		pnlAbove.add(pnlTitle);
		
		JLabel lblStudentsDetails = new JLabel("Students Details");
		pnlTitle.add(lblStudentsDetails);
		
		JPanel pnlActions = new JPanel();
		pnlAbove.add(pnlActions);
		
		// Delete Button
		{
			btnDelete = new JButton("\u05DE\u05D7\u05D9\u05E7\u05EA \u05EA\u05DC\u05DE\u05D9\u05D3");
			btnDelete.setEnabled(false);
//			btnDelete.addActionListener(actionListener);
			
			pnlActions.add(btnDelete);
			
			pnlActions.add(Box.createRigidArea(new Dimension(10,2)));
		}
		//Modify Student Button
		{
			btnModify = 
					new JButton("\u05E2\u05E8\u05D9\u05DB\u05EA \u05E4\u05E8\u05D8\u05D9 \u05EA\u05DC\u05DE\u05D9\u05D3");
	
			btnModify.addActionListener(actionListener);
			
			btnModify.setEnabled(false);
			pnlActions.add(btnModify);
			pnlActions.add(Box.createRigidArea(new Dimension(10, 2)));
		}
		
		// Add Button
		{
			btnAdd = new JButton("\u05D4\u05D5\u05E1\u05E4\u05EA \u05EA\u05DC\u05DE\u05D9\u05D3");
	
			btnAdd.addActionListener(actionListener);

			pnlActions.add(btnAdd);
		}
		
		JPanel pnlTable = new JPanel();
		add(pnlTable, BorderLayout.CENTER);
		
		pnlTable.setLayout(new BorderLayout(0, 0));
		
		pnlTable.add(new JScrollPane(table));
	
		
		// JPanel SearchRecord
		String[] complexCombinations = {"f_name + l_name"};
		JPanel panel = new SearchRecord(table,complexCombinations);
		pnlAbove.add(panel);
		
	}
	
}
