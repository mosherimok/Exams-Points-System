package ui_tests;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ui_donetests_components.JFrameDisplayAllExaminees;

public class ModelMpTests {
	
	private ViewTestsMenuPanel view;
	
	public ModelMpTests(ViewTestsMenuPanel view) {
		this.view = view;
	}
	
	public void setJTableSelectionAction(){
		view.jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		view.jtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				boolean isSelectionEmpty = view.jtable.getSelectionModel().isSelectionEmpty();
				view.panelManagingRecord.setDeleteAndModifyEnable(!isSelectionEmpty);
				view.addExaminees.setEnabled(!isSelectionEmpty);
			}
		});
	}
	
	
	
	public void viewTestedsButton(){
		view.buttonViewTested.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int testid = (int) view.jtable.getValueAt(view.jtable.getSelectedRow(), 0);
				new JFrameDisplayAllExaminees(testid);
			}
		});
	}

}
