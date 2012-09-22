package kr.oks.saboard.core.filter.xss;

public class XssFilterUtil{
	public static final HelperHTMLInputFilter allHtmlFilter = new HelperHTMLInputFilter(); 
	public static final HelperXSSFilter xssFilter = new HelperXSSFilter();
	
	public static String htmlInputFilter(String input){
		return allHtmlFilter.filter(input);
	}
	
	public static String removeXSS(String input){
		return xssFilter.removeXSS(input);
	}
}
