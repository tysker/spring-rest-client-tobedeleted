package dk.lyngby.restclient.aop;

import dk.lyngby.restclient.model.Post;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class SignatureAspect {

    @Pointcut("execution(* dk.lyngby.restclient.service.PostService.*(..))")
    public void postServiceClasses(){}

    @After("postServiceClasses()")
    public void logAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[@After (Finally) Advice] Completed method: " + methodName + " (executed regardless of outcome)");
        System.out.println("======================================================");
    }

    @AfterReturning(pointcut = "postServiceClasses()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, List<Post> result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[@AfterReturning Advice] Method: " + methodName + " successfully returned: " + result.get(0).getTitle());
        System.out.println("======================================================");
    }

    @AfterThrowing(pointcut = "postServiceClasses())", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[@AfterThrowing Advice] Exception caught from method: " + methodName + " -> " + exception.getClass().getSimpleName() + ": " + exception.getMessage());
    }

    @Before("postServiceClasses()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("[@Before Advice] Entering method: " + methodName + " with args: " + Arrays.toString(args));
    }
}
