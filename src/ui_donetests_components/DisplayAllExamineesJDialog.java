package ui_donetests_components;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import database.DatabaseActions;
import ui_components.TextAreaRenderer;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DisplayAllExamineesJDialog extends JDialog {

	private int testID;
	
	private final JPanel contentPanel = new JPanel();
	private JTable jtable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DisplayAllExamineesJDialog dialog = new DisplayAllExamineesJDialog(2);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DisplayAllExamineesJDialog(int testid) {
		this.testID = testid;
		initJTable();
		initGUI();
		initJDialog();
	}
	
	private void initJDialog() {
		setLocationRelativeTo(null);
		setModal(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	private void initJTable(){
		jtable = new JTable();
		DatabaseActions.setCloseConnectionWhenDone(false);
		String script = "SELECT std.id,std.f_name,std.l_name,dt.grade,std.points FROM Students std " +
						"JOIN doneTests dt on std.id=dt.studentid "+
						"WHERE TestID = " + testID;
		try {
			Object [][] data = DatabaseActions.getAllQueryData(script);
			String[] columnsIdentifiers = {"ת\"ז תלמיד","שם פרטי","שם משפחה","ציון","נקודות"};
			jtable.setModel(new DefaultTableModel(data,columnsIdentifiers));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		jtable.setCellSelectionEnabled(false);
//		jtable.setRowHeight(20);
		jtable.setDefaultRenderer(String.class, new TextAreaRenderer());

		Font font = new Font("Ariel", Font.PLAIN, 16);
		jtable.setFont(font);
		jtable.getTableHeader().setFont(font);
		
		
	}

}
