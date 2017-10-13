package cn.test.core.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.test.common.controller.BaseController;
import cn.test.common.po.DefaultRequest;
import cn.test.common.po.PageHandler;
import cn.test.core.task.entity.ScheduleJob;
import cn.test.core.task.service.TaskService;
import cn.test.po.Menu;


@Controller
@RequestMapping("/task")
public class TaskController extends BaseController{
	
	@Autowired
	TaskService taskService;
	
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public Object list(PageHandler<ScheduleJob> page) {
		DefaultRequest<ScheduleJob> d = taskService.getMenuList(page);
		return d;
	}
}
