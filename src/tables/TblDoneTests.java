package tables;

import tablesStructures.DoneTest;
import tablesStructures.TableStructure;

public class TblDoneTests extends Table{

	/*public TblDoneTests() {
		super("DoneTests",new String[]{"StudentID","TestID"},"DoneTest");
	}*/

	@Override
	public String[] getColumnsIdentifiers() {
		return new String[]{"StudentID","TestID","Grade"};
	}

	@Override
	public Class<?>[] getColumnsType() {
		return new Class<?>[]{int.class,int.class,int.class};
	}

	@Override
	public String[] getColumnsLabels() {
		return new String[]{"תז תלמיד","מזהה מבחן","ציון"};
	}

	@Override
	public int getColumnsCount() {
		return 3;
	}

	@Override
	public DoneTest createTableStructure() {
		return new DoneTest();
	}

	@Override
	public TableStructure createTableStructure(Object[] data) {
		return new DoneTest(data);
	}
}
