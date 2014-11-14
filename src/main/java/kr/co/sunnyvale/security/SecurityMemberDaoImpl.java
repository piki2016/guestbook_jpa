package kr.co.sunnyvale.security;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SecurityMemberDaoImpl implements SecurityMemberDao {

	@Autowired
	private SqlSession sqlSession;

	private static final String NS = "kr.co.sunnyvale.mybatis.SecurityUser.";

	@Override
	public SecurityUserDTO getUser(String id) {
		SecurityUserDTO securityUser = sqlSession.selectOne(NS + "getUser", id);
		return securityUser;
	}

}