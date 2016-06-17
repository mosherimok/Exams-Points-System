package tablesStructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

import exceptions.InvalidStructure;
import tables.Table;

public abstract class TableStructure implements Iterable<Object>{
	
	public String getTableName(){
		String className=getClass().getSimpleName();
		int length = className.length();
		return className.charAt(length-1)=='y'?className.substring(0,length-1)+"ies":className;
	}
	
	public abstract PrimaryKey getPrimaryKeyValue();
	
	public abstract Object[] getValues();
	
	public abstract void initFromArray(Object[] values);
	
	public abstract Table getTableObject();
	
	//Prototype pattern based.
	public static TableStructure createObjectByType(String structType) throws InvalidStructure{
		try {
			return (TableStructure)Class.forName("tablesStructures."+structType).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new InvalidStructure("No such structure type! " + "tablesStructures."+structType );
		}
	}
	
	@Override
	public Iterator<Object> iterator() {
		return new Iterator<Object>() {
			Object[] values = getValues();
			int index= 0;
			@Override
			public Object next() {
				if(index==values.length)
					throw new NoSuchElementException("No more elements!");
				return values[index++];
			}
			
			@Override
			public boolean hasNext() {
				return index<values.length;
			}
		};
	}
	
}
