package Languages;

import java.util.ArrayList;

public abstract class Language {
	ArrayList<String> buttonAndMenuTitle;
	abstract String getButtonAndMenuTitle(int index);
}
