package com.yash.student.aspects;

import static org.mockito.Mockito.when;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoggingAspectTest {
	
	@InjectMocks
	private LoggingAspect loggingAspect;
	
	@Mock
	private JoinPoint joinPoint;
	
	@Mock
	private Signature signature;

	@Test
	public void shouldlogBeforeMethodCall() {
		when(joinPoint.getSignature()).thenReturn(signature);
		when(signature.getDeclaringTypeName()).thenReturn("getStudentList");
		loggingAspect.logBefore(joinPoint);
	}
	
	@Test
	public void shouldlogAfterMethodCall() {
		when(joinPoint.getSignature()).thenReturn(signature);
		when(signature.getDeclaringTypeName()).thenReturn("getStudentList");
		loggingAspect.logAfter(joinPoint);
	}
	
	@Test
	public void shouldlogAfterThrowingMethodCall() {
		when(joinPoint.getSignature()).thenReturn(signature);
		when(signature.getDeclaringTypeName()).thenReturn("getStudentList");
		loggingAspect.logAfterThrowing(joinPoint);
	}
	
	@Test
	public void shouldlogAfterReturningMethodCall() {
		when(joinPoint.getSignature()).thenReturn(signature);
		when(signature.getDeclaringTypeName()).thenReturn("getStudentList");
		loggingAspect.logAfterReturning(joinPoint);
	}
	

}
