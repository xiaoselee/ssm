package cn.test.core.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cn.test.core.task.dao.TaskDao;
import cn.test.core.task.entity.ScheduleJob;

@Service
public class InitTaskService implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private TaskDao taskDao;
	

	public static List<ScheduleJob> taskList;
	
	@Autowired
	TaskService taskService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (taskList == null) {
			System.out.println("初始任务列表");
			// 1.获取已保存的任务列表
			taskList = taskDao.selectScheduleJob();
			System.out.println(new Gson().toJson(taskList));
			// 2.遍历，启动标记启动的任务
			for (ScheduleJob task : taskList) {
				if (task.getJobStatus().equals("1")) {
					taskService.startTask(task);
				}
			}
		}
	}

}
