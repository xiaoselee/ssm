package cn.test.common.quartz;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.test.aspectj.LoggingAspect;

public class SystemQuartz extends QuartzJobBean{
	
    /*业务实现*/
    public void work() {
//        System.out.println("执行调度任务："+new Date());
//        System.out.println("=========count："+LoggingAspect.count);
    }

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
	
		work();
	}



}
