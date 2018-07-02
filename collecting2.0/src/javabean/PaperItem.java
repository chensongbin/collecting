package javabean;

public class PaperItem {
	private String createDate;
	private String paperName;
	private String uniqueKey;
	private String paperStatus;
	private String ID;
	
	public PaperItem() {
		this("未初始化", "未初始化", "未初始化", "未初始化", "未初始化");
	}
	
	public PaperItem(String createDate, String paperName, String uniqueKey, String paperStatus, String id) {
		this.createDate = createDate;
		this.paperName = paperName;
		this.uniqueKey = uniqueKey;
		this.paperStatus = paperStatus;
		this.ID = id;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	public String getPaperStatus() {
		return paperStatus;
	}
	public void setPaperStatus(String paperStatus) {
		this.paperStatus = paperStatus;
	}
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
}
