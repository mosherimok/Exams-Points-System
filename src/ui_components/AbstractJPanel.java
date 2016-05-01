package ui_components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import actionListeners.Action;
import actionListeners.MapActionListener;
import database.Condition;
import database.DatabaseActions;
import database.DatabaseUpdatingScripts;
import tables.Table;
import tablesStructures.TableStructure;

public abstract class AbstractJPanel extends JPanel {

	private static final long serialVersionUID = 1L;

		//Table name:
		protected final Table TBL;
	
	//All components:
		protected JTable table;
		protected JButton btnAdd;
		protected JButton btnModify;
		protected JButton btnDelete;
		
	//Buttons Action Listeners map:
		protected MapActionListener actionListener;
		
	//All buttons action-commands:
		protected final String ADD = "AddButton";
		protected final String MODIFY = "ModifyButton";
		protected final String Delete = "DeleteButton";
		
		public AbstractJPanel(final Table table){
			this.TBL = table;
			actionListener = new MapActionListener();
			initTable();
			initGUI();
			initButtonsActionCommand();
			initActionListeners();
			deleteActionListener();
		}
		
		protected abstract void initGUI();			
		
		protected abstract void initButtonsActionCommand();
		
		protected abstract void initActionListeners();
		
		protected void deleteActionListener(){
			btnDelete.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(JOptionPane.showConfirmDialog(null, "האם אתה בטוח שברצונך למחוק רשומה זו?","התרעה לפני מחיקה",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.CANCEL_OPTION)
						return;
					int selectedRow = table.getSelectedRow();
					TableStructure structure = ((ResultSetDefaultTableModel)table.getModel()).
							getRowStructure(selectedRow);
					Condition condition = new Condition();
					for(int i=0;i<structure.getPrimaryKeyName().length;i++)
						condition.addCondition(structure.getPrimaryKeyName()[i], structure.getPrimaryKeyValue()[i]);
					String script = DatabaseUpdatingScripts.deleteFrom(TBL.getTableName(),
							condition);
					try {
						DatabaseActions.executeUpdate(script);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		
		protected void initTable(){
			ResultSetDefaultTableModel rsdtm = 
					new ResultSetDefaultTableModel(TBL);
			table = new JTable(rsdtm);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (table.getSelectedRow()!=-1){
						btnModify.setEnabled(true);
						btnDelete.setEnabled(true);
					}
				}
			});
		}
		
		
		public void refreshDataFromDB(){
			((ResultSetDefaultTableModel)table.getModel()).refreshData(TBL);
			table.getParent().getParent().repaint();
		}
	
}
