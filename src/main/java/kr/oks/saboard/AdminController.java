package kr.oks.saboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.oks.saboard.board.domain.BoardTableDomain;
import kr.oks.saboard.board.domain.validator.BoardTableDomainValidator;
import kr.oks.saboard.board.service.BoardService;
import kr.oks.saboard.common.domain.MemberDomain;
import kr.oks.saboard.core.constants.Constants;
import kr.oks.saboard.core.util.SkinUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	
	@Autowired
	private BoardService boardService;
	
	@ModelAttribute(value="boardTableList")
	public List<BoardTableDomain> boardTableList(){
		return boardService.getAllBoardTableList();
	}
	
	private ModelAndView adminLoginCheck(HttpServletRequest request,String viewName) throws Exception{
		ModelAndView mav = new ModelAndView();
		MemberDomain loginMemberDomain = (MemberDomain) request.getSession().getAttribute("loginMemberDomain"); 
		if(loginMemberDomain == null){
			mav.setViewName("redirect:/login.do");
		}else{
			mav.addObject("skinList",SkinUtil.getSkinList(request));
			mav.addObject("loginMemberDomain", loginMemberDomain);
			mav.setViewName(viewName);
		}
		
		return mav;
	}

	@RequestMapping(value = Constants.URL_ADMIN_INDEX)
	public ModelAndView getAdminView(HttpServletRequest request) throws Exception {
		return adminLoginCheck(request, Constants.VIEW_NAME_ADMIN_INDEX);
	}
	
	@RequestMapping(value = Constants.URL_ADMIN_BOARD_ADD, method=RequestMethod.GET)
	public ModelAndView getAdminBoardAddView(HttpServletRequest request,BoardTableDomain boardTableDomain) throws Exception{
		ModelAndView mav = adminLoginCheck(request, Constants.VIEW_NAME_ADMIN_BOARD_ADD);
		
		mav.addObject("boardTableDomain", boardTableDomain);
		mav.addObject("boardTableList", boardService.getAllBoardTableList());
		return mav;
	}
	
	@RequestMapping(value = Constants.URL_ADMIN_BOARD_ADD, method=RequestMethod.POST)
	public ModelAndView getAdminBoardAdd(HttpServletRequest request,BoardTableDomain boardTableDomain,BindingResult result) throws Exception{
		ModelAndView mav = adminLoginCheck(request, "");
		
		String oldBoardId = boardService.getBoardIdById(boardTableDomain);
		
		new BoardTableDomainValidator(oldBoardId,true).validate(boardTableDomain, result);
		
		if(result.hasErrors()){
			return this.getAdminBoardAddView(request,boardTableDomain);
		}else{
			boardService.insertBoardTable(boardTableDomain);
			mav.setViewName("redirect:"+Constants.URL_ADMIN_BOARD_ADD);
		}
		
		return mav;
	}
	
	@RequestMapping(value = Constants.URL_ADMIN_BOARD_MODIFY,method=RequestMethod.GET)
	public ModelAndView getAdminBoardModifyView(HttpServletRequest request,BoardTableDomain boardTableDomain) throws Exception {
		return adminLoginCheck(request, Constants.VIEW_NAME_ADMIN_BOARD_MODIFY);
	}
	
	@RequestMapping(value = Constants.URL_ADMIN_BOARD_MODIFY,method=RequestMethod.POST)
	public ModelAndView getAdminBoardModify(HttpServletRequest request,BoardTableDomain boardTableDomain,BindingResult result) throws Exception {
		ModelAndView mav =  adminLoginCheck(request, "");
		
		String oldBoardId = boardService.getBoardIdById(boardTableDomain);
		
		new BoardTableDomainValidator(oldBoardId,false).validate(boardTableDomain, result);
		
		if(result.hasErrors()){
			return this.getAdminBoardModifyView(request,boardTableDomain);
		}else{
			boardService.modifyBoardTable(boardTableDomain);
			mav.setViewName("redirect:" + Constants.URL_ADMIN_BOARD_MODIFY);
		}
		
		return mav;
	}
	
	@RequestMapping(value = Constants.URL_ADMIN_BOARD_DELETE,method=RequestMethod.GET)
	public ModelAndView getAdminBoardDeleteView(HttpServletRequest request) throws Exception {
		return adminLoginCheck(request, Constants.VIEW_NAME_ADMIN_BOARD_DELETE);
	}
	
	@RequestMapping(value = Constants.URL_ADMIN_BOARD_DELETE,method=RequestMethod.POST)
	public ModelAndView getAdminBoardDelete(HttpServletRequest request,@RequestParam("id") int id) throws Exception {
		ModelAndView mav = adminLoginCheck(request,"");
		
		boardService.deleteBoardTable(id);
		
		mav.setViewName("redirect:" + Constants.URL_ADMIN_BOARD_DELETE);
		
		return mav;
	}
}
