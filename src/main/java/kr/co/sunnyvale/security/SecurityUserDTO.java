package kr.co.sunnyvale.security;

import java.sql.Date;

public class SecurityUserDTO{
	
	private String id;
	private String passwd;
	private String name;
	private int admin;
	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	private Date regdate;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SecurityUserDTO [id=" + id + ", passwd=" + passwd + ", name="
				+ name + ", regdate=" + regdate + "]";
	}

	
}
