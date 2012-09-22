package kr.oks.saboard.board.dao;

import java.util.List;

import kr.oks.saboard.board.domain.BoardDomain;
import kr.oks.saboard.board.domain.BoardFileDomain;
import kr.oks.saboard.board.domain.BoardReplyDomain;
import kr.oks.saboard.board.domain.BoardTableDomain;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

@SuppressWarnings("unchecked")
public class BoardDaoOracleImpl extends SqlMapClientDaoSupport implements BoardDao{

	@Override
	public int insertBoard(BoardDomain boardDomain) {
		return (Integer) getSqlMapClientTemplate().insert("board.oracle.insertBoard",boardDomain);
	}
	
	@Override
	public void deleteBoardById(int id) {
		getSqlMapClientTemplate().delete("board.oracle.deleteBoardById",id);
	}
	
	@Override
	public int insertBoardReply(BoardReplyDomain boardReplyDomain) {
		return (Integer) getSqlMapClientTemplate().insert("board.oracle.insertBoardReply",boardReplyDomain);
	}

	@Override
	public void deleteBoardReplyById(int id) {
		getSqlMapClientTemplate().delete("board.oracle.deleteBoardReplyById",id);
	}
	
	public int getTotBoardCount(String board_id) {
		return (Integer) getSqlMapClientTemplate().queryForObject("board.oracle.getTotBoardCount",board_id);
	}

	@Override
	public int getTotBoardReplyCount(int no) {
		return (Integer) getSqlMapClientTemplate().queryForObject("board.oracle.getTotBoardReplyCount",no);
	}
	
	@Override
	public List<BoardDomain> getAllBoardList(BoardDomain boardDomain) {
		return getSqlMapClientTemplate().queryForList("board.oracle.getAllBoardList",boardDomain);
	}

	public BoardDomain getBoardDetailById(int id) {
		return (BoardDomain) getSqlMapClientTemplate().queryForObject("board.oracle.getBoardDetailById",id);
	}

	@Override
	public void updateBoardByboardDomain(BoardDomain boardDomain){
		getSqlMapClientTemplate().update("board.oracle.updateBoardByboardDomain",boardDomain);
	}

	@Override
	public void insertBoardFile(BoardFileDomain boardFileDomain) {
		getSqlMapClientTemplate().insert("board.oracle.insertBoardFile", boardFileDomain);
	}

	@Override
	public List<BoardFileDomain> getBoardFileListById(int id) throws Exception{
		return  (List<BoardFileDomain>) getSqlMapClientTemplate().queryForList("board.oracle.getBoardFileListById",id);
	}

	@Override
	public List<BoardReplyDomain> getAllBoardReplyListById(BoardReplyDomain boardReplyDomain) {
		return getSqlMapClientTemplate().queryForList("board.oracle.getAllBoardReplyListById",boardReplyDomain);
	}

	@Override
	public List<BoardTableDomain> getAllBoardTableList() {
		return getSqlMapClientTemplate().queryForList("board.oracle.getAllBoardTableList");
	}

	@Override
	public void insertBoardTable(BoardTableDomain boardTableDomain) {
		getSqlMapClientTemplate().insert("board.oracle.insertBoardTable",boardTableDomain);
	}

	@Override
	public String getBoardTheme(String board_id){
		return (String) getSqlMapClientTemplate().queryForObject("board.oracle.getBoardTheme",board_id);
	}

	@Override
	public void deleteBoardTable(int id) {
		getSqlMapClientTemplate().delete("board.oracle.deleteBoardTable",id); 
	}

	@Override
	public void modifyBoardTable(BoardTableDomain boardTableDomain) {
		getSqlMapClientTemplate().update("board.oracle.modifyBoardTable",boardTableDomain);
	}

	@Override
	public String getBoardIdById(BoardTableDomain boardTableDomain) {
		return (String) getSqlMapClientTemplate().queryForObject("board.oracle.getBoardIdById",boardTableDomain);
	}
}
