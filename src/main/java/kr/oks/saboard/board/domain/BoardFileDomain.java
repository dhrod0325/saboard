package kr.oks.saboard.board.domain;

import java.util.Date;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class BoardFileDomain {
	private int id;
	private int file_no;
	private String file_name;
	private long file_size;
	private Date file_reg_date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFile_no() {
		return file_no;
	}

	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public long getFile_size() {
		return file_size;
	}

	public void setFile_size(long file_size) {
		this.file_size = file_size;
	}

	public Date getFile_reg_date() {
		return file_reg_date;
	}

	public void setFile_reg_date(Date file_reg_date) {
		this.file_reg_date = file_reg_date;
	}

}
