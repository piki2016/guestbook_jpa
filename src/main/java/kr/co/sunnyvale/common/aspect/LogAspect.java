package kr.co.sunnyvale.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	static ThreadLocal<String> idThreadLocal = new ThreadLocal<>();

	// package에 service라고 들어가는 모든 메소드.
	@Around("execution(* kr.co..service..*(..))")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		String id = idThreadLocal.get();
		if(id == null){
			idThreadLocal.set(System.currentTimeMillis() + "_" + Thread.currentThread().getId());
			id = idThreadLocal.get();
		}
		//System.out.println("method start : " + id);
		Object retunValue = pjp.proceed();
		//System.out.println("method end : " + id);

		return retunValue;
	}

	// @Before("execution(* getProduct(String))")
	// public void before(JoinPoint jp) {
	// // 메소드 시작 시에 Weaving하는 Advice
	// System.out.println("Hello Before! *** 메소드가 호출되기 전에 나온다!");
	// Signature sig = jp.getSignature();
	// System.out.println("----->메소드 이름을 취득한다:" + sig.getName());
	// Object[] o = jp.getArgs();
	// System.out.println("-----> 가인수 값을 취득한다:" + o[0]);
	// }
	//
	// @After("execution(* getProduct(String))")
	// public void after() {
	// // 메소드 종료 후에 Weaving하는 Advice
	// System.out.println("Hello After! *** 메소드가 호출된 후에 나온다!");
	// }
	//
	// @AfterReturning(value = "execution(* getProduct(String))", returning =
	// "product")
	// public void afterReturning(JoinPoint jp, Product product) {
	// // 메소드 호출이 예외 송출 없이 종료했을 때 호출되는 Advice
	// System.out.println("Hello AfterReturning! *** 메소드를 호출한 후에 나온다! ");
	// // System.out.println("-----> return value = " + ret);
	// Signature sig = jp.getSignature();
	// System.out.println("-----> 메소드 이름을 취득한다:" + sig.getName());
	// Object[] o = jp.getArgs();
	// System.out.println("----->가인수 값을 취득한다:" + o[0]);
	// }
	//
	// @Around("execution(* getProduct(String))")
	// public Product around(ProceedingJoinPoint pjp) throws Throwable {
	// // 메소드 호출 전후에 Weaving하는 Advice
	// System.out.println("Hello Around! before *** 메소드를 호출하기 전에 나온다!");
	//
	// // Signature sig = pjp.getSignature();
	// // System.out.println("-----> aop:around 메소드 이름을 취득한다:" + sig.getName());
	// Product p = (Product) pjp.proceed();
	// // msg = msg + ": 결과에 멋대로 추가해버린 hoge!";
	// System.out.println("Hello Around! after *** 메소드를 호출한 후에 나온다!");
	// return p;
	// }
	//
	//
	// @AfterThrowing(value = "execution(* getProduct(String))", throwing =
	// "ex")
	// public void afterThrowing(Throwable ex) {
	// // 메소드 호출이 예외를 내보냈을 때 호출되는 Advice
	// System.out.println("Hello Throwing! *** 예외가 생기면 나온다");
	// System.out.println("exception value = " + ex.toString());
	// }
}
