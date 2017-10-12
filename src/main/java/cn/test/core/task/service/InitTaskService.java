package cn.test.core.task.service;

import java.util.List;

import javax.annotation.Resource;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.JobDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cn.test.core.task.dao.TaskDao;
import cn.test.core.task.entity.ScheduleJob;

@Service
public class InitTaskService implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private TaskDao taskDao;

	@Resource
	private Scheduler schedulerFactory;

	public static List<ScheduleJob> taskList;

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
					startTask(task);
				}
			}
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
