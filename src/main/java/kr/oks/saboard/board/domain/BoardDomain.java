package kr.oks.saboard.board.domain;

import java.util.Date;

import kr.oks.saboard.common.tags.domain.PagingDomain;
import kr.oks.saboard.constants.Constants;

public class BoardDomain extends PagingDomain {
	private int id;
	private int rownum;
	private String user_id;
	private String password;
	private String title;
	private String content;
	private String has_File = Constants.HAS_FILE_NO;
	private Date reg_date;
	private int reply_cnt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public String getHas_File() {
		return has_File;
	}

	public void setHas_File(String has_File) {
		this.has_File = has_File;
	}

	public int getReply_cnt() {
		return reply_cnt;
	}

	public void setReply_cnt(int reply_cnt) {
		this.reply_cnt = reply_cnt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
