package ui_components;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.MaskFormatter;

public class RegexMaskFormatter extends MaskFormatter{

	private static final long serialVersionUID = 1L;

	private Pattern pattern;
	private Matcher matcher;
	
	public RegexMaskFormatter(String mask,String pattern) throws ParseException{
		super(mask);
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
			throw new ParseException("Pattern did not match", 0);
		}
		return text;
	}
	
}
