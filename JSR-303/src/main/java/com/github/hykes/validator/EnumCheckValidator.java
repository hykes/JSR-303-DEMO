package com.github.hykes.validator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author hehaiyangwork@gmail.com
 * @date 2019-11-03 00:56:00
 */
public class EnumCheckValidator implements ConstraintValidator<EnumCheck, Object> {
    private EnumCheck enumCheck;

    @Override
    public void initialize(EnumCheck enumCheck) {
        this.enumCheck =enumCheck;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        // 注解表明为必选项 则不允许为空，否则可以为空
        if (value == null) {
            return this.enumCheck.required() ? false : true;
        }
        //最终的返回结果
        Boolean result=Boolean.FALSE;
        // 获取 参数的数据类型
        Class<?> valueClass = value.getClass();
        try {
            Method method = this.enumCheck.enumClass().getMethod(this.enumCheck.enumMethod(), valueClass);
            result = (Boolean)method.invoke(null, value);
            result= result == null ? false : result;
            //所有异常需要在开发测试阶段发现完毕
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            return result;
        }
    }

}