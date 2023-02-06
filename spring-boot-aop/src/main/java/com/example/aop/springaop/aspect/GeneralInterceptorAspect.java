package com.example.aop.springaop.aspect;

import com.example.aop.springaop.model.Department;
import com.example.aop.springaop.model.Employee;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class GeneralInterceptorAspect {

    //@Pointcut("execution(* com.example.aop.springaop.controller.*.*(..))")
    //@Pointcut("within(com.example.aop.springaop..*)")
    //@Pointcut("this(com.example.aop.springaop.service.DepartmentService)")
    @Pointcut("@annotation(com.example.aop.springaop.annotation.CustomAnnotation)")
    public void loggingPointCut(){}

//  @Before("loggingPointCut()")
//    public void before( JoinPoint joinPoint ){
//         System.out.println("Before method invoked::"+joinPoint.getSignature());
//    }
  
  /*

    @AfterReturning(value = "execution(* com.example.aop.springaop.controller.*.*(..))",
            returning = "employee")
    public void after( JoinPoint joinPoint, Employee employee ){
         System.out.println("After method invoked::"+employee);
    }

    @AfterThrowing(value = "execution(* com.example.aop.springaop.controller.*.*(..))",
            throwing = "e")
    public void after( JoinPoint joinPoint, Exception e ){
         System.out.println("After method invoked::"+e.getMessage());
    }*/


    @Around("loggingPointCut()")
    public Object around( ProceedingJoinPoint joinPoint ) throws Throwable {
        System.out.println("Before method invoked::"+joinPoint.getArgs()[0]);
        Object object = joinPoint.proceed();

        if(object instanceof Employee){
            System.out.println("After method invoked..."+joinPoint.getArgs()[0]);
        }else if(object instanceof Department){
            System.out.println("After method invoked..."+joinPoint.getArgs()[0]);
        }else {
        	System.out.println("something went wrong");
        }

        return object;
    }

}
