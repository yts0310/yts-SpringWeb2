package com.yts.chap11;

public class Member {
	String memberId;
	String email;
	String password;
	String name;
	String cdate;
	
	public Member() {
		
	}
	
	public Member(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", email=" + email + ", password=" + password + ", name=" + name
				+ ", Cdate=" + cdate + "]";
	}
	
	

}