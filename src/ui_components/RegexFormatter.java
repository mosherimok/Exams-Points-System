package ui_components;

import java.awt.Color;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatter;

public class RegexFormatter extends DefaultFormatter{

	private Pattern pattern;
	private Matcher matcher;
	
	public RegexFormatter(String pattern) throws ParseException{
		super();
		setPattern(Pattern.compile(pattern));
	}
	
	public RegexFormatter(String pattern,int maxChars) throws ParseException{
		super();
		setPattern(Pattern.compile(pattern));
	}
	
	public void setPattern(Pattern pattern){
		this.pattern = pattern;
	}
	
	public Pattern getPattern(){
		return pattern;
	}
	
	public void setMatch(Matcher matcher){
		this.matcher = matcher;
	}
	
	public Matcher getMatcher(){
		return matcher;
	}
	
	@Override
	public Object stringToValue(String text) throws ParseException {
		if(pattern!=null){
			matcher = pattern.matcher(text);
			
			if(matcher.matches()){
				return super.stringToValue(text);
			}
			if(!text.equals("")){
				JOptionPane.showMessageDialog(null,"שדה זה אינו תקין. אנא הקלד ערך הגיוני");
				return text.substring(0,text.length()-1);
			}
		}
		return text;
	}
	
}
