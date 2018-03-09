package cn.e3mall.content.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;

/**
 * Created by Littlezuo on 2018/2/27.
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getContentCatList(long parentId) {
        //根据parentid查询子节点列表

        TbContentCategoryExample categoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = categoryExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> tbContentCategories = contentCategoryMapper.selectByExample(categoryExample);
        ArrayList<EasyUITreeNode> easyUITreeNodes = new ArrayList<>();
        for (TbContentCategory tbContentCategory : tbContentCategories) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
            /* 添加到列表 */
            easyUITreeNodes.add(node);
        }
        return easyUITreeNodes;
    }

    @Override
    public E3Result addContentCategory(Long parentId, String name) {
        //创建一个tb_content_category表对应的pojo对象
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        //1(正常),2(删除)
        contentCategory.setStatus(1);
        //默认排序就是1
        contentCategory.setSortOrder(1);
        //新添加的节点一定是叶子节点
        contentCategory.setIsParent(false);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        //插入到数据库
        contentCategoryMapper.insert(contentCategory);
        //判断父节点的isparent属性。如果不是true改为true
        //根据parentid查询父节点
        TbContentCategory parent = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parent.getIsParent()) {
            parent.setIsParent(true);
            //更新到数数据库
            contentCategoryMapper.updateByPrimaryKey(parent);
        }
        //返回结果，返回E3Result，包含pojo
        return E3Result.ok(contentCategory);
    }

    @Override
    public E3Result updateContentCategory(Long id, String name) {
        TbContentCategory tbContentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        tbContentCategory.setName(name);
        return E3Result.ok(tbContentCategory);
    }

    @Override
    public E3Result deleteContentCategory(Long id) {
        TbContentCategory tbContentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        if (!tbContentCategory.getIsParent()) {
            int i = contentCategoryMapper.deleteByPrimaryKey(id);
        }
        return E3Result.ok();
    }
}
