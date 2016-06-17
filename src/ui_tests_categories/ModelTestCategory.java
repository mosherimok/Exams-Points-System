package ui_tests_categories;

import exceptions.FieldsValuesException;
import exceptions.InvalidStructure;
import mvc_managing_records.Model;
import mvc_managing_records.View;
import tablesStructures.TableStructure;
import tablesStructures.TestCategory;

public class ModelTestCategory extends Model{

	private ViewTestCategoryRecord viewtcr;
	
	public ModelTestCategory(View view) {
		super(view);
		viewtcr = (ViewTestCategoryRecord)view;
	}
	
	public ModelTestCategory(View view, TableStructure oldTestCategory) {
		super(view, oldTestCategory);
		viewtcr = (ViewTestCategoryRecord)view;
		try {
			initFieldsFromOldStructure();
		} catch (InvalidStructure e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initFieldsFromOldStructure() throws InvalidStructure {
		TestCategory tc = (TestCategory)structure;
		viewtcr.textFieldTestType.setText(tc.getCategoryName());
		viewtcr.spinnerPoints1.setValue(tc.getPoints75());
		viewtcr.spinnerPoints2.setValue(tc.getPoints85());
		viewtcr.spinnerPoints3.setValue(tc.getPoints95());
	}

	@Override
	public TableStructure createStructureFromFields() throws FieldsValuesException {
		if(!areFieldsValid())
			throw new FieldsValuesException();
		TestCategory tc = new TestCategory();
		tc.setCategoryName(viewtcr.textFieldTestType.getText());
		tc.setPoints75((int)viewtcr.spinnerPoints1.getValue());
		tc.setPoints85((int)viewtcr.spinnerPoints2.getValue());
		tc.setPoints95((int)viewtcr.spinnerPoints3.getValue());
		return tc;
	}

	@Override
	protected boolean areFieldsValid() {
		if(viewtcr.textFieldTestType.getText().equals(""))
			return false;
		if(!pointsRangeIsValid())
			return false;
		return true;
	}
	
	private boolean pointsRangeIsValid(){
		int a = (int)viewtcr.spinnerPoints1.getValue();
		int b = (int)viewtcr.spinnerPoints2.getValue();
		int c = (int)viewtcr.spinnerPoints3.getValue();
		return c>b&&b>a;
	}

	@Override
	public void clearFields() {
		viewtcr.textFieldTestType.setText("");
		viewtcr.spinnerPoints1.setValue(0);
		viewtcr.spinnerPoints2.setValue(0);
		viewtcr.spinnerPoints3.setValue(0);
	}

}
