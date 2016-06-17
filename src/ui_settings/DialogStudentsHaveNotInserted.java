package ui_settings;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import components_utility.CustomizedJTable;
import database.FailedUpdatesList;
import tables.TblStudents;

import javax.swing.WindowConstants;

public class DialogStudentsHaveNotInserted extends JFrame {

	private final JPanel contentPanel = new JPanel();
	private CustomizedJTable jtable;
	

	/**
	 * Create the dialog.
	 */
	public DialogStudentsHaveNotInserted(FailedUpdatesList faileds) {
		initGUI(faileds);
		initFrame();
	}
	
	private void initFrame(){
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	private void initGUI(FailedUpdatesList faileds){
		setBounds(100, 100, 486, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			TblStudents tbl = new TblStudents();
			String[] colsLabels = new String[tbl.getColumnsLabels().length+1];
			for(int i =0;i<colsLabels.length-1;i++){
				colsLabels[i]=(tbl.getColumnsLabels()[i]);
			}
			colsLabels[colsLabels.length-1] = "ριαδ";
			
			DefaultTableModel dtm = new DefaultTableModel(colsLabels,0){
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			System.out.println(faileds.getSize());
			for(int i=0;i<faileds.getSize();i++){
				dtm.addRow(faileds.getThemBoth(i));
			}
			
			jtable = new CustomizedJTable(dtm);
			contentPanel.add(new JScrollPane(jtable));
		}
	}

}
