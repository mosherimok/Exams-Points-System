package components_utility;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.util.Enumeration;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class CustomizedJTable extends JTable{

	public CustomizedJTable(DefaultTableModel tableModel) {
		super(tableModel);
		
		setFonts();
		
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		setHeadersStyle();
		
		setCellsStyle();
		
	}
	
	public CustomizedJTable() {
		setFonts();
		
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		setHeadersStyle();
		
		setCellsStyle();
	}
	
	public void setDefaultTableModel(DefaultTableModel dataModel) {
		setModel(dataModel);
		setCellsStyle();
	}
	
	private void setFonts(){
		Font columnsFont = new Font("Ariel", Font.PLAIN, 16);
		setFont(columnsFont);
		Font headerFont = new Font("Aharoni Bold", Font.PLAIN, 16);
		getTableHeader().setFont(headerFont);
	}
	
	private void setHeadersStyle(){
		((DefaultTableCellRenderer)getTableHeader().getDefaultRenderer()).
		setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	private void setCellsStyle(){
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		Enumeration<TableColumn> columns = getColumnModel().getColumns();
		while(columns.hasMoreElements()){
			columns.nextElement().setCellRenderer(dtcr);
		}
		setRowHeight(getFont().getSize()+10);
	}
	
	public void resizeColumnWidth() {
	    final TableColumnModel columnModel = getColumnModel();
	    for (int column = 0; column < getColumnCount(); column++) {
	        int width = 50; // Min width
	        for (int row = 0; row < getRowCount(); row++) {
	            TableCellRenderer renderer = getCellRenderer(row, column);
	            Component comp = prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
	
	public void updateJTableData(){
		if(getModel() instanceof DefaultSqlTableModel){
			((DefaultSqlTableModel)getModel()).refreshData();
			((DefaultSqlTableModel)getModel()).fireTableDataChanged();
			System.out.println("Refresh JTable data");
		}
		else
			System.out.println("Table model must be DefaultSqlTableModel or inherit from it.");
	}
 
	
}
