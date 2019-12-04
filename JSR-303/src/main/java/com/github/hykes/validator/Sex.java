package com.github.hykes.validator;

import java.util.Objects;
import lombok.AllArgsConstructor;

/**
 * @author hehaiyangwork@gmail.com
 * @date 2019-11-03 00:56:00
 */
@AllArgsConstructor
public enum  Sex{

    MAN("男",1),

    WOMAN("女",2),

    ;

    private String label;

    private Integer value;


    /**
     * 判断值是否满足枚举中的value
     * @param value
     * @return
     */
    public static boolean validation(Integer value){
        for(Sex s:Sex.values()){
            if(Objects.equals(s.value,value)){
                return true;
            }
        }
        return false;
    }
}