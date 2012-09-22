package kr.oks.saboard.core.filter.xss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HelperHTMLInputFilter {
	protected static final boolean ALWAYS_MAKE_TAGS = true;

	protected static final boolean STRIP_COMMENTS = true;

	protected static final int REGEX_FLAGS_SI = Pattern.CASE_INSENSITIVE | Pattern.DOTALL;

	protected Map<String, List<String>> vAllowed;

	protected Map<String, Integer> vTagCounts;

	protected String[] vSelfClosingTags;

	protected String[] vNeedClosingTags;

	protected String[] vProtocolAtts;

	protected String[] vAllowedProtocols;

	protected String[] vRemoveBlanks;

	protected String[] vAllowedEntities;

	protected boolean vDebug;

	protected void reset() {
		vTagCounts = new HashMap<String, Integer>();
	}

	// ---------------------------------------------------------------
	// my versions of some PHP library functions

	public static String chr(int decimal) {
		return String.valueOf((char) decimal);
	}

	public static String htmlSpecialChars(String s) {
		s = s.replaceAll("&", "&amp;");
		s = s.replaceAll("\"", "&quot;");
		s = s.replaceAll("<", "&lt;");
		s = s.replaceAll(">", "&gt;");
		return s;
	}

	// ---------------------------------------------------------------

	public synchronized String filter(String input) {
		reset();
		String s = input;

		return s;
	}

	protected String escapeComments(String s) {
		Pattern p = Pattern.compile("<!--(.*?)-->", Pattern.DOTALL);
		Matcher m = p.matcher(s);
		StringBuffer buf = new StringBuffer();
		if (m.find()) {
			String match = m.group(1); // (.*?)
			m.appendReplacement(buf, "<!--" + htmlSpecialChars(match) + "-->");
		}
		m.appendTail(buf);

		return buf.toString();
	}

	protected String balanceHTML(String s) {
		if (ALWAYS_MAKE_TAGS) {
			s = regexReplace("^>", "", s);
			s = regexReplace("<([^>]*?)(?=<|$)", "<$1>", s);
			s = regexReplace("(^|>)([^<]*?)(?=>)", "$1<$2", s);

		} else {
			//
			// escape stray brackets
			//
			s = regexReplace("<([^>]*?)(?=<|$)", "&lt;$1", s);
			s = regexReplace("(^|>)([^<]*?)(?=>)", "$1$2&gt;<", s);

			//
			// the last regexp causes '<>' entities to appear
			// (we need to do a lookahead assertion so that the last bracket can
			// be used in the next pass of the regexp)
			//
			s = s.replaceAll("<>", "");
		}

		return s;
	}

	protected String checkTags(String s) {
		Pattern p = Pattern.compile("<(.*?)>", Pattern.DOTALL);
		Matcher m = p.matcher(s);

		StringBuffer buf = new StringBuffer();
		while (m.find()) {
			String replaceStr = m.group(1);
			replaceStr = processTag(replaceStr);
			m.appendReplacement(buf, replaceStr);
		}
		m.appendTail(buf);

		s = buf.toString();

		// these get tallied in processTag
		// (remember to reset before subsequent calls to filter method)
		for (String key : vTagCounts.keySet()) {
			for (int ii = 0; ii < vTagCounts.get(key); ii++) {
				s += "</" + key + ">";
			}
		}

		return s;
	}

	protected String processRemoveBlanks(String s) {
		for (String tag : vRemoveBlanks) {
			s = regexReplace("<" + tag + "(\\s[^>]*)?></" + tag + ">", "", s);
			s = regexReplace("<" + tag + "(\\s[^>]*)?/>", "", s);
		}

		return s;
	}

	protected String regexReplace(String regex_pattern, String replacement, String s) {
		Pattern p = Pattern.compile(regex_pattern);
		Matcher m = p.matcher(s);
		return m.replaceAll(replacement);
	}

	protected String processTag(String s) {
		// ending tags
		Pattern p = Pattern.compile("^/([a-z0-9]+)", REGEX_FLAGS_SI);
		Matcher m = p.matcher(s);
		if (m.find()) {
			String name = m.group(1).toLowerCase();
			if (vAllowed.containsKey(name)) {
				if (!inArray(name, vSelfClosingTags)) {
					if (vTagCounts.containsKey(name)) {
						vTagCounts.put(name, vTagCounts.get(name) - 1);
						return "</" + name + ">";
					}
				}
			}
		}

		// starting tags
		p = Pattern.compile("^([a-z0-9]+)(.*?)(/?)$", REGEX_FLAGS_SI);
		m = p.matcher(s);
		if (m.find()) {
			String name = m.group(1).toLowerCase();
			String body = m.group(2);
			String ending = m.group(3);

			// debug( "in a starting tag, name='" + name + "'; body='" + body +
			// "'; ending='" + ending + "'" );
			if (vAllowed.containsKey(name)) {
				String params = "";

				Pattern p2 = Pattern.compile("([a-z0-9]+)=([\"'])(.*?)\\2", REGEX_FLAGS_SI);
				Pattern p3 = Pattern.compile("([a-z0-9]+)(=)([^\"\\s']+)", REGEX_FLAGS_SI);
				Matcher m2 = p2.matcher(body);
				Matcher m3 = p3.matcher(body);
				List<String> paramNames = new ArrayList<String>();
				List<String> paramValues = new ArrayList<String>();
				while (m2.find()) {
					paramNames.add(m2.group(1)); // ([a-z0-9]+)
					paramValues.add(m2.group(3)); // (.*?)
				}
				while (m3.find()) {
					paramNames.add(m3.group(1)); // ([a-z0-9]+)
					paramValues.add(m3.group(3)); // ([^\"\\s']+)
				}

				String paramName, paramValue;
				for (int ii = 0; ii < paramNames.size(); ii++) {
					paramName = paramNames.get(ii).toLowerCase();
					paramValue = paramValues.get(ii);

					// debug( "paramName='" + paramName + "'" );
					// debug( "paramValue='" + paramValue + "'" );
					// debug( "allowed? " + vAllowed.get( name ).contains(
					// paramName ) );

					if (vAllowed.get(name).contains(paramName)) {
						if (inArray(paramName, vProtocolAtts)) {
							paramValue = processParamProtocol(paramValue);
						}
						params += " " + paramName + "=\"" + paramValue + "\"";
					}
				}

				if (inArray(name, vSelfClosingTags)) {
					ending = " /";
				}

				if (inArray(name, vNeedClosingTags)) {
					ending = "";
				}

				if (ending == null || ending.length() < 1) {
					if (vTagCounts.containsKey(name)) {
						vTagCounts.put(name, vTagCounts.get(name) + 1);
					} else {
						vTagCounts.put(name, 1);
					}
				} else {
					ending = " /";
				}
				return "<" + name + params + ending + ">";
			} else {
				return "";
			}
		}

		// comments
		p = Pattern.compile("^!--(.*)--$", REGEX_FLAGS_SI);
		m = p.matcher(s);
		if (m.find()) {
			String comment = m.group();
			if (STRIP_COMMENTS) {
				return "";
			} else {
				return "<" + comment + ">";
			}
		}

		return "";
	}

	protected String processParamProtocol(String s) {
		s = decodeEntities(s);
		Pattern p = Pattern.compile("^([^:]+):", REGEX_FLAGS_SI);
		Matcher m = p.matcher(s);
		if (m.find()) {
			String protocol = m.group(1);
			if (!inArray(protocol, vAllowedProtocols)) {
				// bad protocol, turn into local anchor link instead
				s = "#" + s.substring(protocol.length() + 1, s.length());
				if (s.startsWith("#//"))
					s = "#" + s.substring(3, s.length());
			}
		}

		return s;
	}

	protected String decodeEntities(String s) {
		StringBuffer buf = new StringBuffer();

		Pattern p = Pattern.compile("&#(\\d+);?");
		Matcher m = p.matcher(s);
		while (m.find()) {
			String match = m.group(1);
			int decimal = Integer.decode(match).intValue();
			m.appendReplacement(buf, chr(decimal));
		}
		m.appendTail(buf);
		s = buf.toString();

		buf = new StringBuffer();
		p = Pattern.compile("&#x([0-9a-f]+);?");
		m = p.matcher(s);
		while (m.find()) {
			String match = m.group(1);
			int decimal = Integer.decode(match).intValue();
			m.appendReplacement(buf, chr(decimal));
		}
		m.appendTail(buf);
		s = buf.toString();

		buf = new StringBuffer();
		p = Pattern.compile("%([0-9a-f]{2});?");
		m = p.matcher(s);
		while (m.find()) {
			String match = m.group(1);
			int decimal = Integer.decode(match).intValue();
			m.appendReplacement(buf, chr(decimal));
		}
		m.appendTail(buf);
		s = buf.toString();

		s = validateEntities(s);
		return s;
	}

	protected String validateEntities(String s) {
		// validate entities throughout the string
		Pattern p = Pattern.compile("&([^&;]*)(?=(;|&|$))");
		Matcher m = p.matcher(s);
		if (m.find()) {
			String one = m.group(1); // ([^&;]*)
			String two = m.group(2); // (?=(;|&|$))
			s = checkEntity(one, two);
		}

		// validate quotes outside of tags
		p = Pattern.compile("(>|^)([^<]+?)(<|$)", Pattern.DOTALL);
		m = p.matcher(s);
		StringBuffer buf = new StringBuffer();
		if (m.find()) {
			String one = m.group(1); // (>|^)
			String two = m.group(2); // ([^<]+?)
			String three = m.group(3); // (<|$)
			m.appendReplacement(buf, one + two.replaceAll("\"", "&quot;") + three);
		}
		m.appendTail(buf);

		return s;
	}

	protected String checkEntity(String preamble, String term) {
		if (!term.equals(";")) {
			return "&amp;" + preamble;
		}

		if (isValidEntity(preamble)) {
			return "&" + preamble;
		}

		return "&amp;" + preamble;
	}

	protected boolean isValidEntity(String entity) {
		return inArray(entity, vAllowedEntities);
	}

	private boolean inArray(String s, String[] array) {
		for (String item : array)
			if (item != null && item.equals(s))
				return true;

		return false;
	}

}
