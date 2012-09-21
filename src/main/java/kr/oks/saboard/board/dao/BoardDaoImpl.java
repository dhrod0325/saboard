package kr.oks.saboard.board.dao;

import java.util.List;

import kr.oks.saboard.board.domain.BoardDomain;
import kr.oks.saboard.board.domain.BoardFileDomain;
import kr.oks.saboard.board.domain.BoardReplyDomain;
import kr.oks.saboard.board.domain.BoardTableDomain;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

@SuppressWarnings("unchecked")
public class BoardDaoImpl extends SqlMapClientDaoSupport implements BoardDao{
	public int insertBoard(BoardDomain boardDomain) {
		return (Integer) getSqlMapClientTemplate().insert("board.insertBoard",boardDomain);
	}
	
	public List<BoardDomain> getAllBoardList(BoardDomain boardDomain) {
		return getSqlMapClientTemplate().queryForList("board.getAllBoardList",boardDomain);
	}

	public BoardDomain getBoardDetailById(int id) {
		return (BoardDomain) getSqlMapClientTemplate().queryForObject("board.getBoardDetailById",id);
	}

	public void deleteBoardById(int id) {
		getSqlMapClientTemplate().delete("board.deleteBoardById",id);
	}

	public void updateBoardByboardDomain(BoardDomain boardDomain){
		getSqlMapClientTemplate().update("board.updateBoardByboardDomain",boardDomain);
	}

	public int getTotBoardCount(String board_id) {
		return (Integer) getSqlMapClientTemplate().queryForObject("board.getTotBoardCount",board_id);
	}

	public void insertBoardFile(BoardFileDomain boardFileDomain) {
		getSqlMapClientTemplate().insert("board.insertBoardFile", boardFileDomain);
	}

	public List<BoardFileDomain> getBoardFileListById(int id) throws Exception{
		return  (List<BoardFileDomain>) getSqlMapClientTemplate().queryForList("board.getBoardFileListById",id);
	}

	public void insertBoardReply(BoardReplyDomain boardReplyDomain) {
		getSqlMapClientTemplate().insert("board.insertBoardReply",boardReplyDomain);
	}

	public List<BoardReplyDomain> getAllBoardReplyListById(BoardReplyDomain boardReplyDomain) {
		return getSqlMapClientTemplate().queryForList("board.getAllBoardReplyListById",boardReplyDomain);
	}

	public List<BoardTableDomain> getAllBoardTableList() {
		return getSqlMapClientTemplate().queryForList("board.getAllBoardTableList");
	}

	public void insertBoardTable(BoardTableDomain boardTableDomain) {
		getSqlMapClientTemplate().insert("board.insertBoardTable",boardTableDomain);
	}
	
	public String getBoardTheme(String board_id){
		return (String) getSqlMapClientTemplate().queryForObject("board.getTheme",board_id);
	}

	public void deleteBoardTable(int id) {
		getSqlMapClientTemplate().delete("board.deleteBoardTable",id); 
	}

	public void modifyBoardTable(BoardTableDomain boardTableDomain) {
		getSqlMapClientTemplate().update("board.modifyBoardTable",boardTableDomain);
	}

	public String getBoardIdById(BoardTableDomain boardTableDomain) {
		return (String) getSqlMapClientTemplate().queryForObject("board.getBoardIdById",boardTableDomain);
	}

	public int getTotBoardReplyCount(int no) {
		return (Integer) getSqlMapClientTemplate().queryForObject("board.getTotBoardReplyCount",no);
	}
}
