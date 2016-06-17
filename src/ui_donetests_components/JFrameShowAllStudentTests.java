package ui_donetests_components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import components_utility.CustomizedJTable;
import database.Database;

public class JFrameShowAllStudentTests extends JFrame {

	//Finals:
	private final String[] COLUMNS_LABELS = {"מזהה מבחן","שם המבחן","קטגורית מבחן","תאריך",
											 "ציון","ניקוד מתאים"
											};
	private final int STUDENT_ID;
	
	//Components:
	private final JPanel contentPanel = new JPanel();
	private CustomizedJTable jtable;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JFrameShowAllStudentTests dialog = new JFrameShowAllStudentTests(111111111);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public JFrameShowAllStudentTests(int studentID) {
		this.STUDENT_ID = studentID;
		initJTable();
		initGUI();
		initJDialog();
	}
	
	private void initJDialog() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initJTable() {
		String script = "SELECT t.rowid,t.name,t.category,t.testDate,dt.grade "+
						"FROM tests t join doneTests dt on t.rowid=dt.testID "+
						"WHERE dt.studentID = " + STUDENT_ID;
		Object[][] values;
		try {
			values = Database.executeQuery(script);
			jtable = new CustomizedJTable(new DefaultTableModel(values, COLUMNS_LABELS));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void initGUI(){
		setBounds(100, 100, 578, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane(jtable);
			contentPanel.add(scrollPane, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u05E1\u05D2\u05D5\u05E8");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	

}
