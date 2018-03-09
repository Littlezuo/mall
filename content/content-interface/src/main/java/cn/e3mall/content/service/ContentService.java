package cn.e3mall.content.service;

import java.util.List;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbContent;

/**
 * Created by Littlezuo on 2018/2/28.
 */

public interface ContentService {
    List<TbContent> getContentListByCid(long cid) throws Exception;
    E3Result addContent(TbContent content);
}
