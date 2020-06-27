package org.geekbang.thinking.in.spring.ioc.dependency.injection.loiter;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Fun Description //TODO
 * @Date 2020/6/26 22:46 26
 * @Author chenhj(brenda)
 * site: https://www.ant-loiter.com
 **/
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    private ObjectProvider<User> objectProvider;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");

        applicationContext.refresh();

        LazyAnnotationDependencyInjectionDemo beans = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);
        ObjectProvider<User> objectProvider = beans.objectProvider;
        List<String> lists = objectProvider.stream().map(item -> item.getName()).collect(Collectors.toList());
        lists.parallelStream().forEach(System.out::println);

        applicationContext.close();

    }
}
