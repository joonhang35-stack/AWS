package com.bcs.zsg.acct.web.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.acct.bo.FinancialPeriodBO;
import com.bcs.zsg.acct.vo.FinancialPeriodLockVO;
import com.bcs.zsg.acct.vo.FinancialPeriodVO;
import com.bcs.zsg.common.web.bean.AppBackingBean;

public class FinancialPeriodBean extends AppBackingBean {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient FinancialPeriodBO finPeriodBO;
	
	private FinancialPeriodVO finPeriodVO;
	private FinancialPeriodLockVO finPeriodLockVO;
	
	private List<FinancialPeriodVO> finPeriodList;
	private List<String> yearList;
	
	private static final SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy");
	private static final int YEAR_PERIOD = 11;

	/*
	 * (non-Javadoc)
	 * @see com.bcs.zsg.core.web.swf.bean.BaseBackingBean#resetForm()
	 */
	@Override
	public void resetForm() {
		finPeriodVO = new FinancialPeriodVO();
		searchParamVO.setObj2("1");
		searchParamVO.setObj3("0");
	}

	/**
	 * Initialization
	 */
	public void init() {
		try {
			// Initialize year list
			String year = YEAR_FORMAT.format(new Date());
			yearList = getCurrentYearList(year);
			
			// Initialize search param
			initSearchParam();
			searchParamVO.setObj1(year);
			
			// Initialize financial period list
			finPeriodList = finPeriodBO.getFinPeriodList(getSessionInfoBean().getCompanyVO().getId(), year);
			// initialize financial lock period
			finPeriodLockVO = finPeriodBO.getFinPeriodLock(getSessionInfoBean().getCompanyVO().getId());
			if (finPeriodLockVO == null) finPeriodLockVO = new FinancialPeriodLockVO();
			
			// reset form
			resetForm();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Add financial period
	 */
	public void addFinPeriod() {
		try {
			// add financial period
			finPeriodBO.addFinPeriod(getSessionInfoBean().getCompanyVO().getId(), searchParamVO);
			// refresh list
			finPeriodList = finPeriodBO.getFinPeriodList(getSessionInfoBean().getCompanyVO().getId(), (String) searchParamVO.getObj1());
			// reset form
			resetForm();
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Update financial period - to close period
	 */
	public void updFinPeriod() {
		try {
			// update financial period
			finPeriodBO.updFinPeriod(getSessionInfoBean().getCompanyVO().getId(), getSessionInfoBean().getUserVO().getName(), finPeriodVO);
			// refresh list
			finPeriodList = finPeriodBO.getFinPeriodList(getSessionInfoBean().getCompanyVO().getId(), (String) searchParamVO.getObj1());
			// reset form
			resetForm();
			successResult();
			
		} catch (Throwable t) {
			t.printStackTrace();
			errorResult(t);
		}
	}
	
	/**
	 * Update lock period
	 */
	public void updLockPeriod() {
		try {
			if (finPeriodLockVO.getIdCompany() == null) {
				finPeriodLockVO.setIdCompany(getSessionInfoBean().getCompanyVO().getId());
				finPeriodBO.insertVO(finPeriodLockVO);
				
			} else finPeriodBO.updateFinPeriodLock(finPeriodLockVO);
			successResult();
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Handle year selection
	 */
	public void handleYearSelect() {
		try {
			finPeriodList = finPeriodBO.getFinPeriodList(getSessionInfoBean().getCompanyVO().getId(), (String) searchParamVO.getObj1());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**
	 * Handle close period
	 */
	public void handleClosePeriod() {
		try {
			finPeriodVO = finPeriodBO.getFinPeriod(getSessionInfoBean().getCompanyVO().getId());
			
		} catch (Throwable t) {
			errorResult(t);
		}
	}
	
	/**********
	 * HELPER *
	 **********/
	
	/**
	 * 
	 * @param year 
	 * @param date
	 * @return
	 */
	private List<String> getCurrentYearList(String year) {
		yearList = new ArrayList<String>();
		
		int y = Integer.parseInt(year);
		for (int i = YEAR_PERIOD ; i > 0 ; i--) {
			yearList.add(String.valueOf(y - i));
		}
		for (int i = 0 ; i <= YEAR_PERIOD ; i++) {
			yearList.add(String.valueOf(y + i));
		}
		return yearList;
	}

	/*******************
	 * GETTER & SETTER *
	 *******************/

	/**
	 * @return the finPeriodVO
	 */
	public FinancialPeriodVO getFinPeriodVO() {
		return finPeriodVO;
	}

	/**
	 * @param finPeriodVO the finPeriodVO to set
	 */
	public void setFinPeriodVO(FinancialPeriodVO finPeriodVO) {
		this.finPeriodVO = finPeriodVO;
	}

	/**
	 * @return the finPeriodLockVO
	 */
	public FinancialPeriodLockVO getFinPeriodLockVO() {
		return finPeriodLockVO;
	}

	/**
	 * @param finPeriodLockVO the finPeriodLockVO to set
	 */
	public void setFinPeriodLockVO(FinancialPeriodLockVO finPeriodLockVO) {
		this.finPeriodLockVO = finPeriodLockVO;
	}

	/**
	 * @return the finPeriodList
	 */
	public List<FinancialPeriodVO> getFinPeriodList() {
		return finPeriodList;
	}

	/**
	 * @param finPeriodList the finPeriodList to set
	 */
	public void setFinPeriodList(List<FinancialPeriodVO> finPeriodList) {
		this.finPeriodList = finPeriodList;
	}

	/**
	 * @return the yearList
	 */
	public List<String> getYearList() {
		return yearList;
	}

	/**
	 * @param yearList the yearList to set
	 */
	public void setYearList(List<String> yearList) {
		this.yearList = yearList;
	}
}
