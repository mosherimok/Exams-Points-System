package actions;

import java.sql.SQLException;

import database.DatabaseActions;
import exceptions.InvalidStructure;
import mv_StudentManagingDialog.ModelAddStudent;
import mv_StudentManagingDialog.ModelModifyStudent;
import mv_StudentManagingDialog.ViewStudentRecord;
import mv_TestCategoriesManagingDialog.ModelAddTestCategory;
import mv_TestCategoriesManagingDialog.ModelModifyTestCategory;
import mv_TestCategoriesManagingDialog.ViewTestCategoryRecord;
import mv_TestsManagingDialog.ModelAddTest;
import mv_TestsManagingDialog.ModelModifyTest;
import mv_TestsManagingDialog.ViewTestRecord;
import mvc_dialogs.Model;
import mvc_dialogs.View;
import tablesStructures.Student;
import tablesStructures.TableStructure;
import tablesStructures.Test;
import tablesStructures.TestCategory;

public class MV_Factory {
	
	public static enum Views{
		Students,Tests,TestsCategories
	};
	
	private Views viewType;
	
	public MV_Factory(Views view){
		this.viewType = view;
	}
	
	public View getView(){
		switch(viewType){
		case Students:
			return new ViewStudentRecord();
		case Tests:
			String[] categories = null;
			try {
				categories = DatabaseActions.getDataOfColumnIdentifier(
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
				return new ModelAddStudent();
			case Tests:
				return new ModelAddTest();
			case TestsCategories:
				return new ModelAddTestCategory();
			default:
				return null;
		}
	}
	
	public Model getModifyModel(TableStructure oldStructure) throws InvalidStructure{
		switch(viewType){
		case Students:
				if(!(oldStructure instanceof Student))
					throw new InvalidStructure("Student structure expected!");
			return new ModelModifyStudent((Student)oldStructure);
		case Tests:
				if(!(oldStructure instanceof Test))
					throw new InvalidStructure("Test structure expected!");
			return new ModelModifyTest((Test)oldStructure);
		case TestsCategories:
				if(!(oldStructure instanceof TestCategory))
					throw new InvalidStructure("TestCategory structure expected!");
			return new ModelModifyTestCategory((TestCategory)oldStructure);
		default:
			return null;
		}
	}
	
	public Views getViewType(){
		return viewType;
	}

}
