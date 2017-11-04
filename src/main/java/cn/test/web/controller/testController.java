package cn.test.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.ModelAndView;

import cn.test.common.service.RedisService;
import cn.test.util.FileUtils;

@Controller
public class testController {
	
	@Autowired
	private RedisTemplate<String,Object> redis;
	
	@RequestMapping("security/testredis")
	@ResponseBody
	public Object testRedis(HttpSession session) {
		HashMap<String, Object> m = new HashMap<>();
		m.put("id", 123);	
		List<String> l = new ArrayList<>();
		l.add("5555");
		
		redis.opsForValue().set("list", l);
		//redis.setList("list", l);
		redis.opsForValue().set("map", m);
		
		//@SuppressWarnings("unchecked")
		//HashMap<String, Object> m2 = redis.get("map", HashMap.class);
		// l2 = redis.getList("list", ArrayList.class);
		Object o = redis.opsForValue().get("list");
		
		session.setAttribute("testsession", 123566);
		
		return o;
	}
	
	@RequestMapping("security/testredis2")
	@ResponseBody
	public Object testRedis2(HttpSession session) {
		Object o2 = session.getAttribute("testsession");
		Object o = redis.opsForValue().get("map");
		return o2;
	}
	
	@RequestMapping("security/fileup")
	@ResponseBody
	public String fileUp(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException{
		if(file != null){
			String path = "D:/java/t2";
			//FileUtils.saveByPath(path,file);
			String filename = file.getOriginalFilename();
			file.transferTo(new File(path,filename));
		}
		return "true";
	}

	@RequestMapping("/system/mainpage")
	public ModelAndView toEasyuiPage(){
		ModelAndView model = new ModelAndView();
		model.setViewName("view/easyui/main.html");
		return model;
	}
	
}
