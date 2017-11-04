package cn.test.web.controller;


import java.io.IOException;
import java.util.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import cn.test.common.util.SystemConfig;
import cn.test.core.menu.service.MenuService;
import cn.test.po.HttpResult;
import cn.test.po.Menu;
import cn.test.service.ApiService;
import cn.test.service.ITestService;
import cn.test.util.RedisUtil;

@Controller
public class IndexController {

	@Autowired
	private ITestService testService;

   /* @Autowired
    protected RedisTemplate<String, Object> rt;
	*/
	/*@Autowired
	private CacheManager cm;*/
	
	@Autowired
	private ApiService apiService;
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("system/main")
	public ModelAndView name(HttpServletRequest request, ServletRequest sq) {
		System.out.println("system/main");
		ModelAndView m = new ModelAndView();
		List<Object> l = testService.testService();
		Gson g = new Gson();
		String json = g.toJson(l);
		String port = request.getLocalPort() + "";
		Session session2 = SecurityUtils.getSubject().getSession();
		Object o = session2.getAttribute(SystemConfig.Systemname);
		m.addObject("user",o);
		m.addObject("json", json);
		m.addObject("port", port);
		m.setViewName("view/index.jsp");
		return m;
	}

	@RequestMapping("ws/i")
	public ModelAndView name2(HttpServletRequest request) {
		System.out.println("/ws/i");
		ModelAndView m = new ModelAndView();
		String port = request.getLocalPort() + "";
		m.addObject("port", port);
		m.setViewName("templates/index.jsp");
		return m;
	}

	@RequestMapping("rd/i")
	public ModelAndView name3(HttpServletRequest request) {
		System.out.println("/ws/i");
		ModelAndView m = new ModelAndView();
		m.addObject("port", "re test");
		m.setViewName("index.jsp");
		Gson g = new Gson();
		Map<String,String> user = new HashMap<String,String>();
		user.put("id", "123");
		user.put("name", "candy");
		RedisUtil.getJedis().hmset("user", user);
		List<String> u = RedisUtil.getJedis().hmget("user", "*");
		String json = g.toJson(u);
		m.addObject("json", json);
		return m;
	}
	
	@RequestMapping("test/api")
	@ResponseBody
	public Object name5(String name,String id) {
		Map<String,String> user = new HashMap<String,String>();
		user.put("id", id==null?"null":id);
		user.put("name", name==null?"null":name);
		return user;
	}
	
	
	
	@RequestMapping("httpclient/i")
	public ModelAndView name4(HttpServletRequest request) throws IOException {
		System.out.println("httpclient/i");
		ModelAndView m = new ModelAndView();
		Gson gson = new Gson();
		Map<String, String> param = new HashMap<>();
		param.put("name", "candy");
		param.put("id", "123");
		//String r = 
			HttpResult hr =	apiService.doPost("http://127.0.0.1:8081/test/test/api",param);
			String hrStr = gson.toJson(hr,HttpResult.class);
		//String rr = r==null?"null":r;

/*		Map<String, String> retMap = gson.fromJson(r,
				new TypeToken<Map<String, String>>() {
				}.getType());*/
		
		m.addObject("data",hrStr);
		m.setViewName("templates/testapi.jsp");
		return m;
	}

	@RequestMapping("menu/getmenu")
	@ResponseBody
	public Object getmenu(HttpServletRequest request) {
		List<Menu> l = menuService.getMenuList(); 
		return l;
	}
	
	@RequestMapping("security/ky")
	public Object getmenu2(HttpServletRequest request) {
	return new ModelAndView("/common/ky.jsp");
	}

}
