package cn.e3mall.service;

import java.util.List;

import cn.e3mall.common.pojo.EasyUITreeNode;

/**
 * Created by Littlezuo on 2018/2/9.
 */

public interface ItemCatService {
    List<EasyUITreeNode> getItemCatlist(long parentId);
}
