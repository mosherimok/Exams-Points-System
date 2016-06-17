package ui_tests;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components_utility.HebrewJFormattedTextfield;
import components_utility.RegexFormatter;
import mvc_managing_records.View;

import javax.swing.JLabel;

import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;
import javax.swing.DefaultComboBoxModel;

public class ViewTestRecord extends View {
	
	//Finals:
	private static final String RECORD_NAME = "îáçï";
	private final String TEST_NAME_PATTERN = "^[0-9a-zA-Zà-ú'\" ]+$";
	
	//Objects:
	public int testID;
	
	//components:
	private final JPanel contentPanel = new JPanel();
	public JComboBox<String> comboBox_categories;
	public HebrewJFormattedTextfield textField_testName;
	public JSpinner spinner_testDate;
//	String[] categories;
	
	public final String DATE_FORMAT = "dd-MM-yyyy";

	/**
	 * Create the dialog.
	 */
	public ViewTestRecord() {
		super(RECORD_NAME);
		initGUI();
	}
	
	public ViewTestRecord(String[] categories) {
		super(RECORD_NAME);
		initGUI();
		setCategories(categories);
	}
	
	private void initGUI(){
		setBounds(100, 100, 338, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelTitle = new JPanel();
			contentPanel.add(panelTitle, BorderLayout.NORTH);
			{
				JLabel label = new JLabel("\u05D4\u05D5\u05E1\u05E4\u05EA \u05DE\u05D1\u05D7\u05DF \u05D7\u05D3\u05E9");
				panelTitle.add(label);
			}
		}
		{
			JPanel panelFields = new JPanel();
			contentPanel.add(panelFields, BorderLayout.CENTER);
			panelFields.setLayout(new GridLayout(3, 0, 0, 0));
			{
				JPanel panelFieldCat = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				panelFields.add(panelFieldCat);
				{
					comboBox_categories = new JComboBox<>();
					comboBox_categories.setModel(new DefaultComboBoxModel<>());
					panelFieldCat.add(comboBox_categories);
				}
				{
					JLabel label = new JLabel("\u05E1\u05D5\u05D2 \u05D4\u05DE\u05D1\u05D7\u05DF:");
					panelFieldCat.add(label);
				}
			}
			{
				JPanel panelFieldName = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				panelFields.add(panelFieldName);
				{
					try {
						textField_testName = new HebrewJFormattedTextfield(new RegexFormatter(TEST_NAME_PATTERN));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					panelFieldName.add(textField_testName);
					textField_testName.setColumns(20);
				}
				{
					JLabel label = new JLabel("\u05E9\u05DD \u05D4\u05DE\u05D1\u05D7\u05DF:");
					panelFieldName.add(label);
				}
			}
			{
				JPanel panelFieldDate = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				panelFields.add(panelFieldDate);
				{
					spinner_testDate = new JSpinner();
					Date date = new Date();
					SpinnerDateModel model = 
							new SpinnerDateModel(date,null,date,Calendar.DAY_OF_MONTH);
					
					spinner_testDate.setModel(model);
					spinner_testDate.setEditor(new JSpinner.DateEditor(spinner_testDate,DATE_FORMAT));
					
					panelFieldDate.add(spinner_testDate);
				}
				{
					JLabel label = new JLabel("\u05EA\u05D0\u05E8\u05D9\u05DA \u05D4\u05DE\u05D1\u05D7\u05DF:");
					panelFieldDate.add(label);
				}
			}
		}
	}
	
	public void setCategories(String[] categories){
		if (categories!=null)
			comboBox_categories.setModel(new DefaultComboBoxModel<String>(categories));
	}

	@Override
	protected void initButtons() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
			buttonPane.add(clearButton);
		}
	}

}
