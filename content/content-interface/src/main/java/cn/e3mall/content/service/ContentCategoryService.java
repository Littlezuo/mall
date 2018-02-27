package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUITreeNode;

/**
 * Created by Littlezuo on 2018/2/27.
 */

public interface ContentCategoryService {
    List<EasyUITreeNode> getContentCatList(long parentId);
}
