package mvc_dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import exceptions.InvalidStructure;
import tablesStructures.TableStructure;
import ui_components.CustomizedJTable;

public class Controller implements ActionListener{

	private View view;
	private Model model;
	private CustomizedJTable jtable;
	private boolean needToUpdateJTable;
	

	public Controller(View view,Model model){
		this.view = view;
		this.model = model;
		initOkButton();
	}
	
	public Controller(View view,Model model,CustomizedJTable jtableToRefresh){
		this.view = view;
		this.model = model;
		this.jtable = jtableToRefresh;
		initOkButton();
	}
	
	private void initOkButton(){
		if(model instanceof AddRecord)
			view.okButton.setText(view.ADD_BUTTON_TEXT);
		else{
			view.okButton.setText(view.MODIFY_BUTTON_TEXT);
			try {
				view.putStructureIntoFields(model.structure);
			} catch (InvalidStructure e) {
				e.printStackTrace();
			}
		}
		
		view.okButton.addActionListener(this);
		
		view.initJDialog();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!view.fieldsAreNotEmpty()){
			JOptionPane.showMessageDialog(null, "בדוק שכל השדות תקינים");
			return;
		}
		model.setStructure(view.createStructureFromFields());
		try {
			model.updateDatabase();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return;
		}
		
		if(jtable!=null)
			jtable.updateJTableData();
		
		
		
		view.dispose();
		
		
	}
	
	public boolean isNeedToUpdateJTable(){
		return needToUpdateJTable;
	}
	
	public TableStructure getNewTableStructure(){
		return model.structure;
	}
	
	
}
