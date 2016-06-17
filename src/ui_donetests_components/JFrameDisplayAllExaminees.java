package ui_donetests_components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Action;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import components_done_tests.DoneTestUtilities;
import components_utility.CustomizedJTable;
import database.Database;

import javax.swing.WindowConstants;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JFrameDisplayAllExaminees extends JFrame {

	private int testID;
	
	private final JPanel contentPanel = new JPanel();
	private CustomizedJTable jtable;
	private ArrayList<Integer> grades = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			JFrameDisplayAllExaminees dialog = new JFrameDisplayAllExaminees(2);
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public JFrameDisplayAllExaminees(int testid) {
		this.testID = testid;
		if(initJTable()){
			initGUI();
			initJDialog();
		}
		else
			dispose();
	}
	
	private void initJDialog() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void initGUI(){
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JScrollPane scrollPane = new JScrollPane(jtable);
			contentPanel.add(scrollPane, BorderLayout.NORTH);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("שמור שינויים");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						for(int i=0;i<grades.size();i++){
							int grade = Integer.parseInt(jtable.getValueAt(i, 3).toString());
							System.out.println("jtable grade: " + grade);
							if(grades.get(i)!=grade){ 
								int studentID = (int) jtable.getValueAt(i, 0);
								try {
									DoneTestUtilities.changeDoneTestGrade(studentID, testID, grade);
									System.out.println("grade changed to " + grade);
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							}
						}
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	private boolean initJTable(){
		Database.closeConnectionWhenDoneOperation=false;
		String script = "SELECT std.id,std.f_name,std.l_name,dt.grade,std.points FROM Students std " +
						"JOIN doneTests dt on std.id=dt.studentid "+
						"WHERE TestID = " + testID;
		try {
			Object [][] data = Database.
					executeQuery(script);
			String[] columnsIdentifiers = {"ת\"ז תלמיד","שם פרטי","שם משפחה","ציון","ניקוד מתאים"};
			
			if(data.length==0){
				JOptionPane.showMessageDialog(null, "אין נבחנים עבור מבחן זה");
				return false;
			}
			
			jtable = new CustomizedJTable(){
				
				@Override
				public boolean isCellEditable(int row, int column) {
					if(column==3)
						return true;
					return false;
				}
				
			};
			
			
			jtable.setDefaultTableModel(new DefaultTableModel(data,columnsIdentifiers));
			
			for (Object[] row : data){
				grades.add((int)row[3]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

}
