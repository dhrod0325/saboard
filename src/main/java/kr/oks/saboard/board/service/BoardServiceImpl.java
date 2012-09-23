package kr.oks.saboard.board.service;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import kr.oks.saboard.board.dao.BoardDao;
import kr.oks.saboard.board.domain.BoardDomain;
import kr.oks.saboard.board.domain.BoardFileDomain;
import kr.oks.saboard.board.domain.BoardReplyDomain;
import kr.oks.saboard.board.domain.BoardTableDomain;
import kr.oks.saboard.board.event.BoardInsertEvent;
import kr.oks.saboard.core.filter.xss.XSSFilter;
import kr.oks.saboard.core.util.auth.AuthUtil;

public class BoardServiceImpl implements BoardService,ApplicationContextAware{
	private BoardDao boardDao;
	
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	@Autowired
	private ApplicationContext context;
	
	public int insertBoard(BoardDomain boardDomain) throws Exception {
		BoardDomain oldBoardDomain = getBoardDetailById(boardDomain.getId());
		
		boardDomain.setUser_id(XSSFilter.removeXSS(boardDomain.getUser_id()));
		boardDomain.setTitle(XSSFilter.removeXSS(boardDomain.getTitle()));
		boardDomain.setContent(XSSFilter.removeXSS(boardDomain.getContent()));
		boardDomain.setPassword(XSSFilter.removeXSS(boardDomain.getPassword()));
		
		if(oldBoardDomain == null){
			
			context.publishEvent(new BoardInsertEvent(this, boardDomain));
			
			return boardDao.insertBoard(boardDomain);
		}else{
			if(AuthUtil.getLoginMemberDomain().getIsAdmin()){
				boardDao.updateBoardByboardDomain(boardDomain);
				return boardDomain.getId();
			}
			
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

	public int getTotBoardCount(String board_id) {
		return boardDao.getTotBoardCount(board_id);
	}

	public void insertBoardFile(BoardFileDomain boardFileDomain) {
		boardFileDomain.setFile_name(XSSFilter.removeXSS(boardFileDomain.getFile_name()));
		boardDao.insertBoardFile(boardFileDomain);
	}


	public int insertBoardReply(BoardReplyDomain boardReplyDomain) {
		boardReplyDomain.setUser_id(XSSFilter.removeXSS(boardReplyDomain.getUser_id()));
		boardReplyDomain.setEmail(XSSFilter.removeXSS(boardReplyDomain.getEmail()));
		boardReplyDomain.setContent(XSSFilter.removeXSS(boardReplyDomain.getContent()));
		return boardDao.insertBoardReply(boardReplyDomain);
	}


	public List<BoardReplyDomain> getAllBoardReplyListById(BoardReplyDomain boardReplyDomain) {
		return boardDao.getAllBoardReplyListById(boardReplyDomain);
	}

	public List<BoardFileDomain> getBoardFileListById(int id) throws Exception {
		return boardDao.getBoardFileListById(id);
	}
	
	public List<BoardTableDomain> getAllBoardTableList() {
		return boardDao.getAllBoardTableList();
	}
	
	public void insertBoardTable(BoardTableDomain boardTableDomain) {
		boardDao.insertBoardTable(boardTableDomain);
	}

	public String getBoardTheme(String board_id) {
		return boardDao.getBoardTheme(board_id);
	}

	public void deleteBoardTable(int id) {
		boardDao.deleteBoardTable(id);
	}

	public void modifyBoardTable(BoardTableDomain boardTableDomain) {
		boardDao.modifyBoardTable(boardTableDomain);
	}

	public String getBoardIdById(BoardTableDomain boardTableDomain) {
		return boardDao.getBoardIdById(boardTableDomain);
	}

	public int getTotBoardReplyCount(int no) {
		return boardDao.getTotBoardReplyCount(no);
	}

	@Override
	public void deleteBoardReplyById(int id) {
		boardDao.deleteBoardReplyById(id);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		
	}
}