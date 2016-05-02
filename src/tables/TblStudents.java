package tables;


public class TblStudents extends Table{
	
	public TblStudents() {
		super("Students",new String[]{"id"},"Student");
	}

	@Override
	public String[] getColumnsIdentifiers() {
		return new String[]{"id","f_name","l_name","reception_year","points"};
	}

	@Override
	public Class<?>[] getColumnsType() {
		return new Class<?>[]{int.class,String.class,String.class,short.class,int.class};
	}

	@Override
	public String[] getColumnsLabels() {
		return new String[]{"��","�� ����","�� �����","��� ����","������"};
	}

	@Override
	public int getColumnsCount() {
		return 5;
	}
	
}
