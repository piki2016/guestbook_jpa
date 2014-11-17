package kr.co.sunnyvale.security;

import java.util.ArrayList;
import java.util.Collection;

import kr.co.sunnyvale.guestbook.domain.User;
import kr.co.sunnyvale.guestbook.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// spring security파일에서
// <security:authentication-provider user-service-ref="memberService" >
// 의 memberService객체이다.
// spring security는 사용자가 login을 요청할 경우 사용자 정보를 필요로 하는데 이때
// UserDetailsService인터페이스를 구현한 객체를 필요로 한다.
@Service("memberService")
public class SecurityMemberService implements UserDetailsService  {
	
	@Autowired
	private UserRepository userRepository;

	// username은 로그인 id를 의미한다. 사람이름이 아니다. 
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		//로그인 아이디로 패스워드를 가지고 오다.
		User securityUser = userRepository.findByUserId(username);
		System.out.println("securityUser :" + securityUser);
		if(securityUser == null)
			return null;
		
		//"ROLE_USER" 란 이름으로 권한을 설정한다. 
		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		if(securityUser.getAdmin() == 1){
			roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
	 
		//로그인 정보를 리컨한다
		SecurityLoginInfoDTO user = new SecurityLoginInfoDTO(username, securityUser.getPasswd(), roles);
		
		user.setId(securityUser.getId());
		user.setUserId(securityUser.getUserId());
		user.setName(securityUser.getName());
		user.setCreatedDate(securityUser.getCreatedDate());
		user.setAdmin(securityUser.getAdmin());
		
		System.out.println(user);
		return user;
	}
	
}


