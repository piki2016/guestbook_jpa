package kr.co.sunnyvale.guestbook.repository;

import kr.co.sunnyvale.guestbook.domain.Guestbook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long>, QueryDslPredicateExecutor<Guestbook>, JpaSpecificationExecutor<Guestbook>{
}