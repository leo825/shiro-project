package org.leo.shiro.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.leo.shiro.model.User;
import org.springframework.stereotype.Service;


@Service
@Aspect
public class UserServiceAspect extends BaseCacheService {

    public UserServiceAspect() {
        this.setCacheName("shiro-userCache");
    }

    private String idPrefix = "id-";
    private String usernamePrefix = "username-";

    @Pointcut("target(org.leo.shiro.service.UserService)")
    public void userServicePointcut() {
    }

    @Pointcut("execution(* add(..))||execution(* update(..))||execution(* login(..))")
    public void userPutPointcut() {
        System.out.println("=================路过==================");
    }

    @Pointcut("execution(* load(..))||execution(* loadByUsername(..))")
    public void userReadPointcut() {
    }

    @Pointcut(value = "(execution(* delete(*))) && args(arg)", argNames = "arg")
    public void userEvictPointcut(Object arg) {
        System.out.println("=========" + arg + "========");
    }

    @After(value = "userServicePointcut()&&userEvictPointcut(arg)", argNames = "arg")
    public void userEvictAdvice(Object arg) {
        super.evict(idPrefix + arg);
    }


    @AfterReturning(pointcut = "userServicePointcut()&&userPutPointcut()", returning = "rel")
    public void userPutAdvice(Object rel) {
        put((User) rel);
    }

    @Around(value = "userServicePointcut()&&userReadPointcut()")
    public Object userReadPointCut(ProceedingJoinPoint pjp) throws Throwable {
        String methodName = pjp.getSignature().getName();
        Object arg = pjp.getArgs().length > 0 ? pjp.getArgs()[0] : null;
        String key = null;
        boolean isId = false;
        if (methodName.equals("load")) {
            isId = true;
            key = idPrefix + arg;
        } else if (methodName.equals("loadByUsername")) {
            key = usernamePrefix + arg;
        }
        User user = null;
        if (isId) {
            user = (User) super.get(key);
        } else {
            String idKey = idPrefix + (Integer) super.get(key);
            user = (User) super.get(idKey);
        }
        System.out.println("====user=====" + user + "====user====");
        if (user != null) return user;
        return pjp.proceed();
    }

    private void put(User rel) {
        super.put(idPrefix + rel.getId(), rel);
        super.put(usernamePrefix + rel.getUsername(), rel.getId());
    }
}
