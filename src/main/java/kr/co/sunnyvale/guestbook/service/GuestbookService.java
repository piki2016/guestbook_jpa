package kr.co.sunnyvale.guestbook.service;

import kr.co.sunnyvale.guestbook.domain.Guestbook;

import org.springframework.data.domain.Page;

public interface GuestbookService {
	Page<Guestbook> getGuestbookList(int page);
}