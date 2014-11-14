package kr.co.sunnyvale.guestbook.dao;


import java.util.List;

import kr.co.sunnyvale.guestbook.dto.GuestbookDTO;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/test-dbpool-context.xml","classpath:/spring/test-root-context.xml",
	"file:src/main/webapp/WEB-INF/spring-security-context.xml"})
@TransactionConfiguration(defaultRollback=true)
public class GuestbookDAOTest {
	@Test
	public void test() throws Exception{
		
	}
	
	@Autowired
	private GuestbookDAO guestbookDAO;
	
	@Test
	public void selectCount() throws Exception{
		int count = guestbookDAO.selectCount();
		//Assert.assertEquals(count, 4);
	}
	

	
	@Test
	@Transactional
	public void addGuestbook() throws Exception{
		GuestbookDTO guest = new GuestbookDTO();
		guest.setContent("hello content");
		guest.setName("hello");
		int count = guestbookDAO.insertGuestbook(guest);
		System.out.println("seq :" + guest.getSeq());
		System.out.println("count :" + count);		
		Assert.assertEquals(1, count);
	}
	
}
