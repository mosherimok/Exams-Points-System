package ui_students;

import javax.swing.Box;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import components_utility.HebrewJFormattedTextfield;
import components_utility.RegexFormatter;
import components_utility.RegexMaskFormatter;
import mvc_managing_records.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.font.TextAttribute;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Vector;

public class ViewDialogStudent extends View{
	
	//Finals:
	private static final String RECORD_NAME = "תלמיד";
	private final String FL_PATTERN = "^[a-zA-Zא-ת'\" ]+$";
	
	
	//Components:
	private final JPanel contentPanel = new JPanel();
	public JFormattedTextField id;
	public HebrewJFormattedTextfield textField_f_name;
	public HebrewJFormattedTextfield textField_l_name;
	public JSpinner rec_year,points;
	
	public ViewDialogStudent() {
		super(RECORD_NAME);
		initGUI();
		initFocusTravelsalPolicy();
	}
	
	
	
	/*public void initDialogProperties() {
		dialog.setModal(true);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}*/
	private void initGUI() {
		setBounds(100, 100, 523, 283);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel titlePnl = new JPanel();
			contentPanel.add(titlePnl, BorderLayout.NORTH);
			{
				JLabel label = new JLabel("\u05D4\u05D5\u05E1\u05E4\u05EA \u05EA\u05DC\u05DE\u05D9\u05D3 \u05D7\u05D3\u05E9");
				HashMap<TextAttribute, Integer> underline = new HashMap<TextAttribute,Integer>();
				underline.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);		
				label.setFont(new Font("Serif", Font.PLAIN, 18));
				titlePnl.add(label);
			}
		}
		{
			JPanel pnlFields = new JPanel();
			pnlFields.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u05E4\u05E8\u05D8\u05D9 \u05EA\u05DC\u05DE\u05D9\u05D3", TitledBorder.RIGHT, TitledBorder.TOP, null, Color.BLACK));
			contentPanel.add(pnlFields, BorderLayout.CENTER);
			pnlFields.setLayout(new GridLayout(4, 0, 0, 0));
			{
				JPanel pnlID = new JPanel();
				pnlFields.add(pnlID);
				pnlID.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				{
					id = new JFormattedTextField();
					RegexMaskFormatter rmf;
					try {
						rmf = new RegexMaskFormatter("###-###-###","\\d\\d\\d-\\d\\d\\d-\\d\\d\\d");
						id = new JFormattedTextField(rmf);
					} catch (ParseException e) {
						e.printStackTrace();
					}
						

					
					id.setColumns(9);
					pnlID.add(id);
				}
				{
					JLabel label = new JLabel("\u05EA\"\u05D6:");
					pnlID.add(label);
					pnlID.add(Box.createRigidArea(new Dimension(4, 0)));
				}
			}
			{
				JPanel pnlFullName = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				pnlFields.add(pnlFullName);
				{
					try {
						textField_l_name = new HebrewJFormattedTextfield(new RegexFormatter(FL_PATTERN));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					
					
					pnlFullName.add(textField_l_name);
					textField_l_name.setColumns(20);
				}
				{
					JLabel label = new JLabel("\u05E9\u05DD \u05DE\u05E9\u05E4\u05D7\u05D4:");
					pnlFullName.add(label);
				
					try {
						textField_f_name = new HebrewJFormattedTextfield(new RegexFormatter(FL_PATTERN));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					pnlFullName.add(textField_f_name);
					textField_f_name.setColumns(20);
				}
				{
					JLabel label = new JLabel("\u05E9\u05DD \u05E4\u05E8\u05D8\u05D9:");
					pnlFullName.add(label);
				}
			}
			{
				JPanel pnlClass = new JPanel();
				pnlFields.add(pnlClass);
				{
					JPanel panel = new JPanel();
					pnlClass.add(panel);
					panel.setLayout(new BorderLayout(0, 0));
				}
				{
					rec_year = new JSpinner();
					rec_year.setPreferredSize(new Dimension(110, rec_year.getPreferredSize().height));
//					rec_year.setModel(new SpinnerListModel(new String[] {"\u05D0", "\u05D1", "\u05D2", "\u05D3", "\u05D4", "\u05D5", "\u05D6", "\u05D7", "\u05D8", "\u05D9", "\u05D9\u05D0", "\u05D9\u05D1", "\u05D9\u05D2", "\u05D9\u05D3", "\u05D8\u05D5", "\u05D8\u05D6", "\u05D9\u05D6", "\u05D9\u05D7", "\u05D9\u05D8", "\u05DB", "\u05DB\u05D0", "\u05DB\u05D1", "\u05DB\u05D2", "\u05DB\u05D3", "\u05DB\u05D4", "\u05DB\u05D5", "\u05DB\u05D6", "\u05DB\u05D7", "\u05DB\u05D8", "\u05DC"}));
					// Set inital value, minimum and maximum.
					rec_year.setModel(new SpinnerNumberModel(
							LocalDate.now().getYear(),LocalDate.now().getYear()-30,LocalDate.now().getYear(), 1));
					rec_year.setEditor(new JSpinner.NumberEditor(rec_year,"#"));
					pnlClass.add(rec_year);
				}
				{
					JLabel label = new JLabel("\u05E9\u05E0\u05EA \u05E7\u05D1\u05DC\u05D4 \u05DC\u05D9\u05E9\u05D9\u05D1\u05D4");
					pnlClass.add(label);
				}
			}
			{
				JPanel pnlPoints = new JPanel();
				pnlFields.add(pnlPoints);
				{
					points = new JSpinner(new SpinnerNumberModel());
					pnlPoints.add(points);
					points.setPreferredSize(new Dimension(70, 20));
				}
				{
					JLabel label = new JLabel("\u05DE\u05E1\u05E4\u05E8 \u05E0\u05E7\u05D5\u05D3\u05D5\u05EA \u05E0\u05D5\u05DB\u05D7\u05D9:");
					pnlPoints.add(label);
				}
			}
		}
	}
	
	private void initFocusTravelsalPolicy() {
		Vector<Component> order = new Vector<>();
		order.addElement(id);
		order.addElement(textField_f_name);
		order.addElement(textField_l_name);
		order.addElement(points);
		order.addElement(rec_year);
		setFocusTraversalPolicy(new CustomedFocusTraversalPolicy(order));
	}



	@Override
	protected void initButtons() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			buttonPane.add(okButton);
			buttonPane.add(clearButton);
		}
	}
	
}
