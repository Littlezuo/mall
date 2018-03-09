package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.utils.E3Result;

/**
 * Created by Littlezuo on 2018/2/27.
 */

public interface ContentCategoryService {
    List<EasyUITreeNode> getContentCatList(long parentId);

    E3Result addContentCategory(Long parentId, String name);
    E3Result updateContentCategory(Long id,String name);

    E3Result deleteContentCategory(Long id);
}
