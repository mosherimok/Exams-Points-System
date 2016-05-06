package ui_searchRecord;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import mvc_dialogs.Controller;
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
import tablesStructures.TableStructure;

public class ControllerFactory {

	private static HashMap<String,Class[]> viewsAndModels;//view,add,modify
	
	static{
		viewsAndModels.put("Students", new Class[]{ViewStudentRecord.class,
												   ModelAddStudent.class,
												   ModelModifyStudent.class});
		
		viewsAndModels.put("Tests", new Class[]{ViewTestRecord.class,
												   ModelAddTest.class,
												   ModelModifyTest.class});
		
		viewsAndModels.put("TestsCategories", new Class[]{ViewTestCategoryRecord.class,
												   		  ModelAddTestCategory.class,
												   		  ModelModifyTestCategory.class});
	}
	
	public static Controller getControllerAddRecord(String tableName){
		Class[] vam = viewsAndModels.get(tableName);
		try {
			return new Controller((View)vam[0].newInstance(),(Model)vam[1].newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Controller getControllerModifyRecord(String tableName,TableStructure old){
		Class[] vam = viewsAndModels.get(tableName);
		try {
			return new Controller((View)vam[0].newInstance(),
					(Model)vam[2].getConstructors()[0].newInstance(old));
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
