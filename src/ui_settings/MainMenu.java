package ui_settings;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Insets;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.BoxLayout;
import javax.swing.Box;

public class MainMenu extends JFrame {
	private JPanel panel;
	private JLabel label;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JButton button;
	private Component verticalStrut;

	/**
	 * Create the panel.
	 */
	public MainMenu() {
		initGUI();
		initJFrame();
	}
	
	private void initJFrame() {
		setSize(313,207);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void initGUI() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		
		label = new JLabel("\u05D4\u05D2\u05D3\u05E8\u05D5\u05EA");
		panel.add(label);
		
		panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		panel_2 = new JPanel();
		panel_1.add(panel_2);
		
		panel_3 = new JPanel();
//		panel_3.setAlignmentX(CENTER_ALIGNMENT);
		panel_3.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
		panel_1.add(panel_3);
		
		button = new JButton("\u05D9\u05D9\u05D1\u05D5\u05D0 \u05EA\u05DC\u05DE\u05D9\u05D3\u05D9\u05DD");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DialogImportStudents();
			}
		});
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		verticalStrut = Box.createVerticalStrut(20);
		panel_3.add(verticalStrut);
		panel_3.add(button);
	}

}
