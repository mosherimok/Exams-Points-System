package database;

import tablesStructures.PrimaryKey;

public class Condition {

	private String condition = "WHERE ";
	
	public Condition(){}
	
	public Condition(String columnName,Object value){
		addCondition(columnName, value);
	}
	
	public Condition(PrimaryKey primaryKey){
		addCondition(primaryKey);
	}
	
	public void addCondition(String columnName,Object value){
		if(!condition.equals("WHERE "))
			condition += " AND ";
		condition += String.format("%s='%s'",columnName,value);
	}
	
	public void addCondition(PrimaryKey primaryKey){
		if(!condition.equals("WHERE "))
			condition += " AND ";
		for(Object[] key : primaryKey){
			condition += String.format("%s='%s' AND ",key[0],key[1]);
		}
		condition = condition.substring(0,condition.length()-5);
	}
	
	@Override
	public String toString(){
		return condition;
	}
	
	
}
