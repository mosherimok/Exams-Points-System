package ui_components;

import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

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
		protected final Table table;
	
	//All components:
		protected JTable jtable;
		protected JButton btnAdd;
		protected JButton btnModify;
		protected JButton btnDelete = new JButton();
		
	//Buttons Action Listeners map:
		protected MapActionListener mapActionListener;
		
	//All buttons action-commands:
		protected final String ADD = "AddButton";
		protected final String MODIFY = "ModifyButton";
		protected final String Delete = "DeleteButton";
		
		public AbstractJPanel(final Table table){
			this.table = table;
			mapActionListener = new MapActionListener();
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
					int selectedRow = jtable.getSelectedRow();
					TableStructure structure = ((ResultSetDefaultTableModel)jtable.getModel()).
							getRowStructure(selectedRow);
					Condition condition = new Condition(structure.getPrimaryKey());
//					for(int i=0;i<structure.getPrimaryKeyName().length;i++)
//						condition.addCondition(structure.getPrimaryKeyName()[i], structure.getPrimaryKeyValue()[i]);
					String script = DatabaseUpdatingScripts.deleteFrom(table.getTableName(),
							condition);
					try {
						DatabaseActions.executeUpdate(script);
						refreshDataFromDB();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		
		protected void initTable(){
			jtable = new JTableMenuPanels(table);
			jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					if (jtable.getSelectedRow()!=-1){
						btnModify.setEnabled(true);
						btnDelete.setEnabled(true);
					}
				}
			});
			
		
			
		}
		
		
		public void refreshDataFromDB(){
			((ResultSetDefaultTableModel)jtable.getModel()).refreshData();
			jtable.getParent().getParent().repaint();
			repaint();
			System.out.println("JTable data refreshed!");
		}
	
}
