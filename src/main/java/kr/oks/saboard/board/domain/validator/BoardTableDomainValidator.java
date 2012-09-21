package kr.oks.saboard.board.domain.validator;

import kr.oks.saboard.board.domain.BoardTableDomain;
import kr.oks.saboard.core.util.RegExpUtil;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BoardTableDomainValidator implements Validator {
	private boolean board_id_check;
	
	private String board_id;
	
	public BoardTableDomainValidator(String board_id,boolean board_id_check) {
		super();
		this.board_id_check = board_id_check;
		this.board_id = board_id;
	}

	public boolean supports(Class<?> arg0) {
		return false;
	}

	public void validate(Object target, Errors errors) {
		BoardTableDomain boardTableDomain = (BoardTableDomain) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "board_id", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "board_nm", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "board_desc", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "theme", "required");
		
		if (RegExpUtil.isContainKo(boardTableDomain.getBoard_id())) {
			errors.rejectValue("board_id", "board_id.not_ko");
		}
		
		if(board_id_check){
			if(boardTableDomain.getBoard_id().equals(board_id)){
				errors.rejectValue("board_id", "board_id.identify");
			}
		}
	}
}
