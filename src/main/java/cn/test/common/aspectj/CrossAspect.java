package cn.test.common.aspectj;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.test.common.annotation.CrossOrigin;


@Component
@Aspect
@Order(0)
public class CrossAspect {
	
	ThreadLocal<Long> time = new ThreadLocal<Long>();
	ThreadLocal<String> tag = new ThreadLocal<String>();

	/**
	 * 在所有标注@CrossOrigin的地方切入
	 * 
	 * @param joinPoint
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 */
	@Before("@annotation(cn.test.common.annotation.CrossOrigin)")
	public void beforeExec(JoinPoint joinPoint) throws IllegalArgumentException, IllegalAccessException,
			NoSuchMethodException, InstantiationException, InvocationTargetException {

		time.set(System.currentTimeMillis());
		tag.set(UUID.randomUUID().toString());
		//info(joinPoint);
		try {
/*	    	ServletWebRequest servletContainer = (ServletWebRequest)RequestContextHolder.getRequestAttributes();
	    	HttpServletRequest request = servletContainer.getRequest();
	    	HttpServletResponse response = servletContainer.getResponse();
*/			MethodSignature ms = (MethodSignature) joinPoint.getSignature();
			Method method = ms.getMethod();
			Field responseField = joinPoint.getTarget().getClass().getField("response");
			HttpServletResponse response = (HttpServletResponse) responseField.get(joinPoint.getTarget());
			String _value = method.getAnnotation(CrossOrigin.class).value();
			setCross(response, _value);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	public void setCross(HttpServletResponse response, String _value) {
		response.addHeader("Access-Control-Allow-Origin", _value);
		response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS,DELETE,PUT");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		response.addHeader("Access-Control-Allow-Credentials", "true");
	}

}
