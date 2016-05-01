package ui_components;

import javax.swing.table.DefaultTableModel;

import database.DatabaseDataFetching;
import tables.Table;
import tablesStructures.TableStructure;

public class ResultSetDefaultTableModel extends DefaultTableModel{

	private static final long serialVersionUID = 1L;
	
	public ResultSetDefaultTableModel(Table table) {
		refreshData(table);
		initColumnsLabels(table);
	}
	
	/*public String getColumnName(int col){
		try {
			return rsmd.getColumnName(col+1); // rsmd counts from 1. tableModel counts from 0.
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
	/*public Class<?> getColumnClass(int col){
		try {
			int coltype = rsmd.getColumnType(col+1);
			switch(coltype){
			case java.sql.Types.INTEGER:
				return Integer.TYPE;
			case java.sql.Types.DECIMAL:
				return Double.TYPE;
			case java.sql.Types.VARCHAR:
				return String.class;
			case java.sql.Types.DATE:
				return SimpleDateFormat.class;
			default:
				return Object.class;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Object.class;
	}*/
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private void initColumnsLabels(Table table){
		for(String label : table.getColumnsLabels()){
			columnIdentifiers.addElement(label);
		}
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		return ((TableStructure)dataVector.get(row)).getValues()[column];
	}
	
	public void refreshData(Table table){
		dataVector = DatabaseDataFetching.getAllTableData(table.getTableName(),table.getTableStructureName());
	}

	public TableStructure getRowStructure(int row){
		TableStructure struct = (TableStructure) dataVector.get(row);
		return struct;
		
	}
	
	public void replaceRow(int selectedRow, Object[] newValues) throws Exception {
		if(newValues.length!=columnIdentifiers.size())
			throw new Exception("Mismatch between expected and actual new values");
		for(int i=0;i<columnIdentifiers.size();i++){
			if(! newValues[i].getClass().equals(getValueAt(selectedRow, i).getClass()))
				throw new Exception("Data type of index " + i + " is not fit to column's data type");
			setValueAt(newValues[i],selectedRow,i);
		}
	}

}
