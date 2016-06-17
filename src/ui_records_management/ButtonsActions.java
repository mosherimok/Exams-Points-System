package ui_records_management;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import components_utility.CustomizedJTable;
import components_utility.DefaultSqlTableModel;
import database.Condition;
import database.Database;
//import database.DatabaseActions;
import database.DatabaseUpdatingScripts;
import exceptions.InvalidStructure;
import mvc_managing_records.Controller;
import mvc_managing_records.Model;
import tablesStructures.TableStructure;
import ui_records_management.ModelViewFactory.Views;

public class ButtonsActions {

	protected CustomizedJTable jtable;
	private ModelViewFactory MVfactory;
	/**
	 * Add and Modify dialogs View (Mvc)
	 */
	
	public ButtonsActions(CustomizedJTable jtable,Views view){
		this.jtable = jtable;
		this.MVfactory = new ModelViewFactory(view);
	}
	
	
	public final ActionListener getAddAction(){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Model model = MVfactory.getAddModel();
				Controller controller = new Controller(model,jtable);
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
				
				Model model;
				try {
					model = MVfactory.getModifyModel(oldStructure);
					Controller controller = new Controller(model,jtable);
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
				
				Condition condition = new Condition(structure.getPrimaryKeyValue());
				
				String script = DatabaseUpdatingScripts.deleteFrom(MVfactory.getViewType().toString(),
						condition);

				Database.executeUpdate(script);
				jtable.updateJTableData();

			}
		};
	}
}
