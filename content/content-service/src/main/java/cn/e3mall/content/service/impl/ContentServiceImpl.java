package cn.e3mall.content.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;


/**
 * Created by Littlezuo on 2018/2/28.
 */
@Service
public class ContentServiceImpl implements ContentService {
    /**
     * 根据内容分类id查询内容列表
     *
     * @param cid
     * @return
     */
    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${CONTENT_LIST}")
    private String CONTENT_LIST;

    @Override
    public List<TbContent> getContentListByCid(long cid) throws Exception{
//        int i = 1/0;
        //查询缓存
        try {
            String json = jedisClient.hget(CONTENT_LIST, cid + "");
            if (org.apache.commons.lang3.StringUtils.isNotBlank(json)) {
                System.out.println("---------------取缓存");
                List<TbContent> tbContents = JsonUtils.jsonToList(json, TbContent.class);
                return tbContents;
            }
        } catch (Exception e) {

        }
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        //设置查询条件
        criteria.andCategoryIdEqualTo(cid);
        //执行查询
        List<TbContent> tbContents = contentMapper.selectByExampleWithBLOBs(tbContentExample);
        try {
            jedisClient.hset(CONTENT_LIST, cid + "", JsonUtils.objectToJson(tbContents));
            System.out.println("---------------缓存");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbContents;
    }

    @Override
    public E3Result addContent(TbContent content) {
        //将内容数据插入到内容表
        content.setCreated(new Date());
        content.setUpdated(new Date());
        //插入到数据库
        contentMapper.insert(content);
        return E3Result.ok();
    }
}
