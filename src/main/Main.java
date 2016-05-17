package main;

import ui_main.MainScreen;;

public class Main {
	
	public static void main(String [] args){
		InstallBasicFilesAndDirs installation = new InstallBasicFilesAndDirs();
		installation.install();
		
		MainScreen ms = new MainScreen();
		ms.setVisible(true);
	}

}
