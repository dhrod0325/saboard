package kr.oks.saboard.core.tags;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.JspTagException;

public class FunctionTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;

	public int doAfterBody() throws JspTagException {
		PagingTag parent = (PagingTag) findAncestorWithClass(this, PagingTag.class);

		if (parent == null) {
			throw new JspTagException("부모 태그(PagingTag)가 없습니다.");
		}

		String function = getBodyContent().getString().trim();
		parent.setFunction(function);
		return SKIP_BODY;
	}
}
