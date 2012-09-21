package kr.oks.saboard.board.domain.validator;

import kr.oks.saboard.board.domain.BoardDomain;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BoardDomainValidator implements Validator {
	public boolean supports(Class<?> arg0) {
		return false;
	}

	public void validate(Object target, Errors errors) {
		BoardDomain boardDomain = (BoardDomain) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_id", "required");

		if (boardDomain.getUser_id().getBytes().length > 15) {
			errors.rejectValue("user_id", "user_id.overlength");
		}

		if (boardDomain.getTitle().length() > 50) {
			errors.rejectValue("title", "title.overlength");
		}
	}
}
