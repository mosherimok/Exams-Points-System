package ui_done_tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestSearchForParticularStudent {
	
	private String[][] allData;
	
	private static HashMap<Character, ArrayList<String>> israeliStudents = new HashMap<>();
	private static HashMap<Character, ArrayList<String>> americanStudents = new HashMap<>();
	
	private ArrayList<String> founded = new ArrayList<>();
	
	
	@BeforeClass
	public static void initAllData(){
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileInputStream("Resources/firstNames.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Assert.fail("Could not open the file: \n" + e.getMessage());
		}
		while(scanner.hasNext()){
			String name = scanner.nextLine();
			char l = name.charAt(0);
			
			if((l+"").matches("[à-ú]")){
				if(israeliStudents.containsKey(l))
					israeliStudents.get(l).add(name);
				else{
					ArrayList<String> flArray = new ArrayList<>();
					flArray.add(name);
					israeliStudents.put(l,flArray);
				}
			}
			else{
				l = Character.toLowerCase(l);
				if(americanStudents.containsKey(l)){
					americanStudents.get(l).add(name.toLowerCase());
				}
				else{
					
					ArrayList<String> flArray = new ArrayList<>();
					flArray.add(name.toLowerCase());
					americanStudents.put(l,flArray);
				}
			}
		}
	}
	
	private void algorithm(String text){
		char l = text.charAt(0);
		if((l+"").matches("[à-ú]")){
			ArrayList<String> related = israeliStudents.get(l);
			if(related!=null){
				for(String stds : related){
					if(stds.startsWith(text))
						founded.add(stds);
				}
			}
		}
		else{
			l = Character.toLowerCase(l);
			if((l+"").matches("[a-z]")){
				ArrayList<String> related = americanStudents.get(l);
				if(related!=null){
					for(String stds : related){
						if(stds.startsWith(text))
							founded.add(stds);
					}
				}
			}
		}
	}
	
	@Test
	public void testTimeEfficienct(){
		String[] expecteds = {"michel","benny","zachary"};
		algorithm(expecteds[0]);
		algorithm(expecteds[1]);
		algorithm(expecteds[2]);
		
		for(int i=0;i<expecteds.length;i++){
			String expected = expecteds[i];
			String actual = founded.get(i);
			Assert.assertEquals("Expected name " + expected + " did not added",
					expected, actual);
		}
	}

}
