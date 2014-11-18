package kr.co.sunnyvale.guestbook.dao;

import kr.co.sunnyvale.guestbook.domain.Guestbook;
import kr.co.sunnyvale.guestbook.domain.QGuestbook;
import kr.co.sunnyvale.guestbook.domain.QUser;
import kr.co.sunnyvale.guestbook.domain.User;
import kr.co.sunnyvale.guestbook.repository.GuestbookRepository;
import kr.co.sunnyvale.guestbook.repository.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/dbpool-context.xml","classpath:/spring/root-context.xml"
	,"file:webapp/WEB-INF/spring-security-context.xml"})
@TransactionConfiguration(defaultRollback=false)
public class GeustbookDAOTest {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	GuestbookRepository guestbookRepository;
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Test
	@Transactional
	public void saveUser() throws Exception{
		User user = new User();
		user.setUserId("urstory");
		user.setAdmin(1);
		user.setEmail("urstory@gmail.com");
		user.setName("김성박");
		user.setPasswd(passwordEncoder.encode("1234"));
		//user.setRegdate(new Date(System.currentTimeMillis()));
		userRepository.save(user);
	}
	
	@Test
	public void sampleDataInsert() throws Exception{
		User user = userRepository.findByUserId("urstory");
		if(user == null){
			user = new User();
			user.setUserId("urstory");
			user.setAdmin(1);
			user.setEmail("urstory@gmail.com");
			user.setName("김성박");
			user.setPasswd(passwordEncoder.encode("1234"));
			//user.setRegdate(new Date(System.currentTimeMillis()));
			userRepository.save(user);			
		}
		user = userRepository.findByUserId("urstory");
		
		for(int i = 0; i < 100; i++){
			Guestbook guestbook = new Guestbook();
			guestbook.setContent("test" + i);
			guestbook.setUser(user);
			guestbookRepository.save(guestbook);
		}
		
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
	
	@Test
	public void selectPaging() throws Exception{
		Page<Guestbook> page = guestbookRepository.findAll(constructPageSpecification(1));
		System.out.println(page);
	}
	
	private Pageable constructPageSpecification(int pageIndex) {
		QGuestbook qg = QGuestbook.guestbook;
        Pageable pageSpecification = new PageRequest(pageIndex, 5,new Sort(Sort.Direction.DESC, "id"));
        return pageSpecification;
    }
}
