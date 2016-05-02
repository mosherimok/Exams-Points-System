package mvc_dialogs;

import javax.swing.JButton;
import javax.swing.JDialog;

import exceptions.ImproperFieldsValues;
import exceptions.InvalidStructure;
import tablesStructures.TableStructure;

public abstract class View extends JDialog{

	public final String ADD_BUTTON_TEXT;
	public final String MODIFY_BUTTON_TEXT;
	
	public JButton okButton = new JButton("OK");
	
	public View(String recordName){
		this.ADD_BUTTON_TEXT = " десу " + recordName;
		this.MODIFY_BUTTON_TEXT = " щоеш " + recordName;
		
	}
	
	public void initJDialog(){
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setVisible(true);
	}
	
	public abstract TableStructure createStructureFromFields() /*throws InvalidFieldsValues*/;
	
	public abstract void putStructureIntoFields(TableStructure structure) throws InvalidStructure;

//	public abstract void validateFieldsProperiety() throws ImproperFieldsValues;
	
}
