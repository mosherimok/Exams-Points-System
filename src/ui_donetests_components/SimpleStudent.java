package ui_donetests_components;

public class SimpleStudent {

	private String f_name;
	private String l_name;
	
	public SimpleStudent(String firstName,String lastName){
		this.f_name = firstName;
		this.l_name = lastName;
	}
	
	
	
	public String getF_name() {
		return f_name;
	}



	public void setF_name(String f_name) {
		this.f_name = f_name;
	}



	public String getL_name() {
		return l_name;
	}



	public void setL_name(String l_name) {
		this.l_name = l_name;
	}



	@Override
	public String toString(){
		return f_name+" "+l_name;
	}
}
