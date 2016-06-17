package ui_searchRecord;

import java.util.Vector;

import components_utility.DefaultSqlTableModel;
import tables.Table;
import tablesStructures.TableStructure;

public class TableModelFoundedRecords extends DefaultSqlTableModel{

	
	public TableModelFoundedRecords(Table table,Object[][] data){
		dataVector = new Vector<TableStructure>();
		initColumnsIdentifiers(table.getColumnsLabels());
		initData(data,table);
	}
	
	private void initData(Object[][] data,Table table){
		for(Object[] row:data){
			dataVector.addElement(table.createTableStructure(row));
		}
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		return ((TableStructure)dataVector.get(row)).getValues()[column];
	};
	
	@SuppressWarnings("unchecked")
	private void initColumnsIdentifiers(String[] labels){
		for(String label: labels){
			System.out.println(label);
			columnIdentifiers.addElement(label);
		}
	}
	
	@Override
	public TableStructure getRowStructure(int row){
		return (TableStructure)dataVector.get(row);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public void refreshData() {
		/*try {
			replaceRow(jtable.getSelectedRow(), controller.getNewTableStructure());
			fireTableDataChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void replaceRow(int row, TableStructure structure){
		dataVector.remove(row);
		dataVector.add(row, structure);
	}
	
}
