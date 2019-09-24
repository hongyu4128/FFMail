package com.yhhy.FFMailBasic.basic.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * Spring的Bean和context对象获取类
 * 
 * @author yuyy 实现Spring的Bean和context对象获取，需做以下配置：
 *         <bean class="com.xyzq.afa2.util.SpringContext"/>
 */
public class SpringContext {

    private static ApplicationContext applicationContext = null;
// 非@import显式注入，@Component是必须的，且该类必须与main同包或子包
    // 若非同包或子包，则需手动import 注入，有没有@Component都一样
    // 可复制到Test同包测试

    public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContext.applicationContext == null) {
            SpringContext.applicationContext = applicationContext;
        }
    }

    // 获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    // 通过name获取 Bean.
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);

    }

    // 通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    // 通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

}
