/*package tables;

import java.util.HashMap;

public class TableGetter {

	private static final HashMap<Class<?>, Table> tables = new HashMap<>();
	
	public static Table getTable(Class<?> tableType){
		Table table = tables.get(tableType);
		if(table==null){
			try {
				table = (Table) tableType.newInstance();
				tables.put(tableType, table);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return table;
	}
	
}
*/