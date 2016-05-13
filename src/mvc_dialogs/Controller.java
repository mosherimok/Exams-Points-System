package mvc_dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import exceptions.ImproperFieldsValues;
import exceptions.InvalidStructure;
import tablesStructures.TableStructure;

public class Controller implements ActionListener{

	private View view;
	private Model model;
	private boolean needToRefreshJTableData = false;
	
	public boolean isNeedToRefreshJTableData() {
		return needToRefreshJTableData;
	}

	public Controller(View view,Model model){
		this.view = view;
		this.model = model;
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
		needToRefreshJTableData = false;
//		try {
//			view.validateFieldsProperiety();
//		} catch (ImproperFieldsValues e2) {
//			e2.printStackTrace();
//		}
		model.setStructure(view.createStructureFromFields());
		try {
			model.updateDatabase();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return;
		}
		view.dispose();
		needToRefreshJTableData = true;
	}
	
	
	
}
