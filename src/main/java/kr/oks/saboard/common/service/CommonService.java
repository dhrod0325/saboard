package kr.oks.saboard.common.service;

import kr.oks.saboard.common.domain.MemberDomain;

public interface CommonService {
	public void insertMemeber(MemberDomain memberDomain);
	
	public MemberDomain getLoginMemeberDomain(MemberDomain memberDomain);
}
