

package com.bcs.zsg.sales.web.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bcs.zsg.cfg.sec.bo.UserBO;
import com.bcs.zsg.common.helper.TrackingLogUtils;
import com.bcs.zsg.common.web.bean.AppBackingBean;
import com.bcs.zsg.core.exception.BusinessException;
import com.bcs.zsg.sales.bo.SalesSmmryBO;
import com.bcs.zsg.sales.vo.SalesSmmryVO;

public class SalesSmmryByMonthsBean extends AppBackingBean {
	private static final long serialVersionUID = 1L;

	@Autowired
	private transient SalesSmmryBO salesSmmryBO;
	@Autowired
	private transient UserBO userBO;
	
	private List<SalesSmmryVO> salesSmmryList;
	
	private TrackingLogUtils trackingLogUtils;
	
	@Override
	public void resetForm() {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		// set from date
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		searchParamVO.setFromDate(cal.getTime());
		// set to date
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		searchParamVO.setToDate(cal.getTime());
	}

	public void init() throws BusinessException {
		trackingLogUtils = new TrackingLogUtils(this.getClass());
		
		initSearchParam();
		resetForm();
		
		loadSalesSmmry();
	}
	
	private void loadSalesSmmry() throws BusinessException {
		try {
			trackingLogUtils.startLogs();
			
			salesSmmryList = salesSmmryBO.getSalesSmmryByMonthsList(getSessionInfoBean().getCompanyVO().getId());
			
		}catch (Throwable t) {
			errorResult(t);
		} finally {
			trackingLogUtils.endLogs("loadSalesSmmry");
		}
	}

	public List<SalesSmmryVO> getSalesSmmryList() {
		return salesSmmryList;
	}

	public void setSalesSmmryList(List<SalesSmmryVO> salesSmmryList) {
		this.salesSmmryList = salesSmmryList;
	}
}

