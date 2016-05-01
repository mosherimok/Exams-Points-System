package actions;

import javax.swing.JTable;

import actionListeners.Action;
import ui_done_tests.AddGradesToTestJDialog;

public class AddGradesActions {

	
	public static Action getAddGradesAction(JTable table){
		return new Action() {
			
			@Override
			public void perform() {
				int testid = (int) table.getValueAt(table.getSelectedRow(), 0);
				new AddGradesToTestJDialog(testid);
			}
		};
	}
	
}
