package cn.e3mall.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;


/**
 * Created by Littlezuo on 2018/2/27.
 */
@Controller
public class ContentCatController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(name = "id", defaultValue = "0") Long parentId) {
        List<EasyUITreeNode> contentCatList = contentCategoryService.getContentCatList(parentId);
        return contentCatList;
    }

    /**
     * 添加分类节点
     */
    @RequestMapping(value = "/content/category/create", method = RequestMethod.POST)
    @ResponseBody
    public E3Result createContentCategory(Long parentId, String name) {
        E3Result result = null;

        result = contentCategoryService.addContentCategory(parentId, name);
        return result;
    }

    /**
     * 更新分类节点
     */
    @RequestMapping(value = "/content/category/update/", method = RequestMethod.POST)
    @ResponseBody
    public E3Result updateContentCategory(Long id, String name) {
        E3Result result = null;
        result = contentCategoryService.updateContentCategory(id, name);
        return result;
    }

    /**
     * 删除分类节点
     */
    @RequestMapping(value = "/content/category/delete",method = RequestMethod.POST)
    @ResponseBody
    public E3Result deleteContentCategory(Long id) {
        E3Result result = contentCategoryService.deleteContentCategory(id);
        return result;
    }
}
