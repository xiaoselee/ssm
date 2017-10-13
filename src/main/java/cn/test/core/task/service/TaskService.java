package cn.test.core.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.test.common.po.DefaultRequest;
import cn.test.common.po.PageHandler;
import cn.test.core.task.dao.TaskDao;
import cn.test.core.task.entity.ScheduleJob;

@Service
public class TaskService {
	
	@Autowired
	private TaskDao taskDao;
	
	

	public DefaultRequest<ScheduleJob> getMenuList(PageHandler<ScheduleJob> page) {
		DefaultRequest<ScheduleJob> d = new DefaultRequest<>();
		List<ScheduleJob> l = taskDao.selectScheduleJob();
		if(l != null && l.size() > 0){
			d.setRows(l);
			d.setTotal(l.size());
		}
		return d;
	}

}
