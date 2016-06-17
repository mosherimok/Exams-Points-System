package ui_students;

import java.time.LocalDate;

import exceptions.FieldsValuesException;
import exceptions.InvalidStructure;
import mvc_managing_records.Model;
import mvc_managing_records.View;
import tablesStructures.Student;
import tablesStructures.TableStructure;

public class ModelDialogStudent extends Model{
	
	private ViewDialogStudent stdview;
	
	public ModelDialogStudent(View view) {
		super(view);
		stdview = (ViewDialogStudent)view;
	}
	
	public ModelDialogStudent(View view, TableStructure oldStudent) {
		super(view, oldStudent);
		stdview = (ViewDialogStudent)view;
		try {
			initFieldsFromOldStructure();
		} catch (InvalidStructure e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initFieldsFromOldStructure() throws InvalidStructure {
		if(!(structure instanceof Student))
			throw new InvalidStructure("Given structure is not a Student");
		
		Student student = (Student) structure;
		
		stdview.id.setText(String.valueOf(student.getId()));
		
		stdview.textField_f_name.setText(student.getFirstName());
		
		stdview.textField_l_name.setText(student.getLastName());
		
		stdview.rec_year.setValue(student.getReceptionYear());
		
		stdview.points.setValue(student.getPoints());
	}

	@Override
	public TableStructure createStructureFromFields() throws FieldsValuesException {
		if(!areFieldsValid())
			throw new FieldsValuesException();
		Student student = new Student();
		student.setId(Integer.parseInt(stdview.id.getText().replace("-","")));
		student.setFirstName(stdview.textField_f_name.getText());
		student.setLastName(stdview.textField_l_name.getText());
		student.setReceptionYear(Short.parseShort(stdview.rec_year.getValue().toString()));
		student.setPoints((int)stdview.points.getValue());
		return student;
	}

	@Override
	protected boolean areFieldsValid() {
		if(stdview.id.getText().length()<9)
			return false;
		if(stdview.textField_f_name.getText().equals(""))
			return false;
		if(stdview.textField_l_name.getText().equals(""))
			return false;
		return true;
	}

	@Override
	public void clearFields() {
		stdview.id.setValue(null);
		stdview.textField_f_name.setText("");
		stdview.textField_f_name.setText("");
		stdview.rec_year.setValue(LocalDate.now().getYear()-30);
		stdview.points.setValue(0);		
	}

	

}
