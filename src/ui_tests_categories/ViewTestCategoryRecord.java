package ui_tests_categories;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;

import javax.swing.border.EtchedBorder;

import components_utility.HebrewJFormattedTextfield;
import components_utility.LimitedDocument;
import components_utility.RegexFormatter;
import mvc_managing_records.View;

import java.awt.Font;
import javax.swing.Box;
import java.awt.Component;
import java.text.ParseException;

public class ViewTestCategoryRecord extends View {

	//Finals:
	private static final String RECORD_NAME = "סוג מבחן";
	private final String TEST_TYPE_PATTERN = "^[0-9a-zA-Zא-ת'\" ]+$";
	
	//components:
	private final JPanel contentPanel = new JPanel();
	public HebrewJFormattedTextfield textFieldTestType;
	public JSpinner spinnerPoints1,spinnerPoints2,spinnerPoints3;
	
	public ViewTestCategoryRecord() {
		super(RECORD_NAME);
		initGUI();
	}

	private void initGUI(){
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel pnlTitle = new JPanel();
			contentPanel.add(pnlTitle, BorderLayout.NORTH);
			{
				JLabel label = new JLabel("\u05D4\u05D5\u05E1\u05E4\u05EA \u05E1\u05D5\u05D2 \u05DE\u05D1\u05D7\u05DF \u05D7\u05D3\u05E9");
				label.setFont(new Font("Segoe UI", Font.BOLD, 13));
				pnlTitle.add(label);
			}
		}
		{
			JPanel pnlFields = new JPanel();
			contentPanel.add(pnlFields, BorderLayout.CENTER);
			pnlFields.setLayout(new GridLayout(4, 0, 0, 0));
			{
				JPanel pnlFieldsCateg = new JPanel();
				pnlFields.add(pnlFieldsCateg);
				{
					try {
						textFieldTestType = new HebrewJFormattedTextfield(new RegexFormatter(TEST_TYPE_PATTERN));
						textFieldTestType.setDocument(new LimitedDocument(20));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					pnlFieldsCateg.add(textFieldTestType);
					textFieldTestType.setColumns(20);
				}
				{
					JLabel label = new JLabel("\u05E9\u05DD \u05E1\u05D5\u05D2 \u05D4\u05DE\u05D1\u05D7\u05DF:");
					pnlFieldsCateg.add(label);
				}
			}
			{
				JPanel pnlFieldsRange1 = new JPanel();
				pnlFieldsRange1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				pnlFields.add(pnlFieldsRange1);
				pnlFieldsRange1.setPreferredSize(new Dimension(20, 20));
				pnlFieldsRange1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					JLabel label = new JLabel("\u05E0\u05E7\u05D5\u05D3\u05D5\u05EA");
					pnlFieldsRange1.add(label);
				}
				
				{
					spinnerPoints1 = new JSpinner(new SpinnerNumberModel(0,0,null,1));
					spinnerPoints1.setPreferredSize(new Dimension(60,25));
					pnlFieldsRange1.add(spinnerPoints1);
				}
				{
					JLabel label = new JLabel("\u05D4\u05D7\u05DC \u05DE\u05E6\u05D9\u05D5\u05DF 75 \u05E2\u05D3 84 \u05E9\u05D5\u05D5\u05D4");
					pnlFieldsRange1.add(label);
				}
			}
			{
				JPanel pnlFieldsRange2 = new JPanel();
				pnlFieldsRange2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				pnlFieldsRange2.setPreferredSize(new Dimension(20, 20));
				pnlFields.add(pnlFieldsRange2);
				pnlFieldsRange2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					JLabel label = new JLabel("\u05E0\u05E7\u05D5\u05D3\u05D5\u05EA");
					pnlFieldsRange2.add(label);
				}
				{
					spinnerPoints2 = new JSpinner(new SpinnerNumberModel(0,0,null,1));
					spinnerPoints2.setPreferredSize(new Dimension(60, 25));
					pnlFieldsRange2.add(spinnerPoints2);
				}
				{
					JLabel label = new JLabel("\u05D4\u05D7\u05DC \u05DE\u05E6\u05D9\u05D5\u05DF 85 \u05E2\u05D3 94 \u05E9\u05D5\u05D5\u05D4");
					pnlFieldsRange2.add(label);
				}
			}
			{
				JPanel pnlFieldsRange3 = new JPanel();
				pnlFieldsRange3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				pnlFieldsRange3.setPreferredSize(new Dimension(20, 20));
				pnlFields.add(pnlFieldsRange3);
				pnlFieldsRange3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					JLabel label = new JLabel("\u05E0\u05E7\u05D5\u05D3\u05D5\u05EA");
					pnlFieldsRange3.add(label);
				}
				{
					spinnerPoints3 = new JSpinner(new SpinnerNumberModel(0,0,null,1));
					spinnerPoints3.setPreferredSize(new Dimension(60, 25));
					pnlFieldsRange3.add(spinnerPoints3);
				}
				{
					JLabel label = new JLabel("\u05D4\u05D7\u05DC \u05DE\u05E6\u05D9\u05D5\u05DF 95 \u05E2\u05D3 100 \u05E9\u05D5\u05D5\u05D4");
					pnlFieldsRange3.add(label);
				}
			}
		}
	}

	@Override
	protected void initButtons() {
		JPanel pnlButtons = new JPanel();
		pnlButtons.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(pnlButtons, BorderLayout.SOUTH);
		{
			Component rigidArea = Box.createRigidArea(new Dimension(80, 40));
			pnlButtons.add(rigidArea);
		}
		{
			pnlButtons.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		{
			Component rigidArea = Box.createRigidArea(new Dimension(30, 20));
			pnlButtons.add(rigidArea);
		}
		{
			pnlButtons.add(clearButton);
		}
	}

}
