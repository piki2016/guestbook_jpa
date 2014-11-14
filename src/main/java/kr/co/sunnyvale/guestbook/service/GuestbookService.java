package kr.co.sunnyvale.guestbook.service;

import java.util.List;

import kr.co.sunnyvale.guestbook.dto.GuestbookDTO;
import kr.co.sunnyvale.guestbook.dto.ImageDTO;

public interface GuestbookService {
	public List<GuestbookDTO> getList();
	public int getCount();
	public boolean addGuestbook(GuestbookDTO guestbook, List<ImageDTO> imageList);
	public ImageDTO getImage(int seq);
}