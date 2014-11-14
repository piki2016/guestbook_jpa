package kr.co.sunnyvale.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.sunnyvale.user.dto.UserDTO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	SqlSession sqlSession;

	private static final String NS = "kr.co.sunnyvale.mybatis.User.";

	
	@Override
	public int insertUser(UserDTO user) {
		
		int count = sqlSession.insert(NS + "insertUser", user);
		return count;
	}

}
