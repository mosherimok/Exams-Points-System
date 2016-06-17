package ui_students;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import components_utility.CustomizedJTable;
import components_utility.ResultSetDefaultTableModel;
import pointsHistory.HistoryPointsJFrame;
import tables.Table;
import tables.TblStudents;
import ui_donetests_components.JFrameShowAllStudentTests;

public class ModelMpStudents {
	
	private ViewStudentsMenuPanel view;
	
	public ModelMpStudents(ViewStudentsMenuPanel view){
		this.view = view;
	}
	
	public void displayAllDoneTestsAction(){
		view.buttonDisplayAllDoneTests.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int studentID = (int)view.jtable.getValueAt(view.jtable.getSelectedRow(),0);
				new JFrameShowAllStudentTests(studentID);
			}
		});
		
	}
	
	public void viewByStudentsClassAction(){
		view.buttonViewByClass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TblStudents table = new TblStudents(){
					@Override
					public String getSelectAllScript() {
						return "SELECT * FROM Students WHERE reception_year="+(Calendar.getInstance().get(Calendar.YEAR)-((int)view.spinnerClass.getValue()));
					}
				};
				initTableModel(table);
			}
		});
	}
	
	public void viewAllStudentsAction(){
		view.buttonViewAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				initTableModel(new TblStudents());
			}
		});
	}
	
	public void initTableModel(Table table){
		view.jtable.setDefaultTableModel(new ResultSetDefaultTableModel(table));
	}
	
	public void setJTableSelection(){
		view.jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
}
