package com.bcs.zsg.scheduler;

import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.feedback.bo.FeedbackBO;
import com.bcs.zsg.mail.bo.EmailingBO;
import com.bcs.zsg.product.vo.FeedbackVO;

public class FeedbackEmailJob implements Job {

    @Autowired
    private transient FeedbackBO feedbackBO;

    @Autowired
    private transient EmailingBO emailingBO;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("FeedbackEmailJob triggered at: " + new Date());

        // Inject Spring beans into this Quartz-instantiated job instance
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        TrackingLogUtils trackingLogUtils = new TrackingLogUtils(getClass());
        trackingLogUtils.startLogs();

        try {
            List<FeedbackVO> feedbackList = feedbackBO.getInvoiceForFeedback();
           
            if (!CollectionUtils.isEmpty(feedbackList)) {
                for (FeedbackVO vo : feedbackList) {
                    System.out.println("FeedbackEmailJob selected record - Tour Booking ID: " + vo.getTourBookingId() 
                                       + ", Invoice Code: " + vo.getInvCode() + ", PS No: " + vo.getPsNo());
                }
                // Call a Spring-managed Async method instead of spinning up a raw thread
                emailingBO.sendCustomerFeedbackEmail(feedbackList);
            } else {
                System.out.println("No feedback invoices found to process.");
            }

        } catch (Throwable t) {
            // Log properly, ensuring Quartz doesn't choke on future runs
            System.err.println("FeedbackEmailJob failed during execution");
            t.printStackTrace();
        } finally {
            trackingLogUtils.endLogs("FeedbackEmailJob");
        }
    }
}