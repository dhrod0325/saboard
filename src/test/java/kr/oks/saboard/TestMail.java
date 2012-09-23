package kr.oks.saboard;

import javax.annotation.Resource;

import kr.oks.saboard.board.domain.BoardDomain;
import kr.oks.saboard.board.service.BoardService;
import kr.oks.saboard.core.constants.ConstantsDB;
import kr.oks.saboard.core.mail.MailService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestMail extends TestAbstract {

	@Resource(name=ConstantsDB.MYSQL_BOARD_SERVICE_BEAN_NAME)
	private BoardService boardService;

	@Test
	public void test() throws Exception{
		boardService.insertBoard(new BoardDomain());
	}
}
