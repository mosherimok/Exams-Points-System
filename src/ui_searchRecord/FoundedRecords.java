package ui_searchRecord;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.JobAttributes.DefaultSelectionType;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.DatabaseActions;
import tables.Table;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FoundedRecords extends JDialog {

	//All stuff:
	private final Table TABLE;
	
	//All Components:
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 *//*
	public static void main(String[] args) {
		try {
			FoundedRecords dialog = new FoundedRecords();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	public FoundedRecords(Table table,Object[][] data) {
		TABLE = table;
		initGUI();
		initTable(data);
		initDialog();
	}
	
	
	private void initDialog() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}


	/**
	 * Create the dialog.
	 */
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
			JPanel panel = new JPanel();
			panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				table = new JTable();
				panel.add(table);
			}
		}
		{
			JPanel panelButtons = new JPanel();
			panelButtons.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			contentPanel.add(panelButtons, BorderLayout.SOUTH);
			{
				JButton button = new JButton("\u05DE\u05D7\u05E7 \u05E8\u05E9\u05D5\u05DE\u05D4");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/*String[] pks = TABLE.getPrimaryKey();
						Object[] vals = new Object[pks.length];
						String condition = "";
						for(int i=0;i<vals.length;i++){
							int index =((CustomTableModel)table.getModel()).getColumnIndex(pks[i]);
							condition += pks[i] + " = " +Table.getFormatedValues(
									table.getValueAt(table.getSelectedRow(),index));
						}
						Database.delete(TABLE.getTableName(), "");*/
					}
				});
				panelButtons.add(button);
			}
			{
				JButton button = new JButton("\u05E2\u05E8\u05D5\u05DA \u05E4\u05E8\u05D8\u05D9\u05DD");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				panelButtons.add(button);
			}
		}
		{
			JPanel panelCloseButton = new JPanel();
			panelCloseButton.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panelCloseButton.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(panelCloseButton, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u05E1\u05D2\u05D5\u05E8");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				panelCloseButton.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	public void initTable(Object[][] data){
		table.setModel(new CustomTableModel(data,TABLE.getColumnsLabels()));
		table.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
	}
	
	private class CustomTableModel extends DefaultTableModel{
		
		public CustomTableModel(Object[][] data,Object[] identifiers){
			super(data,identifiers);
		}
		
		public int getColumnIndex(String name){
			return columnIdentifiers.indexOf(name);
		}
	}

}
