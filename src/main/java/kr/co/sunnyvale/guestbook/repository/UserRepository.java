package kr.co.sunnyvale.guestbook.repository;

import kr.co.sunnyvale.guestbook.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;


public interface UserRepository  extends JpaRepository<User, String>, QueryDslPredicateExecutor<User>, JpaSpecificationExecutor<User>{
	
	// method name query 
	public User findByUserId(String userId);
	//public Page<User> findAll(Predicate predicate, Pageable pageable, OrderSpecifier<?>... orders);
}
