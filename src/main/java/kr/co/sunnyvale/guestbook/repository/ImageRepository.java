package kr.co.sunnyvale.guestbook.repository;

import kr.co.sunnyvale.guestbook.domain.Image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface ImageRepository extends JpaRepository<Image, Long>, QueryDslPredicateExecutor<Image>, JpaSpecificationExecutor<Image>{
}