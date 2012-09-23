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
import org.springframework.mock.web.MockHttpServletRequest;

public class TestBoardMysql extends TestAbstract {

	@Resource(name = ConstantsDB.MYSQL_BOARD_SERVICE_BEAN_NAME)
	private BoardService boardService;

	private Logger logger = Logger.getLogger(getClass());

	public BoardDomain getBoardDomain(String s) {
		BoardDomain boardDomain = new BoardDomain();
		boardDomain.setUser_id("test"); // 유저아이디는 입력할수 있는 범위가 스크립트를 입력하는 범위보다
										// 작으므로 테스트에서 제외한다.
		boardDomain.setBoard_id("test");
		boardDomain.setTitle(s);
		boardDomain.setContent(s);
		boardDomain.setTheme(s);

		return boardDomain;
	}

	public void xssTest(String xss) throws Exception {
		BoardDomain boardDomain = getBoardDomain(xss);

		int id = boardService.insertBoard(boardDomain);

		boardDomain = boardService.getBoardDetailById(id);

		logger.info(boardDomain.toString());

		boardService.deleteBoardById(id);
	}

	@Test
	public void testBoardInsertAndDelete() throws Exception {
		BoardDomain boardDomain = getBoardDomain("default");
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
		boardReplyDomain.setContent("test");
		boardReplyDomain.setPassword("test");
		boardReplyDomain.setTitle("test");
		boardReplyDomain.setUser_id("test");

		boardReplyDomain.setNo(boardId);// no는 board의 외래키이다.

		int boardReplyId = boardService.insertBoardReply(boardReplyDomain);

		boardService.deleteBoardById(boardId);
		boardService.deleteBoardReplyById(boardReplyId);
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