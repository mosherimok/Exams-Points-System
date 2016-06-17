package components_utility;

import java.sql.SQLException;

import database.DatabaseUtilities;
//import database.DatabaseActions;
import exceptions.InvalidStructure;
import tables.Table;
import tablesStructures.TableStructure;

public class ResultSetDefaultTableModel extends DefaultSqlTableModel{

	private static final long serialVersionUID = 1L;
	private Table sqlTable;
	
	public ResultSetDefaultTableModel(Table table) {
		this.sqlTable=table;
		initColumnsLabels();
		refreshData();
	}
	
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
			dataVector = DatabaseUtilities.getAllTableData(sqlTable);
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
