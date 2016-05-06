package actions;

import java.sql.SQLException;

import database.DatabaseDataFetching;
import exceptions.InvalidStructure;
import mvc_dialogs.Model;
import mvc_dialogs.View;
import mvc_students.ModelAddStudent;
import mvc_students.ModelModifyStudent;
import mvc_students.ViewStudentRecord;
import mvc_tests.ModelAddTest;
import mvc_tests.ModelModifyTest;
import mvc_tests.ViewTestRecord;
import mvc_testsCategories.ModelAddTestCategory;
import mvc_testsCategories.ModelModifyTestCategory;
import mvc_testsCategories.ViewTestCategoryRecord;
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
	
	public View getViewByType(){
		switch(viewType){
		case Students:
			return new ViewStudentRecord();
		case Tests:
			String[] categories = null;
			try {
				categories = DatabaseDataFetching.getDataOfColumnIdentifier(
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
	
	public Model getAddModelByType(){
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
	
	public Model getModifyModelByType(TableStructure oldStructure) throws InvalidStructure{
		switch(viewType){
		case Students:
				if(!(oldStructure instanceof Student))
					throw new InvalidStructure("Student expected!");
			return new ModelModifyStudent((Student)oldStructure);
		case Tests:
				if(!(oldStructure instanceof Test))
					throw new InvalidStructure("Test expected!");
			return new ModelModifyTest((Test)oldStructure);
		case TestsCategories:
				if(!(oldStructure instanceof TestCategory))
					throw new InvalidStructure("TestCategory expected!");
			return new ModelModifyTestCategory((TestCategory)oldStructure);
		default:
			return null;
		}
	}

}
