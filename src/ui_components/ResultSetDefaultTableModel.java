package ui_components;

import java.sql.SQLException;

import database.DatabaseActions;
import exceptions.InvalidStructure;
import tables.Table;
import tablesStructures.TableStructure;

public class ResultSetDefaultTableModel extends DefaultSqlTableModel{

	private static final long serialVersionUID = 1L;
	private Table sqlTable;
	
	public ResultSetDefaultTableModel(Table table) {
		this.sqlTable=table;
		refreshData();
		initColumnsLabels();
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
	private void initColumnsLabels(){
		for(String label : sqlTable.getColumnsLabels()){
			columnIdentifiers.addElement(label);
		}
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		return ((TableStructure)dataVector.get(row)).getValues()[column];
	}
	
	@Override
	public void refreshData(){
		try {
			dataVector = DatabaseActions.getAllTableData(sqlTable);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public TableStructure getRowStructure(int row){
		TableStructure struct = (TableStructure) dataVector.get(row);
		return struct;
		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void replaceRow(int row, TableStructure structure) throws Exception {
		if(!structure.getTableName().equals(sqlTable.getTableName()))
			throw new InvalidStructure("Given mismatch table structure.\nExpected: "+
						sqlTable.getTableName() + " But found " + structure.getTableName());
		
		dataVector.remove(row);
		dataVector.add(row, structure);
	}

}
