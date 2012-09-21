package kr.oks.saboard.common.tags;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class PageImgPrevPathTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;

	public int doAfterBody() throws javax.servlet.jsp.JspException {
		String prev = "";

		PagingTag parent = (PagingTag) findAncestorWithClass(this, PagingTag.class);

		if (parent == null) {
			throw new JspTagException("부모 태그(PagingTag.class)가 없습니다.");
		}

		prev = getBodyContent().getString().trim();

		parent.setImgPrevPath(prev);

		return SKIP_BODY;
	}
}
