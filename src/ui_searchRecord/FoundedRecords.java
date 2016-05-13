package ui_searchRecord;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import actions.MV_Factory;
import actions.MV_Factory.Views;
import database.Condition;
import database.DatabaseActions;
import database.DatabaseUpdatingScripts;
import exceptions.InvalidStructure;
import mvc_dialogs.Controller;
import mvc_dialogs.Model;
import mvc_dialogs.View;
import tables.Table;
import tablesStructures.TableStructure;
import ui_main.MainScreen;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class FoundedRecords extends JDialog {
	
	//All stuff:
	private final Table table;
	private MV_Factory mv_factory;
	
	//All Components:
	private final JPanel contentPanel = new JPanel();
	private JTable jtable;
	private JButton buttonModify;
	private JButton buttonDelete;

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
		this.table = table;
		mv_factory = new MV_Factory(Views.valueOf(table.getTableName()));
		initTable(data);
		initGUI();
		initDialog();
	}
	
	
	private void initDialog() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
			}
			
		});
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
				panel.add(new JScrollPane(jtable));
			}
		}
		{
			JPanel panelButtons = new JPanel();
			panelButtons.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			contentPanel.add(panelButtons, BorderLayout.SOUTH);
			{
				buttonDelete = new JButton("\u05DE\u05D7\u05E7 \u05E8\u05E9\u05D5\u05DE\u05D4");
				buttonDelete.addActionListener(new ActionListener() {
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
						if(JOptionPane.showConfirmDialog(null, "האם אתה בטוח שברצונך למחוק רשומה זו?","התרעה לפני מחיקה",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.CANCEL_OPTION)
							return;
						
						TableStructure structure = ((CustomTableModel)jtable.getModel()).
								getRowStructure(jtable.getSelectedRow());
						Condition condition = new Condition(structure.getPrimaryKey());
						String script = DatabaseUpdatingScripts.deleteFrom(table.getTableName(),
								condition);
						try {
							DatabaseActions.executeUpdate(script);
							MainScreen.getCurrentViewPanel().refreshDataFromDB();
							
							buttonDelete.setEnabled(false);
							buttonModify.setEnabled(false);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
				buttonDelete.setEnabled(false);
				panelButtons.add(buttonDelete);
			}
			{
				buttonModify = new JButton("\u05E2\u05E8\u05D5\u05DA \u05E4\u05E8\u05D8\u05D9\u05DD");
				buttonModify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TableStructure oldStructure = ((CustomTableModel)jtable.getModel()).
								getRowStructure(jtable.getSelectedRow());
						View view = mv_factory.getView();
						Model model = null;
						try {
							model = mv_factory.getModifyModel(oldStructure);
						} catch (InvalidStructure e1) {
							e1.printStackTrace();
						}
						Controller controller = new Controller(view, model);
						boolean isNeedToRefreshData = controller.isNeedToRefreshJTableData();
						if(isNeedToRefreshData)
							MainScreen.getCurrentViewPanel().refreshDataFromDB();
					}
				});
				buttonModify.setEnabled(false);
				panelButtons.add(buttonModify);
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
		jtable = new JTable(new CustomTableModel(data,table.getColumnsLabels()));
		jtable.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		jtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (jtable.getSelectedRow()!=-1){
					buttonModify.setEnabled(true);
					buttonDelete.setEnabled(true);
				}
			}
		});
	}
	
	private class CustomTableModel extends DefaultTableModel{
		
		public CustomTableModel(Object[][] data,String[] labels){
			dataVector = new Vector<TableStructure>();
			initColumnsIdentifiers(labels);
			initData(data);
		}
		
		private void initData(Object[][] data){
			for(Object[] row:data){
				dataVector.addElement(table.createTableStructure(row));
			}
		}
		
		@Override
		public Object getValueAt(int row, int column) {
			return ((TableStructure)dataVector.get(row)).getValues()[column];
		};
		
		@SuppressWarnings("unchecked")
		private void initColumnsIdentifiers(String[] labels){
			for(String label: labels){
				System.out.println(label);
				columnIdentifiers.addElement(label);
			}
		}
		
		public TableStructure getRowStructure(int row){
			return (TableStructure)dataVector.get(row);
		}
		
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	
	}

}
