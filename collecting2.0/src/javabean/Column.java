package javabean;

public class Column {
	private String field;
	private String title;
	private String formatter;
	
	public Column(String field, String title, String formatter) {
		super();
		this.field = field;
		this.title = title;
		this.formatter = formatter;
	}
	
	public Column(String field, String title) {
		super();
		this.field = field;
		this.title = title;
		this.formatter = null;
	}
	
	public String getField() {
		return field;
	}


	public void setField(String field) {
		this.field = field;
	}


	public String getFormatter() {
		return formatter;
	}


	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
