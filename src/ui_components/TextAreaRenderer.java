package ui_components;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

public class TextAreaRenderer extends JTextArea implements TableCellRenderer{
	
	public TextAreaRenderer(){
		
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setText(value.toString());
		setLineWrap(true);
		setWrapStyleWord(true);
		int fontHeight = this.getFontMetrics(this.getFont()).getHeight();
		int textLength = this.getText().length();
		int lines = textLength / this.getColumns() +1;//+1, cause we need at least 1 row.           
		int height = fontHeight * lines;            
		table.setRowHeight(row, height);
		
		return this;
	}

}
