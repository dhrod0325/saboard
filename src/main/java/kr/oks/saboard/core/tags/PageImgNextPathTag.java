package kr.oks.saboard.core.tags;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class PageImgNextPathTag extends BodyTagSupport{
	private static final long serialVersionUID = 1L;

	public int doAfterBody() throws JspTagException {
		String next = "";
		
		PagingTag parent = (PagingTag) findAncestorWithClass(this, PagingTag.class);

		if (parent == null) {
			throw new JspTagException("부모 태그(PagingTag.class)가 없습니다.");
		}

		next = getBodyContent().getString().trim();
		
		parent.setImgNextPath(next);

		return SKIP_BODY;
	}
}
