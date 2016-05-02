package ui_components;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitDocument extends PlainDocument {

	private int maxChars;
	
	public LimitDocument(int maxChars){
		this.maxChars = maxChars;
	}

	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		if(str==null)
			return;
		
		if((getLength() + str.length())<=maxChars)
			super.insertString(offs, str, a);
	}
}
