package kr.oks.saboard.common.tags;

import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.JspTagException;

/**
 *
 * @author John_Kim <johnkim@prompt.co.kr / Prompt, Inc.>
 *
 */
public class TotalRowsTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;

	public int doAfterBody() throws JspTagException {
		int totalPage = 0;
		PagingTag parent = (PagingTag) findAncestorWithClass(this, PagingTag.class);

		if (parent == null) {
			throw new JspTagException("부모 태그(PagingTag)가 없습니다.");
		}

		totalPage = Integer.parseInt(getBodyContent().getString().trim());
		parent.setTotalRows(totalPage);

		return SKIP_BODY;
	}
}
