package actions;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import actionListeners.Action;
import database.Condition;
import database.DatabaseActions;
import database.DatabaseDataFetching;
import database.DatabaseUpdatingScripts;
import mvc_dialogs.Controller;
import mvc_dialogs.View;
import mvc_tests.ModelAddTest;
import mvc_tests.ModelModifyTest;
import mvc_tests.ViewTestRecord;
import tables.Table;
import tablesStructures.Test;
import ui_components.ResultSetDefaultTableModel;

public class TestsActions extends ButtonsActions{
	
	public TestsActions(Table sqlTable,JTable table){
		super(sqlTable,table,)
	}
	
	public Action getAddAction(){
		return new Action() {
			
			@Override
			public void perform() {
				String[] categories;
				try {
					categories = DatabaseDataFetching.getDataOfColumnIdentifier(
							"TestsCategories", "CategoryName",String.class);
					controller = new Controller(new ViewTestRecord(categories), new ModelAddTest());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		};
		
	}
	
	public Action getModifyAction(){
		return new Action() {
			
			@Override
			public void perform() {
				String[] categories;
				try {
					categories = DatabaseDataFetching.getDataOfColumnIdentifier(
							"TestsCategories", "CategoryName",String.class);
					Test oldTest = (Test) ((ResultSetDefaultTableModel)table.getModel()).
							getRowStructure(table.getSelectedRow());
					controller = new Controller(new ViewTestRecord(categories),
							new ModelModifyTest(oldTest));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		};
	}

	@Override
	public boolean addAction() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyAction() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*public Action getDeleteAction(JTable table){
		final String TABLE_NAME = "Tests";
		final String PRIMARY_KEY = "TestID";
		
		return new Action() {
			
			@Override
			public void perform() {
				try {
					int choice = JOptionPane.showConfirmDialog(null, "אתה בטוח שברצונך למחוק מבחן זה?","Warning",
							JOptionPane.YES_NO_OPTION);
					if(choice==JOptionPane.NO_OPTION)
						return;
					Condition condition = new Condition();
					condition.addCondition(PRIMARY_KEY,table.getValueAt(table.getSelectedRow(),0));
					DatabaseActions.executeUpdate(DatabaseUpdatingScripts.deleteFrom(TABLE_NAME, condition));
//					((DefaultTableModel)table.getModel()).removeRow(table.getSelectedRow());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}*/

}
