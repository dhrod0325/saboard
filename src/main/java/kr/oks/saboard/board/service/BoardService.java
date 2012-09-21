package kr.oks.saboard.board.service;

import java.util.List;

import kr.oks.saboard.board.domain.BoardDomain;
import kr.oks.saboard.board.domain.BoardFileDomain;
import kr.oks.saboard.board.domain.BoardReplyDomain;
import kr.oks.saboard.board.domain.BoardTableDomain;

public interface BoardService {
	public int insertBoard(BoardDomain boardDomain) throws Exception;
	
	public List<BoardDomain> getAllBoardList(BoardDomain boardDomain);
	
	public BoardDomain getBoardDetailById(int id);
	
	public void deleteBoardById(int id) throws Exception;
	
	public void updateBoardByboardDomain(BoardDomain boardDomain);
	
	public int getTotBoardCount(String board_id);

	public int getTotBoardReplyCount(int no);
	
	public void insertBoardFile(BoardFileDomain boardFileDomain);
	
	public List<BoardFileDomain> getBoardFileListById(int id) throws Exception;
	
	public void insertBoardReply(BoardReplyDomain boardReplyDomain);
	
	public List<BoardReplyDomain> getAllBoardReplyListById(BoardReplyDomain boardReplyDomain);
	
	public List<BoardTableDomain> getAllBoardTableList();
	
	public void insertBoardTable(BoardTableDomain boardTableDomain);
	
	public String getBoardTheme(String board_id);
	
	public void deleteBoardTable(int id);
	
	public void modifyBoardTable(BoardTableDomain boardTableDomain);
	
	public String getBoardIdById(BoardTableDomain boardTableDomain);
}
