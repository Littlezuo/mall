package cn.e3mall.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;


/**
 * Created by Littlezuo on 2018/2/27.
 */
@Controller
public class IndexController {
    @Value("89")
    private Long CONTENT_LUNBO_ID;

    @Autowired
    private ContentService contentService;

    @RequestMapping("/index")
    public String showIndex(Model model) throws Exception {
        //查询内容列表

        List<TbContent> contentListByCid = contentService.getContentListByCid(CONTENT_LUNBO_ID);
        model.addAttribute("ad1List", contentListByCid);
        System.out.println("--------" + contentListByCid.size());
        return "index";
    }
}
