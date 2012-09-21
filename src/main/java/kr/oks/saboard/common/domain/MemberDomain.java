package kr.oks.saboard.common.domain;


public class MemberDomain {
	private int no;
	private String id;
	private String pw;
	private String name;
	private int role;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	public boolean getIsAdmin(){
		return getRole() == 10 ? true : false;
	}
	
	public boolean getIsMember(){
		return getRole() != 0 && !getIsAdmin();
	}
	public boolean getIsNotMember(){
		return !getIsAdmin() && !getIsMember();
	}
}
