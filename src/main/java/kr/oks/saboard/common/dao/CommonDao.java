package kr.oks.saboard.common.dao;

import java.util.HashMap;
import java.util.List;

import kr.oks.saboard.common.domain.MemberDomain;

public interface CommonDao {
	public List<HashMap<String, String>> test();

	public void join(MemberDomain memberDomain);
	
	public MemberDomain getLoginMemeberDomain(MemberDomain memberDomain);
}
