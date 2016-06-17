package ui_main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;

import components_utility.MenuPanelView;
import ui_settings.MainMenu;
import ui_students.ViewStudentsMenuPanel;
import ui_tests.ViewTestsMenuPanel;
import ui_tests_categories.ViewTestsCategoriesPanel;

public class MainScreen extends JFrame implements ActionListener{

	private JPanel pnlCardLayout;
	private static final String BLANK_PNL = "BlankPnl";
	private static  final String STUDENTS_PNL = "StudentsPnl";
	private static final String DONE_TESTS_PNL = "DoneTestsPnl";
	private static final String TESTS_CATEGORIES_PNL = "TestsCategoriesPnl";
	private static final String SETTINGS_PNL = "SettingsPnl";
	
	
	public static JPanel currentView;
	
	public MainScreen() {
		init_look_and_feel();
		initGUI();
		init_pnl_cards();
		init_jframe_features();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initGUI() {
		
		
		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		getContentPane().add(pnlTitle, BorderLayout.NORTH);
		
		JLabel label = new JLabel("\u05D1\u05E8\u05D5\u05DA \u05D4\u05D1\u05D0 \u05DC\u05DE\u05E2\u05E8\u05DB\u05EA \u05E0\u05D9\u05D4\u05D5\u05DC \u05DE\u05D1\u05D7\u05E0\u05D9\u05DD");
		pnlTitle.add(label);
		
		JPanel pnlMenu = new JPanel();
		pnlMenu.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u05EA\u05E4\u05E8\u05D9\u05D8", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 0))));
		getContentPane().add(pnlMenu, BorderLayout.EAST);
		
		pnlMenu.setLayout(new BoxLayout(pnlMenu, BoxLayout.Y_AXIS));
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		pnlMenu.add(verticalStrut_2);
		
		JButton btnStdsDetails = new JButton("\u05E4\u05E8\u05D8\u05D9 \u05EA\u05DC\u05DE\u05D9\u05D3\u05D9\u05DD");
		btnStdsDetails.setActionCommand(STUDENTS_PNL);
		btnStdsDetails.addActionListener(this);
		btnStdsDetails.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlMenu.add(btnStdsDetails);
		
		Component verticalStrut = Box.createVerticalStrut(30);
		pnlMenu.add(verticalStrut);
		
		JButton btnDoneTests = new JButton("\u05DE\u05D1\u05D7\u05E0\u05D9\u05DD \u05E9\u05E0\u05E2\u05E9\u05D5");
		btnDoneTests.setActionCommand(DONE_TESTS_PNL);
		btnDoneTests.addActionListener(this);

		btnDoneTests.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlMenu.add(btnDoneTests);
		
		Component verticalStrut_1 = Box.createVerticalStrut(30);
		pnlMenu.add(verticalStrut_1);
		
		JButton btnTestsCategories = new JButton("\u05E1\u05D5\u05D2\u05D9 \u05DE\u05D1\u05D7\u05E0\u05D9\u05DD");
		btnTestsCategories.setActionCommand(TESTS_CATEGORIES_PNL);
		btnTestsCategories.addActionListener(this);

		btnTestsCategories.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlMenu.add(btnTestsCategories);
		
		Component verticalStrut_3 = Box.createVerticalStrut(120);
		pnlMenu.add(verticalStrut_3);
		
		JButton btnSettings = new JButton("\u05D4\u05D2\u05D3\u05E8\u05D5\u05EA");
		btnSettings.setActionCommand(SETTINGS_PNL);
		btnSettings.addActionListener(this);
		btnSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlMenu.add(btnSettings);
		
	}
	
	public void init_pnl_cards(){
		pnlCardLayout = new JPanel();
		pnlCardLayout.setLayout(new CardLayout(0, 0));
		
		pnlCardLayout.add(new JPanel(),BLANK_PNL);

		getContentPane().add(pnlCardLayout, BorderLayout.CENTER);
	}
	
	public void init_look_and_feel(){
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	void init_jframe_features(){
		setBounds(0,0, 820, 460);// set bounds of frame.
		setLocationRelativeTo(null); // allign it to the center of the screen.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		switch(e.getActionCommand()){
		case MainScreen.STUDENTS_PNL:
			switchView(new ViewStudentsMenuPanel(),e.getActionCommand());
			break;
		case MainScreen.DONE_TESTS_PNL:
			switchView(new ViewTestsMenuPanel(),e.getActionCommand());
			break;
		case MainScreen.TESTS_CATEGORIES_PNL:
			switchView(new ViewTestsCategoriesPanel(),e.getActionCommand());
			break;
		case MainScreen.SETTINGS_PNL:
				new MainMenu();
		}
	}
	
	private void switchView(JPanel view,String name){
		if(currentView!=null)
			pnlCardLayout.remove(currentView);
		currentView=view;
		pnlCardLayout.add(currentView, name);
		((CardLayout)pnlCardLayout.getLayout()).show(pnlCardLayout,name);
	}
	
	public static MenuPanelView getCurrentViewAsMenuPanel(){
		return (MenuPanelView)currentView;
	}
	
}
