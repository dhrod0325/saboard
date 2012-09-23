package kr.oks.saboard.core.util.auth;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.oks.saboard.common.domain.MemberDomain;
import kr.oks.saboard.core.constants.Constants;
import kr.oks.saboard.core.util.SessionUtil;

public class AuthUtil extends SessionUtil{
	public static MemberDomain getLoginMemberDomain() throws Exception{
		MemberDomain memberDomain =  (MemberDomain) getAttribute(Constants.LOGIN_MEMBER_DOMAIN);
		return memberDomain == null ? new MemberDomain() : memberDomain;
	}
	
	public static boolean IsAdmin() throws Exception{
		return getLoginMemberDomain().getIsAdmin();
	}
	
	public static boolean IsMember() throws Exception{
		return getLoginMemberDomain().getIsMember();
	}
	
	public static boolean IsNotMember() throws Exception{
		return getLoginMemberDomain().getIsNotMember();
	}
	
	public static void logout() throws Exception{
		removeAttribute(Constants.LOGIN_MEMBER_DOMAIN);
	}
	
	public static void setRsaModule(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int KEY_SIZE = 1024;

		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(KEY_SIZE);

		KeyPair keyPair = generator.genKeyPair();
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");

		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();

		HttpSession session = request.getSession();

		// 세션에 공개키의 문자열을 키로하여 개인키를 저장한다.
		session.setAttribute("__rsaPrivateKey__", privateKey);

		// 공개키를 문자열로 변환하여 JavaScript RSA 라이브러리 넘겨준다.
		RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
		
		String publicKeyModulus = publicSpec.getModulus().toString(16);
		String publicKeyExponent = publicSpec.getPublicExponent().toString(16);

		setAttribute("publicKeyModulus", publicKeyModulus);
		setAttribute("publicKeyExponent", publicKeyExponent);
	}
	
	public static HashMap<String, String> getRsaDecodeMap(HttpServletRequest request) throws Exception{
		HashMap<String, String>tmpMap = new HashMap<String, String>();
		
		String securedUsername = request.getParameter("securedUsername");
		String securedPassword = request.getParameter("securedPassword");
		
		HttpSession session = request.getSession();
		PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
		session.removeAttribute("__rsaPrivateKey__"); 
		
		if (privateKey == null) {
			return null;
		}
		
		String username = CipherUtil.decryptRsa(privateKey, securedUsername);
		String password = CipherUtil.decryptRsa(privateKey, securedPassword);
		
		tmpMap.put("username", username);
		tmpMap.put("password", password);
		
		return tmpMap;
	}
}
