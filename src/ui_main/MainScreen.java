package ui_main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout.Constraints;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import database.DatabaseConnection;
import ui_components.AbstractJPanel;
import ui_menuPanels.StudentsDetailsPnl;
import ui_menuPanels.TestsCategoriesPnl;
import ui_menuPanels.TestsJPanel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.Box;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;

public class MainScreen {

	private JFrame frame;
	private JPanel pnlCardLayout;
	private final String BLANK_PNL = "BlankPnl";
	private final String STUDENTS_PNL = "StudentsPnl";
	private final String DONE_TESTS_PNL = "DoneTestsPnl";
	private final String TESTS_CATEGORIES = "TestsCategories";
	
	
	private JPanel currentView;

	public MainScreen() {
		frame = new JFrame();
		init_look_and_feel();
		initialize();
		init_onClose();
		init_jframe_features();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		init_pnl_cards();
		
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		frame.getContentPane().add(pnlTitle, BorderLayout.NORTH);
		
		JLabel label = new JLabel("\u05D1\u05E8\u05D5\u05DA \u05D4\u05D1\u05D0 \u05DC\u05DE\u05E2\u05E8\u05DB\u05EA \u05E0\u05D9\u05D4\u05D5\u05DC \u05DE\u05D1\u05D7\u05E0\u05D9\u05DD");
		pnlTitle.add(label);
		
		JPanel pnlMenu = new JPanel();
		pnlMenu.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u05EA\u05E4\u05E8\u05D9\u05D8", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 0))));
		frame.getContentPane().add(pnlMenu, BorderLayout.EAST);
		
		pnlMenu.setLayout(new BoxLayout(pnlMenu, BoxLayout.Y_AXIS));
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		pnlMenu.add(verticalStrut_2);
		
		JButton btnStdsDetails = new JButton("\u05E4\u05E8\u05D8\u05D9 \u05EA\u05DC\u05DE\u05D9\u05D3\u05D9\u05DD");
		btnStdsDetails.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlMenu.add(btnStdsDetails);
		
		Component verticalStrut = Box.createVerticalStrut(30);
		pnlMenu.add(verticalStrut);
		
		JButton btnDoneTests = new JButton("\u05DE\u05D1\u05D7\u05E0\u05D9\u05DD \u05E9\u05E0\u05E2\u05E9\u05D5");
		btnDoneTests.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentView!=null){
					pnlCardLayout.remove(currentView);
					currentView.removeAll();
				}
				currentView = new TestsJPanel();
				pnlCardLayout.add(currentView,DONE_TESTS_PNL);
				((CardLayout)pnlCardLayout.getLayout()).show(pnlCardLayout, DONE_TESTS_PNL);
			}
		});
		btnDoneTests.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlMenu.add(btnDoneTests);
		
		Component verticalStrut_1 = Box.createVerticalStrut(30);
		pnlMenu.add(verticalStrut_1);
		
		JButton btnTestsCategories = new JButton("\u05E1\u05D5\u05D2\u05D9 \u05DE\u05D1\u05D7\u05E0\u05D9\u05DD");
		btnTestsCategories.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentView!=null){
					pnlCardLayout.remove(currentView);
					currentView.removeAll();
				}
				currentView = new TestsCategoriesPnl();
				pnlCardLayout.add(currentView,TESTS_CATEGORIES);
				((CardLayout)pnlCardLayout.getLayout()).show(pnlCardLayout, TESTS_CATEGORIES);
			}
		});
		btnTestsCategories.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlMenu.add(btnTestsCategories);
		
		Component verticalStrut_3 = Box.createVerticalStrut(120);
		pnlMenu.add(verticalStrut_3);
		
		JButton btnNewButton = new JButton("\u05E8\u05E2\u05E0\u05DF \u05DE\u05D9\u05D3\u05E2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				((AbstractJPanel)currentView).refreshDataFromDB();
			}
		});
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlMenu.add(btnNewButton);
		
		
		btnStdsDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(currentView!=null){
					pnlCardLayout.remove(currentView);
					currentView.removeAll();
				}
				currentView = new StudentsDetailsPnl();
				pnlCardLayout.add(currentView,STUDENTS_PNL);
				((CardLayout)pnlCardLayout.getLayout()).show(pnlCardLayout, STUDENTS_PNL);
			}
		});
		
		
		
		
	}
	
	public void init_pnl_cards(){
		pnlCardLayout = new JPanel();
		pnlCardLayout.setLayout(new CardLayout(0, 0));
		
		// Set all panels to be in it.
		pnlCardLayout.add(new JPanel(),BLANK_PNL);
		//pnlCardLayout.add(new StudentsDetailsPnl(),STUDENTS_PNL);
		//pnlCardLayout.add(new TestsCategoriesPnl(),TESTS_CATEGORIES);
		//pnlCardLayout.add(new TestsJPanel(),DONE_TESTS_PNL);
		
		frame.getContentPane().add(pnlCardLayout, BorderLayout.CENTER);
	}
	
	public void init_look_and_feel(){
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	public void init_onClose(){
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
//				DatabaseConnection.closeConnection();
			}
		});
	}
	
	void init_jframe_features(){
		frame.setBounds(0,0, 820, 460);// set bounds of frame.
		frame.setLocationRelativeTo(null); // allign it to the center of the screen.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setVisible(boolean visibility){
		frame.setVisible(visibility);
	}
	
}
