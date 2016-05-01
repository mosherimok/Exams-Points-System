package mvc_tests;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exceptions.InvalidStructure;
import mvc_dialogs.View;
import tablesStructures.TableStructure;
import tablesStructures.Test;
import ui_components.RegexFormatter;

import javax.swing.JLabel;

import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class ViewTestRecord extends View {
	
	private static final String RECORD_NAME = "îáçï";
	
	//All components:
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> comboBox_categories;
	private JFormattedTextField textField_testName;
	private JSpinner spinner_testDate; 
	
	private final String DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * Create the dialog.
	 */
	public ViewTestRecord(String [] categories) {
		super(RECORD_NAME);
		initGUI(categories);
	}
	
	private void initGUI(String [] categories){
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
					comboBox_categories.setModel(new DefaultComboBoxModel<>(categories));
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
						textField_testName = new JFormattedTextField(new RegexFormatter("^^[a-zA-Zà-ú']+$"));
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
					SpinnerDateModel model = 
							new SpinnerDateModel(Calendar.getInstance().getTime()
									,null,new Date(),Calendar.DAY_OF_YEAR);
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
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{
					JButton btnClear = new JButton("Clear");
					btnClear.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							comboBox_categories.setSelectedIndex(0);
							textField_testName.setText("");
							spinner_testDate.setValue(Calendar.getInstance().getTime());
						}
					});
					buttonPane.add(btnClear);
				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	

	@Override
	public TableStructure createStructureFromFields() {
		//TODO: Validate fields correction
		Test test = new Test();
		
		test.setCategory((String)comboBox_categories.getSelectedItem());
		test.setName(textField_testName.getText());
		
		Date date = ((SpinnerDateModel)(spinner_testDate.getModel())).getDate();
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		
		test.setTestDate(format.format(date));
		
		return test;
	}

	@Override
	public void putStructureIntoFields(TableStructure structure) throws InvalidStructure {
		if(!(structure instanceof Test))
			throw new InvalidStructure("Given structure is not a Test");
		Test test = (Test) structure;
		textField_testName.setText(test.getName());
		comboBox_categories.setSelectedItem(test.getCategory());
		System.out.println(test.getTestDate());
		try {
			((SpinnerDateModel)(spinner_testDate.getModel())).
				setValue(new SimpleDateFormat(DATE_FORMAT).parse(test.getTestDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

}
