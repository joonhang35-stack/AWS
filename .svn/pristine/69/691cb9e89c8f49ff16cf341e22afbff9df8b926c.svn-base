package com.bcs.zsg.scheduler;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bcs.zsg.common.helper.SchedulerUtils;
import com.bcs.zsg.scheduler.helper.EnumJobKey;
import com.bcs.zsg.scheduler.helper.TriggerType;

public class SchedulerApp implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(SchedulerApp.class);
	
	private static SchedulerApp objSchedulerApp;
	
	private Scheduler scheduler;
	
	 @PostConstruct
	public static SchedulerApp getInstance() {
		if(objSchedulerApp == null)
			objSchedulerApp = new SchedulerApp();
		return objSchedulerApp;
	}
	
	private SchedulerApp() {
		init();
	}
	
	public void init() {
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
		} catch (Exception e) {
			logger.error("Error When Startup Scheduler");
		} 
	}
	
	/***************
	 * Schedule Job *
	 ***************/
	
	public void scheduledJob(EnumJobKey jobKeyType, TriggerType triggerType, Class<? extends Job> objClass, String jobName, String propScheduledTime) throws SchedulerException {
		scheduledJob(jobKeyType, triggerType.toString(), triggerType, objClass, jobName, propScheduledTime);
	}
	
	private void scheduledJob(EnumJobKey jobKeyType, String strTriggerType, TriggerType triggerType, Class<? extends Job> objClass, 
			String jobName, String propScheduledTime) throws SchedulerException {
	    //Create JobDetail object specifying which Job you want to execute
		String strJobKey = strTriggerType + jobKeyType + jobName;
	    JobDetail job= constructJob(jobKeyType, jobName, strJobKey, objClass);
	    
	    // Trigger the job to run now with cron job setup
	    Trigger trigger = constructTrigger(jobKeyType.toString(), strTriggerType + jobKeyType, triggerType, propScheduledTime, job.getKey());
	    
	    //Pass JobDetail and trigger dependencies to scheduler
	    scheduler.scheduleJob(job, trigger);
	}
	
	/***************
	 * Construction 
	 * @throws SchedulerException *
	 ***************/
	
	public void removeJob(EnumJobKey jobKeyType, String strTriggerType, String jobName) throws SchedulerException {
		String strJobKey = strTriggerType + jobKeyType + jobName;
		JobKey jobKey = new JobKey(jobKeyType.toString() + jobName, strJobKey);
		
		removeJob(jobKey);
	}
	
	private void removeJob(JobKey jobKey) throws SchedulerException {
		scheduler.deleteJob(jobKey);
	}
	
	private JobDetail constructJob(EnumJobKey enumJobKey, String uniqueJobName, String jobKeyGroup, Class<? extends Job> JobClass) {
		JobKey jobKey = new JobKey(enumJobKey.toString() + uniqueJobName, jobKeyGroup);
		
    	return JobBuilder.newJob(JobClass).withIdentity(jobKey).build();
	}
	
	private Trigger constructTrigger(String triggerName, String triggerGroup, TriggerType _triggerType, String timePattern, JobKey _jobKey) {
		if(_triggerType.equals(TriggerType.Schedule))
			return TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroup).withSchedule(
					CronScheduleBuilder.cronSchedule(timePattern))
				    .forJob(_jobKey)
				    .build();
		else {
			return TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroup).startNow().build();
		}
	}
	
	public void getAllScheduler() throws SchedulerException {
		for (String groupName : scheduler.getJobGroupNames()) {
		    for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
		        System.out.println("Job: " + jobKey.getName() + " Group: " + jobKey.getGroup());
		    }
		}
	}
	
	public List<SchedulerVO> getSchedulerList() throws SchedulerException {
		return SchedulerUtils.getSchedulerList(scheduler);
	}
	
	@Override
	public void run() {}
	
	public static class SchedulerVO implements Serializable {
		
		private static final long serialVersionUID = 1L;
		
		String jobKey;
		String group;
		String jobClass;
		Date nextFireTime;
		String schedule;
		public String getJobClass() {
			return jobClass;
		}
		public void setJobClass(String jobClass) {
			this.jobClass = jobClass;
		}
		public Date getNextFireTime() {
			return nextFireTime;
		}
		public void setNextFireTime(Date nextFireTime) {
			this.nextFireTime = nextFireTime;
		}
		public String getSchedule() {
			return schedule;
		}
		public void setSchedule(String schedule) {
			this.schedule = schedule;
		}
		public String getJobKey() {
			return jobKey;
		}
		public void setJobKey(String jobKey) {
			this.jobKey = jobKey;
		}
		public String getGroup() {
			return group;
		}
		public void setGroup(String group) {
			this.group = group;
		}
	}
}
