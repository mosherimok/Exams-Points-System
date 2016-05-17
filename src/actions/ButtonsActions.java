package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import actions.MV_Factory.Views;
import database.Condition;
import database.DatabaseActions;
import database.DatabaseUpdatingScripts;
import exceptions.InvalidStructure;
import mvc_dialogs.Controller;
import mvc_dialogs.Model;
import mvc_dialogs.View;
import tablesStructures.TableStructure;
import ui_components.DefaultSqlTableModel;
import ui_components.CustomizedJTable;

public class ButtonsActions {

	protected CustomizedJTable jtable;
	private MV_Factory MVfactory;
	/**
	 * Add and Modify dialogs View (Mvc)
	 */
	
	/*public ButtonsActions(Views view){
		this.MVfactory = new MV_Factory(view);
	}*/
	
	public ButtonsActions(CustomizedJTable jtable,Views view){
		this.jtable = jtable;
		this.MVfactory = new MV_Factory(view);
	}
	
	
	public final ActionListener getAddAction(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				View view = MVfactory.getView();
				Model model = MVfactory.getAddModel();
//				if(jtable!=null){
					Controller controller = new Controller(view, model,jtable);
//				}
//				else{
//					Controller controller = new Controller(view, model);
//				}
			}
		};
	}
	
	public final ActionListener getModifyAction(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = jtable.getSelectedRow();
				TableStructure oldStructure = ((DefaultSqlTableModel)jtable.getModel()).
						getRowStructure(selectedRow);
				
				View view = MVfactory.getView();
				Model model;
				try {
					model = MVfactory.getModifyModel(oldStructure);
//					if(jtable!=null){
						Controller controller = new Controller(view, model,jtable);
//					}
//					else{
//						Controller controller = new Controller(view, model);
//					}
				} catch (InvalidStructure ex) {
					ex.printStackTrace();
				}
			}
		};
	}
	
	public final ActionListener getDeleteAction(){
		return new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "האם אתה בטוח שברצונך למחוק רשומה זו?","התרעה לפני מחיקה",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.CANCEL_OPTION)
					return;
				int selectedRow = jtable.getSelectedRow();
				TableStructure structure = ((DefaultSqlTableModel)jtable.getModel()).
						getRowStructure(selectedRow);
				
				Condition condition = new Condition(structure.getPrimaryKey());
				
				String script = DatabaseUpdatingScripts.deleteFrom(MVfactory.getViewType().toString(),
						condition);
				try {
					DatabaseActions.executeUpdate(script);
					jtable.updateJTableData();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		};
	}
}
