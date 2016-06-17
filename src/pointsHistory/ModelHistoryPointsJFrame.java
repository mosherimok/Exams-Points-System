package pointsHistory;

import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import database.HistoryPointsDatabase;

public class ModelHistoryPointsJFrame {

	public String[] labels = {"�����","���� ������","���� ������"};
	
	public DefaultTableModel getDefaultTableModel(int studentID) throws SQLException{
		Object[][] data = HistoryPointsDatabase.getStudentHistoryByID(studentID);
		if(data.length==0){
			data = null;
			labels = new String[]{"�� ����� ������ ���� ����� ��"};
		}
			
		return new DefaultTableModel(data,labels);
	}
	
}
