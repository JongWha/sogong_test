package gram.model.domain;

public class Member {
	
	private String user_id;
	private String user_password;
	private int user_num;
	private int user_status;
	
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public int getUser_num() {
		return user_num;
	}

	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}

	public int getUser_status() {
		return user_status;
	}

	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}

	public Member() {
		super();
	}
	
	public Member(String user_id, String user_password, int user_num, int user_status){
		super();
		this.user_id = user_id;
		this.user_password = user_password;
		this.user_num = user_num;
		this.user_status = user_status;
	}
	
}
