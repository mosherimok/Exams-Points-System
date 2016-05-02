package exceptions;

public class ImproperFieldsValues extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ImproperFieldsValues(String fieldName,Object value){
		super("The field " + fieldName + " is not legal for the value " + value);
	}
	
	
}
