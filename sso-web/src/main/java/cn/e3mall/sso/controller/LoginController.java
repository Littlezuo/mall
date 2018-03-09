package cn.e3mall.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.e3mall.common.utils.CookieUtils;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.sso.service.LoginService;

/**
 * Created by Littlezuo on 2018/3/9.
 */
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;

    @RequestMapping("page/login")
    public String showLogin() {
        return "login";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public E3Result login(String username, String password,
                          HttpServletRequest request, HttpServletResponse response) {
        E3Result result = loginService.userLogin(username, password);
        //判断时候登录成功
        if (result.getStatus() == 200) {
            String token = result.getData().toString();
            //如果登录成功需要把token写入cookie
            CookieUtils.setCookie(request, response, TOKEN_KEY, token);
        }
        return result;
    }

}
