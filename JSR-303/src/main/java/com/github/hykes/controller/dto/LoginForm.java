package com.github.hykes.controller.dto;

import com.github.hykes.validator.EnumCheck;
import com.github.hykes.validator.Sex;
import java.io.Serializable;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author hehaiyangwork@gmail.com
 * @date 2019-10-26 11:27:00
 */
@Data
public class LoginForm implements Serializable {

    private static final long serialVersionUID = 1009822536319289692L;

    @EnumCheck(message = "只能选男：1或女:2",enumClass = Sex.class)
    private Integer sex;

    @NotBlank(message = "用户名不能为空")
    @Size(min = 2, max = 30)
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

}
