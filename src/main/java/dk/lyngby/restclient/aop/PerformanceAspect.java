package dk.lyngby.restclient.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {

    @Around("@annotation(LogExecutionTime)")
    public Object logTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // 1. Capture its return value
        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        // 2. Print out the class name, method name, and the time it took
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("⏱️ [PERF LOG] " + methodName + " executed in " + executionTime + "ms");
        System.out.println("======================================================");


        // 3. Return the result back to whoever originally called the method
        return result;
    }
}
