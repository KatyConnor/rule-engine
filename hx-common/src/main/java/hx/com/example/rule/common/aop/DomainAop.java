package hx.com.example.rule.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author mingliang
 * @Date 2018-01-26 13:52
 */
@Aspect
@Component
public class DomainAop {

    private final static Logger LOGGER = LoggerFactory.getLogger(DomainAop.class);

    /**
     * 定义拦截规则：拦截com.hc.ala.scheduler.base.mapperdao.BaseMapper.findDynamicConditional 方法
     */
    @Pointcut("execution(* hx.com.example.rule.common.domain.RuleParamDomain.RuleParamDomain())")
    public void afterPointcut(){}

    @Around("afterPointcut()")
    public Object doAfter(ProceedingJoinPoint pjp){
        LOGGER.info("className = {} ",pjp.getThis().getClass().getSimpleName());
        Object object = null;
        try {
            object = pjp.proceed();
            LOGGER.info("------className = {} ",object.getClass().getSimpleName());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return object;
    }
}
