package Languages;

import java.util.ArrayList;

public class English extends Language{

	English(){
		buttonAndMenuTitle = new ArrayList<String>();
		buttonAndMenuTitle.add("Quit Game");//0
		buttonAndMenuTitle.add("Start Menu");//1
		buttonAndMenuTitle.add("AI Menu");//2
		buttonAndMenuTitle.add("Options");//3
		buttonAndMenuTitle.add("Network Game");//4
		buttonAndMenuTitle.add("Local Game");//5
		buttonAndMenuTitle.add("AI");//6
		buttonAndMenuTitle.add("next");//7
		buttonAndMenuTitle.add("back");//8
		buttonAndMenuTitle.add("import AI");//9
		buttonAndMenuTitle.add("export AI");//10
		buttonAndMenuTitle.add("delete AI");//11
		buttonAndMenuTitle.add("Do you really want delete this AI?: ");//12
		buttonAndMenuTitle.add("Yes");//13
		buttonAndMenuTitle.add("No");//14
		buttonAndMenuTitle.add(" can not be deleted, this AI is unchangeable");//15
		buttonAndMenuTitle.add("OK");//16
		buttonAndMenuTitle.add("Player1");//17
		buttonAndMenuTitle.add("Player2");//18
	}
	
	String getButtonAndMenuTitle(int index) {
		return buttonAndMenuTitle.get(index);
	}
}
