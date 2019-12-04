package com.github.hykes.controller.dto;

import com.github.hykes.validator.EnumCheck;
import com.github.hykes.validator.Sex;
import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author hehaiyangwork@gmail.com
 * @date 2019-10-26 11:27:00
 */
@Data
public class UserQuery implements Serializable {

    private static final long serialVersionUID = 1009822536319289692L;

    @NotNull
    @Min(10)
    private Long id;

    @NotNull(message = "密码不能为空")
    private Integer type;

    @EnumCheck(message = "只能选男：1或女:2", enumClass = Sex.class)
    private Integer sex;

}
