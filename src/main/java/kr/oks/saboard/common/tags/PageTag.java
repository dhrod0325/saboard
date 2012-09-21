package kr.oks.saboard.common.tags;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.JspTagException;

public class PageTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;

	public int doAfterBody() throws JspTagException {
		int page = 0;
		PagingTag parent = (PagingTag) findAncestorWithClass(this, PagingTag.class);

		if (parent == null) {
			throw new JspTagException("부모 태그(PagingTag.class)가 없습니다.");
		}

		page = Integer.parseInt(getBodyContent().getString().trim());
		parent.setCurrentPage(page);

		return SKIP_BODY;
	}
}
