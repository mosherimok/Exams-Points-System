package database;

import java.util.ArrayList;

public class FailedUpdatesList{

	private ArrayList<Object[]> failedRecords;
	private ArrayList<String> failedReasons;
	
	public FailedUpdatesList(int initSize) {
		failedRecords = new ArrayList<>(initSize);
		failedReasons = new ArrayList<>(initSize);
	}

	public FailedUpdatesList() {
		failedRecords = new ArrayList<>();
		failedReasons = new ArrayList<>();
	}

	public void addFailed(Object[] record,String reason){
		failedRecords.add(record);
		failedReasons.add(reason);
	}
	
	public Object[] getFailedRecord(int index){
		try{
			return failedRecords.get(index);
		}catch(IndexOutOfBoundsException ex){
				ex.printStackTrace();
				return null;
		}
	}
	
	public String getFailedReason(int index){
		try{
			return failedReasons.get(index);
		}catch(IndexOutOfBoundsException ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public Object[] getThemBoth(int index){
		try{
			Object[] rec = failedRecords.get(index);
			Object[] both = new Object[rec.length+1];
			for (int i = 0; i < both.length-1; i++) {
				both[i] = rec[i];
			}
			both[both.length-1] = failedReasons.get(index);
			return both;
		}
		catch(IndexOutOfBoundsException ex){ex.printStackTrace(); return null;}
	}

	public int getSize(){
		return failedRecords.size();
	}
	
}
