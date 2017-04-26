package main.utils;

import main.controllers.listeners.NewAppStartListener;
import main.services.StudentServiceImpl;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by admin on 26.04.2017.
 */
@Component
public class BenchmarkBeanPostProcessor implements BeanPostProcessor {
    static {
        PropertyConfigurator.configure(NewAppStartListener.class.getClassLoader().getResource("log4j.xml"));
    }
    private static final Logger logger = Logger.getLogger(StudentServiceImpl.class);

    @Override
    public Object postProcessBeforeInitialization(final Object bean, String beanName) throws BeansException {
        Class type = bean.getClass();
        if (type.isAnnotationPresent(Benchmark.class)) {
            Object proxy = Proxy.newProxyInstance(type.getClassLoader(),type.getInterfaces(),new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    long before = System.nanoTime();
                    Object retVal = method.invoke(bean, args);
                    long after = System.nanoTime();
                    logger.info("Hello! postProcessAfterInitialization working "+(after-before)+" ns");
                    return retVal;
                }
            });
            return proxy;
        } else {
            return bean;
        }
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {
        logger.info("Hello! postProcessAfterInitialization");
        return bean;
    }
}
