package cn.test.core.task.service;

import java.util.List;

import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
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
	
	@Resource
	private Scheduler schedulerFactory;

	public DefaultRequest<ScheduleJob> getMenuList(PageHandler<ScheduleJob> page) {
		DefaultRequest<ScheduleJob> d = new DefaultRequest<>();
		List<ScheduleJob> l = taskDao.selectScheduleJob();
		if(l != null && l.size() > 0){
			d.setRows(l);
			d.setTotal(l.size());
		}
		return d;
	}
	
	
	void endTask(ScheduleJob task){
		try {
			TriggerKey triggerKey = new TriggerKey(task.getJobName());
			schedulerFactory.unscheduleJob(triggerKey );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void startTask(ScheduleJob task) {
		try {
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(task.getCronExpression());
			JobDetail jobDetail = JobBuilder.newJob(getClassByScn(task.getClassName()))
					.withIdentity(task.getJobName(), Scheduler.DEFAULT_GROUP).build();
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(task.getJobName(), task.getJobGroup())
					.withSchedule(scheduleBuilder).build();
			schedulerFactory.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private Class<? extends Job> getClassByScn(String className) {
		try {
			return (Class<? extends Job>) Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
