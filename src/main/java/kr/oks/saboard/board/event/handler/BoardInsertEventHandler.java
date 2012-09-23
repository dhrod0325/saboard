package kr.oks.saboard.board.event.handler;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;

import kr.oks.saboard.board.domain.BoardDomain;
import kr.oks.saboard.board.event.BoardInsertEvent;
import kr.oks.saboard.core.event.handler.ApplicationEventHandler;

public class BoardInsertEventHandler implements ApplicationEventHandler{
	private Logger logger = Logger.getLogger(getClass());
	
	@Override
	public void handle(ApplicationEvent event) throws Exception {
		BoardDomain boardDomain = ((BoardInsertEvent)event).getBoardDomain();
		
		logger.info("------------------------");
		logger.info("글쓰기 이벤트가 발생했습니다.");
		logger.info("여기서 이벤트가 발생했을때 할 행동들을 넣어주면 되겠습니다.");
		logger.info(boardDomain.toString());
		logger.info("------------------------");
	}
}
