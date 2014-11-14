package kr.co.sunnyvale.guestbook.service;

import java.util.List;

import kr.co.sunnyvale.guestbook.dao.GuestbookDAO;
import kr.co.sunnyvale.guestbook.dto.GuestbookDTO;
import kr.co.sunnyvale.guestbook.dto.ImageDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GuestbookServiceImpl implements GuestbookService {
	
	@Autowired
	private GuestbookDAO guestbookDAO;

	@Override
	public List<GuestbookDTO> getList() {
		return guestbookDAO.selectList();
	}

	@Override
	public int getCount() {
		return guestbookDAO.selectCount();
	}

	@Override
	@Transactional
	public boolean addGuestbook(GuestbookDTO guestbook, List<ImageDTO> imageList) {
		int seq = guestbookDAO.insertGuestbook(guestbook);
		for (ImageDTO imageDTO : imageList) {
			imageDTO.setGuestbookSeq(guestbook.getSeq());
			guestbookDAO.insertImageDTO(imageDTO);
		}
		
		if(seq > 0)
			return true;
		else
			return false;
	}

	@Override
	public ImageDTO getImage(int seq) {
		return guestbookDAO.selectImage(seq);
	}

	
}