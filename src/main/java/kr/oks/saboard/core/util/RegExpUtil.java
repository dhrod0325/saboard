package kr.oks.saboard.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpUtil {
	
	/**
	 * 한글이 포함하는지 검사
	 * @param str
	 * @return
	 */
	public static boolean isContainKo(String str) {
		Pattern p = Pattern.compile("[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]");
		Matcher m = p.matcher(str);

		return m.find();
	}

	/**
	 * 이메일 유효성 검사
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email) {
		Pattern p = Pattern.compile("^[_0-9a-zA-Z-]+(\\.[_0-9a-zA-Z-]+)*@[0-9a-zA-Z-]+(\\.[0-9a-zA-Z-]+)*$");
		Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * 금지어 필터링
	 * @param sText
	 * @return
	 */
	public static String filterText(String sText) {
		Pattern p = Pattern.compile("fuck|shit|개새끼", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(sText);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, maskWord(m.group()));
		}
		m.appendTail(sb);
		return sb.toString();
	}

	private static String maskWord(String word) {
		StringBuffer buff = new StringBuffer();
		char[] ch = word.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if (i < 1) {
				buff.append(ch[i]);
			} else {
				buff.append("*");
			}
		}
		return buff.toString();
	}
}
