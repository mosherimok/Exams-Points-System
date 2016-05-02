package actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class MapActionListener implements ActionListener{

	private HashMap<String,Action> actionsMap;
	
	public MapActionListener(){
		actionsMap = new HashMap<>();
	}
	
	public void actionPerformed(ActionEvent e){
		actionsMap.get(e.getActionCommand()).perform();
	}
	
	public void addAction(String actionCommand,Action action){
		actionsMap.put(actionCommand, action);
	}
	
	public void removeAction(String actionCommand){
		actionsMap.remove(actionCommand);
	}
	
}
