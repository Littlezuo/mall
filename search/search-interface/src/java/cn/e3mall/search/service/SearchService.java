package cn.e3mall.search.service;

import cn.e3mall.common.pojo.SearchResult;

/**
 * Created by Littlezuo on 2018/3/8.
 */

public interface SearchService {
    SearchResult search(String keyword,int page , int rows) throws Exception;
}
