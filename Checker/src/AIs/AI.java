package AIs;

public class AI {
	private String filepath, name, ai_location, version, date, writer;
	private Boolean changeable; 

	public AI(String filepath, String name, Boolean changeable, String ai_location, String version, String date, String writer){
		this.filepath = filepath;
		this.name = name;
		this.changeable = changeable;
		this.ai_location = ai_location;
		this.version = version;
		this.date = date;
		this.writer = writer;
	}

	public String getFilepath(){return filepath;}

	public void setFilepath(String filename){this.filepath = filename;}

	public String getName(){return name;}

	public void setName(String name){this.name = name;}

	public Boolean isChangeable(){return changeable;}

	public void setChangeable(Boolean changeable){this.changeable = changeable;}

	public String getAi_location() {
		return ai_location;
	}

	public void setAi_location(String ai_location) {
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String toString(){
		return (ai_location + "\r" + String.valueOf(changeable) + "\r" + name + "\r" + version + "\r" + date + "\r" + writer);
	}
}
