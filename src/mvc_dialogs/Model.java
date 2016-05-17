package mvc_dialogs;

import java.sql.SQLException;

import tablesStructures.TableStructure;

public abstract class Model {
	
//	public final Table table;
	public TableStructure structure;
	
	
	public Model(){
//		this.table = table;
	}
	
	public void setStructure(TableStructure structure){
		this.structure = structure;
	}
	
	/**
	 * Updates the DB accordingly to button's role - Add or Modify.
	 */
	public abstract void updateDatabase () throws SQLException;
	
}
