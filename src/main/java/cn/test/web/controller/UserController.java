package cn.test.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import cn.test.common.po.DefaultRequest;
import cn.test.common.po.PageHandler;
import cn.test.common.po.Role;
import cn.test.po.Menu;
import cn.test.po.User;
import cn.test.service.MenuService;
import cn.test.service.RoleService;
import cn.test.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public Object list(PageHandler<User> page,User user) {
		DefaultRequest<User> d = userService.list(page);
		return d;
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Object add(User user) {
		DefaultRequest<User> d = userService.add(user);
		return d;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Object update(User user) {
		DefaultRequest<User> d = userService.update(user);
		return d;
	}
	
	


}
