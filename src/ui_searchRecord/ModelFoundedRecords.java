package ui_searchRecord;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModelFoundedRecords {
	
	private ViewFoundedRecords view;
	
	public ModelFoundedRecords(ViewFoundedRecords view) {
		this.view = view;
	}
	
	public void buttonOkAction(){
		view.okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				view.jparent.updateJTableData();
				view.dispose();
			}
		});
	}

}
