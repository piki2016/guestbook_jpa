package kr.co.sunnyvale.security;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver { 

	// Controller의 파라미터가 @AuthUser어노테이션이 붙어 있고
	// 파라미터 type이 동시에 SecurityLoginInfoDTO 일경우 true를 반환한다.
    @Override 
    public boolean supportsParameter(MethodParameter parameter) { 
        return parameter.getParameterAnnotation(AuthUser.class) != null  
                && parameter.getParameterType().equals(SecurityLoginInfoDTO.class); 
    } 

    @Override 
    public Object resolveArgument(MethodParameter parameter, 
            ModelAndViewContainer mavContainer, NativeWebRequest webRequest, 
            WebDataBinderFactory binderFactory) throws Exception { 

    	// 위의 메소드를 이용하여 파라미터가 @AuthUser어노테이션이 붙어있으면서 SecurityLoginInfoDTO인지 확인한다.
        if( this.supportsParameter(parameter)){ 
            Object principal = null; 
            // SpringSecurity에 이증된 사용자인지 확인한다.
            if( SecurityContextHolder.getContext().getAuthentication() != null ){
            	// 인증된 사용자 정보를 읽어온다.
            	// <security:authentication-provider user-service-ref="memberService" >
            	// 에서 지정된 memberService의 loadUserByUsername가 반환하는 객체를 의미한다.
            	// UserDetails를 상속받고 있는 SecurityLoginInfoDTO 를 의미한다.
                principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
            } 
             
            System.out.println("principal class type : " + principal.getClass().getName());
            // null이거나 type이 문자열일 경우 null을 반환한다.
            if (principal == null || principal.getClass() == String.class) { 
                return null; 
            } else { 
                return principal; 
            } 
        }else{ 
            return WebArgumentResolver.UNRESOLVED; 
        } 
    } 
} 
