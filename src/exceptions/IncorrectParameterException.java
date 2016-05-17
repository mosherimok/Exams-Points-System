package exceptions;

public class IncorrectParameterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static enum Params{
		TABLE_NAME("Incorrect table name"),COLUMNS_NAME("Incorrect columns names"),
		VALUES("Incorrect values"),
		COLUMNS_NAMES_AND_VALUES_MISMATCH("Mismatch between columns names and values"),
		CONDITION("Incorrect condition");
		
		private String reason;
		private Params(String reason){
			this.reason = reason;
		}
		@Override
		public String toString(){
			return reason;
		}
	}
	
	public IncorrectParameterException(Params param){
		super(param.reason);
	}
	
}
