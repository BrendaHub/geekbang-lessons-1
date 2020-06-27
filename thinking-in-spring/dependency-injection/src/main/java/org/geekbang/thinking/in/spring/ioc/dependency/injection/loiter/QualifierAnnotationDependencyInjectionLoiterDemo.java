package org.geekbang.thinking.in.spring.ioc.dependency.injection.loiter;

import org.geekbang.thinking.in.spring.ioc.dependency.injection.annotation.LoiterUserGroupAnnotation;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collector;

/**
 * @Fun Description // @link( Qualifier)
 * @Date 2020/6/26 21:13 26
 * @Author chenhj(brenda)
 * @see org.springframework.beans.factory.annotation.Qualifier
 * site: https://www.ant-loiter.com
 **/
//@Configuration
public class QualifierAnnotationDependencyInjectionLoiterDemo {

    @Autowired
    private User user1;

    @Autowired
    @Qualifier(value = "user")
    private User subUser;

    @Autowired
    private Collection<User> allUsers;

    // 这个集合属性只是会注入添加@Qualifier的Bean，注意不会注入添加有自定义@LoiterUserGroupAnnotation的bean
    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers;

    @Autowired
    @LoiterUserGroupAnnotation
    private Collection<User> userGroupUsers;


    @Bean
    @Qualifier  /// Qualifier 注解可以进行逻辑分组， 可以将标记有Qualifier注解的的Bean自动注入到一个集合bean中
    public User User1() {
        return createUser(7L);
    }

    @Bean
    @Qualifier
    public User User2() {
        return createUser(8L);
    }

    @Bean
    @LoiterUserGroupAnnotation
//    @Qualifier
    public User User3() {
        return createUser(9L);
    }

    @Bean
    @LoiterUserGroupAnnotation
//    @Qualifier
    public User User4() {
        return createUser(10L);
    }

    private User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 这么注册完后， 就相当于在这个类上添加@Configuration注册
        applicationContext.register(QualifierAnnotationDependencyInjectionLoiterDemo.class);

        // 通过xml 进行依赖查找  "classpath:/META-INF/dependency-lookup-context.xml"
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        int result = xmlBeanDefinitionReader.loadBeanDefinitions("classpath:/META-INF/dependency-lookup-context.xml");
        System.out.println("xmlBeanDefinitionReader.loadBeanDefinitions result is " + result);
        int beanDefinitionCount = xmlBeanDefinitionReader.getBeanFactory().getBeanDefinitionCount();
        System.out.println("beanDefinitionCount = " + beanDefinitionCount);
        String[] names = xmlBeanDefinitionReader.getBeanFactory().getBeanDefinitionNames();
        System.out.println("names = " + Arrays.toString(names));
        // 通过以上代码在Spring 容器里面就有了对应xml里的注入的所有beans, 启动Spring容器后就可以进行依赖查找了
        // 启动Spring容器上下文
        applicationContext.refresh();

        // 通过类型查找当前Bean里的所有bean对象
        QualifierAnnotationDependencyInjectionLoiterDemo beans = applicationContext.getBean(QualifierAnnotationDependencyInjectionLoiterDemo.class);

        System.out.println(beans.subUser);
        System.out.println(beans.user1);
        System.out.println("======");
        System.out.println(beans.allUsers);
        System.out.println(beans.qualifierUsers);
        System.out.println("======");
        System.out.println(beans.userGroupUsers);

        applicationContext.close();
    }
}
