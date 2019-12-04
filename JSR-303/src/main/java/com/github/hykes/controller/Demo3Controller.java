package com.github.hykes.controller;

import com.google.common.base.Preconditions;
import com.github.hykes.common.Response;
import com.github.hykes.controller.dto.UserQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录验证API
 *
 * @author hehaiyangwork@gmail.com
 * @date 2019-10-20 15:09:15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/api/user")
public class Demo3Controller {

    /**
     * 用于测试
     *
     * @param id id数不能小于10 @RequestParam类型的参数需要在Controller上增加@Validated
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String test(@Min(value = 10, message = "id最小只能是10") @RequestParam("id") Integer id) {
        Preconditions.checkNotNull(id, "id.is.empty");
        return "恭喜你拿到参数了";
    }

    @RequestMapping(value = "/info2", method = RequestMethod.GET)
    public String test2(@Min(value = 10, message = "id最小只能是10") @RequestParam("id") Integer id,
            @Size(min = 2) @RequestParam("name") String name) {
        return "恭喜你拿到参数了";
    }

    @GetMapping(value = "/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Boolean> login(@Validated UserQuery query) {
        try {
//            ValidationUtils.invokeValidator();
            log.info(query.toString());
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail("login.fail");
        }
    }


}
