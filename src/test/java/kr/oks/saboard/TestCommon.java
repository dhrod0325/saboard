package kr.oks.saboard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class TestCommon {
	@Test
	public void test(){
		System.out.println("test");
	}
//	
//	@Autowired
//	private CommonService commonService;
//	
//	@Ignore
//	@Test
//	public void test() throws Exception{
//		assertNotNull(commonService.test());
//		
//		MemberDomain memberDomain = new MemberDomain();
//		memberDomain.setId("dhrod0325");
//		memberDomain.setName("test");
//		memberDomain.setPw("aaa");
//		
//		commonService.join(memberDomain);
//	}
	
	//회원 가입시 체크 해야 하는것
	//중복 아이디
	//페스워드 길이
	//아이디 널 또는 공백
	
	
}
