package Languages;

public class Languages{
	static Language language;
	
	public static void setLanguage(String lan){
		if(lan.equals("English"))language = new English();
		if(lan.equals("German"))language = new German();
	}
	
	public static String getButtonAndMenuTitle(int index){
		return language.getButtonAndMenuTitle(index);
	}
}
