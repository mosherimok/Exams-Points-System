package ui_menuPanels;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ui_components.AbstractJPanel;
import ui_searchRecord.SearchRecord;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Component;

import javax.swing.Box;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import actions.AddGradesActions;
import actions.ButtonsActions;
import actions.MV_Factory.Views;
import tables.TableGetter;
import tables.TblTests;

import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestsJPanel extends AbstractJPanel {


	private static final long serialVersionUID = 1L;

	//Addition buttons:
	JButton btnAddGrades;
	
	//Addition action-commands:
	private final String ADD_GRADES = "AddGrades";
	
	public TestsJPanel() {
		super(TableGetter.getTable(TblTests.class));
	}
	
	@Override
	protected void initGUI(){
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
		add(panelTitle, BorderLayout.NORTH);
		
		
		JLabel label = new JLabel("\u05DE\u05D1\u05D7\u05E0\u05D9\u05DD \u05E9\u05E0\u05E2\u05E9\u05D5");
		label.setFont(new Font("Segoe UI", Font.BOLD, 14));
		panelTitle.add(label);
		
		JPanel panelContent = new JPanel();
		add(panelContent, BorderLayout.CENTER);
		panelContent.setLayout(new BorderLayout(0, 0));
		
		JPanel panelUpper = new JPanel();
		panelContent.add(panelUpper, BorderLayout.NORTH);
		panelUpper.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel panelEdition = new JPanel();
		panelEdition.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelUpper.add(panelEdition);
		panelEdition.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnDelete = new JButton("\u05DE\u05D7\u05D9\u05E7\u05EA \u05DE\u05D1\u05D7\u05DF \u05D6\u05D4");
		btnDelete.setEnabled(false);
		btnDelete.setAlignmentX(Component.CENTER_ALIGNMENT);
//		btnDelete.addActionListener(actionListener);
		panelEdition.add(btnDelete);
		
		btnModify = new JButton("\u05E2\u05E8\u05D9\u05DB\u05EA \u05DE\u05D1\u05D7\u05DF \u05D6\u05D4");
		btnModify.setEnabled(false);
		btnModify.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnModify.addActionListener(mapActionListener);
		panelEdition.add(btnModify);
		
		btnAdd = new JButton("\u05D4\u05D5\u05E1\u05E4\u05EA \u05DE\u05D1\u05D7\u05DF \u05D7\u05D3\u05E9");
		btnAdd.addActionListener(mapActionListener);
		btnAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelEdition.add(btnAdd);
		
		Component verticalStrut_2 = Box.createVerticalStrut(50);
		panelEdition.add(verticalStrut_2);
		
		JPanel panelCenter = new JPanel();
	
		JPanel panelTable = new JPanel();
		panelTable.setBorder(new TitledBorder(null, "\u05D8\u05D1\u05DC\u05EA \u05D4\u05DE\u05D1\u05D7\u05E0\u05D9\u05DD", TitledBorder.RIGHT, TitledBorder.TOP, null, null));
		
		panelCenter.setLayout(new BorderLayout(0, 0));
		panelTable.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane(jtable);
		scrollPane.setBorder(null);
		
		panelTable.add(scrollPane);
		
		panelCenter.add(panelTable, BorderLayout.CENTER);
		
		panelContent.add(panelCenter, BorderLayout.CENTER);
		
		JPanel panelTestActions = new JPanel();
		panelTestActions.setBorder(new TitledBorder(null, "\u05E4\u05E2\u05D5\u05DC\u05D5\u05EA \u05DE\u05D1\u05D7\u05DF", TitledBorder.RIGHT, TitledBorder.TOP, null, null));
		panelCenter.add(panelTestActions, BorderLayout.EAST);
		panelTestActions.setLayout(new BoxLayout(panelTestActions, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panelTestActions.add(verticalStrut);
		
		btnAddGrades = new JButton("\u05D4\u05D5\u05E1\u05E4\u05EA \u05E6\u05D9\u05D5\u05E0\u05D9\u05DD \u05DC\u05DE\u05D1\u05D7\u05DF \u05D6\u05D4");
		btnAddGrades.addActionListener(mapActionListener);
		btnAddGrades.setEnabled(false);
		btnAddGrades.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelTestActions.add(btnAddGrades);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panelTestActions.add(verticalStrut_1);
		
		JButton buttonViewTested = new JButton("<html>\r\n\u05D4\u05E6\u05D2\u05EA \u05DB\u05DC \u05D4\u05E0\u05D1\u05D7\u05E0\u05D9\u05DD<br>\r\n\u05E2\u05D1\u05D5\u05E8 \u05DE\u05D1\u05D7\u05DF \u05D6\u05D4\r\n</html>");
		buttonViewTested.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelTestActions.add(buttonViewTested);

		JPanel panelSearch = new SearchRecord(jtable);
		panelUpper.add(panelSearch);
	}
	
	@Override
	protected void initButtonsActionCommand() {
		btnAdd.setActionCommand(ADD);
		btnModify.setActionCommand(MODIFY);
		btnDelete.setActionCommand(Delete);
		btnAddGrades.setActionCommand(ADD_GRADES);
	}
	
	@Override
	protected void initActionListeners() {
		ButtonsActions buttonsActions = new ButtonsActions(jtable,Views.Tests);
		mapActionListener.addAction(ADD,buttonsActions.getAddAction());
		mapActionListener.addAction(MODIFY,buttonsActions.getModifyAction());
	}
	
	@Override
	protected void initTable() {
		super.initTable();
		jtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(jtable.getSelectedRow()!=-1){
					btnAddGrades.setEnabled(true);
				}
			}
		});
	}

}
