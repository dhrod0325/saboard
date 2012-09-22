package kr.oks.saboard.common.service;

import kr.oks.saboard.common.dao.CommonDao;
import kr.oks.saboard.common.domain.MemberDomain;

public class CommonServiceImpl implements CommonService{
	
	private CommonDao commonDao;
	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public void join(MemberDomain memberDomain) {
		commonDao.join(memberDomain);
	}
	
	public MemberDomain getLoginMemeberDomain(MemberDomain memberDomain){
		return commonDao.getLoginMemeberDomain(memberDomain);
	}
	
}
