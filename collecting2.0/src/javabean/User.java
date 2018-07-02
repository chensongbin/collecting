package javabean;

public class User {
	private int userId; 
	private String userName;
	private String userPassword;
	private String userEmail;
	
	public User() {
		this.userName = null;
		this.userPassword = null;
		this.userEmail = null;
		this.userId = -1;
	}
	
	public User(String userName, String userPassword) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = null;
		this.userId = -1;
	}
	
	public User(String userName, String userPassword, String userEmail) {
		this(userName, userPassword);
		this.userEmail = userEmail;
	}
	public User(int userId, String userName, String userPassword, String userEmail) {
		this(userName, userPassword, userEmail);
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}
