package kr.oks.saboard;

import kr.oks.saboard.core.mail.MailService;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestMail extends TestAbstract {

	@Autowired
	private MailService mailService;

	@Ignore
	@Test
	public void test() {
	}
}
