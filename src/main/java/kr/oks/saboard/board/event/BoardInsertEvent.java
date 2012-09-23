package kr.oks.saboard.board.event;

import kr.oks.saboard.board.domain.BoardDomain;

import org.springframework.context.ApplicationEvent;

public class BoardInsertEvent extends ApplicationEvent{

	private BoardDomain boardDomain;
	
	public BoardInsertEvent(Object source,BoardDomain boardDomain) {
		super(source);
		this.boardDomain = boardDomain;
	}

	private static final long serialVersionUID = 1L;

	public BoardDomain getBoardDomain() {
		return boardDomain;
	}
}
