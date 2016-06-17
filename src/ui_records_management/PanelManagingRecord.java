package ui_records_management;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import components_utility.CustomizedJTable;
import ui_records_management.ModelViewFactory.Views;

import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;

public class PanelManagingRecord extends JPanel {
	
	//static finals:
	
	private static final long serialVersionUID = 1L;
	
	//finals:
	private final String RECORD_NAME;
	
	//Object members:
	private ButtonsActions buttonsActions;
	
	//JComponents:
	public JButton buttonAddRecord;
	public JButton buttonModifyRecord;
	public JButton buttonDeleteRecord;
	public ArrayList<JButton> userButtons;

	/**
	 * Create the panel.
	 */
	public PanelManagingRecord(CustomizedJTable jtable, String recordName,Views view) {
		RECORD_NAME = recordName;
		buttonsActions = new ButtonsActions(jtable, view);
		initGUI();
		handleJTable(jtable);
		initActionListener();
	}
	
	private void initGUI() {
		
		setLayout(new FlowLayout(FlowLayout.CENTER));
		
		buttonDeleteRecord = new JButton("מחק "+RECORD_NAME);
		add(buttonDeleteRecord);
		
		buttonModifyRecord = new JButton("שנה פרטי "+RECORD_NAME);
		add(buttonModifyRecord);
		
		buttonAddRecord = new JButton("הוסף "+RECORD_NAME);
		add(buttonAddRecord);
		
		buttonDeleteRecord.setEnabled(false);
		buttonModifyRecord.setEnabled(false);
	}
	
	private void initActionListener(){
		buttonAddRecord.addActionListener(buttonsActions.getAddAction());
		buttonModifyRecord.addActionListener(buttonsActions.getModifyAction());
		buttonDeleteRecord.addActionListener(buttonsActions.getDeleteAction());
	}
	
	public void setDeleteAndModifyEnable(boolean enable){
		buttonModifyRecord.setEnabled(enable);
		buttonDeleteRecord.setEnabled(enable);
	}
	
	public ButtonsActions getButtonsActions(){
		return buttonsActions;
	}
	
	public void addJButton(JButton newButton){
		if(userButtons == null)
			userButtons = new ArrayList<>();
		add(newButton,SwingConstants.CENTER);
		userButtons.add(newButton);
	}
	
	private void handleJTable(JTable jtable) {
		jtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				boolean isSelectionEmpty = jtable.getSelectionModel().isSelectionEmpty();
				buttonDeleteRecord.setEnabled(!isSelectionEmpty);
				buttonModifyRecord.setEnabled(!isSelectionEmpty);
				if (userButtons!=null)
					for(JButton b:userButtons){
						b.setEnabled(!isSelectionEmpty);
					}
			}
		});
	}
}
