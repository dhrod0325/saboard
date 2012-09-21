package kr.oks.saboard.board.dao;

import java.util.List;

import kr.oks.saboard.board.domain.BoardDomain;
import kr.oks.saboard.board.domain.BoardFileDomain;
import kr.oks.saboard.board.domain.BoardReplyDomain;
import kr.oks.saboard.board.domain.BoardTableDomain;

public interface BoardDao {
	public int insertBoard(BoardDomain boardDomain);
	
	public List<BoardDomain> getAllBoardList(BoardDomain boardDomain);
	
	public BoardDomain getBoardDetailById(int id);
	
	public void deleteBoardById(int id);
	
	public void updateBoardByboardDomain(BoardDomain boardDomain);
	
	public int getTotBoardCount();
	
	public void insertBoardFile(BoardFileDomain boardFileDomain);
	
	public List<BoardFileDomain> getBoardFileListById(int id) throws Exception;
	
	public void insertBoardReply(BoardReplyDomain boardReplyDomain);
	
	public List<BoardReplyDomain> getAllBoardReplyListById(int id);
	
	public List<BoardTableDomain> getAllBoardTableList();
}
