package tablesStructures;

import java.time.LocalDate;
import java.util.Iterator;

import exceptions.InvalidStructure;
import tables.Table;
import tables.TableGetter;
import tables.TblStudents;
import tables.TblTests;

public class Student extends TableStructure{

	private Integer id;
	private String firstName;
	private String lastName;
	private Short receptionYear;
	private Integer points;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		if(String.valueOf(id).length()!=9)
			throw new IllegalArgumentException("ID is not correct!");
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public short getReceptionYear() {
		return receptionYear;
	}
	public void setReceptionYear(short receptionYear) {
		if(receptionYear<LocalDate.now().getYear()-30)
			throw new IllegalArgumentException("Reception year is minimum to" + (LocalDate.now().getYear()-30));
		this.receptionYear = receptionYear;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		if(points<0)
			throw new IllegalArgumentException("Points can not be negative!");
		this.points = points;
	}
	
	@Override
	public Object[] getValues() {
		return new Object[]{id,firstName,lastName,receptionYear,points};
	}
	
	@Override
	public void initFromArray(Object[] values) {
		if(values.length!=5)
			try {
				throw new Exception("Not enougth elements");
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		id = (int)values[0];
		firstName = values[1].toString();
		lastName=values[2].toString();
		receptionYear = (short)values[3];
		points = (int)values[4];
	}
	
	@Override
	public Table getTableObject() {
		return TableGetter.getTable(TblStudents.class);
	}
	
	@Override
	public String getTableName() {
		return "Students";
	}
	
	@Override
	public String[] getPrimaryKeyName() {
		return new String[]{"id"};
	}
	@Override
	public Object[] getPrimaryKeyValue() {
		return new Object[]{id};
	}
	

}
