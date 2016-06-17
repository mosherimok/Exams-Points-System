package ui_settings;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import database.Database;
import database.FailedUpdatesList;
import database.OperationObserver;
import utility_dialogs.DialogProcessLoading;

import java.awt.Component;

import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.BoxLayout;

public class DialogImportStudents extends JFrame {

	//Objects:
	private ExcelStudentsExtracter extracter;
	private FailedUpdatesList faileds;
	
	//Componets:
	private final JPanel mainPanel = new JPanel();
	private JPanel panelActions;
	private JPanel panelCheckFaileds;

	/**
	 * Create the dialog.
	 */
	public DialogImportStudents() {
		initGUI();
		initFrame();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				extracter = null;
				super.windowClosing(e);
			}
		});
	}
	private void initFrame() {
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void initGUI(){
		setBounds(100, 100, 482, 248);
		getContentPane().setLayout(new BorderLayout());
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelTitle = new JPanel();
			panelTitle.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			mainPanel.add(panelTitle, BorderLayout.NORTH);
			{
				JLabel label = new JLabel("\u05D9\u05D9\u05D1\u05D5\u05D0 \u05EA\u05DC\u05DE\u05D9\u05D3\u05D9\u05DD");
				panelTitle.add(label);
			}
		}
		{
			JPanel panelContent = new JPanel();
			mainPanel.add(panelContent, BorderLayout.CENTER);
			panelContent.setLayout(new BorderLayout(0, 0));
			{
				JPanel panelOptions = new JPanel();
				panelOptions.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), new BevelBorder(BevelBorder.RAISED, null, null, null, null)));
				panelContent.add(panelOptions, BorderLayout.NORTH);
				JPanel panelExcelOption = new JPanel();
				panelOptions.add(panelExcelOption);
				panelExcelOption.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				JPanel panelChooseButton = new JPanel();
				panelExcelOption.add(panelChooseButton);
				JButton buttonChooseExcel = new JButton("\u05D1\u05D7\u05E8 \u05E7\u05D5\u05D1\u05E5");
				panelChooseButton.add(buttonChooseExcel);
				{
					JLabel lblExcel = new JLabel("\u05D1\u05D7\u05E8 \u05E7\u05D5\u05D1\u05E5 Excel:");
					panelChooseButton.add(lblExcel);
				}
				{
					JPanel panelTextOption = new JPanel();
					panelOptions.add(panelTextOption);
					panelTextOption.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
					{
						JPanel panel_1 = new JPanel();
						panelTextOption.add(panel_1);
						{
							JButton buttonChooseText = new JButton("\u05D1\u05D7\u05E8 \u05E7\u05D5\u05D1\u05E5");
							panel_1.add(buttonChooseText);
						}
						{
							JLabel label = new JLabel("\u05D1\u05D7\u05E8 \u05E7\u05D5\u05D1\u05E5 \u05D8\u05E7\u05E1\u05D8");
							panel_1.add(label);
						}
					}
				}
				buttonChooseExcel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JFileChooser fc = new JFileChooser();
						fc.showOpenDialog(null);
						try {
							extracter = new ExcelStudentsExtracter(fc.getSelectedFile());
//							String FOR_DEBUGGING = "C:\\Users\\משה\\Documents\\Git\\Exams-Points-System\\Resources\\רשימת תלמידים.xlsx";
//							System.out.println(fc.getSelectedFile().toPath());
							extracter = new ExcelStudentsExtracter(fc.getSelectedFile());
							extracter.extract();
							panelActions.setVisible(true);
							pack();
						} catch (EncryptedDocumentException | InvalidFormatException | IOException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
						
					}
				});
			}
			{
				panelActions = new JPanel();
				panelActions.setVisible(false);
				panelActions.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
				panelContent.add(panelActions, BorderLayout.CENTER);
				panelActions.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					{
						JPanel panelView = new JPanel();
						panelActions.add(panelView);
						panelView.setLayout(new BoxLayout(panelView, BoxLayout.Y_AXIS));
						{
							JButton buttonViewMaybeAdded = new JButton("\u05D4\u05E6\u05D2 \u05D0\u05EA \u05D4\u05EA\u05DC\u05DE\u05D9\u05D3\u05D9\u05DD \u05DE\u05EA\u05D5\u05DA \u05D4 Excel");
							buttonViewMaybeAdded.setAlignmentX(Component.CENTER_ALIGNMENT);
							panelView.add(buttonViewMaybeAdded);
							JButton buttonAddAll = new JButton("\u05D4\u05D5\u05E1\u05E3");
							panelView.add(buttonAddAll);
							buttonAddAll.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent arg0) {
									int c = JOptionPane.showConfirmDialog(null, "האם אתה בטוח שברצונך להוסיף את כל תלמידים אלו?","התראה",JOptionPane.OK_CANCEL_OPTION);
									if(c==JOptionPane.CANCEL_OPTION)
										return;
									String script = "INSERT INTO Students(id,f_name,l_name,"+
										"reception_year,points) Values(?,?,?,?,?)";
									int affected = 0;
									try {
										DialogProcessLoading bar = new DialogProcessLoading(extracter.getAllStudents().length);
										Database.setOperationObserver(new OperationObserver() {
											
											@Override
											public void action() {
												bar.increaseProgressBy1();
											}
										});
										faileds = new FailedUpdatesList(extracter.getAllStudents().length);
										affected = Database.executeOneTransactedPreparedStatement(script,
												extracter.getAllStudents(),faileds);
										if(faileds.getSize()>0){
											panelCheckFaileds.setVisible(true);
											pack();
										}
										
									} catch (SQLException e) {
										e.printStackTrace();
									}
									finally{
										JOptionPane.showMessageDialog(null, String.format("סך הכל יובאו " + " %d " + "מתוך "+ "%d" ,affected,extracter.getAllStudents().length));
									}
								}
							});
							
							buttonAddAll.setAlignmentX(Component.CENTER_ALIGNMENT);
							buttonViewMaybeAdded.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									new DialogStudentsMayBeInserted(extracter.getAllStudents());
								}
							});
						}
					}
					
				}
				{
					panelCheckFaileds = new JPanel();
					panelCheckFaileds.setVisible(false);
					panelActions.add(panelCheckFaileds);
					{
						JButton button = new JButton("\u05DC\u05D7\u05E5 \u05DB\u05D0\u05DF \u05DC\u05E8\u05D0\u05D5\u05EA \u05D0\u05D9\u05DC\u05D5 \u05EA\u05DC\u05DE\u05D9\u05D3\u05D9\u05DD \u05DC\u05D0 \u05D4\u05D5\u05E1\u05E4\u05D5");
						button.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {
								new DialogStudentsHaveNotInserted(faileds);
							}
						});
						panelCheckFaileds.add(button);
						button.setAlignmentX(Component.CENTER_ALIGNMENT);
					}
				}
			}
			{
				{
					{
						{
						}
					}
				}
			}
		}
	}

}
