package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Fun Description // 通过 Aware系列接口进行注入接口作
 * @Date 2020/6/26 19:37 26
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
public class AwareInterfaceDependencyInjectonDemo1 implements BeanFactoryAware, ApplicationContextAware {

    // 这个私有的beanFacotory是通过 Aware进行依赖注入的。
    private static BeanFactory beanFactory;
    // 这个私有的 applicationContext 是通过 Aware进行注入的。
    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AwareInterfaceDependencyInjectonDemo1.class);

        context.refresh();

        System.out.println(beanFactory);
        System.out.println(beanFactory == context.getBeanFactory());
        System.out.println(applicationContext == context);

        context.close();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AwareInterfaceDependencyInjectonDemo1.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareInterfaceDependencyInjectonDemo1.applicationContext = applicationContext;

    }
}
