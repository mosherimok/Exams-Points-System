package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class ResultSetManipulation {

	public static Object[][] getResultSetDataArray(ResultSet result){
		try{
			int columns = result.getMetaData().getColumnCount();
			result.last();
			int rows = result.getRow();
			Object[][] data = new Object[rows][columns];
			for(int row=0;row<rows;row++){
				for(int col = 0;col<columns;col++){
					data[row][col] = result.getObject(col+1);
				}
			}
			return data;
		}
		catch(SQLException ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public static Vector<Vector<Object>> getResultSetDataVector(ResultSet result){
		try{
			Vector<Vector<Object>> data = new Vector<>();
			int columns = result.getMetaData().getColumnCount();
			while(result.next()){
				Vector<Object> row = new Vector<>();
				for(int col = 1;col<=columns;col++){
					row.addElement(result.getObject(col));
				}
			}
			
			return data;
		}catch(SQLException ex){
			ex.printStackTrace();
			return null;
		}
	}
	
}
