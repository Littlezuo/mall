package cn.e3mall.portal.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Littlezuo on 2018/2/27.
 */
@Controller
public class IndexController {
    @Value("89")
    private Long CONTENT_LUNBO_ID;

    @RequestMapping("/index")
    public String showIndex() {
        return "index";
    }
}
