import org.junit.Test;

import redis.clients.jedis.Jedis;

/**
 * Created by Littlezuo on 2018/3/6.
 */

public class JedisTest {
    @Test
    public void  testJedis() {
        //第一步:创建一个jedis对象,需要指定服务传奇的ip以及端口
        Jedis jedis = new Jedis("192.168.223.129", 6379);
        jedis.set("hello","111111");
        //第二步:使用jedis对象操作数据库,
        String hello = jedis.get("hello");
        System.out.println(hello);
        jedis.close();
    }

}
