package tn.spring.esprit.config;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Component
@Aspect
@Slf4j

public class LoggingAspect {

	@Before("execution(public * tn.esprit.spring.service.*.* (..))")
	public void logMethodEntry(JoinPoint joinPoint) {
		log.info("Debut Execution : " + joinPoint.getSignature().getName() + " : ");
	}

	
}
