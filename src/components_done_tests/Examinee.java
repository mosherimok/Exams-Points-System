package components_done_tests;

public class Examinee {
	private String f_name;
	private String l_name;
	private int id;
	private int grade;
	public Examinee(String f_name,String l_name,int grade){
		this.f_name = f_name;
		this.l_name = l_name;
		this.grade = grade;
	}
	
	public String getFirstName(){
		return f_name;
	}
	
	public String getLastName(){
		return l_name;
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
	
	@Override
	public String toString(){
		return "<html>Name: " + f_name + " " + l_name + "<br> " + "Grade: " + grade + " " +  "</html>";
	}
	
	@Override
	public boolean equals(Object other){
		if(other instanceof Examinee)
			return ((Examinee)other).f_name.equals(f_name)&&((Examinee)other).l_name.equals(l_name);
		return false;
	}
}
