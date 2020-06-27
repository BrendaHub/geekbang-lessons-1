package org.geekbang.thinking.in.spring.ioc.dependency.injection.annotation;

import javax.inject.Qualifier;
import java.lang.annotation.*;

/**
 * @Author chenhj(brenda)
 * @Description // 自定义一个注解 {@link javax.inject.Qualifier Qualifier}
 * @Date 21:58 2020/6/26
 * @Param
 * @return site: https://www.ant-loiter.com
 * 自定义了一个Qualifier的注解派生
 * @see Qualifier
 **/
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier
public @interface LoiterUserGroupAnnotation {
}
