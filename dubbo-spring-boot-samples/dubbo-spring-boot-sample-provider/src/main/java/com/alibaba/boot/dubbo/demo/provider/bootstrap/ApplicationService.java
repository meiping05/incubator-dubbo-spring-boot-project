package com.alibaba.boot.dubbo.demo.provider.bootstrap;

import com.alibaba.dubbo.config.ApplicationConfig;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
public class ApplicationService implements ApplicationContextAware, EnvironmentAware {

    private final Log log = LogFactory.getLog(getClass());
    private Environment environment;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        log.info(this.environment.getProperty("dubbo.application.id"));
        ApplicationConfig bean = (ApplicationConfig) applicationContext.getBean("dubbo-provider-demo");
        log.info(bean);
        //TODO 实际的id应该为prod中的id，事实为默认文件中的beanId,在注册bean,id取错了
        bean = (ApplicationConfig) applicationContext.getBean(this.environment.getProperty("dubbo.application.id"));
        log.info(bean);
    }


    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
