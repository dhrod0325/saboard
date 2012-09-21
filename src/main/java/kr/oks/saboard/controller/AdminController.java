package kr.oks.saboard.controller;

import java.util.List;

import kr.oks.saboard.board.service.BoardService;
import kr.oks.saboard.constants.Constants;
import kr.oks.saboard.helper.HelperUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private HelperUtil helperUtil;
	
	@ModelAttribute(value="skinList")
	public List<String> skinList() throws Exception{
		return helperUtil.getSkinList();
	}
	
	@RequestMapping(value = Constants.URL_ADMIN_ROOT)
	public ModelAndView getAdmin() {
		return this.getAdminView();
	}
	
	@RequestMapping(value = Constants.URL_ADMIN_INDEX)
	public ModelAndView getAdminView() {
		ModelAndView mav = new ModelAndView(Constants.VIEW_NAME_ADMIN_INDEX);
		return mav;
	}
	
	@RequestMapping(value = Constants.URL_ADMIN_BOARD_ADD)
	public ModelAndView getAdminBoardAdd() {
		ModelAndView mav = new ModelAndView(Constants.VIEW_NAME_ADMIN_BOARD_ADD);
		return mav;
	}
	
	@RequestMapping(value = Constants.URL_ADMIN_BOARD_MODIFY)
	public ModelAndView getAdminBoardModify() {
		ModelAndView mav = new ModelAndView(Constants.VIEW_NAME_ADMIN_BOARD_MODIFY);
		return mav;
	}
	
	@RequestMapping(value = Constants.URL_ADMIN_BOARD_DELETE)
	public ModelAndView getAdminBoardDelete() {
		ModelAndView mav = new ModelAndView(Constants.VIEW_NAME_ADMIN_BOARD_DELETE);
		return mav;
	}
	
}
