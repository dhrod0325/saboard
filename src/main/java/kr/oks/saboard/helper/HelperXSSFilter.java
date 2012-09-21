package kr.oks.saboard.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperXSSFilter {
	private static interface Patterns {
		public static final Pattern SCRIPTS = Pattern.compile("<(no)?script[^>]*>.*?</(no)?script>", Pattern.DOTALL);
		public static final Pattern SCRIPTS2 = Pattern.compile("(?i)\\<script(.*?)</script>", Pattern.DOTALL);
		public static final Pattern STYLE = Pattern.compile("<style[^>]*>.*</style>", Pattern.DOTALL);
		
		@SuppressWarnings("unused")
		public static final Pattern TAGS = Pattern.compile("<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>");
		
		@SuppressWarnings("unused")
		public static final Pattern nTAGS = Pattern.compile("<\\w+\\s+[^<]*\\s*>");
		
		@SuppressWarnings("unused")
		public static final Pattern ENTITY_REFS = Pattern.compile("&[^;]+;");
		
		public static final Pattern WHITESPACE = Pattern.compile("\\s\\s+");
	}
	
	public boolean checkHasInScript(String str_low){
		return str_low.contains("javascript") || str_low.contains("script")
				|| str_low.contains("iframe") || str_low.contains("document")
				|| str_low.contains("vbscript") || str_low.contains("applet")
				|| str_low.contains("embed") || str_low.contains("object")
				|| str_low.contains("frame") || str_low.contains("grameset")
				|| str_low.contains("layer") || str_low.contains("bgsound")
				|| str_low.contains("alert") || str_low.contains("onblur")
				|| str_low.contains("onchange") || str_low.contains("onclick")
				|| str_low.contains("ondblclick")
				|| str_low.contains("enerror") || str_low.contains("onfocus")
				|| str_low.contains("onload") || str_low.contains("onmouse")
				|| str_low.contains("onscroll") || str_low.contains("onsubmit")
				|| str_low.contains("onunload");
	}
	
	public String removeXSS(String str) {
		Matcher m;
		
		m = Patterns.SCRIPTS.matcher(str);
		str = m.replaceAll("");
		m = Patterns.SCRIPTS2.matcher(str);
		str = m.replaceAll("");
		m = Patterns.STYLE.matcher(str);
		str = m.replaceAll("");
		m = Patterns.WHITESPACE.matcher(str);
		str = m.replaceAll("");
		
		String str_low = str.toLowerCase();
		
		if (checkHasInScript(str_low)) {
			str = str_low;
			str = str.replaceAll("javascript", "x-javascript");
			str = str.replaceAll("script", "x-script");
			str = str.replaceAll("iframe", "x-iframe");
			str = str.replaceAll("document", "x-document");
			str = str.replaceAll("vbscript", "x-vbscript");
			str = str.replaceAll("applet", "x-applet");
			str = str.replaceAll("embed", "x-embed");
			str = str.replaceAll("object", "x-object");
			str = str.replaceAll("frame", "x-frame");
			str = str.replaceAll("grameset", "x-grameset");
			str = str.replaceAll("layer", "x-layer");
			str = str.replaceAll("bgsound", "x-bgsound");
			str = str.replaceAll("alert", "x-alert");
			str = str.replaceAll("onblur", "x-onblur");
			str = str.replaceAll("onchange", "x-onchange");
			str = str.replaceAll("onclick", "x-onclick");
			str = str.replaceAll("ondblclick", "x-ondblclick");
			str = str.replaceAll("enerror", "x-enerror");
			str = str.replaceAll("onfocus", "x-onfocus");
			str = str.replaceAll("onload", "x-onload");
			str = str.replaceAll("onmouse", "x-onmouse");
			str = str.replaceAll("onscroll", "x-onscroll");
			str = str.replaceAll("onsubmit", "x-onsubmit");
			str = str.replaceAll("onunload", "x-onunload");
		}
		return str;
	}
}
