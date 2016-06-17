package components_utility;

import javax.swing.JPanel;

import tables.Table;

public abstract class MenuPanelView extends JPanel{
	
	public final String RECORD_NAME;
	public final Table table;
	public final CustomizedJTable jtable = new CustomizedJTable();
	
	public MenuPanelView(String rECORD_NAME, Table tABLE) {
		RECORD_NAME = rECORD_NAME;
		table = tABLE;
	}
	

	public String getRECORD_NAME() {
		return RECORD_NAME;
	}



	public Table getTable() {
		return table;
	}



	public CustomizedJTable getJtable() {
		return jtable;
	}
	
	
	
}
