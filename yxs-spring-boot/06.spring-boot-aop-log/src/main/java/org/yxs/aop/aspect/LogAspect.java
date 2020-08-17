package org.yxs.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.yxs.aop.annotation.Log;
import org.yxs.aop.entity.SysLog;
import org.yxs.aop.event.SysLogEvent;
import org.yxs.aop.service.SysLogService;
import org.yxs.aop.util.HttpContextUtils;
import org.yxs.aop.util.IPUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Description: 切面注解得到请求数据 -> 发布监听事件 -> 异步监听日志入库
 * @Author: yang-xiansen
 * @Date: 2020/08/17 15:01
 */
@Aspect
@Component
public class LogAspect {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 事件发布是由ApplicationContext对象管控的，我们发布事件前需要注入ApplicationContext对象调用publishEvent方法完成事件发布
     **/
    @Autowired
    private ApplicationContext applicationContext;

    @Pointcut("@annotation(org.yxs.aop.annotation.Log)")
    public void pointCut() {
    }


    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        long beginTime = System.currentTimeMillis();

        try {
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        long time = System.currentTimeMillis() - beginTime;
        saveLog(point, time);
        return result;

    }


    private void saveLog(ProceedingJoinPoint point, long time) {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        Log logAnnotation = method.getAnnotation(Log.class);
        SysLog sysLog = new SysLog();
        if (logAnnotation != null) {

            //获得注解上的描述
            sysLog.setOperation(logAnnotation.value());
        }

        //获得请求的方法名
        String className = point.getTarget().getClass().getName();
        String methodName = methodSignature.getName();
        sysLog.setMethod(className + "," + methodName + "()");

        //请求的方法参数值
        Object[] args = point.getArgs();
        //请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);

        if (args != null && paramNames != null) {
            String params = "";
            for (int i = 0; i < args.length; i++) {
                params += "  " + paramNames[i] + ": " + args[i];
            }
            sysLog.setParams(params);
        }

        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        // 设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        // 模拟一个用户名
        sysLog.setUsername("yxs");
        sysLog.setTime((int) time);
        sysLog.setCreateTime(new Date());
        // 保存系统日志
//        sysLogService.add(sysLog);

        //发布事件监听
        applicationContext.publishEvent(new SysLogEvent(sysLog));


    }


}
