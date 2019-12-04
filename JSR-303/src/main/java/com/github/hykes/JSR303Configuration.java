package com.github.hykes;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan({"com.github.hykes.*"})
@EnableAutoConfiguration
public class JSR303Configuration extends WebMvcConfigurerAdapter {

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class )
                .configure()
                .failFast( false )
                .buildValidatorFactory();
//
//        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
//                .configure()
//                .addProperty("hibernate.validator.fail_fast", "true")
//                .buildValidatorFactory();

        return validatorFactory.getValidator();
    }

    /**
     * 貌似已经不用主动实例化bean了
     */
//    @Bean
//    public MethodValidationPostProcessor methodValidationPostProcessor() {
//        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
//        /**设置validator模式为快速失败返回*/
//        postProcessor.setValidator(validator());
//        return postProcessor;
//    }


}
