package actions;

import javax.swing.JTable;

import actionListeners.Action;
import mvc_dialogs.View;
import tables.Table;
import ui_components.ResultSetDefaultTableModel;

public abstract class ButtonsActions {

	protected JTable table;
	/**
	 * Add and Modify dialogs View (Mvc)
	 */
	protected final View VIEW;
	private Table sqlTable;
	
	public ButtonsActions(Table sqlTable, JTable table,View view){
		this.table = table;
		this.VIEW = view;
		this.sqlTable = sqlTable;
	}
	
	public abstract boolean addAction();
	public abstract boolean modifyAction();
	
	public final Action getAddAction(){
		return new Action() {
			
			@Override
			public void perform() {
				boolean result = addAction();
				if(result)
					updateJTable();
			}
		};
	}
	
	public final Action getModifyAction(){
		return new Action() {
			
			@Override
			public void perform() {
				boolean result = modifyAction();
				if(result)
					updateJTable();
			}
		};
	}
	
	
	private void updateJTable(){
		((ResultSetDefaultTableModel)table.getModel()).refreshData(sqlTable);
		table.getParent().getParent().repaint();
	}
}
