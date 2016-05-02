package database;

public class Condition {

	private String condition = "WHERE ";
	
	public void addCondition(String columnName,Object value){
		if(!condition.equals("WHERE "))
			condition += " AND ";
		condition += String.format("%s='%s'",columnName,value);
	}
	
	@Override
	public String toString(){
		return condition;
	}
	
	
}
