package utility_dialogs;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;

public class DialogProcessLoading extends JFrame {


	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private JProgressBar bar;
	private int maxProgress;
	
	public DialogProcessLoading(int maxProgress) {
		this.maxProgress=maxProgress;
		initGUI();
		initDialog();
	}
	
	public void initDialog() {
		setLocationRelativeTo(getParent());
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void initGUI(){
		setBounds(100, 100, 318, 99);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel label = new JLabel("\u05D0\u05E0\u05D0 \u05D4\u05DE\u05EA\u05DF \u05DC\u05E1\u05D9\u05D5\u05DD \u05D4\u05EA\u05D4\u05DC\u05D9\u05DA");
				panel.add(label);
			}
		}
		{
			bar = new JProgressBar(0,maxProgress);
			contentPanel.add(bar, BorderLayout.CENTER);
		}
		{
			Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
			contentPanel.add(rigidArea, BorderLayout.WEST);
		}
		{
			Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
			contentPanel.add(rigidArea, BorderLayout.EAST);
		}
	}
	
	public void increaseProgressBy1(){
		bar.setValue(bar.getValue()+1);
		System.out.println("value: " + bar.getValue());
		if(bar.getValue()==maxProgress)
			dispose();
	}
	
	public void setProgressValue(int val){
		bar.setValue(val);
		if(val==maxProgress)
			dispose();
	}

}
