package ui_components;

import javax.swing.table.DefaultTableModel;

import tablesStructures.TableStructure;

public abstract class DefaultSqlTableModel extends DefaultTableModel{


	public abstract void refreshData();
	
	public abstract TableStructure getRowStructure(int row);
	
	public abstract Object getValueAt(int row, int column);

	public abstract void replaceRow(int row, TableStructure structure) throws Exception;
	
}
