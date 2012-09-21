package kr.oks.saboard.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.oks.saboard.board.domain.BoardDomain;
import kr.oks.saboard.board.domain.BoardFileDomain;
import kr.oks.saboard.board.domain.BoardReplyDomain;
import kr.oks.saboard.board.domain.validator.BoardDomainPasswordValidator;
import kr.oks.saboard.board.domain.validator.BoardDomainValidator;
import kr.oks.saboard.board.domain.validator.BoardReplyDomainValidator;
import kr.oks.saboard.board.service.BoardService;
import kr.oks.saboard.constants.Constants;
import kr.oks.saboard.helper.HelperUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private HelperUtil helperUtil;
	
	/**
	 * 글 쓰기
	 * 
	 * @param boardDomain
	 * @return
	 */
	@RequestMapping(value = Constants.URL_BOARD_WRITE)
	public ModelAndView getBoardWriteView(BoardDomain boardDomain) {
		ModelAndView mav = new ModelAndView(Constants.VIEW_NAME_BOARD_WRITE);
		mav.addObject("boardDomain", boardDomain);
		return mav;
	}
	
	/**
	 * 글 등록
	 * 
	 * @param boardDomain
	 * @param result
	 * @return
	 */
	@RequestMapping(value = Constants.URL_BOARD_INSERT, method = RequestMethod.POST)
	public ModelAndView getBoardInsert(MultipartHttpServletRequest request, BoardDomain boardDomain, BindingResult result) throws Exception {
		ModelAndView mav = new ModelAndView();
		MultipartFile multiPartFile = request.getFile("upload_file");

		new BoardDomainValidator().validate(boardDomain, result);

		if (result.hasErrors()) {
			return this.getBoardWriteView(boardDomain);
		} else {
			mav.setViewName("redirect:" + Constants.URL_BOARD_LIST);

			if (multiPartFile.getSize() > 0)
				boardDomain.setHas_File(Constants.HAS_FILE_YES);

			int boardDomainId = boardService.insertBoard(boardDomain);

			if (multiPartFile.getSize() > 0) {
				String fileName = helperUtil.uploadDirectbbsFile(multiPartFile, Constants.UPLOAD_DIRECTORY);

				BoardFileDomain boardFileDomain = new BoardFileDomain();
				boardFileDomain.setId(boardDomainId);
				boardFileDomain.setFile_name(fileName);
				boardFileDomain.setFile_size(multiPartFile.getSize());

				boardService.insertBoardFile(boardFileDomain);
			}
		}

		return mav;
	}
	
	/**
	 * 댓글 등록
	 * 
	 * @param boardDomain
	 * @param result
	 * @return
	 */
	@RequestMapping(value = Constants.URL_BOARD_INSERT_REPLY, method = RequestMethod.POST)
	public ModelAndView getBoardInsertReply(BoardReplyDomain boardReplyDomain, BindingResult result) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		new BoardReplyDomainValidator().validate(boardReplyDomain, result);
		
		if(result.hasErrors()){
			BoardDomain boardDomain = boardService.getBoardDetailById(boardReplyDomain.getNo());
			return this.getBoardDetailView(boardDomain,boardReplyDomain);
		}else{
			boardService.insertBoardReply(boardReplyDomain);
			mav.setViewName("redirect:" + Constants.URL_BOARD_DETAIL+"?id="+boardReplyDomain.getNo());
		}
		
		return mav;
	}
	
	/**
	 * 글삭제
	 * @param boardDomain
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = Constants.URL_BOARD_DELETE, method = RequestMethod.POST)
	public ModelAndView getBoardDelete(BoardDomain boardDomain,BindingResult result) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		BoardDomain passwordCheckBoardDomain = boardService.getBoardDetailById(boardDomain.getId());
		
		new BoardDomainPasswordValidator(passwordCheckBoardDomain.getPassword()).validate(boardDomain, result);
		
		if(result.hasErrors()){
			return this.getBoardDetailView(boardDomain, new BoardReplyDomain());
		}else{
			boardService.deleteBoardById(boardDomain.getId());
			mav.setViewName("redirect:"+Constants.URL_BOARD_LIST);
		}
		
		return mav;
	}
	
	/**
	 * 글 수정
	 * @param boardDomain
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = Constants.URL_BOARD_MODIFY)
	public ModelAndView getBoardModifyView(BoardDomain boardDomain,BindingResult result)throws Exception {
		ModelAndView mav = new ModelAndView(Constants.VIEW_NAME_BOARD_WRITE);
		
		BoardDomain passwordCheckBoardDomain = boardService.getBoardDetailById(boardDomain.getId());
		
		new BoardDomainPasswordValidator(passwordCheckBoardDomain.getPassword()).validate(boardDomain, result);
		
		if(result.hasErrors()){
			return this.getBoardDetailView(boardDomain, new BoardReplyDomain());
		}else{
			mav.addObject("boardDomain", passwordCheckBoardDomain);
			return mav;
		}
	}
	
	/**
	 * 글 리스트
	 * 
	 * @return
	 */
	@RequestMapping(value = Constants.URL_BOARD_LIST)
	public ModelAndView getBoardListView(BoardDomain boardDomain,HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView(Constants.VIEW_NAME_BOARD_LIST);

		int totSize = boardService.getTotBoardCount();
		boardDomain.setPageSize(10);
		boardDomain.setTotalSize(totSize);

		List<BoardDomain> boardListDomain = boardService.getAllBoardList(boardDomain);
		mav.addObject("boardListDomain", boardListDomain);

		return mav;
	}

	/**
	 * 글 상세보기
	 * 
	 * @param boardDomain
	 * @return
	 */
	@RequestMapping(value = Constants.URL_BOARD_DETAIL)
	public ModelAndView getBoardDetailView(BoardDomain boardDomain,BoardReplyDomain boardReplyDomain) throws Exception {
		ModelAndView mav = new ModelAndView(Constants.VIEW_NAME_BOARD_DETAIL);

		List<BoardFileDomain> boardFileListDomain = null;

		boardDomain = boardService.getBoardDetailById(boardDomain.getId());

		if (boardDomain.getHas_File().equals("YES"))
			boardFileListDomain = boardService.getBoardFileListById(boardDomain.getId());
		
		List<BoardReplyDomain> boardReplyListDomain = boardService.getAllBoardReplyListById(boardDomain.getId());
		
		mav.addObject("boardDomain", boardDomain);
		mav.addObject("boardFileListDomain", boardFileListDomain);
		mav.addObject("boardReplyListDomain",boardReplyListDomain);
		mav.addObject("boardReplyDomain", boardReplyDomain);
		
		return mav;
	}
}
