package kr.oks.saboard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class TestBoard {

	@Test
	public void test() {
		
	}

	// @Autowired
	// private BoardService boardService;
	//
	// @Ignore
	// @Test
	// public void insertBoard() {
	// BoardDomain bd = new BoardDomain();
	// bd.setContent("test");
	// bd.setTitle("zzz");
	// bd.setUser_id("dhrod0325");
	//
	// System.out.println(boardService.insertBoard(bd));
	// }
	//
	// @Ignore
	// @Test
	// public void insertBoardReply() {
	// BoardReplyDomain boardReplyDomain = new BoardReplyDomain();
	//
	// boardReplyDomain.setNo(89);
	// boardReplyDomain.setContent("test");
	// boardReplyDomain.setTitle("zzz");
	// boardReplyDomain.setUser_id("dhrod0325");
	// boardReplyDomain.setPassword("1234");
	// boardReplyDomain.setEmail("dhrod0325.naver.com");
	//
	// boardService.insertBoardReply(boardReplyDomain);
	// }
	//
	// // @Test
	// // public void selectBoardReplyList(){
	// // List<BoardReplyDomain> boardReplyListDomain =
	// boardService.getAllBoardReplyListById(89);
	// //
	// // for(BoardReplyDomain br : boardReplyListDomain){
	// // System.out.println(br.getTitle());
	// // }
	// // }
	//
	// @Ignore
	// @Test
	// public void updateBoardByboardDomain() {
	// BoardDomain bd = new BoardDomain();
	// bd.setTitle("asdf");
	// bd.setContent("아 이건 레알이다!!");
	// bd.setId(5);
	// bd.setUser_id("dhrod0325");
	//
	// boardService.updateBoardByboardDomain(bd);
	// }
	//
	// @Ignore
	// @Test
	// public void deleteBoardById() {
	// boardService.deleteBoardById(1);
	// }
	//
	// @Ignore
	// @Test
	// public void selectBoard() {
	// BoardDomain bd = new BoardDomain();
	// assertNotNull(boardService.getAllBoardList(bd));
	// }
	//
	// @Ignore
	// @Test
	// public void selectBoardById() {
	// BoardDomain boardDomain = boardService.getBoardDetailById(2);
	// assertNotNull(boardDomain);
	// assertEquals("dhrod0325", boardDomain.getUser_id());
	// }
	//
	// @Test
	// public void getTotBoardCount() {
	// //assertNotNull("board count = " + boardService.getTotBoardCount(),
	// boardService.getTotBoardCount());
	// }
}