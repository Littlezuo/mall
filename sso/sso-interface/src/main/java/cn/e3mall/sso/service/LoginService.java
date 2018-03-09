package cn.e3mall.sso.service;

import cn.e3mall.common.utils.E3Result;

/**
 * Created by Littlezuo on 2018/3/9.
 */

public interface LoginService {

    /**
     * 业务逻辑:
     * 判断用户名和密码是否正确
     * 如果不正确,返回登录失败
     * 如果争取生成toker
     * 把用户信息写入redis, key:taoken value:用户信息
     * 设置Session的过期时间
     * 把token返回
     * @param username
     * @param password
     * @return E3Result, 其中包含token信息
     */
    E3Result userLogin(String username, String password);
}
