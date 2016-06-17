package ui_donetests_components;

import javax.swing.DefaultListModel;

import components_done_tests.Examinee;

public class ExmainesDefaultListModel extends DefaultListModel<Examinee> {

	private static final long serialVersionUID = 1L;

	@Override
	public void addElement(Examinee element) {
		if(contains(element)){
			return;
		}
		super.addElement(element);
	}
	
	
	@Override
	public Examinee remove(int index){
//		btnRemoveFromList.setEnabled(false);
		return super.remove(index);
	}
	
	public void replaceWith(Examinee other,int index){
		removeElementAt(index);
		add(index, other);
	}
}
