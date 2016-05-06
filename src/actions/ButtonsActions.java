package actions;

import javax.swing.JTable;

import actionListeners.Action;
import actions.MV_Factory.Views;
import exceptions.InvalidStructure;
import mvc_dialogs.Controller;
import mvc_dialogs.Model;
import mvc_dialogs.View;
import tables.Table;
import tablesStructures.TableStructure;
import ui_components.ResultSetDefaultTableModel;

public class ButtonsActions {

	protected JTable jtable;
	private MV_Factory MVfactory;
	/**
	 * Add and Modify dialogs View (Mvc)
	 */
//	protected View VIEW;
//	private Table sqlTable;
	
	public ButtonsActions(JTable table,Views view){
		this.jtable = table;
		this.MVfactory = new MV_Factory(view);
		/*this.VIEW = view;
		this.sqlTable = sqlTable;*/
	}
	
//	public abstract boolean addAction();
//	public abstract boolean modifyAction();
	
	public final Action getAddAction(){
		return new Action() {
			
			@Override
			public void perform() {
//				boolean result = addAction();
				View view = MVfactory.getViewByType();
				Model model = MVfactory.getAddModelByType();
				Controller controller = new Controller(view, model);
				if(controller.isNeedToRefreshData())
					updateJTable();
			}
		};
	}
	
	public final Action getModifyAction(){
		return new Action() {
			
			@Override
			public void perform() {
//				boolean result = modifyAction();
				int selectedRow = jtable.getSelectedRow();
				TableStructure oldStructure = ((ResultSetDefaultTableModel)jtable.getModel()).
						getRowStructure(selectedRow);
				
				View view = MVfactory.getViewByType();
				Model model;
				try {
					model = MVfactory.getModifyModelByType(oldStructure);
					Controller controller = new Controller(view, model);
					if(controller.isNeedToRefreshData())
						updateJTable();
				} catch (InvalidStructure e) {
					e.printStackTrace();
				}
			}
		};
	}
	
	
	private void updateJTable(){
		((ResultSetDefaultTableModel)jtable.getModel()).refreshData();
		jtable.getParent().getParent().repaint();
	}
}
