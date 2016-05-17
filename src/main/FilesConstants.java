package main;

import javax.swing.JFileChooser;

public class FilesConstants {

	//Local path:
		public static final String LOCAL_PATH;
		
		//Directories names:
		public static final String LOCAL_DIR = "Exams Points System";
		public static final String DATABASE_DIR = "database";
		public static final String BACKUPS_DIR = "backups";
		
		//Directories Paths:
		public static final String DATABASE_DIR_PATH;
		public static final String BACKUPS_DIR_PATH;
		
		//Files names:
		public static final String DATABASE_FILE_NAME = "EPS.db";
		public static final String DATABASE_BACKUP_FILE_NAME = "BKP.db";
		public static final String EMPTY_PREPARED_DATABASE = "EmptyPreparedDatabase.db";
		
		//Files paths:
		public static final String DATABASE_FILE_PATH;
		public static final String BACKUPS_FILE_PATH;
		
		static{
			LOCAL_PATH = new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\"+LOCAL_DIR;
			DATABASE_DIR_PATH = LOCAL_PATH+"\\"+DATABASE_DIR;
			BACKUPS_DIR_PATH = LOCAL_PATH+"\\"+BACKUPS_DIR;
			
			DATABASE_FILE_PATH = LOCAL_PATH+"\\"+DATABASE_DIR+"\\"+DATABASE_FILE_NAME;
			BACKUPS_FILE_PATH = LOCAL_PATH+"\\"+DATABASE_BACKUP_FILE_NAME;
		}
	
}
