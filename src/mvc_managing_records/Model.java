package mvc_managing_records;

import java.sql.SQLException;

import database.Condition;
import database.Database;
import database.DatabaseUpdatingScripts;
import exceptions.FieldsValuesException;
import exceptions.InvalidStructure;
import tablesStructures.TableStructure;

public abstract class Model {
	
	//Members:
	protected TableStructure structure;
	protected Condition condition;
	protected View view;
	public static enum Types{
		ADD,MODIFY
	}
	private Types type;
	
	public Types getType() {
		return type;
	}

	public Model(View view) {
		setView(view);
		this.type = Types.ADD;
	}
	
	public Model(View view, TableStructure oldStructure){
		setView(view);
		setStructure(oldStructure);
		condition = new Condition(oldStructure.getPrimaryKeyValue());
		this.type = Types.MODIFY;
	}
	
	public abstract void initFieldsFromOldStructure() throws InvalidStructure;
	
	public abstract TableStructure createStructureFromFields() throws FieldsValuesException;
	
	protected abstract boolean areFieldsValid();
	
	/**
	 * Updates the DB accordingly to button's role - Add or Modify.
	 */
	
	public void updateDatabase() throws SQLException {
		if(structure==null)
			throw new NullPointerException("Structure is null!");
		if(condition==null)
			throw new NullPointerException("Structure is null!");
		String script = DatabaseUpdatingScripts.updateTable(structure, condition);
		Database.executeUpdate(script);
	}
	
	/**
	 * Updates the DB accordingly to button's role - Add or Modify.
	 */
	
	public void insertToDatabase() throws SQLException {
		if(structure==null)
			throw new NullPointerException("Structure is null!");
		String script = DatabaseUpdatingScripts.insertIntoPreparedStatementScript(structure);
		Database.executeSinglePreparedStatement(script, structure.getValues());
	}
	
	public abstract void clearFields();
	
	//GS's
	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public TableStructure getStructure() {
		return structure;
	}

	public void setStructure(TableStructure structure){
		this.structure = structure;
	}
	
}
