package actions;

import java.sql.SQLException;

import database.DatabaseUtilities;
//import database.DatabaseActions;
import exceptions.InvalidStructure;
import mvc_managing_records.Model;
import mvc_managing_records.View;
import tablesStructures.Student;
import tablesStructures.TableStructure;
import tablesStructures.Test;
import tablesStructures.TestCategory;
import ui_students.ModelDialogStudent;
import ui_students.ViewDialogStudent;
import ui_tests.ModelTest;
import ui_tests.ViewTestRecord;
import ui_tests_categories.ModelTestCategory;
import ui_tests_categories.ViewTestCategoryRecord;

public class MV_Factory {
	
	public static enum Views{
		Students,Tests,TestsCategories
	};
	
	private Views viewType;
	
	public MV_Factory(Views view){
		this.viewType = view;
	}
	
	private View getView(){
		switch(viewType){
		case Students:
			return new ViewDialogStudent();
		case Tests:
			String[] categories = null;
			try {
				categories = DatabaseUtilities.getDataOfColumnIdentifier(
						"TestsCategories", "CategoryName",String.class);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return new ViewTestRecord(categories);
		case TestsCategories:
			return new ViewTestCategoryRecord();
		default:
			return null;
		}
	}
	
	public Model getAddModel(){
		switch(viewType){
			case Students:
				return new ModelDialogStudent(getView());
			case Tests:
				return new ModelTest(getView());
			case TestsCategories:
				return new ModelTestCategory(getView());
			default:
				return null;
		}
	}
	
	public Model getModifyModel(TableStructure oldStructure) throws InvalidStructure{
		switch(viewType){
			case Students:
					if(!(oldStructure instanceof Student))
						throw new InvalidStructure("Student structure expected!");
				return new ModelDialogStudent(getView(), oldStructure);
			case Tests:
					if(!(oldStructure instanceof Test))
						throw new InvalidStructure("Test structure expected!");
				return new ModelTest(getView(),oldStructure);
			case TestsCategories:
					if(!(oldStructure instanceof TestCategory))
						throw new InvalidStructure("TestCategory structure expected!");
				return new ModelTestCategory(getView(),oldStructure);
			default:
				return null;
		}
	}
	
	public Views getViewType(){
		return viewType;
	}

}
