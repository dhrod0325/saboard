package kr.oks.saboard.common.dao;

import kr.oks.saboard.common.domain.MemberDomain;

public interface CommonDao {
	public void join(MemberDomain memberDomain);
	
	public MemberDomain getLoginMemeberDomain(MemberDomain memberDomain);
}
