package pointsHistory;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import components_utility.CustomizedJTable;
import database.HistoryPointsDatabase;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class HistoryPointsJFrame extends JFrame {

	//finals:
	private final int STUDENT_ID;
	
	//Objects:
	ModelHistoryPointsJFrame model = new ModelHistoryPointsJFrame();
	
	//Components:
	private JPanel contentPane;
	private JPanel panel;
	private JLabel label;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistoryPointsJFrame frame = new HistoryPointsJFrame(318358587);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HistoryPointsJFrame(int studentID) {
		this.STUDENT_ID = studentID;
		initGUI();
		initJFrame();
	}
	
	private void initJFrame() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		label = new JLabel("\u05D4\u05D9\u05E1\u05D8\u05D5\u05E8\u05D9\u05D9\u05EA \u05E0\u05E7\u05D5\u05D3\u05D5\u05EA \u05E2\u05D1\u05D5\u05E8 \u05D4\u05EA\u05DC\u05DE\u05D9\u05D3");
		panel.add(label);
		
		try {
			scrollPane = new JScrollPane(new CustomizedJTable(model.getDefaultTableModel(STUDENT_ID)));
			contentPane.add(scrollPane, BorderLayout.CENTER);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
