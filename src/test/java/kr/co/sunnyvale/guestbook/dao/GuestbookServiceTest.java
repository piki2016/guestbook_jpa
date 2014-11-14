package kr.co.sunnyvale.guestbook.dao;

import kr.co.sunnyvale.guestbook.dto.GuestbookDTO;
import kr.co.sunnyvale.guestbook.service.GuestbookService;

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
public class GuestbookServiceTest {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@Test
	public void selectList() throws Exception{
		
	}
	
}
