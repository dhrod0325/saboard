package kr.oks.saboard.core.filter.xss;

import kr.oks.saboard.core.util.NullUtil;

public class XssFilterUtil{
	public static final HelperXSSFilter xssFilter = new HelperXSSFilter();
	
	public static String removeXSS(String input){
		if(NullUtil.isNull(input))
			return "";
		
		return xssFilter.removeXSS(input);
	}
}
