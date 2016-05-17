package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import ui_donetests.AddGradesToTestJDialog;

public class AddGradesActions {

	
	public static ActionListener getAddGradesAction(JTable table){
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int testid = (int) table.getValueAt(table.getSelectedRow(), 0);
				new AddGradesToTestJDialog(testid);
			}
		};
	}
	
}
