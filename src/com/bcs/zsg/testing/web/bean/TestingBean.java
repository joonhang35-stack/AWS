package com.bcs.zsg.testing.web.bean;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.maintenance.bo.SystemNumberGenerationBO;
import com.bcs.zsg.maintenance.vo.SystemNumberGenerationVO;

public class TestingBean extends AppBackingBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient SystemNumberGenerationBO sysNumGenBO;

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.core.web.swf.bean.BaseBackingBean#resetForm()
	 */
	@Override
	public void resetForm() {
		try {
			long startTime = System.currentTimeMillis();
			SystemNumberGenerationVO vo = sysNumGenBO.getTestSystemNumberGeneration("bank_pmnt", 1L);
			long endTime = System.currentTimeMillis();
			System.out.println("*************/// " + startTime + "-" + endTime + "=" + (endTime - startTime) + "|" + vo.getCode() + "|" + vo.getNextnumber());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}

}
