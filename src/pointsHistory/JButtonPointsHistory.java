package pointsHistory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTable;

public class JButtonPointsHistory extends JButton{

	private static final long serialVersionUID = 1L;

	public JButtonPointsHistory(JTable jtable,boolean isEnabled) {
		setText("היסטורית נקודות");
		setEnabled(isEnabled);
		
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int studentID = (int)jtable.getValueAt(jtable.getSelectedRow(), 0);
				new HistoryPointsJFrame(studentID);
			}
		});
		
	}

	
}
