package org.geekbang.thinking.in.spring.ioc.dependency.injection.loiter;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * @Fun Description //TODO
 * @Date 2020/6/27 10:38 27
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
public class AnnotationDependencyInjetionResouteDemo {

    @Autowired
    private User user;

    @Autowired
    private Collection<User> collection;

    @Autowired
    private Optional<User> optionalUser;

    @Autowired
    @Lazy
    private ObjectProvider<User> objectProvider;

    @Inject
    private User injectedUser;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationDependencyInjetionResouteDemo.class);
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");
        BeanDefinitionRegistry beanFactory = xmlBeanDefinitionReader.getBeanFactory();
        applicationContext.refresh();

        AnnotationDependencyInjetionResouteDemo beans = applicationContext.getBean(AnnotationDependencyInjetionResouteDemo.class);
        User user = beans.user;
        System.out.println("user is " + user);
        System.out.println(">>>> " + beans.collection);
        System.out.println(">>>> + > " + beans.optionalUser);
        // lazy 模式
        System.out.println(">####>>>> " + beans.objectProvider.getObject());

        // @Inject user
        System.out.println(">$$$  >>>>" + beans.injectedUser);

        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        Arrays.asList(beanDefinitionNames).forEach(item -> {
            Object bean = applicationContext.getBean(item);
            System.out.println("bean is " + bean);
        });

        applicationContext.close();
    }
}
