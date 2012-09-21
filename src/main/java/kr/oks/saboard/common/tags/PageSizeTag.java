package kr.oks.saboard.common.tags;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.JspTagException;

public class PageSizeTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;

	public int doAfterBody() throws JspTagException {
		int pageSize = 10; // default = 10;
		PagingTag parent = (PagingTag) findAncestorWithClass(this, PagingTag.class);

		if (parent == null) {
			throw new JspTagException();
		}

		pageSize = Integer.parseInt(getBodyContent().getString().trim());
		if (pageSize == 0) {
			pageSize = 10;
		}
		parent.setPageSize(pageSize);

		return SKIP_BODY;
	}
}