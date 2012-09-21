package kr.oks.saboard.board.domain.validator;

import kr.oks.saboard.board.domain.BoardDomain;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BoardDomainPasswordValidator implements Validator {
	private String password;
	
	public BoardDomainPasswordValidator(String password) {
		this.password = password;
	}

	public boolean supports(Class<?> arg0) {
		return false;
	}

	public void validate(Object target, Errors errors) {
		BoardDomain boardDomain = (BoardDomain) target;
		
		if(!boardDomain.getPassword().equals(password)){
			errors.rejectValue("password", "password.noteq");
		}
	}
}
