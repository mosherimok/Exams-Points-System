package ui_components;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.util.Enumeration;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import tables.Table;

public class JTableMenuPanels extends JTable{

	public JTableMenuPanels(Table table) {
		super(new ResultSetDefaultTableModel(table));
		setFonts();
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
	
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		Enumeration<TableColumn> columns = getColumnModel().getColumns();
		while(columns.hasMoreElements()){
			columns.nextElement().setCellRenderer(dtcr);
		}
		((DefaultTableCellRenderer)getTableHeader().getDefaultRenderer()).
			setHorizontalAlignment(SwingConstants.RIGHT);
		
		setRowHeight(getFont().getSize()+10);
	}
	
	private void setFonts(){
		Font columnsFont = new Font("Ariel", Font.PLAIN, 16);
		setFont(columnsFont);
		Font headerFont = new Font("Aharoni Bold", Font.PLAIN, 16);
		getTableHeader().setFont(headerFont);
	}
	
	@Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);
        int rendererWidth = component.getPreferredSize().width;
        TableColumn tableColumn = getColumnModel().getColumn(column);
        tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
        return component;
     }
 
	
}
