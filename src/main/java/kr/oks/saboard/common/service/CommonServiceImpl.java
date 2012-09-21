package kr.oks.saboard.common.service;

import java.util.HashMap;
import java.util.List;

import kr.oks.saboard.common.dao.CommonDao;
import kr.oks.saboard.common.domain.MemberDomain;

import org.springframework.beans.factory.annotation.Autowired;

public class CommonServiceImpl implements CommonService{
	
	@Autowired
	private CommonDao commonDao;
	
	public List<HashMap<String, String>> test(){
		return commonDao.test();
	}

	public void join(MemberDomain memberDomain) {
		//회원 가입시 체크 해야 하는것
		//중복 아이디
		//페스워드 길이
		//아이디 널 또는 공백
		
		commonDao.join(memberDomain);
	}
	
	
}
