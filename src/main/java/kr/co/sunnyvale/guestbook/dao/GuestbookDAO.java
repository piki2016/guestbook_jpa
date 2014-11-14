package kr.co.sunnyvale.guestbook.dao;

import java.util.List;

import kr.co.sunnyvale.guestbook.dto.GuestbookDTO;
import kr.co.sunnyvale.guestbook.dto.ImageDTO;

public interface GuestbookDAO {
	public List<GuestbookDTO> selectList();
	public int selectCount();
	public int insertGuestbook(GuestbookDTO guestbook);
	public int insertImageDTO(ImageDTO imageDTO);
	public List<ImageDTO> selectImages(int guestbookSeq);
	public ImageDTO selectImage(int imageSeq);
}