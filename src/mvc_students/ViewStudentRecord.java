package mvc_students;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import exceptions.InvalidStructure;
import mvc_dialogs.View;
import tablesStructures.Student;
import tablesStructures.TableStructure;
import ui_components.LimitDocument;
import ui_components.RegexFormatter;
import ui_components.RegexMaskFormatter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;

public class ViewStudentRecord extends View{
	
	private final JPanel contentPanel = new JPanel();
	private static final String RECORD_NAME = "תלמיד";
	
	//All components:
	public JFormattedTextField id;
	public JFormattedTextField textField_f_name;
	public JFormattedTextField textField_l_name;
	public JSpinner rec_year,points;
	
	public ViewStudentRecord() {
		super(RECORD_NAME);
		initGUI();
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
//					try {
//						MaskFormatter mask = new MaskFormatter("###-###-###");
					RegexMaskFormatter rmf;
					try {
						rmf = new RegexMaskFormatter("###-###-###","\\d\\d\\d-\\d\\d\\d-\\d\\d\\d");
						id = new JFormattedTextField(rmf);
					} catch (ParseException e) {
						e.printStackTrace();
					}
						
						
/*					} catch (ParseException e) {
						e.printStackTrace();
					}*/
					
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
				JPanel pnlFullName = new JPanel();
				pnlFields.add(pnlFullName);
				{
					try {
						textField_l_name = new JFormattedTextField(new RegexFormatter("^[a-zA-Zא-ת']+$"));
						textField_l_name.setDocument(new LimitDocument(25));
						textField_l_name.setHorizontalAlignment(SwingConstants.RIGHT);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					
					
					pnlFullName.add(textField_l_name);
					textField_l_name.setColumns(20);
				}
				{
					JLabel label = new JLabel("\u05E9\u05DD \u05DE\u05E9\u05E4\u05D7\u05D4:");
					pnlFullName.add(label);
				}
				{
					try {
						textField_f_name = new JFormattedTextField(new RegexFormatter("^[a-zA-Zא-ת']+$"));
						textField_f_name.setDocument(new LimitDocument(25));
						textField_f_name.setHorizontalAlignment(SwingConstants.RIGHT);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					/*textField_f_name.addKeyListener(new KeyAdapter() {
						
						@Override
						public void keyTyped(KeyEvent e) {
							if(!Character.isLetter(e.getKeyChar())||
									textField_f_name.getText().length()>20)
								e.consume();
						}
					});*/
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
							LocalDate.now().getYear()-30,LocalDate.now().getYear()-30,LocalDate.now().getYear(), 1));
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
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				//Clear Button
				JButton button = new JButton("\u05E0\u05E7\u05D4 \u05D4\u05DB\u05DC");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						id.setValue(null);
						textField_f_name.setText("");
						textField_f_name.setText("");
						rec_year.setValue(LocalDate.now().getYear()-30);
						points.setValue(0);
					}
				});
				buttonPane.add(button);
			}
			{
				JButton button = new JButton("\u05D1\u05D9\u05D8\u05D5\u05DC");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				buttonPane.add(button);
			}
			{
				//Ok button
				buttonPane.add(okButton);
			}
			
		}
	}
	
	

	@Override
	public TableStructure createStructureFromFields() {
		Student student = new Student();
		student.setId(Integer.parseInt(id.getText().replace("-","")));
		student.setFirstName(textField_f_name.getText());
		student.setLastName(textField_l_name.getText());
		student.setReceptionYear((short)rec_year.getValue());
		student.setPoints((int)points.getValue());
		return student;
	}

	@Override
	public void putStructureIntoFields(TableStructure structure) throws InvalidStructure {
		if(!(structure instanceof Student))
			throw new InvalidStructure("Given structure is not a Student");
		Student student = (Student) structure;
		
		id.setText(String.valueOf(student.getId()));
		
		textField_f_name.setText(student.getFirstName());
		
		textField_l_name.setText(student.getLastName());
		
		rec_year.setValue(student.getReceptionYear());
		
		points.setValue(student.getPoints());
	}
	
}
