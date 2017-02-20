package com.asiainfo.demo.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Aspect
public class ServiceAspect {
	
	private static Log log = LogFactory.getLog(ServiceAspect.class);
	
	@Before(value="execution(* com.asiainfo..*ServiceImpl.*(..)) && @annotation(tl)", argNames="tl")
	public void before(JoinPoint point, Transactional tl) throws Throwable {
		Signature signature = point.getSignature();
		Object targetObj = point.getTarget();
		String className = targetObj.getClass().getName();
		String methodName = signature.getName();
		log.info(className + "-" + methodName + " transaction value is " +tl.value());
		
		TransactionSignManager.pushTransaction(tl.value());
	}
	
	
	@After(value="execution(* com.asiainfo..*ServiceImpl.*(..)) && @annotation(tl)", argNames="tl")
	public void after(JoinPoint point, Transactional tl) throws Throwable {
		Signature signature = point.getSignature();
		Object targetObj = point.getTarget();
		String className = targetObj.getClass().getName();
		String methodName = signature.getName();
		log.info(className + "-" + methodName + " transaction value is " +tl.value());
		TransactionSignManager.popTransaction();
	}
	
	
}
