package kr.oks.saboard.core.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.oks.saboard.core.constants.Constants;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;


public class SessionUtil {
	/**
	 * attribute 값을 가져 오기 위한 method
	 * 
	 * @param String
	 *            attribute key name
	 * @return Object attribute obj
	 */
	public static Object getAttribute(String name) throws Exception {
		return RequestContextHolder.getRequestAttributes().getAttribute(name, RequestAttributes.SCOPE_SESSION);
	}

	/**
	 * attribute 설정 method
	 * 
	 * @param String
	 *            attribute key name
	 * @param Object
	 *            attribute obj
	 * @return void
	 */
	public static void setAttribute(String name, Object object) throws Exception {
		RequestContextHolder.getRequestAttributes().setAttribute(name, object, RequestAttributes.SCOPE_SESSION);
	}

	/**
	 * 설정한 attribute 삭제
	 * 
	 * @param String
	 *            attribute key name
	 * @return void
	 */
	public static void removeAttribute(String name) throws Exception {
		RequestContextHolder.getRequestAttributes().removeAttribute(name, RequestAttributes.SCOPE_SESSION);
	}

	/**
	 * session id
	 * 
	 * @param void
	 * @return String SessionId 값
	 */
	public static String getSessionId() throws Exception {
		return RequestContextHolder.getRequestAttributes().getSessionId();
	}

	public static boolean isLogined() throws Exception {
		Object obj = getAttribute(Constants.LOGIN_MEMBER_DOMAIN);
		return (obj != null);
	}

	@SuppressWarnings("unchecked")
	public static void invalidate(HttpSession session) {
		Enumeration<String> enu = session.getAttributeNames();
		while (enu.hasMoreElements()) {
			session.removeAttribute(enu.nextElement());
		}
		session.invalidate();
	}
	
	public static void sendRedirect(HttpServletRequest request,HttpServletResponse response,String url) throws Exception{
		String rootPath = request.getContextPath();
		String serverName = request.getServerName();
		int port = request.getServerPort();
		
		String urlprefix = "http://"+serverName+":"+port+rootPath;
		
		String redirectUrl = urlprefix+ url;
		response.sendRedirect(redirectUrl);
	}
}
