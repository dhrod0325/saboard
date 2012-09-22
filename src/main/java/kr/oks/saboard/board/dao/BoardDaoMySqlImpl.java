package kr.oks.saboard.board.dao;

import java.util.List;

import kr.oks.saboard.board.domain.BoardDomain;
import kr.oks.saboard.board.domain.BoardFileDomain;
import kr.oks.saboard.board.domain.BoardReplyDomain;
import kr.oks.saboard.board.domain.BoardTableDomain;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

@SuppressWarnings("unchecked")
public class BoardDaoMySqlImpl extends SqlMapClientDaoSupport implements BoardDao{
	@Override
	public int insertBoard(BoardDomain boardDomain) {
		return (Integer) getSqlMapClientTemplate().insert("board.mysql.insertBoard",boardDomain);
	}
	@Override
	public List<BoardDomain> getAllBoardList(BoardDomain boardDomain) {
		return getSqlMapClientTemplate().queryForList("board.mysql.getAllBoardList",boardDomain);
	}

	@Override
	public BoardDomain getBoardDetailById(int id) {
		return (BoardDomain) getSqlMapClientTemplate().queryForObject("board.mysql.getBoardDetailById",id);
	}

	@Override
	public void deleteBoardById(int id) {
		getSqlMapClientTemplate().delete("board.mysql.deleteBoardById",id);
	}

	@Override
	public void updateBoardByboardDomain(BoardDomain boardDomain){
		getSqlMapClientTemplate().update("board.mysql.updateBoardByboardDomain",boardDomain);
	}
	
	@Override
	public int getTotBoardCount(String board_id) {
		return (Integer) getSqlMapClientTemplate().queryForObject("board.mysql.getTotBoardCount",board_id);
	}

	@Override
	public void insertBoardFile(BoardFileDomain boardFileDomain) {
		getSqlMapClientTemplate().insert("board.mysql.insertBoardFile", boardFileDomain);
	}

	@Override
	public List<BoardFileDomain> getBoardFileListById(int id) throws Exception{
		return  (List<BoardFileDomain>) getSqlMapClientTemplate().queryForList("board.mysql.getBoardFileListById",id);
	}

	@Override
	public int insertBoardReply(BoardReplyDomain boardReplyDomain) {
		return (Integer) getSqlMapClientTemplate().insert("board.mysql.insertBoardReply",boardReplyDomain);
	}

	@Override
	public List<BoardReplyDomain> getAllBoardReplyListById(BoardReplyDomain boardReplyDomain) {
		return getSqlMapClientTemplate().queryForList("board.mysql.getAllBoardReplyListById",boardReplyDomain);
	}

	@Override
	public List<BoardTableDomain> getAllBoardTableList() {
		return getSqlMapClientTemplate().queryForList("board.mysql.getAllBoardTableList");
	}

	@Override
	public void insertBoardTable(BoardTableDomain boardTableDomain) {
		getSqlMapClientTemplate().insert("board.mysql.insertBoardTable",boardTableDomain);
	}
	
	@Override
	public String getBoardTheme(String board_id){
		return (String) getSqlMapClientTemplate().queryForObject("board.mysql.getBoardTheme",board_id);
	}

	@Override
	public void deleteBoardTable(int id) {
		getSqlMapClientTemplate().delete("board.mysql.deleteBoardTable",id); 
	}

	@Override
	public void modifyBoardTable(BoardTableDomain boardTableDomain) {
		getSqlMapClientTemplate().update("board.mysql.modifyBoardTable",boardTableDomain);
	}

	@Override
	public String getBoardIdById(BoardTableDomain boardTableDomain) {
		return (String) getSqlMapClientTemplate().queryForObject("board.mysql.getBoardIdById",boardTableDomain);
	}

	@Override
	public int getTotBoardReplyCount(int no) {
		return (Integer) getSqlMapClientTemplate().queryForObject("board.mysql.getTotBoardReplyCount",no);
	}

	@Override
	public void deleteBoardReplyById(int id) {
		getSqlMapClientTemplate().delete("board.mysql.deleteBoardReplyById",id);
	}
}
