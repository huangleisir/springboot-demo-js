package com.jst.demo.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jst.demo.bean.User;
import com.jst.demo.dao.test.UserDao;

@CacheConfig(cacheNames = "users")
@Service
public class UserCacheImpl {

	protected Logger  log = LoggerFactory.getLogger(getClass());
   // public static final String MY_LOGIN_KEY = "member_loginUserInfo";

    @Autowired
    UserDao userDao;
    
    @Cacheable(key="#user.name")
  	public User userLogin(User user){
  	    log.info("进入UserLoginCacheImpl.userLogin,查库");
  	      User result = userDao.userLogin(user); //查询数据库用户登录信息，密码
          log.info("登录请求返回数据：" + result == null?"":JSON.toJSONString(result));
          return result;
  	}
  	
    @CachePut(key="#user.name")
  	public void addUsrInfoPwd(User user){
    	log.info("增加用户信息=====" + JSON.toJSONString(user));
  		userDao.addUser(user);
  		log.info("插入用户信息请求返回结果：result =====" + userDao.addUser(user));
  		//return result;
  	}
  	
  	@CachePut(key="#user.name")
  	public void updUsrPwdInf(User user){
    	log.info("更新用户信息=====" + JSON.toJSONString(user));
  		 userDao.updateUser(user);
  		log.info("更新用户信息请求返回结果：updNum ======" + userDao.updateUser(user));
  		//return updNum;
  	}
}
