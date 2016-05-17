package ui_components;

import javax.swing.JPanel;
import javax.swing.JTable;

import actions.ButtonsActions;
import actions.MV_Factory.Views;

import java.awt.FlowLayout;

import javax.swing.JButton;

public class PanelManagingRecord extends JPanel {
	
	//static finals:
	
	private static final long serialVersionUID = 1L;
	
	//finals:
	private final String RECORD_NAME;
	
	//Object members:
	private ButtonsActions buttonsActions;
	
	//JComponents:
	private JButton btnAddRecord;
	private JButton btnModifyRecord;
	private JButton btnDeleteRecord;
	

	/**
	 * Create the panel.
	 */
	public PanelManagingRecord(CustomizedJTable jtable, String recordName,Views view) {
		RECORD_NAME = recordName;
		buttonsActions = new ButtonsActions(jtable, view);
		initGUI();
		initActionListener();
	}
	private void initGUI() {
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		btnDeleteRecord = new JButton("מחק "+RECORD_NAME);
		add(btnDeleteRecord);
		
		btnModifyRecord = new JButton("שנה פרטי "+RECORD_NAME);
		add(btnModifyRecord);
		
		btnAddRecord = new JButton("הוסף "+RECORD_NAME);
		add(btnAddRecord);
		
		btnDeleteRecord.setEnabled(false);
		btnModifyRecord.setEnabled(false);
	}
	
	private void initActionListener(){
		/*btnAddRecord.setActionCommand(ADD);
		btnModifyRecord.setActionCommand(MODIFY);
		btnDeleteRecord.setActionCommand(DELETE);*/
		
		btnAddRecord.addActionListener(buttonsActions.getAddAction());
		btnModifyRecord.addActionListener(buttonsActions.getModifyAction());
		btnDeleteRecord.addActionListener(buttonsActions.getDeleteAction());
	}
	
	public void setDeleteAndModifyEnable(boolean enable){
		btnModifyRecord.setEnabled(enable);
		btnDeleteRecord.setEnabled(enable);
	}
	
	public ButtonsActions getButtonsActions(){
		return buttonsActions;
	}

}
