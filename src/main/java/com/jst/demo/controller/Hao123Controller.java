package com.hl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//  增加配置     spring.resources.staticLocations=classpath:/
/**
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8"> 
</head>
<body>
 我的123网址之家
<h3><a href="http://localhost:9080/wangyi">网易</a></h3>
<h3><a href="http://localhost:9080/baidu">百度</a></h3>
<h3><a href="http://localhost:9080/qq">腾讯</a></h3>
<h3><a href="http://localhost:9080/douyu">斗鱼</a></h3>
<h3><a href="http://localhost:9080/iqiyi">爱奇艺</a></h3>
<h3><a href="http://localhost:9080/youku">优酷</a></h3>
<h3><a href="http://localhost:9080/imooc">慕课</a></h3>
<h3><a href="http://localhost:9080/panda">熊猫</a></h3>
<h3><a href="http://localhost:9080/yizhibo">一直播</a></h3>


</body>
</html>

*/

@RestController
public class Hao123Controller {
	 private static final Logger logger = LoggerFactory.getLogger(Hao123Controller.class);
	/**
	 * http://localhost:8080/中文    不错，是可以支持中文的
	 * @param value
	 * @return
	 */
	/*@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	public String demo(){
		logger.info("---------------~~~~~~~~~~~~23424242~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return "welcome to us , demo";
	}*/
	
	
	@RequestMapping(value = "/baidu", method = RequestMethod.GET)
	public void baidu(HttpServletRequest req,HttpServletResponse resp){
		logger.info("---------------~~~~~~~~~~~~wangyi~~~~~~~~~~~~~~~~~~~~~~~~~~");
		try {
			resp.sendRedirect("http://www.baidu.com"); 
		} catch (IOException e) {
			logger.info("跳转到网易门户失败");
			e.printStackTrace();
		} 
	}
	
	@RequestMapping(value = "/wangyi", method = RequestMethod.GET)
	public void wangyi(HttpServletRequest req,HttpServletResponse resp){
		logger.info("---------------~~~~~~~~~~~~wangyi~~~~~~~~~~~~~~~~~~~~~~~~~~");
		try {
			resp.sendRedirect("http://www.163.com"); 
		} catch (IOException e) {
			logger.info("跳转到网易门户失败");
			e.printStackTrace();
		} 
	}
	
	@RequestMapping(value = "/qq", method = RequestMethod.GET)
	public void tencentIndex(HttpServletRequest req,HttpServletResponse resp){
		logger.info("---------------~~~~~~~~~~~~wangyi~~~~~~~~~~~~~~~~~~~~~~~~~~");
		try {
			resp.sendRedirect("http://www.qq.com"); 
		} catch (IOException e) {
			logger.info("跳转到网易门户失败");
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "/douyu", method = RequestMethod.GET)
	public void douyu(HttpServletRequest req,HttpServletResponse resp){
		logger.info("---------------~~~~~~~~~~~~wangyi~~~~~~~~~~~~~~~~~~~~~~~~~~");
		try {
			resp.sendRedirect("http://www.douyu.com"); 
		} catch (IOException e) {
			logger.info("跳转到网易门户失败");
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "/iqiyi", method = RequestMethod.GET)
	public void iqiyi(HttpServletRequest req,HttpServletResponse resp){
		logger.info("---------------~~~~~~~~~~~~wangyi~~~~~~~~~~~~~~~~~~~~~~~~~~");
		try {
			resp.sendRedirect("http://www.iqiyi.com"); 
		} catch (IOException e) {
			logger.info("跳转到爱奇艺门户失败");
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "/youku", method = RequestMethod.GET)
	public void youku(HttpServletRequest req,HttpServletResponse resp){
		logger.info("---------------~~~~~~~~~~~~wangyi~~~~~~~~~~~~~~~~~~~~~~~~~~");
		try {
			resp.sendRedirect("http://www.youku.com"); 
		} catch (IOException e) {
			logger.info("跳转到youku门户失败");
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "/imooc", method = RequestMethod.GET)
	public void imooc(HttpServletRequest req,HttpServletResponse resp){
		logger.info("---------------~~~~~~~~~~~~wangyi~~~~~~~~~~~~~~~~~~~~~~~~~~");
		try {
			resp.sendRedirect("https://www.imooc.com/"); 
		} catch (IOException e) {
			logger.info("跳转到youku门户失败");
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "/panda", method = RequestMethod.GET)
	public void panda(HttpServletRequest req,HttpServletResponse resp){
		logger.info("---------------~~~~~~~~~~~~wangyi~~~~~~~~~~~~~~~~~~~~~~~~~~");
		try {
			resp.sendRedirect("https://www.panda.tv"); 
		} catch (IOException e) {
			logger.info("跳转到youku门户失败");
			e.printStackTrace();
		} 
	}
	@RequestMapping(value = "/yizhibo", method = RequestMethod.GET)
	public void yizhibo(HttpServletRequest req,HttpServletResponse resp){
		logger.info("---------------~~~~~~~~~~~~wangyi~~~~~~~~~~~~~~~~~~~~~~~~~~");
		try {
			resp.sendRedirect("http://new.yizhibo.com/"); 
		} catch (IOException e) {
			logger.info("跳转到youku门户失败");
			e.printStackTrace();
		} 
	}
	
}
