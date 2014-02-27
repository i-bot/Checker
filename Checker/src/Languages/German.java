package Languages;

import java.util.ArrayList;

public class German extends Language{

	German(){
		buttonAndMenuTitle = new ArrayList<String>();
		buttonAndMenuTitle.add("Spiel beenden");//0
		buttonAndMenuTitle.add("Startmenü");//1
		buttonAndMenuTitle.add("Einstellungen");//2
		buttonAndMenuTitle.add("Weltenauswahl");//3
		buttonAndMenuTitle.add("Leveleditor");//4
		buttonAndMenuTitle.add("nächste Seite");//5
		buttonAndMenuTitle.add("letzte Seite");//6
	}
	
	public String getButtonAndMenuTitle(int index) {
		return buttonAndMenuTitle.get(index);
	}
}
