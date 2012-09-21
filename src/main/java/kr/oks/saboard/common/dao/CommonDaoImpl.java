package kr.oks.saboard.common.dao;

import java.util.HashMap;
import java.util.List;

import kr.oks.saboard.common.domain.MemberDomain;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

@SuppressWarnings("unchecked")
public class CommonDaoImpl extends SqlMapClientDaoSupport implements CommonDao{
	public List<HashMap<String, String>> test() {
		return getSqlMapClientTemplate().queryForList("common.test");
	}

	public void join(MemberDomain memberDomain) {
		getSqlMapClientTemplate().insert("common.join",memberDomain);
	}

	public MemberDomain getLoginMemeberDomain(MemberDomain memberDomain) {
		return (MemberDomain) getSqlMapClientTemplate().queryForObject("common.loginCheck",memberDomain);
	}
}
