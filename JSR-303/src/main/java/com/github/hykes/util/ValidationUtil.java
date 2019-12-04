package com.github.hykes.util;

import com.github.hykes.common.JsonResponseException;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.hibernate.validator.HibernateValidator;

/**
 * @author hehaiyangwork@gmail.com
 * @date 2019-11-03 11:57:00
 */
public class ValidationUtil {

    private static Validator failFastValidator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    private static Validator allCheckValidator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(false).buildValidatorFactory().getValidator();

    /**
     * 实体校验
     *
     * @param obj
     * @throws JsonResponseException
     */
    public static <T> void fastFailValidate(T obj) throws JsonResponseException {
        Set<ConstraintViolation<T>> constraintViolations = failFastValidator.validate(obj, new Class[0]);
        if (constraintViolations.size() > 0) {
            ConstraintViolation<T> validateInfo = constraintViolations.iterator().next();
            // validateInfo.getMessage() 校验不通过时的信息，即message对应的值
            throw new JsonResponseException(validateInfo.getMessage());
        }

    }

    public static <T> void allCheckValidate(T obj) throws JsonResponseException {
        Set<ConstraintViolation<T>> constraintViolations = allCheckValidator.validate(obj, new Class[0]);
        if (constraintViolations.size() > 0) {
            ConstraintViolation<T> validateInfo = constraintViolations.iterator().next();
            // validateInfo.getMessage() 校验不通过时的信息，即message对应的值
            throw new JsonResponseException(validateInfo.getMessage());
        }

    }

}
