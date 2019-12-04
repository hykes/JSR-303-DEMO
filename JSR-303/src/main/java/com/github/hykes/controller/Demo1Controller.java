package com.github.hykes.controller;

import com.github.hykes.controller.dto.ExampleVO;
import com.github.hykes.controller.dto.LoginForm;
import com.github.hykes.common.Response;
import com.github.hykes.controller.dto.ValidGroupA;
import java.util.Date;
import javax.validation.Valid;
import javax.validation.groups.Default;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录验证API
 *
 * @author hehaiyangwork@gmail.com
 * @date 2019-10-20 15:09:15
 */
@Slf4j
@RestController
@RequestMapping("/api/jsr303")
public class Demo1Controller {


    @GetMapping(value = "/time", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Date> time() {
        return Response.ok(new Date());
    }

    @PostMapping(value = "/test01", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Boolean> login(@Valid @RequestBody LoginForm form) {
        try {
            log.info(form.toString());
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail("login.fail");
        }
    }

    /**
     *  如果需要全局统一处理，校验不过，则需要在方法定义时，除去 , BindingResult result,定义该类处理拦截器
     * @param form
     * @param result
     * @return
     */
    @PostMapping(value = "/test02", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Boolean> login2(@Valid @RequestBody LoginForm form, BindingResult result) {
        try {
            if(result.hasErrors()){
                for (ObjectError error : result.getAllErrors()) {
                    System.out.println(error.getDefaultMessage());
                }
            }
            log.info(form.toString());
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail("login.fail");
        }
    }

    @PostMapping(value = "/test03", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Boolean> login3(@Validated({ValidGroupA.class, Default.class})  @RequestBody ExampleVO vo) {
        try {
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail("login.fail");
        }
    }

    @PostMapping(value = "/test04", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Boolean> login4(@Validated @RequestBody LoginForm form) {
        try {
            log.info(form.toString());
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail("login.fail");
        }
    }

}
