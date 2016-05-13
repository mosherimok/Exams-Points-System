package ui_donetests;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import database.DatabaseActions;
import tablesStructures.DoneTest;
import ui_donetests_components.Examinee;
import ui_donetests_components.ExmainesDefaultListModel;

import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class AddGradesToTestJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final int TEST_ID;
	
	//All components:
	private JTextField textFieldStudentName;
	private JComboBox<String> comboBox_Lookfor;
	private String[] allStudents; // First name space Last name.
	private JSpinner grade;
	private JList<Examinee> allExaminees;
	private JButton btnRemoveFromList;
	private JButton saveChanges;
	// END
	
	
	public AddGradesToTestJDialog(int testID) {
		TEST_ID = testID;
		DatabaseActions.setCloseConnectionWhenDone(false);
		initGUI();
		initJDialog();
		initAllStudents();
	}
	

	private void initJDialog() {
		setVisible(true);
		setModal(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	private void initGUI(){
		setBounds(100, 100, 548, 357);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelTitle = new JPanel();
			contentPanel.add(panelTitle, BorderLayout.NORTH);
			panelTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel label = new JLabel("\u05D4\u05D5\u05E1\u05E4\u05EA \u05E6\u05D9\u05D5\u05DF \u05E0\u05D1\u05D7\u05DF \u05E2\u05D1\u05D5\u05E8 \u05DE\u05D1\u05D7\u05DF \u05DE\u05E1\u05E4\u05E8");
				label.setText(label.getText() + " " + TEST_ID);
				panelTitle.add(label);
			}
		}
		{
			JPanel panelAllExaminees = new JPanel();
			panelAllExaminees.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u05E8\u05E9\u05D9\u05DE\u05EA \u05DB\u05DC \u05D4\u05E0\u05D1\u05D7\u05E0\u05D9\u05DD", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panelAllExaminees, BorderLayout.WEST);
			panelAllExaminees.setLayout(new BorderLayout(0, 0));
			{
				allExaminees = new JList<Examinee>(new ExmainesDefaultListModel());
				allExaminees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				allExaminees.addListSelectionListener(new ListSelectionListener() {
					
					@Override
					public void valueChanged(ListSelectionEvent e) {
						boolean isSelectionEmpty = allExaminees.isSelectionEmpty();
							btnRemoveFromList.setEnabled(!isSelectionEmpty);
							saveChanges.setEnabled(!isSelectionEmpty);
					}
				});
				panelAllExaminees.add(new JScrollPane(allExaminees));
			}
			{
				JPanel panelRemoveFromList = new JPanel();
				panelAllExaminees.add(panelRemoveFromList, BorderLayout.SOUTH);
				{
					btnRemoveFromList = new JButton("\u05DE\u05D7\u05E7 \u05DE\u05D4\u05E8\u05E9\u05D9\u05DE\u05D4");
					btnRemoveFromList.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							((ExmainesDefaultListModel)allExaminees.getModel()).
								remove(allExaminees.getSelectedIndex());
						}
					});
					{
						saveChanges = new JButton("\u05E9\u05DE\u05D5\u05E8 \u05E9\u05D9\u05E0\u05D5\u05D9\u05D9\u05DD");
						saveChanges.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Examinee modified = allExaminees.getSelectedValue();
								modified.setGrade((int)grade.getValue());
								((ExmainesDefaultListModel)allExaminees.getModel()).
											replaceWith(modified,allExaminees.getSelectedIndex());
							}
						});
						saveChanges.setEnabled(false);
						panelRemoveFromList.add(saveChanges);
					}
					btnRemoveFromList.setEnabled(false);
					panelRemoveFromList.add(btnRemoveFromList);
				}
			}
		}
		{
			JPanel panelFields = new JPanel();
			panelFields.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u05E4\u05E8\u05D8\u05D9 \u05D4\u05E0\u05D1\u05D7\u05DF", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panelFields, BorderLayout.CENTER);
			panelFields.setLayout(new GridLayout(3, 0, 0, 0));
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				panelFields.add(panel_1);
				panel_1.setLayout(new GridLayout(2, 0, 0, 0));
				{
					JPanel panel = new JPanel();
					panel_1.add(panel);
					{
						textFieldStudentName = new JTextField();
						
						textFieldStudentName.getDocument().addDocumentListener(new DocumentListener() {
							
							@Override
							public void removeUpdate(DocumentEvent e) {
								if(textFieldStudentName.getText().equals("")){
									ActionListener previous = comboBox_Lookfor.getActionListeners()[0];
									comboBox_Lookfor.removeActionListener(previous);
									comboBox_Lookfor.removeAllItems();
									comboBox_Lookfor.addActionListener(previous);
								}
								else{
									System.out.println("Text deleted");
									updateIfStudentFound(textFieldStudentName.getText());
								}
							}
							
							@Override
							public void insertUpdate(DocumentEvent e) {
								updateIfStudentFound(textFieldStudentName.getText());
							}
							
							@Override
							public void changedUpdate(DocumentEvent e) {}
						});

						panel.add(textFieldStudentName);
						textFieldStudentName.setColumns(10);
					}
					{
						JLabel lblStudentName = new JLabel("\u05E9\u05DD \u05D4\u05EA\u05DC\u05DE\u05D9\u05D3:");
						panel.add(lblStudentName);
					}
				}
				{
					JPanel panel = new JPanel();
					panel_1.add(panel);
					{
						comboBox_Lookfor = new JComboBox();
						comboBox_Lookfor.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								textFieldStudentName.setText(comboBox_Lookfor.getSelectedItem().toString());
							}
						});
						panel.add(comboBox_Lookfor);
					}
					{
						JLabel label = new JLabel("\u05D4\u05D0\u05DD \u05D0\u05EA\u05D4 \u05DE\u05D7\u05E4\u05E9 \u05D0\u05EA");
						panel.add(label);
					}
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				panelFields.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					grade = new JSpinner(new SpinnerNumberModel(0,0,100,1));
					panel_1.add(grade);
				}
				{
					JLabel lblGrade = new JLabel("\u05E6\u05D9\u05D5\u05DF:");
					panel_1.add(lblGrade);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
				panelFields.add(panel_1);
				panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					JButton button = new JButton("\u05E0\u05E7\u05D4 \u05E9\u05D3\u05D5\u05EA");
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							textFieldStudentName.setText("");
							grade.setValue(0);
						}
					});
					panel_1.add(button);
				}
				{
					JButton btnAddToList = new JButton("\u05D4\u05D5\u05E1\u05E3 \u05DC\u05E8\u05E9\u05D9\u05DE\u05D4");
					btnAddToList.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							String name = textFieldStudentName.getText();
							if(!IsTextInCombobox(name)){
								JOptionPane.showMessageDialog(null, "אין תלמיד כזה");
								return;
							}
							int Intgrade = (Integer)grade.getValue();
							try {
									int id = Grades.getStudentID(name);
									
									Examinee ex = new Examinee(name, Intgrade);
									((ExmainesDefaultListModel)allExaminees.getModel()).
										addElement(ex);
									ex.setID(id);
							} catch (SQLException e) {
								JOptionPane.showMessageDialog(null, e.getMessage());
								return;
							}
								
								
							
						}
					});
					panel_1.add(btnAddToList);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u05E2\u05D3\u05DB\u05DF \u05D4\u05DB\u05DC");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ExmainesDefaultListModel model = (ExmainesDefaultListModel) allExaminees.getModel();
						for(int i=0;i<model.getSize();i++){
							try {
								Examinee ex = model.get(i);
								DoneTest doneTest = new DoneTest();
								doneTest.setTestid(TEST_ID);
								doneTest.setStudentid(ex.getID());
								doneTest.setGrade(ex.getGrade());
								Grades.addGradesToDoneTest(doneTest);
								Points.updatePoints(TEST_ID,ex.getID(),ex.getID());
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						
						DatabaseActions.setCloseConnectionWhenDone(true);
						DatabaseActions.closeStuff();
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u05D1\u05D8\u05DC \u05D4\u05DB\u05DC");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DatabaseActions.setCloseConnectionWhenDone(true);
						DatabaseActions.closeStuff();
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void initAllStudents() {
		try {
			Object[][] fls = DatabaseActions.getAllQueryData("SELECT f_name,l_name FROM Students");
			allStudents = new String[fls.length];
			for(int i=0;i<fls.length;i++){
				allStudents[i] = fls[i][0]+" " +fls[i][1];
				System.out.println(allStudents[i]);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void updateIfStudentFound(String text){
		ActionListener previous = comboBox_Lookfor.getActionListeners()[0];
		comboBox_Lookfor.removeActionListener(previous);
		comboBox_Lookfor.removeAllItems();
		for(String student:allStudents){
			if(student.toLowerCase().startsWith(text.toLowerCase())){
				comboBox_Lookfor.addItem(student);
			}
		}
		comboBox_Lookfor.addActionListener(previous);
	}

	private boolean IsTextInCombobox(String text){
		for(int i=0;i<comboBox_Lookfor.getItemCount();i++){
			if(comboBox_Lookfor.getItemAt(i).equals(text))
				return true;
		}
		return false;
	}
	/*private boolean isStudentExists(String fullname){
		String[] f_l_names = fullname.split(" ");
		String query = "SELECT ID FROM Students WHERE f_name = '" + f_l_names[0] + "'" + 
							" AND l_name = '" + f_l_names[1] + "'";
		try {
			return (int) Database.executeQuery(query).get(0).get(0);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("No exists such student id");
		}
	}*/

	
	
	
}
