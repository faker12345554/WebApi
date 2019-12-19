package com.admin.config.log;

import com.admin.admin.dao.dw_log.LogDao;
import com.admin.admin.entity.dw_log.LogInformation;
import com.admin.config.CacheUtils;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @ClassName AdminLogAspect
 * @Author Wen.GD
 * @Date 2019/5/28 11:37
 **/
@Aspect //定义为一个切面类
@Component  //申明是Spring管理的Bean
@Order(1)   //标记切面类的处理优先级,i值越小,优先级别越高
public class LogAspectConfig {

    @Autowired
    private LogDao  logDao;
    private static final Logger logger = LoggerFactory.getLogger(LogAspectConfig.class);


    /*
        ThreadLocal是一个本地线程副本变量工具类。

        主要用于将私有线程和该线程存放的副本对象做一个映射,各个线程之间的变量互不干扰,在高并发场景下,可以实现无状态的调用
        特别适用于各个线程依赖不通的变量值完成操作的场景
    */
    private final ThreadLocal<String> methodDescribe = new ThreadLocal<>();


    /*
        申明一个切点(里面是Execution表达式)  这个输出没问题的

        第一个 public 表示方法的修饰符,可以用*代替
        第一个 * 表示 返回值,*代表所有
        com.store.admin.controller.* 包路径,.*表示路径下的所有包
        第三个.* 表示路径下,所有包下的所有类的方法
        (..) 表示不限方法参数 这个路径不对吗？可能跟你controller层下面还有文件夹的原因，
    */
    @Pointcut("execution(public * com.admin.admin.controller..*.*(..))")
    public void adminControllerLog() {
    }


    //  获取抽象方法
    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getMethod();
    }



    //  避免HttpServletRequest无法转为JSON,故所以二次处理
    private String parseArgs(JoinPoint joinPoint) {
        StringBuffer sb = new StringBuffer();
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            for (Object arg : args) {
                if (arg instanceof BindingResult ||
                        arg instanceof HttpServletRequest ||
                        arg instanceof HttpServletResponse ||
                        arg instanceof MultipartFile ||
                        arg instanceof MultipartFile[]) {
                    continue;
                }

                sb.append(" ");
                sb.append(new Gson().toJson(arg));
            }
        }
        return sb.toString();
    }


    //  获取方法执行后的返回信息   你个垃圾，路径都不是这样搞得   @Pointcut("execution(public * com.admin.admin.controller.*.*(..))")去百度一下这句话啥意思，你个小鸡儿 .......
    // 可以了，你要加多一个点读到下面的文件夹噢噢
    private void recordMethodLog(JoinPoint joinPoint, HttpServletRequest request) {
        Method method = getMethod(joinPoint);
        // 获取Swagger注解信息
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
        String value = "";
        if (null != apiOperation) {
            value = apiOperation.value();
            if (StringUtils.isNotEmpty(value)) {
                // set()方法用于保存当前线程的副本变量值
                methodDescribe.set(value);
            }
        }
        //避免HttpServletRequest无法转为JSON,故所以不使用GSON
        logger.info(value + "入参 : {} ", parseArgs(joinPoint));
    }



    /*
        在切点前执行方法,内容为指定的切点
    */
    @Before("adminControllerLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求,获取客户端请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        recordMethodLog(joinPoint, attributes.getRequest());
    }


    /*
        在切入点return后执行,如果想对某些方法的返回参数进行处理,可以在这操作
    */
    @AfterReturning(returning = "ret", pointcut = "adminControllerLog()")
    public void doAfterReturning(Object ret) throws Throwable {

        // get()方法用于获取当前线程的副本变量值
        Object Userid= CacheUtils.get("UserId");
        LogInformation logInformation=new LogInformation();
        if (Userid!=null){
            logInformation.setOperator(Integer.parseInt(Userid.toString()));
        }


        logInformation.setModular(methodDescribe.get());
        logInformation.setOperatingtime(new Date());
        logDao.insertLog(logInformation);

        logger.info(methodDescribe.get() + "返回参数 : {}", new Gson().toJson(ret));
        // remove()方法移除当前线程的副本变量值
        methodDescribe.remove();
    }


    /*
        在切入点抛出异常后处理逻辑
    */
    @AfterThrowing(pointcut = "adminControllerLog()", throwing = "e")
    public void doException(JoinPoint joinPoint, Throwable e) {
        if (e != null) {
            // getSignature()方法可获取封装了署名信息的对象,在该对象中可以获取到目标方法名,所属类的Class等信息
            Logger logger = LoggerFactory.getLogger(joinPoint.getSignature().getClass());
            logger.error("程序发生了异常 : " + e.getMessage(), e);
        }
    }

}
