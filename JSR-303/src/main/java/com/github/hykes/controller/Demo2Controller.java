package com.github.hykes.controller;

import com.github.hykes.common.Response;
import com.github.hykes.controller.dto.UserQuery;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/api/person")
public class Demo2Controller {

    @Autowired
    private Validator validator;

    @GetMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Boolean> login(@Validated UserQuery query) {
        try {
            Set<ConstraintViolation<UserQuery>> violationSet = validator.validate(query);
            for (ConstraintViolation<UserQuery> model : violationSet) {
                System.out.println(model.getMessage());
            }
            log.info(query.toString());
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail("login.fail");
        }
    }


}
