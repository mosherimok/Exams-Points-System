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
				View view = MVfactory.getView();
				Model model = MVfactory.getAddModel();
				Controller controller = new Controller(view, model);
				if(controller.isNeedToRefreshJTableData())
					updateJTable();
				else
					System.out.println("NOT NEED TO REFRESH in table ");
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
				
				View view = MVfactory.getView();
				Model model;
				try {
					model = MVfactory.getModifyModel(oldStructure);
					Controller controller = new Controller(view, model);
					if(controller.isNeedToRefreshJTableData())
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
