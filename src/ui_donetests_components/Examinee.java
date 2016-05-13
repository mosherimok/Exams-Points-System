package ui_donetests_components;

public class Examinee {
	private String name;
	private int id;
	private int grade;
	public Examinee(String name,int grade){
		this.name = name;
		this.grade = grade;
	}
	
	public String getName(){
		return name;
	}
	
	public int getGrade(){
		return grade;
	}
	
	public void setGrade(int grade){
		if(grade<=100&&grade>=0)
			this.grade = grade;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public int getID(){
		return id;
	}
	
	public String toString(){
		return "<html>Name: " + name + " " + "<br> " + "Grade: " + grade + " " +  "</html>";
	}
	
	@Override
	public boolean equals(Object other){
		return ((Examinee)other).name.equals(name);
	}
}
