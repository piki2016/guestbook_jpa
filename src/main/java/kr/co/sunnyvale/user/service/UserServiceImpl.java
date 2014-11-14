package kr.co.sunnyvale.user.service;

import kr.co.sunnyvale.user.dao.UserDAO;
import kr.co.sunnyvale.user.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public boolean addUser(UserDTO user) {
		int count = userDAO.insertUser(user);
		if(count > 0 )
			return true;
		else
			return false;
	}

}
