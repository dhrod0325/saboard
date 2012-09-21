package kr.oks.saboard.board.service;

import java.util.List;

import kr.oks.saboard.board.dao.BoardDao;
import kr.oks.saboard.board.domain.BoardDomain;
import kr.oks.saboard.board.domain.BoardFileDomain;
import kr.oks.saboard.board.domain.BoardReplyDomain;
import kr.oks.saboard.helper.HelperUtil;

public class BoardServiceImpl implements BoardService{
	private HelperUtil helperUtil = new HelperUtil();
	
	private BoardDao boardDao;
	
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	public int insertBoard(BoardDomain boardDomain) {
		BoardDomain oldBoardDomain = getBoardDetailById(boardDomain.getId());
		
		boardDomain.setUser_id(helperUtil.htmlInputFilter(boardDomain.getUser_id()));
		boardDomain.setTitle(helperUtil.htmlInputFilter(boardDomain.getTitle()));
		boardDomain.setContent(helperUtil.removeXSS(boardDomain.getContent()));
		
		if(oldBoardDomain == null){
			return boardDao.insertBoard(boardDomain);
		}else{
			if(oldBoardDomain.getPassword().equals(boardDomain.getPassword()))
				boardDao.updateBoardByboardDomain(boardDomain);
			
			return boardDomain.getId();
		}
	}

	public List<BoardDomain> getAllBoardList(BoardDomain boardDomain) {
		return boardDao.getAllBoardList(boardDomain);
	}

	public BoardDomain getBoardDetailById(int id) {
		return boardDao.getBoardDetailById(id);
	}

	public void deleteBoardById(int id) {
		boardDao.deleteBoardById(id);
	}

	public void updateBoardByboardDomain(BoardDomain domain) {
		boardDao.updateBoardByboardDomain(domain);
	}

	public int getTotBoardCount() {
		return boardDao.getTotBoardCount();
	}

	public void insertBoardFile(BoardFileDomain boardFileDomain) {
		boardFileDomain.setFile_name(helperUtil.htmlInputFilter(boardFileDomain.getFile_name()));
		boardDao.insertBoardFile(boardFileDomain);
	}


	public void insertBoardReply(BoardReplyDomain boardReplyDomain) {
		boardReplyDomain.setUser_id(helperUtil.htmlInputFilter(boardReplyDomain.getUser_id()));
		boardReplyDomain.setEmail(helperUtil.htmlInputFilter(boardReplyDomain.getEmail()));
		boardReplyDomain.setContent(helperUtil.htmlInputFilter(boardReplyDomain.getContent()));
		boardDao.insertBoardReply(boardReplyDomain);
	}


	public List<BoardReplyDomain> getAllBoardReplyListById(int id) {
		return boardDao.getAllBoardReplyListById(id);
	}

	public List<BoardFileDomain> getBoardFileListById(int id) throws Exception {
		return boardDao.getBoardFileListById(id);
	}
}