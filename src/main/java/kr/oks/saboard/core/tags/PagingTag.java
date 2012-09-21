package kr.oks.saboard.core.tags;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;

public class PagingTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private String function;
	private int currentPage;
	private int totalRows;
	private int pageSize;
	private int pageLimit;
	
	private String imgPrevPath;
	private String imgNextPath;

	// private int rowsLimit;

	public PagingTag() {
		pageLimit = 10;
	}

	public int doStartTag() {
		return 1;
	}

	public int doEndTag() {
		StringBuffer uri = new StringBuffer();
		int totalPage = totalRows / pageSize;
		if (totalRows % pageSize > 0)
			totalPage++;
		int nowGroup = ((currentPage % pageLimit) == 0) ? (currentPage / pageLimit) - 1 : currentPage / pageLimit;
		int totalGroup = totalPage / pageLimit;
		if (totalPage % pageLimit > 0)
			totalGroup++;
		uri.append("<span class=\"prev\">");
		if (nowGroup <= 0)
			uri.append("<img src=\"" + imgPrevPath + "\" alt=\"이전 목록으로\" />");
		// uri.append("<");
		else
			uri.append((new StringBuilder()).append("<a href=\"JavaScript:").append(function).append("('").append(((nowGroup - 1) * 10) + 1).toString())
					.append((new StringBuilder()).append("','").append(nowGroup - 1).append("')\">").toString())
					.append("<img src=\""+imgPrevPath+"\" alt=\"처음 목록으로\" /></a>");
		int doStart = nowGroup * pageLimit;
		doStart = ((currentPage % pageLimit) == 0) ? currentPage - (pageLimit - 1) : ((currentPage / pageLimit) * pageLimit) + 1; 
		
		int doEnd = doStart + pageLimit;
		if (totalRows == 0)
			doEnd = 1 + 1;
		if (totalGroup - 1 == nowGroup)
			doEnd = totalPage + 1;
		uri.append("</span>");
		for (int i = doStart; i < doEnd; i++)
			if ((i) == currentPage)
				uri.append((new StringBuilder()).append("<a href='#' class='on' title=\"현재 선택된 목록\" >").append(i).append("&nbsp;</a>").toString());
			else
				uri.append((new StringBuilder()).append("<a href=\"JavaScript:").append(function).append("('").append(i).toString()).append(
						(new StringBuilder()).append("','").append(nowGroup).append("')\">").append(i).append("&nbsp;</a>").toString());

		uri.append("<span class=\"next\">");
		if (nowGroup < totalGroup - 1)
			uri.append((new StringBuilder()).append("<a href=\"JavaScript:").append(function).append("('").append(((nowGroup + 1) * 10) + 1).toString())
					.append((new StringBuilder()).append("','").append(nowGroup + 1).append("')\">").toString())
					.append("<img src=\""+imgNextPath+"\" alt=\"마지막 목록으로\" /></a>");
		else
			uri.append("<img src=\"" + imgNextPath + "\" alt=\"다음 목록으로\" />");
		uri.append("</span>");
		JspWriter out = pageContext.getOut();
		try {
			out.print(uri.toString());
		} catch (IOException e) {
		}
		return 6;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setImgPrevPath(String imgPrevPath) {
		this.imgPrevPath = imgPrevPath;
	}

	public void setImgNextPath(String imgNextPath) {
		this.imgNextPath = imgNextPath;
	}
}
