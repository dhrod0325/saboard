package kr.oks.saboard;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import kr.oks.saboard.board.domain.BoardDomain;
import kr.oks.saboard.board.domain.BoardReplyDomain;
import kr.oks.saboard.board.service.BoardService;
import kr.oks.saboard.core.constants.ConstantsDB;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

public class TestBoardOracle extends TestAbstract {

	@Resource(name = ConstantsDB.ORACLE_BOARD_SERVICE_BEAN_NAME)
	private BoardService boardService;

	private Logger logger = Logger.getLogger(getClass());

	public BoardDomain getBoardDomain(String s) {
		BoardDomain boardDomain = new BoardDomain();
		boardDomain.setUser_id("test"); // 유저아이디는 입력할수 있는 범위가 스크립트를 입력하는 범위보다
		boardDomain.setBoard_id("test");
		boardDomain.setTitle(s);
		boardDomain.setContent(s);
		boardDomain.setTheme(s);
		boardDomain.setPassword(s);

		return boardDomain;
	}

	public void xssTest(String xss) throws Exception {
		BoardDomain boardDomain = getBoardDomain(xss);

		int id = boardService.insertBoard(boardDomain);

		boardDomain = boardService.getBoardDetailById(id);

		logger.info(boardDomain.toString());
		
		boardService.deleteBoardById(id);
	}

	@Ignore
	@Test
	public void testBoardInsertAndDelete() throws Exception {
		BoardDomain boardDomain = getBoardDomain("DEFAULT");
		int id = boardService.insertBoard(boardDomain);
		
		boardService.deleteBoardById(id);
	}
	
	@Ignore
	@Test
	public void testBoardReplyInsertAndDelete() throws Exception {
		BoardDomain boardDomain = getBoardDomain("default");
		int boardId = boardService.insertBoard(boardDomain);
		BoardReplyDomain boardReplyDomain = new BoardReplyDomain();
		
		boardReplyDomain.setBoard_id("test");
		boardReplyDomain.setContent("ass");
		boardReplyDomain.setPassword("a");
		boardReplyDomain.setTitle("a");
		boardReplyDomain.setUser_id("a");
		boardReplyDomain.setNo(boardId);// no는 board의 외래키이다.
		
		int boardReplyId = boardService.insertBoardReply(boardReplyDomain);

		boardService.deleteBoardById(boardId);
		boardService.deleteBoardReplyById(boardReplyId);
	}
	
	@Ignore
	@Test
	public void testTotCounts(){
		assertNotNull(boardService.getTotBoardCount("test"));
		assertNotNull(boardService.getTotBoardReplyCount(10));
	}
	
	@Ignore
	@Test
	public void testGetAllBoardList() throws Exception{
		BoardDomain boardDomain = getBoardDomain("default");
		
		boardDomain.setPageNo(1);
		System.out.println("startNo = "+boardDomain.getStartNo());
		System.out.println("pageSize ="+boardDomain.getPageSize());
		
		List<BoardDomain>getBoardList = boardService.getAllBoardList(boardDomain);
		
		for(BoardDomain bd : getBoardList){
			System.out.println(bd.getId());
		}
	}
	
	@Ignore
	@Test
	public void testGetBoardDetailById(){
		BoardDomain boardDomain = boardService.getBoardDetailById(69);
		
		System.out.println(boardDomain.toString());
	}


	@Ignore
	@Test
	public void testXss() throws Exception {
		xssTest("<script>alert(1);</script>");
		xssTest("<<script>alert(1);</script>");
		xssTest("--<script>></script>alert(/Hacking/);</script>");
		xssTest("<script>document.write('Hacking');</script>");
		xssTest("</script>alert(/Hacking/);</script>");
		xssTest("<img src='javascript:alert(Hacking');'>");
		xssTest("<script src='http://malicous js'</script>");
	}

	@Ignore
	@Test
	public void testSelectBoardReplyList() throws Exception {
		BoardDomain boardDomain = getBoardDomain("default");
		int boardId = boardService.insertBoard(boardDomain);

		BoardReplyDomain boardReplyDomain = new BoardReplyDomain();
		boardReplyDomain.setBoard_id("test");
		boardReplyDomain.setContent("test");
		boardReplyDomain.setPassword("test");
		boardReplyDomain.setTitle("test");
		boardReplyDomain.setUser_id("test");

		boardReplyDomain.setNo(boardId);

		int boardReplyId = boardService.insertBoardReply(boardReplyDomain);

		logger.info("boardReply =" + boardReplyId);

		List<BoardReplyDomain> boardReplyListDomain = boardService.getAllBoardReplyListById(boardReplyDomain);

		assertNotNull(boardReplyListDomain);

		for (BoardReplyDomain br : boardReplyListDomain) {
			assertNotNull(br.getContent());
		}

		boardService.deleteBoardById(boardId);
		boardService.deleteBoardReplyById(boardReplyId);
	}

	@Ignore
	@Test
	public void updateBoardByboardDomain() throws Exception {
		BoardDomain boardDomain = getBoardDomain("default");
		int boardId = boardService.insertBoard(boardDomain);

		boardDomain = boardService.getBoardDetailById(boardId);
		boardDomain.setContent("update");

		boardService.updateBoardByboardDomain(boardDomain);

		boardDomain = boardService.getBoardDetailById(boardId);

		assertEquals("update", boardDomain.getContent());

		boardService.deleteBoardById(boardId);
	}
}