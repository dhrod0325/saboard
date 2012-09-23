package kr.oks.saboard.common.dao;

import kr.oks.saboard.common.domain.MemberDomain;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class CommonDaoOracleImpl extends SqlMapClientDaoSupport implements CommonDao{
	public void insertMemeber(MemberDomain memberDomain) {
		getSqlMapClientTemplate().insert("common.oracle.join",memberDomain);
	}

	public MemberDomain getLoginMemeberDomain(MemberDomain memberDomain) {
		return (MemberDomain) getSqlMapClientTemplate().queryForObject("common.oracle.loginCheck",memberDomain);
	}
}
