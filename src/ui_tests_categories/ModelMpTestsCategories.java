package ui_tests_categories;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import components_utility.CustomizedJTable;

public class ModelMpTestsCategories {
	
	private ViewTestsCategoriesPanel view;
	
	public ModelMpTestsCategories(ViewTestsCategoriesPanel view) {
		this.view = view;
	}
	
	public void setJTableSelectionAction(){
		CustomizedJTable jtable = view.jtable;
		jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				boolean isSelected = jtable.getSelectionModel().isSelectionEmpty();
				view.panelManagingRecord.setDeleteAndModifyEnable(!isSelected);
			}
		});
	}

}
