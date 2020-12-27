package nuc.zy.controller;

import nuc.zy.entity.SysLog;
import nuc.zy.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@SuppressWarnings("unchecked")
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request ;
    @Autowired
    private ISysLogService sysLogService ;


    private Date visitTime ;
    private Class clazz ;
    private Method method ;

    //前置通知 获取开始时间 执行类 执行哪个方法
    @Before("execution(* nuc.zy.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        clazz = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();

        Object[] args = jp.getArgs() ;
        if (args==null||args.length==0){
            method = clazz.getMethod(methodName) ;
        } else {
            // 有参数，就将args中所有元素遍历，获取对应的Class,装入到一个Class[]
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName,classArgs) ;
        }

    }

    // 主要获取日志中其它信息，时长、ip、url...
    @After("execution(* nuc.zy.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        // 获取类上的@RequestMapping对象
        if (clazz != SysLogController.class) {
            RequestMapping classAnnotation = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                // 获取方法上的@RequestMapping对象
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String url = ""; // 它的值应该是类上的@RequestMapping的value+方法上的
                    //@RequestMapping的value
                    url = classAnnotation.value()[0] + methodAnnotation.value()[0];
                    SysLog sysLog = new SysLog();
                    // 获取访问时长
                    Long executionTime = new Date().getTime() - visitTime.getTime();
                    // 将sysLog对象属性封装
                    sysLog.setExecutionTime(executionTime);
                    sysLog.setUrl(url);
                    // 获取ip
                    String ip = request.getRemoteAddr();
                    sysLog.setIp(ip);
                    // 可以通过securityContext获取，也可以从request.getSession中获取
                    SecurityContext context = SecurityContextHolder.getContext();
                    //request.getSession().getAttribute("SPRING_SECURITY_CONTEXT") ;
                    String username = ((User)
                            (context.getAuthentication().getPrincipal())).getUsername();
                    sysLog.setUsername(username);
                    sysLog.setMethod("[类名]" + clazz.getName() + "[方法名]" +
                            method.getName());
                    sysLog.setVisitTime(visitTime);
                    // 调用Service，调用dao将sysLog insert数据库
                    sysLogService.save(sysLog);
                }
            }
        }
    }
}
