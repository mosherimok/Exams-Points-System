package actions;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import actionListeners.Action;
import database.Condition;
import database.DatabaseActions;
import database.DatabaseUpdatingScripts;
import mvc_dialogs.Controller;
import mvc_dialogs.View;
import tables.Table;
import tables.TableGetter;
import tables.TblStudents;
import tablesStructures.Student;
import ui_components.ResultSetDefaultTableModel;
import mvc_students.*;

public class StudentsActions extends ButtonsActions{

	
	public StudentsActions(Table sqlTable,JTable table){
		super(sqlTable,table,new ViewStudentRecord());
	}
	
	public Action getModifyAction(){
		
		return new Action(){
			public void perform(){
				int selectedRowIndex = table.getSelectedRow();
				
				Student old = (Student)((ResultSetDefaultTableModel)table.getModel()).
						getRowStructure(selectedRowIndex);
				Controller controller = new Controller(VIEW, new ModelModifyStudent(old));
//				ModifyStudentDetailsJDialog dialog = new ModifyStudentDetailsJDialog(oldValues);
				//TODO: decide wether update the ui table manualy or not at all.
			}	
		};
	}

	@Override
	public boolean addAction() {
		int selectedRowIndex = table.getSelectedRow();
		
		Student old = (Student)((ResultSetDefaultTableModel)table.getModel()).
				getRowStructure(selectedRowIndex);
		Controller controller = new Controller(VIEW, new ModelModifyStudent(old));
		return controller.isNeedToRefreshData();
	}

	@Override
	public boolean modifyAction() {
		int selectedRowIndex = table.getSelectedRow();
		
		Student old = (Student)((ResultSetDefaultTableModel)table.getModel()).
				getRowStructure(selectedRowIndex);
		Controller controller = new Controller(VIEW, new ModelModifyStudent(old));
		return controller.isNeedToRefreshData();
	}
	
	/*public Action getDeleteAction(JTable table){
		return new Action(){
			public void perform(){
				int choice = JOptionPane.showConfirmDialog(null,
						"Do you sure you want"+ " delete this student?","Warning!",
						JOptionPane.OK_CANCEL_OPTION);
				if(choice==JOptionPane.CANCEL_OPTION)
					return;
				else{
					int selectedStudentID = (int) table.getValueAt(table.getSelectedRow(),0);
					Condition condition = new Condition();
					condition.addCondition("id", selectedStudentID);
					DatabaseActions.executeUpdate(DatabaseUpdatingScripts.deleteFrom("Students",condition));
					
					((ResultSetDefaultTableModel)table.getModel()).
					removeRow(table.getSelectedRow());
				}
			}	
		};
	}*/
	
}
