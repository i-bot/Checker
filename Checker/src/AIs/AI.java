package AIs;

public class AI {
	private String filepath, name, ai_location, class_location, version, date, author;
	private Boolean changeable; 

	public AI(String filepath, String name, Boolean changeable, String ai_location, String class_location, String version, String date, String author){
		this.filepath = filepath;
		this.name = name;
		this.changeable = changeable;
		this.ai_location = ai_location;
		this.class_location = class_location;
		this.version = version;
		this.date = date;
		this.author = author;
	}

	public String getFilepath(){return filepath;}

	public void setFilepath(String filename){this.filepath = filename;}

	public String getName(){return name;}

	public void setName(String name){this.name = name;}

	public Boolean isChangeable(){return changeable;}

	public void setChangeable(Boolean changeable){this.changeable = changeable;}

	public String getAI_Location() {
		return ai_location;
	}
	public String getClass_Location() {
		return class_location;
	}

	public void setClass_Location(String class_location) {
		this.class_location = class_location;
	}
	
	public void setAI_Location(String ai_location) {
		this.ai_location = ai_location;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String toString(){
		return (ai_location + "\r" + String.valueOf(changeable) + "\r" + name + "\r" + version + "\r" + date + "\r" + author);
	}
}
