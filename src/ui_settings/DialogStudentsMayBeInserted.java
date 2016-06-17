package ui_settings;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import components_utility.CustomizedJTable;
import tables.TblStudents;

import javax.swing.WindowConstants;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class DialogStudentsMayBeInserted extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private CustomizedJTable jtable;
	private JLabel label;
	

	/**
	 * Create the dialog.
	 */
	public DialogStudentsMayBeInserted(Object[][] values) {
		initGUI(values);
		initFrame();
	}
	
	private void initFrame(){
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	private void initGUI(Object[][] values){
		setBounds(100, 100, 486, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			jtable = new CustomizedJTable(new DefaultTableModel(values,
					new TblStudents().getColumnsLabels()));
			contentPanel.add(new JScrollPane(jtable));
		}
		
		label = new JLabel("\u05E1\u05DA \u05DB\u05DC \u05D4\u05EA\u05DC\u05DE\u05D9\u05D3\u05D9\u05DD \u05E9\u05D4\u05E6\u05DC\u05D9\u05D7\u05D5 \u05DC\u05D4\u05D9\u05E7\u05E8\u05D0 \u05DE\u05EA\u05D5\u05DA \u05D4\u05E7\u05D5\u05D1\u05E5:");
		label.setText(label.getText()+ " " + values.length);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(label, BorderLayout.NORTH);
	}

}
