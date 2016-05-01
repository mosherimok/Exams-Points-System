package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHandle {

	public void handle(ResultSet result) throws SQLException;
	
}
