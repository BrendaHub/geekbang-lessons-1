package org.geekbang.thinking.in.spring.ioc.dependency.injection.loiter;

import org.geekbang.thinking.in.spring.ioc.dependency.injection.selfAnnotation.CicAutowired;
import org.geekbang.thinking.in.spring.ioc.dependency.injection.selfAnnotation.LoiterAutowired;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * @Fun Description //TODO
 * @Date 2020/6/27 17:07 27
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
public class LoiterAutowiredAnnotationDependencyInjectDemo {

    @Autowired
    private ObjectProvider<User> objectProvider;

    @Autowired
    private User user1;

    @CicAutowired
    private User cicUser;

    // 完全自定义的注解，需要重新更新AutowiredAnnotationBeanPostProcessor类
    @LoiterAutowired
    private Collection<User> collection;

    /**
     * @return org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor
     * site: https://www.ant-loiter.com
     * @Author chenhj(brenda)
     * @Description // 这个实现方法， 不安全， Inject.class这个类可能不存在（没有添加相关依赖）
     * @Date 17:34 2020/6/27
     * @Param []
     **/
    @Deprecated
//    @Bean // (name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
//        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(
//                asList(Autowired.class, Inject.class, CicAutowired.class, LoiterAutowired.class)
//        );
//        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationTypes);
////        beanPostProcessor.setAutowiredAnnotationType(LoiterAutowired.class);
//        return beanPostProcessor;
//    }

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE - 4) // 减的越多， 优先级越高
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(LoiterAutowired.class);
        return beanPostProcessor;
    }


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LoiterAutowiredAnnotationDependencyInjectDemo.class);

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");
        applicationContext.refresh();
        LoiterAutowiredAnnotationDependencyInjectDemo beans = applicationContext.getBean(LoiterAutowiredAnnotationDependencyInjectDemo.class);

        System.out.println(beans);
//        beans.objectProvider.stream().forEach(System.out::println);
//        System.out.println(beans.objectProvider.getObject());
        System.out.println("user is " + beans.user1);
        System.out.println("=====================");
        System.out.println("cic User is " + beans.cicUser);

        System.out.println("loiterAutowired is " + beans.collection);

        applicationContext.close();
    }
}
