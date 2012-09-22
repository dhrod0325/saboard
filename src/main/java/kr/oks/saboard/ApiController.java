package kr.oks.saboard;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.oks.saboard.board.domain.BoardDomain;
import kr.oks.saboard.board.domain.BoardFileDomain;
import kr.oks.saboard.board.domain.BoardReplyDomain;
import kr.oks.saboard.board.service.BoardService;
import kr.oks.saboard.core.constants.Constants;
import kr.oks.saboard.core.constants.ConstantsDB;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApiController {
	
	@Resource(name=ConstantsDB.BOARD_SERVICE_BEAN_NAME)
	private BoardService boardService;
	
	/**
	 * 글 목록
	 * @param boardDomain
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/api"+Constants.URL_BOARD_LIST)
	public ModelAndView getBoardListView(BoardDomain boardDomain,HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		
		int totSize = boardService.getTotBoardCount(boardDomain.getBoard_id());
		boardDomain.setPageSize(10);
		boardDomain.setTotalSize(totSize);
		
		List<BoardDomain> boardListDomain = boardService.getAllBoardList(boardDomain);
		
		mav.addObject("boardListDomain", boardListDomain);
		
		return mav;
	}
	
	/**
	 * 글 상세보기
	 * @param boardDomain
	 * @param boardReplyDomain
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/api"+Constants.URL_BOARD_DETAIL)
	public ModelAndView getBoardDetailView(BoardDomain boardDomain,BoardReplyDomain boardReplyDomain) throws Exception {
		ModelAndView mav = new ModelAndView("jsonView");
		
		List<BoardFileDomain> boardFileListDomain = null;

		boardDomain = boardService.getBoardDetailById(boardDomain.getId());

		if (boardDomain.getHas_File().equals("YES"))
			boardFileListDomain = boardService.getBoardFileListById(boardDomain.getId());
		
		int totSize = boardService.getTotBoardReplyCount(boardDomain.getId());
		boardReplyDomain.setPageSize(5);
		boardReplyDomain.setTotalSize(totSize);
		boardReplyDomain.setNo(boardDomain.getId());
		
		List<BoardReplyDomain> boardReplyListDomain = boardService.getAllBoardReplyListById(boardReplyDomain);
		
		String tmpPassword = "패스워드는 공개되지 않습니다.";
		
		for(BoardReplyDomain rd : boardReplyListDomain){
			rd.setPassword(tmpPassword);
		}
		
		boardReplyDomain.setPassword(tmpPassword);
		boardDomain.setPassword(tmpPassword);
		
		mav.addObject("boardDomain", boardDomain);
		mav.addObject("boardFileListDomain", boardFileListDomain);
		mav.addObject("boardReplyListDomain",boardReplyListDomain);
		mav.addObject("boardReplyDomain", boardReplyDomain);
		
		return mav;
	}
}
