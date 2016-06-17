package ui_tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.SpinnerDateModel;

import exceptions.FieldsValuesException;
import exceptions.InvalidStructure;
import mvc_managing_records.Model;
import mvc_managing_records.View;
import tablesStructures.TableStructure;
import tablesStructures.Test;

public class ModelTest extends Model{

	private ViewTestRecord viewtst;
	
	public ModelTest(View view) {
		super(view);
		viewtst = (ViewTestRecord)view;
	}
	
	public ModelTest(View view, TableStructure oldTest) {
		super(view, oldTest);
		viewtst = (ViewTestRecord)view;
		try {
			initFieldsFromOldStructure();
		} catch (InvalidStructure e) {
			e.printStackTrace();
		}
	}

	@Override
	public TableStructure createStructureFromFields() throws FieldsValuesException {
		if(!areFieldsValid())
			throw new FieldsValuesException();
		Test test = new Test();
		test.setTestid(viewtst.testID);
		test.setCategory((String)viewtst.comboBox_categories.getSelectedItem());
		test.setName(viewtst.textField_testName.getText());
		
		Date date = ((SpinnerDateModel)(viewtst.spinner_testDate.getModel())).getDate();
		SimpleDateFormat format = new SimpleDateFormat(viewtst.DATE_FORMAT);
		
		test.setTestDate(format.format(date));
		
		return test;
	}

	@Override
	protected boolean areFieldsValid() {
		if(viewtst.textField_testName.getText().equals(""))
			return false;
		return true;
	}

	@Override
	public void clearFields() {
		viewtst.comboBox_categories.setSelectedIndex(0);
		viewtst.textField_testName.setText("");
		viewtst.spinner_testDate.setValue(Calendar.getInstance().getTime());
	}
	
	@Override
	public void initFieldsFromOldStructure() throws InvalidStructure {
		if(!(structure instanceof Test))
			throw new InvalidStructure("Given structure is not a Test");
		Test test = (Test) structure;
		viewtst.testID = test.getTestid();
		viewtst.textField_testName.setText(test.getName());
		viewtst.comboBox_categories.setSelectedItem(test.getCategory());
		try {
			((SpinnerDateModel)(viewtst.spinner_testDate.getModel())).
				setValue(new SimpleDateFormat(viewtst.DATE_FORMAT).parse(test.getTestDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}


}
