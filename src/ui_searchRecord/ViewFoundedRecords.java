package ui_searchRecord;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;

import components_utility.CustomizedJTable;
import pointsHistory.JButtonPointsHistory;
import tables.Table;
import ui_main.MainScreen;
import ui_records_management.PanelManagingRecord;
import ui_records_management.ModelViewFactory.Views;

public class ViewFoundedRecords extends JFrame{
	
	//Objects-Members:
	private final String RECORD_NAME;
	private final String TABLE_NAME;
	private ModelFoundedRecords model = new ModelFoundedRecords(this);
	
	//Components:
	private final JPanel contentPanel = new JPanel();
	public CustomizedJTable jtable;
	public CustomizedJTable jparent;
	private PanelManagingRecord panelManagingRecord;
	public JButton okButton;
	
	public ViewFoundedRecords() {
		RECORD_NAME = MainScreen.getCurrentViewAsMenuPanel().getRECORD_NAME();
		TABLE_NAME = MainScreen.getCurrentViewAsMenuPanel().getTable().getTableName();
		this.jparent = MainScreen.getCurrentViewAsMenuPanel().getJtable();
		
		initGUI();
		setButtonsListeners();
		initJFrame();
	}
	
	
	private void initJFrame() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}


	private void initGUI() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelTitle = new JPanel();
			contentPanel.add(panelTitle, BorderLayout.NORTH);
			{
				JLabel label = new JLabel("\u05EA\u05D5\u05E6\u05D0\u05D5\u05EA \u05D4\u05D7\u05D9\u05E4\u05D5\u05E9");
				panelTitle.add(label);
			}
		}
		{
			JPanel panelJTable = new JPanel();
			panelJTable.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
			contentPanel.add(panelJTable, BorderLayout.CENTER);
			panelJTable.setLayout(new BorderLayout(0, 0));
			{
				jtable = new CustomizedJTable();
				panelJTable.add(new JScrollPane(jtable));
			}
		}
		{
			panelManagingRecord = new PanelManagingRecord(jtable, RECORD_NAME,Views.valueOf(TABLE_NAME));
			panelManagingRecord.buttonAddRecord.setVisible(false);
			
			panelManagingRecord.addJButton(new JButtonPointsHistory(jtable, false));
			contentPanel.add(panelManagingRecord, BorderLayout.SOUTH);
		}
		{
			JPanel panelCloseButton = new JPanel();
			panelCloseButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panelCloseButton.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(panelCloseButton, BorderLayout.SOUTH);
			
			okButton = new JButton("\u05E9\u05DE\u05D5\u05E8");
			okButton.setActionCommand("OK");
			panelCloseButton.add(okButton);
			getRootPane().setDefaultButton(okButton);
			
		}
	}

	private void setButtonsListeners() {
		model.buttonOkAction();
	}
	
	public void setData(Object[][] data){
		Table table = MainScreen.getCurrentViewAsMenuPanel().getTable();
		jtable.setDefaultTableModel(new TableModelFoundedRecords(table, data));
	}
	
}
