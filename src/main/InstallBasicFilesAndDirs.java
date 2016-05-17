package main;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static main.FilesConstants.*;

public class InstallBasicFilesAndDirs {
	
	public void install(){
		initFolders();
		initFiles();
	}
	
	
	private void initFolders(){
		try {
			Files.createDirectory(Paths.get(LOCAL_PATH));
		} catch (IOException e) {
			if(!(e instanceof FileAlreadyExistsException))
				e.printStackTrace();
		}
		try {
			Files.createDirectory(Paths.get(DATABASE_DIR_PATH));
		} catch (IOException e) {
			if(!(e instanceof FileAlreadyExistsException))
				e.printStackTrace();
		}
		try {
			Files.createDirectory(Paths.get(BACKUPS_DIR_PATH));
		} catch (IOException e) {
			if(!(e instanceof FileAlreadyExistsException))
				e.printStackTrace();
		}
	}
	
	private void initFiles(){
		try{
			if(Files.notExists(Paths.get(DATABASE_FILE_PATH))){
				//Take from latest backup if exists.
				//if not:
				InputStream is = this.getClass().getClassLoader().getResourceAsStream(EMPTY_PREPARED_DATABASE);
				Files.copy(is,Paths.get(DATABASE_FILE_PATH),StandardCopyOption.REPLACE_EXISTING);
			}
		}
		catch(IOException ex){ex.printStackTrace();}
	}
	
	private void fetchDatabaseFromFTPServer(){
		
	}
	
}
