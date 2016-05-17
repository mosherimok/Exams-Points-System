package ui_components;

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

public class CustomizedJTable extends JTable{

	public CustomizedJTable(DefaultSqlTableModel tableModel) {
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
	
	@Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);
        int rendererWidth = component.getPreferredSize().width;
        TableColumn tableColumn = getColumnModel().getColumn(column);
        tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
        return component;
     }
	
	public void updateJTableData(){
		if(getModel() instanceof DefaultSqlTableModel){
			((DefaultSqlTableModel)getModel()).refreshData();
			((DefaultSqlTableModel)getModel()).fireTableDataChanged();
			System.out.println("Refresh JTable data");
		}
	}
 
	
}
