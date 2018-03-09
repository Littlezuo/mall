package cn.e3mall.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Littlezuo on 2018/3/6.
 */

public class TestSolrJ {
    @Test
    public void addDocument() throws Exception {
        //创建一个SolrServer对象,创建一个连接.参数solr服务url
        HttpSolrServer solrServer = new HttpSolrServer("http://192.168.223.132:8080/solr/collection1");
        //创建一个文档对象SolrInputDocument
        SolrInputDocument solrInputDocument = new SolrInputDocument();
        //向文档对象中添加域,文档中必须包含一个id域,所有的域的名称必须在schema.xml中定义.
        solrInputDocument.addField("id", "doc02");
        solrInputDocument.addField("item_title", "测试产品02");
        solrInputDocument.addField("item_price", 1000);
        //把文档写入索引库
        solrServer.add(solrInputDocument);
        //提交
        solrServer.commit();
    }
    HttpSolrServer solrServer;
    @Before
    public void init() {
        //创建一个SolrServer对象,创建一个连接.参数solr服务url
         solrServer = new HttpSolrServer("http://192.168.223.132:8080/solr/collection1");

    }
    @Test
    public void delDocument() throws IOException, SolrServerException {
        solrServer.deleteByQuery("id:doc01");
        solrServer.commit();
    }

    @Test
    public void queryIndex() throws Exception {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("q","*:*");
        QueryResponse query = solrServer.query(solrQuery);
        SolrDocumentList results = query.getResults();
        System.out.println("查询到的总条数" +results.getNumFound());
        for (SolrDocument result : results) {
            System.out.println(result.get("id"));
            System.out.println(result.get("item_title"));
        }

    }

    @Test
    public void queryIndexComplex() throws SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("测试");
        solrQuery.setStart(0);
        solrQuery.setRows(20);
        solrQuery.set("df","item_title");
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em>");
        solrQuery.setHighlightSimplePost("</em>");
        //执行查询
        QueryResponse query = solrServer.query(solrQuery);
        SolrDocumentList results = query.getResults();
        Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();

        for (SolrDocument result : results) {
            System.out.println("id = " + result.get("id"));
            //取高亮显示
            String title = "";
            List<String> list = highlighting.get(result.get("id")).get("item_title");
            if(list != null &&list.size() >0) {
                title = list.get(0);
            }else {
                title = (String) result.get("item_title");
            }
            System.out.println(title);
            System.out.println(result.get("item_price"));
        }
    }


}
