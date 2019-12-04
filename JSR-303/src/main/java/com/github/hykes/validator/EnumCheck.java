package com.github.hykes.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author hehaiyangwork@gmail.com
 * @date 2019-11-03 00:55:00
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EnumCheckValidator.class)
public @interface EnumCheck {
    /**
     * 是否必填 默认是必填的
     * @return
     */
    boolean required() default true;
    /**
     * 验证失败的消息
     * @return
     */
    String message() default "枚举的验证失败";
    /**
     * 分组的内容
     * @return
     */
    Class<?>[] groups() default {};

    /**
     * 错误验证的级别
     * @return
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * 枚举的Class
     * @return
     */
    Class<? extends Enum<?>> enumClass();

    /**
     * 枚举中的验证方法
     * @return
     */
    String enumMethod() default "validation";
}
