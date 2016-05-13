package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class ResultSetManipulation {

	/*public static Object[][] getResultSetDataArray(ResultSet result){
		try{
			int columns = result.getMetaData().getColumnCount();
			ArrayList<Object[]> data = new ArrayList<>();
			while(result.next()){
				Object[] row = new Object[columns];
				for(int col = 0;col<columns;col++){
					row[col] = result.getObject(col+1);
				}
				data.add(row);
			}
			return Arrays.copyOf(data.toArray(),data.size(), Object[][].class);
		}
		catch(SQLException ex){
			ex.printStackTrace();
			return null;
		}
	}*/
	
	/*public static Vector<Vector<Object>> getResultSetDataVector(ResultSet result){
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
	}*/
	
}
