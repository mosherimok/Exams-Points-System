package ui_searchRecord;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import database.Database;

public class ModelSearchRecord {
	
	ViewSearchRecord view;
	
	public ModelSearchRecord(ViewSearchRecord view) {
		this.view = view;
	}
	
	public void textfieldSearchKeyListener(){
		view.textFieldSearch.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
					try {
						view.button_search.doClick();
						super.keyPressed(arg0);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
			
		});
	}
	
	public void buttonSearch(){
		view.button_search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					searchingHandle();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void searchingHandle() throws Exception{
		int selectedIndexPrimary = view.comboBox_primaryCriteria.getSelectedIndex();
		int selectedIndexSecondary = view.comboBox_secondaryCriteria.getSelectedIndex();
		
		String columnIdentifier1 = view.getTable().getAllColumnsIdentifiers()[selectedIndexPrimary];
		String columnIdentifier2 = selectedIndexSecondary==-1?null:view.getTable().getAllColumnsIdentifiers()[selectedIndexSecondary];
		
		String script = null;
		if(columnIdentifier2!=null){
			String[] inputs = view.textFieldSearch.getText().split(" ");
			if(inputs.length<2)
				throw new Exception("Input to search is not valid. Two items to search " +
						"are required!");
			script = String.format(view.getTable().getSelectAllScript()+" WHERE %s='%s' AND %s='%s'",
				columnIdentifier1,inputs[0].toLowerCase(),columnIdentifier2,inputs[1].toLowerCase());
		}
		else
			script = String.format(view.getTable().getSelectAllScript()+" WHERE %s='%s'",
					columnIdentifier1,view.textFieldSearch.getText());
		
		System.out.println(script);
		Object[][] fetchedData = Database.executeQuery(script);

		
		ViewFoundedRecords view = new ViewFoundedRecords();
		view.setData(fetchedData);
		view.setVisible(true);
		
	}

}
