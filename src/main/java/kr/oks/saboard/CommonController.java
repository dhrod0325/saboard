package kr.oks.saboard;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.oks.saboard.common.domain.MemberDomain;
import kr.oks.saboard.common.service.CommonService;
import kr.oks.saboard.core.constants.Constants;
import kr.oks.saboard.core.constants.ConstantsDB;
import kr.oks.saboard.core.util.SessionUtil;
import kr.oks.saboard.core.util.auth.AuthUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController{
	
	@Resource(name=ConstantsDB.COMMON_SERVICE_BEAN_NAME)
	private CommonService commonService;
	
	
	@RequestMapping(value = "/")
	public String index() {
		return "redirect:" + Constants.URL_BOARD_LIST;
	}
	
	@RequestMapping(value = Constants.URL_LOGIN_JOIN, method = RequestMethod.GET)
	public String join(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AuthUtil.setRsaModule(request,response);
		return Constants.VIEW_NAME_JOIN;
	}
	
	@RequestMapping(value = Constants.URL_LOGIN_JOIN, method = RequestMethod.POST)
	public String joinProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, String> authMap = AuthUtil.getRsaDecodeMap(request);
		String secured_reg_userName = request.getParameter("secured_reg_userName");
		
		MemberDomain memberDomain = new MemberDomain();
		memberDomain.setId(authMap.get(Constants.KEY_USER_ID));
		memberDomain.setPw(authMap.get(Constants.KEY_USER_PASSWORD));
		memberDomain.setName(secured_reg_userName);
			
		commonService.join(memberDomain);
			
		return "redirect:"+Constants.URL_LOGIN;
	}
	
	@RequestMapping(value = Constants.URL_LOGIN, method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AuthUtil.setRsaModule(request,response);
		
		return Constants.VIEW_NAME_LOGIN;
	}
	
	@RequestMapping(value = Constants.URL_LOGIN, method = RequestMethod.POST)
	public String loginProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, String> authMap = AuthUtil.getRsaDecodeMap(request);
		
		if(authMap == null){
			return "redirect:"+Constants.URL_LOGIN;
		}else{
			MemberDomain memberDomain = new MemberDomain();
			memberDomain.setId(authMap.get(Constants.KEY_USER_ID));
			memberDomain.setPw(authMap.get(Constants.KEY_USER_PASSWORD));
			
			memberDomain = commonService.getLoginMemeberDomain(memberDomain); 
			
			if(memberDomain == null){
				return "redirect:"+Constants.URL_LOGIN;
			}else{
				SessionUtil.setAttribute(Constants.LOGIN_MEMBER_DOMAIN, memberDomain);
				return "redirect:"+Constants.URL_ADMIN_ROOT;
			}
		}
	}
	
	@RequestMapping(value = Constants.URL_LOOUT)
	public String logout() throws Exception {
		AuthUtil.logout();
		return Constants.VIEW_NAME_LOGIN;
	}
}
