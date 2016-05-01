package ui_menuPanels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import actions.TestsCategoriesActions;
import tables.TableGetter;
import tables.TblTestsCategories;
import ui_components.AbstractJPanel;


import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JButton;

public class TestsCategoriesPnl extends AbstractJPanel {

	private static final long serialVersionUID = 1L;

	public TestsCategoriesPnl(){
		super(TableGetter.getTable(TblTestsCategories.class));
	}
	
	@Override
	protected void initGUI(){
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTitle = new JPanel();
		add(pnlTitle, BorderLayout.NORTH);
		
		JLabel label = new JLabel("\u05E1\u05D5\u05D2\u05D9 \u05DE\u05D1\u05D7\u05E0\u05D9\u05DD");
		pnlTitle.add(label);
		
		JPanel pnlContent = new JPanel();
		
		Dimension tableDimenstion = table.getPreferredSize();
		pnlContent.setLayout(new BorderLayout(0, 0));
		JScrollPane tableScroller = new JScrollPane(table);
		tableScroller.setPreferredSize(new Dimension(tableDimenstion.width,
				table.getRowHeight()*table.getRowCount()+1));
		pnlContent.add(tableScroller);
		
		add(pnlContent, BorderLayout.CENTER);
		
		JPanel pnlButtons = new JPanel();
		add(pnlButtons, BorderLayout.SOUTH);
		
		btnModify = new JButton("\u05E9\u05D9\u05E0\u05D5\u05D9 \u05E1\u05D5\u05D2 \u05DE\u05D1\u05D7\u05DF \u05D6\u05D4");
		btnModify.addActionListener(actionListener);

		btnModify.setEnabled(false);
		pnlButtons.add(btnModify);
		
		btnDelete = new JButton("\u05DE\u05D7\u05D9\u05E7\u05EA \u05E1\u05D5\u05D2 \u05DE\u05D1\u05D7\u05DF \u05D6\u05D4");
//		btnDelete.addActionListener(actionListener);
		
		btnDelete.setEnabled(false);
		pnlButtons.add(btnDelete);
		
		btnAdd = new JButton("\u05D4\u05D5\u05E1\u05E4\u05EA \u05E1\u05D5\u05D2 \u05DE\u05D1\u05D7\u05DF \u05D7\u05D3\u05E9");
		btnAdd.addActionListener(actionListener);

		pnlButtons.add(btnAdd);
	
	}

	@Override
	protected void initButtonsActionCommand() {
		btnAdd.setActionCommand(ADD);
		btnModify.setActionCommand(MODIFY);
		btnDelete.setActionCommand(Delete);
	}

	@Override
	protected void initActionListeners() {
		TestsCategoriesActions actions = new TestsCategoriesActions();
		actionListener.addAction(ADD, actions.getAddAction());
		actionListener.addAction(MODIFY,  actions.getModifyAction(table));
//		actionListener.addAction(Delete,  actions.getDeleteAction(table));
	}

}
