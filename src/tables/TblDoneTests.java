package tables;

import tablesStructures.DoneTest;
import tablesStructures.TableStructure;

public class TblDoneTests extends Table{

	@Override
	public String[] getColumnsIdentifiers() {
		return new String[]{"StudentID","TestID","Grade"};
	}

	@Override
	public String[] getColumnsLabels() {
		return new String[]{"תז תלמיד","מזהה מבחן","ציון"};
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
