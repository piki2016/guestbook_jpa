package kr.co.sunnyvale.guestbook.service;

import kr.co.sunnyvale.guestbook.domain.Guestbook;
import kr.co.sunnyvale.guestbook.domain.QGuestbook;
import kr.co.sunnyvale.guestbook.repository.GuestbookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GuestbookServiceImpl implements GuestbookService {
	protected static final int NUMBER_OF_PERSONS_PER_PAGE = 5;
	
	@Autowired GuestbookRepository guestbookRepository;

	@Override
	public Page<Guestbook> getGuestbookList(int pageNumber) {
		
		Page<Guestbook> page = guestbookRepository.findAll(constructPageSpecification(pageNumber));
		return page;
	}
	
	private Pageable constructPageSpecification(int pageIndex) {
		QGuestbook qg = QGuestbook.guestbook;
        Pageable pageSpecification = new PageRequest(pageIndex-1, NUMBER_OF_PERSONS_PER_PAGE,new Sort(Sort.Direction.DESC, "id"));
        return pageSpecification;
    }
	
}
