package com.github.hykes.controller.dto;

import com.github.hykes.validator.EnumCheck;
import com.github.hykes.validator.Sex;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * @author hehaiyangwork@gmail.com
 * @date 2019-11-03 00:59:00
 */
@Data
public class ExampleVO {

    @NotNull(message = "主键不允许为空", groups = ValidGroupA.class)
    private Integer id;

    @NotBlank(message = "用户名不能为空", groups = Default.class)
    private String userName;

    @Range(min = 18,max = 60,message = "只能填报年龄在18~60岁的", groups = Default.class)
    private String age;

    @EnumCheck(message = "只能选男：1或女:2", enumClass = Sex.class, groups = Default.class)
    private Integer sex;

}
