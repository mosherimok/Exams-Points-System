package components_utility;

import java.awt.ComponentOrientation;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Locale;

import javax.swing.JFormattedTextField;

public class HebrewJFormattedTextfield extends JFormattedTextField{

	public HebrewJFormattedTextfield(AbstractFormatter format){
		super(format);
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				super.focusGained(e);
				getInputContext().selectInputMethod(new Locale("iw", "IL"));
			}
		});
		
	}
	
	
	
}
