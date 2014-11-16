package kr.co.sunnyvale.guestbook.dao;

import java.sql.Date;

import kr.co.sunnyvale.guestbook.domain.QUser;
import kr.co.sunnyvale.guestbook.domain.User;
import kr.co.sunnyvale.guestbook.repository.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/dbpool-context.xml","classpath:/spring/root-context.xml"})
@TransactionConfiguration(defaultRollback=true)
public class GeustbookDAOTest {
	
	@Autowired
	UserRepository userRepository;
	
	@Test
	@Transactional
	public void saveUser() throws Exception{
		User user = new User("urstory");
		user.setAdmin(1);
		user.setEmail("urstory@gmail.com");
		user.setName("김성박");
		user.setPasswd("1234");
		user.setRegdate(new Date(System.currentTimeMillis()));
		userRepository.save(user);
	}
	
	@Test
	public void findByUserId() throws Exception{
		User user = userRepository.findByUserId("urstory");
		System.out.println(user);
	}	
	
	@Test
	public void QueryDSLFindUserId() throws Exception{
		QUser u = QUser.user;
		User user = userRepository.findOne(u.userId.eq("urstory"));
		System.out.println(user);
	}

}
