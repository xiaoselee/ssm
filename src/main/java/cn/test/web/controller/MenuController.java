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
import cn.test.service.MenuService;
import cn.test.service.RoleService;

@RequestMapping("/menu")
@Controller
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public Object list(PageHandler<Menu> page) {
		DefaultRequest<Menu> d = menuService.getMenuList(page);
		return d;
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Object add(Menu menu) {
		DefaultRequest<Menu> d = menuService.addMenu(menu);
		return d;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Object update(Menu menu) {
		DefaultRequest<Menu> d = menuService.updateMenu(menu);
		return d;
	}
	
	


}
