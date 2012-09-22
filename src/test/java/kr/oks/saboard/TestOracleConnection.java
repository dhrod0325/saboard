package kr.oks.saboard;

import kr.oks.saboard.board.service.BoardService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestOracleConnection extends TestAbstract{

	@Autowired
	BoardService boardService;
	
	@Test
	public void test() throws Exception{
		boardService.getBoardFileListById(5);
		System.out.println(boardService); 
	}
}
