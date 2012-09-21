package kr.oks.saboard.board.domain;

public class BoardTableDomain {
	private int id;
	private String board_nm;
	private String theme;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getboard_nm() {
		return board_nm;
	}

	public void setboard_nm(String board_nm) {
		this.board_nm = board_nm;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
}
