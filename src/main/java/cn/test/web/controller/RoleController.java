package cn.test.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.test.common.po.DefaultRequest;
import cn.test.common.po.PageHandler;
import cn.test.common.po.Role;
import cn.test.service.RoleService;

@RequestMapping("/role")
@Controller
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public Object list(PageHandler<Role> page) {
		DefaultRequest<Role> d = roleService.getRoleList(page);
		return d;
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Object add(Role role) {
		DefaultRequest<Role> d = new DefaultRequest<>();
		int i = roleService.save(role);
		if(i > 0 ){
			d.setType("0");
			d.setMessage("添加成功");
		}else{
			d.setType("1");
			d.setMessage("添加失败");
		}
		return d;
	}
	


}
