package com.bcs.zsg.common.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;

import com.bcs.zsg.scheduler.SchedulerApp;
import com.bcs.zsg.scheduler.SchedulerApp.SchedulerVO;
import com.bcs.zsg.scheduler.helper.EnumJobKey;
import com.bcs.zsg.scheduler.helper.TriggerType;

public class SchedulerUtils {
	
	public static void rescheduleJobByCron(String timePattern, EnumJobKey jobKey, Class<? extends Job> clazz, String jobName, boolean isRemoveJob) throws Exception {
		if (isRemoveJob) {
			SchedulerApp.getInstance().removeJob(jobKey, TriggerType.Schedule.toString(), jobName);
		}
		SchedulerApp.getInstance().scheduledJob(jobKey, 
				TriggerType.Schedule, clazz, jobName, timePattern);
	}

	public static void rescheduleJob(String time, EnumJobKey jobKey, Class<? extends Job> clazz, String jobName, boolean isRemoveJob) throws Exception {
		String timePattern = "";
		if (StringUtils.isNotBlank(time)) { // 0 59 12 1/1 * ? * Daily at 12:59  ||  0/30 * * * * ? every 30 sec
			String[] timeSplitList = time.split(":");
			timePattern = "0 " + (StringUtils.equals(timeSplitList[1], "00") ? "0" : timeSplitList[1]) + " " + timeSplitList[0] + " 1/1 * ? *";
			rescheduleJobByCron(timePattern, jobKey, clazz, jobName, isRemoveJob);
		}
	}
	
	public static void scheduleJobByCron(String time, EnumJobKey jobKey, Class<? extends Job> clazz, String jobName) throws Exception {
		rescheduleJobByCron(time, jobKey, clazz, jobName, true);
	}
	
	public static void scheduleJob(String time, EnumJobKey jobKey, Class<? extends Job> clazz, String jobName) throws Exception {
		rescheduleJob(time, jobKey, clazz, jobName, true);
	}
	
	public static void removeJob(EnumJobKey jobKey, String jobName) throws SchedulerException {
		SchedulerApp.getInstance().removeJob(jobKey, TriggerType.Schedule.toString(), jobName);
	}
	
	public static List<SchedulerVO> getSchedulerList(Scheduler scheduler) throws SchedulerException {
		List<SchedulerVO> list = new ArrayList<SchedulerApp.SchedulerVO>();
		
		for (String groupName : scheduler.getJobGroupNames()) {
		    for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
		    	
		    	// 1. Get Job Details
		        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
		        
		        // 2. Get Triggers
		        List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
		        
		        SchedulerVO vo = new SchedulerVO();
		    	vo.setJobKey(jobKey.getName());
		    	vo.setGroup(jobKey.getGroup());
		        
		    	// --- Added Information ---
		        
		        // Job Class and Description
		        vo.setJobClass(jobDetail.getJobClass().getName());
		        
		        // Trigger Information (We often focus on the first trigger if a job has multiple)
		        if (triggers != null && !triggers.isEmpty()) {
		            Trigger trigger = triggers.get(0);
		            
		            // Next Fire Time
		            vo.setNextFireTime(trigger.getNextFireTime());
		            
		            // Schedule (CRON Expression or Simple Interval)
		            if (trigger instanceof CronTrigger) {
		                CronTrigger cronTrigger = (CronTrigger) trigger;
		                vo.setSchedule(cronTrigger.getCronExpression());
		            } else {
		                // Handle SimpleTrigger or other trigger types here
		                vo.setSchedule("Simple or other schedule type."); 
		            }
		        }
		        
		    	list.add(vo);
		    }
		}
		
		return list;
	}
	
	public static String secondsToCron(int seconds) {
	    if (seconds <= 0) {
	        throw new IllegalArgumentException("Seconds must be greater than 0");
	    }
	    
	    if (seconds < 60) {
		    if (seconds == 1) {
		        return "0/1 * * * * ?";
		    }

		    // must divide 60
		    if (60 % seconds != 0) {
		        throw new IllegalArgumentException(
		            "Cron does not support every " + seconds + " seconds"
		        );
		    }

		    return "0/" + seconds + " * * * * ?";
	    }

	    int minutes = seconds / 60;

	    if (minutes < 60) {
	        return "0 */" + minutes + " * * * ?";
	    }

	    int hours = minutes / 60;
	    return "0 0 */" + hours + " * * ?";
	}
	
	public static String secondsToCron(String seconds) {
		return secondsToCron(Integer.valueOf(seconds));
	}
}
