package kr.oks.saboard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.oks.saboard.board.domain.BoardDomain;
import kr.oks.saboard.board.domain.BoardFileDomain;
import kr.oks.saboard.board.domain.BoardReplyDomain;
import kr.oks.saboard.board.domain.validator.BoardDomainPasswordValidator;
import kr.oks.saboard.board.domain.validator.BoardDomainValidator;
import kr.oks.saboard.board.domain.validator.BoardReplyDomainValidator;
import kr.oks.saboard.board.service.BoardService;
import kr.oks.saboard.core.constants.Constants;
import kr.oks.saboard.core.util.FileUtil;
import kr.oks.saboard.core.util.auth.AuthUtil;

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

	private void setRedirectView(ModelAndView mav, String url, BoardDomain boardDomain) {
		mav.setViewName("redirect:" + url + "?board_id=" + boardDomain.getBoard_id());
	}

	private ModelAndView getThemeView(BoardDomain boardDomain, String viewName) {
		ModelAndView mav = new ModelAndView();
		
		String boardId = "";
		
		/**
		 * get 파라미터와 post 파라미터가 겹치기 때문에 짜르는 로직 추가
		 */
		if (boardDomain.getBoard_id() != null) {
			boardId = boardDomain.getBoard_id();

			if (boardId.indexOf(",") != -1) {
				boardId = boardId.split(",")[0];
			}
		}

		String theme = boardService.getBoardTheme(boardId);

		if (theme == null) {
			mav.setViewName(Constants.VIEW_NAME_BOARD_ERROR);
		} else {
			boardDomain.setBoard_id(boardId);
			mav.addObject("theme", theme);
			mav.setViewName(viewName);
		}
		
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
			setRedirectView(mav, Constants.URL_BOARD_LIST, boardDomain);

			if (multiPartFile.getSize() > 0)
				boardDomain.setHas_File(Constants.HAS_FILE_YES);

			int boardDomainId = boardService.insertBoard(boardDomain);

			if (multiPartFile.getSize() > 0) {
				String fileName = FileUtil.uploadDirectbbsFile(request, multiPartFile, Constants.UPLOAD_DIRECTORY);
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

		if (result.hasErrors()) {
			BoardDomain boardDomain = boardService.getBoardDetailById(boardReplyDomain.getNo());
			return this.getBoardDetailView(boardDomain, boardReplyDomain);
		} else {
			boardService.insertBoardReply(boardReplyDomain);
			mav.setViewName("redirect:" + Constants.URL_BOARD_DETAIL + "?id=" + boardReplyDomain.getNo() + "&board_id=" + boardReplyDomain.getBoard_id());
		}

		return mav;
	}

	/**
	 * 글삭제
	 * 
	 * @param boardDomain
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = Constants.URL_BOARD_DELETE, method = RequestMethod.POST)
	public ModelAndView getBoardDelete(BoardDomain boardDomain, BindingResult result) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		if(AuthUtil.IsAdmin()){
			boardService.deleteBoardById(boardDomain.getId());
			setRedirectView(mav, Constants.URL_BOARD_LIST, boardDomain);
			return mav;
		}
		
		if(AuthUtil.IsMember()){
			BoardDomain passwordCheckBoardDomain = boardService.getBoardDetailById(boardDomain.getId());

			new BoardDomainPasswordValidator(passwordCheckBoardDomain.getPassword()).validate(boardDomain, result);

			if (result.hasErrors()) {
				return this.getBoardDetailView(boardDomain, new BoardReplyDomain());
			} else {
				boardService.deleteBoardById(boardDomain.getId());
				setRedirectView(mav, Constants.URL_BOARD_LIST, boardDomain);
			}
		}
		
		return mav;
	}

	/**
	 * 글 수정
	 * 
	 * @param boardDomain
	 * @param result
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = Constants.URL_BOARD_MODIFY)
	public ModelAndView getBoardModifyView(BoardDomain boardDomain, BindingResult result) throws Exception {
		ModelAndView mav = getThemeView(boardDomain, Constants.VIEW_NAME_BOARD_WRITE);
		
		BoardDomain passwordCheckBoardDomain = boardService.getBoardDetailById(boardDomain.getId());
		
		if(AuthUtil.IsAdmin()){
			mav.addObject("boardDomain", passwordCheckBoardDomain);
			return mav;
		}
		
		if(AuthUtil.IsMember()){
			new BoardDomainPasswordValidator(passwordCheckBoardDomain.getPassword()).validate(boardDomain, result);

			if (result.hasErrors()) {
				return this.getBoardDetailView(boardDomain, new BoardReplyDomain());
			} else {
				mav.addObject("boardDomain", passwordCheckBoardDomain);
				return mav;
			}
		}
		
		return null;
	}

	/**
	 * 글 쓰기
	 * 
	 * @param boardDomain
	 * @return
	 */
	@RequestMapping(value = Constants.URL_BOARD_WRITE)
	public ModelAndView getBoardWriteView(BoardDomain boardDomain) {
		ModelAndView mav = getThemeView(boardDomain, Constants.VIEW_NAME_BOARD_WRITE);
		mav.addObject("boardDomain", boardDomain);
		
		return mav;
	}

	/**
	 * 글 리스트
	 * 
	 * @return
	 */
	@RequestMapping(value = Constants.URL_BOARD_LIST)
	public ModelAndView getBoardListView(BoardDomain boardDomain, HttpServletRequest request) throws Exception {
		ModelAndView mav = getThemeView(boardDomain, Constants.VIEW_NAME_BOARD_LIST);

		int totSize = boardService.getTotBoardCount(boardDomain.getBoard_id());
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
	public ModelAndView getBoardDetailView(BoardDomain boardDomain, BoardReplyDomain boardReplyDomain) throws Exception {
		ModelAndView mav = getThemeView(boardDomain, Constants.VIEW_NAME_BOARD_DETAIL);

		List<BoardFileDomain> boardFileListDomain = null;

		boardDomain = boardService.getBoardDetailById(boardDomain.getId());

		if (boardDomain.getHas_File().equals("YES"))
			boardFileListDomain = boardService.getBoardFileListById(boardDomain.getId());

		int totSize = boardService.getTotBoardReplyCount(boardDomain.getId());
		boardReplyDomain.setPageSize(5);
		boardReplyDomain.setTotalSize(totSize);
		boardReplyDomain.setNo(boardDomain.getId());

		List<BoardReplyDomain> boardReplyListDomain = boardService.getAllBoardReplyListById(boardReplyDomain);

		mav.addObject("boardDomain", boardDomain);
		mav.addObject("boardFileListDomain", boardFileListDomain);
		mav.addObject("boardReplyListDomain", boardReplyListDomain);
		mav.addObject("boardReplyDomain", boardReplyDomain);

		return mav;
	}
}

