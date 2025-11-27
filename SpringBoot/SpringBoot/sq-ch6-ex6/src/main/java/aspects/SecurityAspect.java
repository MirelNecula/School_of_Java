package aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.logging.Logger;

@Aspect
public class SecurityAspect {

    private Logger logger =
             Logger.getLogger(SecurityAspect.class.getName());

    @Around(value = "@annotation(ToLog)")
    public Object secure(ProceedingJoinPoint joinPoint) throws Throwable {

        logger.info("Security aspect : Calling the intercepted method");

        Object returnedValue = joinPoint.proceed();

        logger.info("Security aspect : Returning the method executed" + returnedValue);

        return returnedValue;
    }
}
