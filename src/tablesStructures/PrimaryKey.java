package tablesStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class PrimaryKey implements Iterable<Object[]>{
	
	private ArrayList<Object[]> keys;
	private int numberOfKeys;
	
	public PrimaryKey(){keys = new ArrayList<>();}
	
	public PrimaryKey(String key,Object value){
		keys = new ArrayList<>();
		keys.add(new Object[]{key,value});
		numberOfKeys++;
	}
	
	public void addKey(String key, Object value){
		keys.add(new Object[]{key,value});
		numberOfKeys++;
	}
	
	public String getPrimaryKeyName(int index){
		return (String) keys.get(index)[0];
	}
	
	public Object getPrimaryKeyValue(int index){
		return keys.get(index)[1];
	}
	
	public int getNumberOfKeys(){
		return numberOfKeys;
	}

	@Override
	public Iterator<Object[]> iterator() {
		return new Iterator<Object[]>() {
			int counter  = 0;
			@Override
			public boolean hasNext() {
				return counter<keys.size();
			}

			@Override
			public Object[] next() {
				return keys.get(counter++);
			}
		};
	}

}
