package kr.oks.saboard.board.domain;

public class BoardTableDomain {
	private int id;
	private String board_id;
	private String board_nm;
	private String board_desc;
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

	public String getBoard_nm() {
		return board_nm;
	}

	public void setBoard_nm(String board_nm) {
		this.board_nm = board_nm;
	}

	public String getBoard_desc() {
		return board_desc;
	}

	public void setBoard_desc(String board_desc) {
		this.board_desc = board_desc;
	}

	public String getBoard_id() {
		return board_id;
	}

	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}

}
