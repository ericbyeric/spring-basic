package ericbyeric.springbasic.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAOP {

    @Around("execution(* ericbyeric.springbasic..*(..))")
    public Object execute(ProceedingJoinPoint jointPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START = " + jointPoint.toString());
        try {
            Object object = jointPoint.proceed(); // 다음 메소드로 진행이 된다
            return object;
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END = " + jointPoint.toString() + " " + timeMs + "ms");
        }

    }
}
