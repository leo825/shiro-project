//package org.leo.shiro.proxy;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//
///**
// * Created by LX on 2017/2/10.
// */
//
//@Component("logAspect")
//@Aspect
//public class LogAspect {
//
//    /**
//     * execution(* org.zttc.itat.spring.dao.*.add*(..))
//     * 第一个*表示任意返回值
//     * 第二个*表示org.zttc.itat.spring.dao包中的所有类
//     * 第三个*表示以add方法
//     * (..)表示任意参数
//     */
//
//    @Pointcut("target(org.leo.shiro.service.UserService)")
//    public void userServicePointcut() {
//    }
//
//    @Pointcut("execution(* add*(..))||execution(* update*(..))|| execution(* delete*(..))")
//    public void userServicePutPointcut() {
//
//    }
//
//    @Pointcut("execution(* load(..))||execution(* loadByUsername(..))")
//    public void userServiceLoadPointcut() {
//
//    }
//
//    @Before(value = "userServicePointcut() && userServicePutPointcut()")
//    void logBefore(JoinPoint joinPoint) {
////        //方法执行的类
////        System.out.println(joinPoint.getTarget());
////        //具体的执行的方法名称
////        System.out.println(joinPoint.getSignature().getName());
//        System.out.println("日志加入之前：");
//    }
//
//    @After(value = "userServicePointcut() && userServicePutPointcut()")
//    void logAfter(JoinPoint joinPoint) {
////        //方法执行的类
////        System.out.println(joinPoint.getTarget());
////        //具体的执行的方法名称
////        System.out.println(joinPoint.getSignature().getName());
//        System.out.println("日志加入之后");
//    }
//
//
//    @Around(value = "userServicePointcut() && userServicePutPointcut()")
//    public void logAround(ProceedingJoinPoint pjp) throws Throwable {
//        System.out.println("程序执行中");
//        pjp.proceed();
//    }
//
//
//}
