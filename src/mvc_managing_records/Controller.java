package mvc_managing_records;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import components_utility.CustomizedJTable;
import exceptions.FieldsValuesException;
import mvc_managing_records.Model.Types;
import tablesStructures.TableStructure;

public class Controller{

	private View view;
	private Model model;
	private CustomizedJTable jtable;
	private boolean needToUpdateJTable;
	

	public Controller(Model model){
		this(model, null);
	}
	
	public Controller(Model model,CustomizedJTable jtableToRefresh){
		this.model = model;
		this.view = model.getView();
		
		if(jtableToRefresh!=null)
			this.jtable = jtableToRefresh;
		
		view.initButtons();
		
		initOkButton();
		
		handleOkButton();
		
		handleClearAllFieldsButton();
		
		view.initFrame();
	}
	
	private void initOkButton(){
		if(model.getType()==Types.MODIFY)
			view.okButton.setText(view.MODIFY_BUTTON_TEXT);
		else
			view.okButton.setText(view.ADD_BUTTON_TEXT);
	}

	private void handleOkButton(){
		
		view.okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if(model.getType()==Types.MODIFY){
						// Checking whether there is a change in the record so that the DB needs  to be updated.
						Object[] oldValues = model.getStructure().getValues();
						model.setStructure(model.createStructureFromFields());
						Object[] newValues = model.getStructure().getValues();
						boolean needToUpdate = false;
						for (int i = 0; i < oldValues.length; i++) {
							if(!oldValues[i].toString().equals(newValues[i].toString())){
								needToUpdate = true;
								break;
							}
						}
						if(needToUpdate){
							System.out.println("Need to update");
							model.updateDatabase();
							jtable.updateJTableData();
						}
					}
					else{
						model.setStructure(model.createStructureFromFields());
						model.insertToDatabase();
						jtable.updateJTableData();
					}
				}catch(FieldsValuesException ex){
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "בדוק שכל השדות תקינים");
				} catch (SQLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "לא היה ניתן להוסיף את רשומה זו");
				}
				
				
				view.dispose();
			}
		});
		
	}
	
	private void handleClearAllFieldsButton() {
		view.clearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.clearFields();
			}
		});
	}
	
	
	public TableStructure getTheNewTableStructure(){
		return model.structure;
	}
	
	
}
