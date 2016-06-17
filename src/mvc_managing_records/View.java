package mvc_managing_records;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public abstract class View extends JFrame{

	public final String ADD_BUTTON_TEXT;
	public final String MODIFY_BUTTON_TEXT;
	
	public JButton okButton = new JButton("OK");
	public JButton clearButton = new JButton("נקה הכל");
	
	public View(String recordName){
		this.ADD_BUTTON_TEXT = " הוסף " + recordName;
		this.MODIFY_BUTTON_TEXT = " שמור " + recordName;
	}
	
	protected abstract void initButtons();
	
	public void initFrame(){
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
}
