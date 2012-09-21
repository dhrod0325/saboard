package kr.oks.saboard.common.service;

import java.util.HashMap;
import java.util.List;

import kr.oks.saboard.common.dao.CommonDao;
import kr.oks.saboard.common.domain.MemberDomain;

public class CommonServiceImpl implements CommonService{
	
	private CommonDao commonDao;
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public List<HashMap<String, String>> test(){
		return commonDao.test();
	}

	public void join(MemberDomain memberDomain) {
		commonDao.join(memberDomain);
	}
	
	public MemberDomain getLoginMemeberDomain(MemberDomain memberDomain){
		return commonDao.getLoginMemeberDomain(memberDomain);
	}
	
}
