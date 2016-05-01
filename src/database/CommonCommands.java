/*package database;

import java.util.ArrayList;
import java.util.Vector;

import exceptions.IncorrectParameterException;
import static exceptions.IncorrectParameterException.Params;

public class CommonCommands {
	
	private static ArrayList<String> allTables = new ArrayList<>();
	static{
		allTables.add("students");
		allTables.add("testscategories");
		allTables.add("tests");
		allTables.add("donetests");
	}
	
	public String updateTable(String tblName,String columnsNames[] ,Object values[],
			String condition){
		try{
		if(tblName==null||tblName.equals(""))
			throw new IncorrectParameterException(Params.TABLE_NAME);
		if(columnsNames==null||columnsNames.length==0)
			throw new IncorrectParameterException(Params.COLUMNS_NAME);
		if(values==null||values.length==0)
			throw new IncorrectParameterException(Params.VALUES);
		if(condition==null||condition.equals(null))
			throw new IncorrectParameterException(Params.CONDITION);
		if(columnsNames.length!=values.length)
			throw new IncorrectParameterException(Params.COLUMNS_NAMES_AND_VALUES_MISMATCH);
		}
		catch(IncorrectParameterException ex){ex.printStackTrace(); return null;}
		
		formatValues(values);
		String script = "UPDATE " + tblName + " SET\n";
		for(int i = 0;i<columnsNames.length-1;i++){
			script+= String.format("%s = %s,\n", columnsNames[i],values[i].toString());
			if(values[i] instanceof String)
				script += String.format("%s = '%s',\n",columnsNames[i],values[i]);
			else
				script += String.format("%s = %s,\n",columnsNames[i],values[i]);
		}
		if(values[columnsNames.length-1] instanceof String)
			script += String.format("%s = '%s'\n",columnsNames[columnsNames.length-1],
					values[columnsNames.length-1]);
		else
			script += String.format("%s = %s\n",columnsNames[columnsNames.length-1],
					values[columnsNames.length-1]);
		
		script = script.substring(0, script.length()-1) + "WHERE " + condition.replace(" ", "");
		System.out.println(script);
		return script;
	}
	
	public String insertInto(String tblName,String columnsNames[],Object values[]) {
		try{
			if(tblName==null||tblName.equals(""))
				throw new IncorrectParameterException(Params.TABLE_NAME);
			if(values==null||values.length==0)
				throw new IncorrectParameterException(Params.VALUES);
		}catch(IncorrectParameterException ex){ex.printStackTrace(); return null;}
		
		String script = "INSERT INTO " + tblName;
		
		if(columnsNames!=null&&columnsNames.length>0){
			script += "(";
			for(int i=0;i<columnsNames.length-1;i++){
				script += columnsNames[i]+",";
			}
			script += columnsNames[columnsNames.length-1];
			script+=")";
		}
		
		script+="\n";
		
		formatValues(values);
		script += "Values(";
		for(int i=0;i<values.length-1;i++){
			script += values[i] + ",";
			if(values[i] instanceof String)
				script += "'"+values[i] + "',";
			else
				script += values[i] + ",";
		}
		return script;
	}
		
		public String insertInto(String tblName,Object values[]){
			try{
				if(tblName==null||tblName.equals(""))
					throw new IncorrectParameterException(Params.TABLE_NAME);
				if(values==null||values.length==0)
					throw new IncorrectParameterException(Params.VALUES);
			}catch(IncorrectParameterException ex){ex.printStackTrace(); return null;}
			
			String script = "INSERT INTO " + tblName;
			
			script+="\n";
			
			formatValues(values);
			script += "Values(";
			for(int i=0;i<values.length-1;i++){
				script += values[i] + ",";
				if(values[i] instanceof String)
					script += "'"+values[i] + "',";
				else
					script += values[i] + ",";
			}
		
		if(values[values.length-1] instanceof String)
			script += "\n'"+values[values.length-1]+"'";
		else
			script += "\n"+values[values.length-1];
		
		script = script.substring(0, script.length()-1) + ")";
		
		return script;
	}
	
	public String deleteFrom(String tblName,String condition){
		try{
			if(tblName==null||tblName.isEmpty())
				throw new IncorrectParameterException(IncorrectParameterException.Params.TABLE_NAME);
			if(condition==null||condition.isEmpty())
				throw new IncorrectParameterException(IncorrectParameterException.Params.CONDITION);
		}catch(IncorrectParameterException ex){ex.printStackTrace(); return null;}
		
		String script = "Delete from " + tblName + "\n"+
							"Where " + condition;
		return script;
	}
	
	private void formatValues(Object[] values){
		for (int i = 0; i < values.length; i++) {
			if(values[i] instanceof String)
				values[i] = "'"+values[i]+"'";
		}
	}
	
}
*/