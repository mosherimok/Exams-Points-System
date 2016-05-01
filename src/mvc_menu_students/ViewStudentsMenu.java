package mvc_menu_students;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import mvc_panels.View;
import tables.TableGetter;
import tables.TblStudents;
import ui_searchRecord.SearchRecord;

public class ViewStudentsMenu extends View{

	private JButton addButton;
	private JButton modifyButton;
	private JButton deleteButton;
	private JTable table = new JTable();
	
	public ViewStudentsMenu() {
		super(TableGetter.getTable(TblStudents.class),"תלמיד");
		initGUI();
		initTable(table);
	}
	
	private void initGUI() {
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
			deleteButton = new JButton("\u05DE\u05D7\u05D9\u05E7\u05EA \u05EA\u05DC\u05DE\u05D9\u05D3");
		    deleteButton.setEnabled(false);
//			btnDelete.addActionListener(actionListener);
			
			pnlActions.add(deleteButton);
			
			pnlActions.add(Box.createRigidArea(new Dimension(10,2)));
		}
		//Modify Student Button
		{
			modifyButton = 
					new JButton("\u05E2\u05E8\u05D9\u05DB\u05EA \u05E4\u05E8\u05D8\u05D9 \u05EA\u05DC\u05DE\u05D9\u05D3");
	
//			modifyButton.addActionListener(actionListener);
			
		    modifyButton.setEnabled(false);
			pnlActions.add(modifyButton);
			pnlActions.add(Box.createRigidArea(new Dimension(10, 2)));
		}
		
		// Add Button
		{
			addButton = new JButton("\u05D4\u05D5\u05E1\u05E4\u05EA \u05EA\u05DC\u05DE\u05D9\u05D3");
	
//			addButton.addActionListener(actionListener);

			pnlActions.add(addButton);
		}
		
		
		
		JPanel pnlTable = new JPanel();
		add(pnlTable, BorderLayout.CENTER);
		
		pnlTable.setLayout(new BorderLayout(0, 0));
		
		pnlTable.add(new JScrollPane(table));
	
		
		// JPanel SearchRecord
		String[] complexCombinations = {"f_name + l_name"};
//		JPanel panel = new SearchRecord(table,complexCombinations);
//		pnlAbove.add(panel);
	}



	@Override
	protected void initActionListeners() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public JButton getAddButton() {
		return addButton;
	}



	@Override
	public JButton getModifyButton() {
		return modifyButton;
	}



	@Override
	public JButton getDeleteButton() {
		return deleteButton;
	}
	
	
}
