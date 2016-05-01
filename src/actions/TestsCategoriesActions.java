package actions;


import javax.swing.JOptionPane;
import javax.swing.JTable;

import actionListeners.Action;
import database.Condition;
import database.DatabaseActions;
import database.DatabaseUpdatingScripts;
import mvc_dialogs.Controller;
import mvc_dialogs.View;
import mvc_testsCategories.ModelAddTestCategory;
import mvc_testsCategories.ModelModifyTestCategory;
import mvc_testsCategories.ViewTestCategoryRecord;
import tablesStructures.TestCategory;
import ui_components.ResultSetDefaultTableModel;

public class TestsCategoriesActions {

	private Controller controller;
	private final View VIEW = new ViewTestCategoryRecord();
	
	private JTable table;
	
	public TestsCategoriesActions(JTable table){
		this.table = table;
	}
	
	public  Action getAddAction(){
		return new Action() {
			
			@Override
			public void perform() {
				controller = new Controller(VIEW, new ModelAddTestCategory());
			}
		};
	}
	
	public Action getModifyAction(){
		return new Action() {
			
			@Override
			public void perform() {
				ResultSetDefaultTableModel model =
						(ResultSetDefaultTableModel)table.getModel();
				
				TestCategory tc = (TestCategory) model.getRowStructure(table.getSelectedRow());
				
				controller = new Controller(VIEW, new ModelModifyTestCategory(tc));
			}
		};
	}
	
	/*public Action getDeleteAction(JTable table){
		 final String TABLE_NAME = "TestsCategories";
		 final String PRIMARY_KEY = "CategoryName";
		
		return new Action() {
			
			@Override
			public void perform() {
				int choice = JOptionPane.showConfirmDialog(null, "האם האתה בטוח שברצונך למחוק את סוג מבחן זה?",
						"התראה",JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.NO_OPTION)
					return;
					
				try {
					ResultSetDefaultTableModel model =
							(ResultSetDefaultTableModel)table.getModel();
					String selectedRowPrimaryKey = 
							(String)model.getValueAt(table.getSelectedRow(), 0);
					System.out.println(selectedRowPrimaryKey);
					Condition condition = new Condition();
					condition.addCondition(PRIMARY_KEY,selectedRowPrimaryKey);
					DatabaseActions.executeUpdate(DatabaseUpdatingScripts.deleteFrom(TABLE_NAME, condition));
					
//					model.removeRow(table.getSelectedRow());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		};
	}*/
	
}
